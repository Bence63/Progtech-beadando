package org.habittracker.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.habittracker.model.HabitRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HabitRecordRepository {

    private static final Logger logger = LogManager.getLogger(HabitRecordRepository.class);

    public int countCompleted() {
        String sql = "SELECT COUNT(*) FROM habit_records WHERE completed = 1";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:habit.db");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            logger.error("Hiba a teljesített szokások számolásakor", e);
        }

        return 0;
    }

    public void insert(HabitRecord record) {
        String sql = "INSERT INTO habit_records (habit_id, date, completed) VALUES ("
                + record.getHabitId()   + ", '"
                + record.getDate()      + "', "
                + (record.isCompleted() ? 1 : 0) + ")";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:habit.db");
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            logger.info("HabitRecord beszúrva: " + record);
        } catch (SQLException e) {
            logger.error("Hiba a HabitRecord beszúrásakor", e);
        }
    }
}