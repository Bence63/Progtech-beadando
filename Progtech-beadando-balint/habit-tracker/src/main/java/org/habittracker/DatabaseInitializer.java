package org.habittracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private static final Logger logger = LogManager.getLogger(DatabaseInitializer.class);

    public static void initialize() {

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:habit.db");
            Statement stmt = conn.createStatement()
        ) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS habits (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    type TEXT NOT NULL,
                    isImportant INTEGER,
                    isRewarded INTEGER,
                    created_at TEXT
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS habit_records (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    habit_id INTEGER NOT NULL,
                    date TEXT NOT NULL,
                    completed INTEGER,
                    FOREIGN KEY (habit_id) REFERENCES habits(id)
                )
            """);

            logger.info("Adatbázis inicializálása megtörtént.");
        } catch (SQLException e) {
            logger.error("Hiba történt az adatbázis inicializálás közben.", e);
        }
    }
}
