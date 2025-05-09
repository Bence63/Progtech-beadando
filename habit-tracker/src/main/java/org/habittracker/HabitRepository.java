package org.habittracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HabitRepository {

    private static final Logger logger = LogManager.getLogger(HabitRepository.class);

    public void insert(Habit habit) {
        String sql = "INSERT INTO habits (name, type, isImportant, isRewarded, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:habit.db");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, habit.getName());
            stmt.setString(2, habit.getType());
            stmt.setInt(3, habit.isImportant() ? 1 : 0);
            stmt.setInt(4, habit.isRewarded() ? 1 : 0);
            stmt.setString(5, habit.getCreatedAt().toString());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
            logger.error("Hiba történt beszúrás közben", e);

        }

        return habits;
    }
}

