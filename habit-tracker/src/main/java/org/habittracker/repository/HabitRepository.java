package org.habittracker.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.habittracker.model.Habit;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HabitRepository {
    private static final Logger logger = LogManager.getLogger(HabitRepository.class);

    public void insert(Habit habit) {
        String sql = "INSERT INTO habits (name, type, isImportant, isRewarded, created_at) VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = DriverManager.getConnection("jdbc:sqlite:habit.db");
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            stmt.setString(1, habit.getName());
            stmt.setString(2, habit.getType());
            stmt.setInt(3, habit.isImportant() ? 1 : 0);
            stmt.setInt(4, habit.isRewarded() ? 1 : 0);
            stmt.setString(5, habit.getCreatedAt().toString());

            int affected = stmt.executeUpdate();
            if (affected == 0) {
                logger.warn("Nem szúrt be egyetlen sort sem a habits táblába.");
            } else {

                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        habit.setId(keys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Hiba szokás beszúrásakor", e);
        }
    }

    public List<Habit> findAll() {
        List<Habit> habits = new ArrayList<>();
        String sql = "SELECT * FROM habits";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:habit.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Habit h = new Habit(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getInt("isImportant") == 1,
                        rs.getInt("isRewarded") == 1,
                        LocalDate.parse(rs.getString("created_at"))
                );
                habits.add(h);
            }

        } catch (SQLException e) {
            logger.error("Hiba történt szokások lekérdezésekor", e);

        }

        return habits;
    }

    // Később: delete(id), update(habit), findById(id) stb.
}

