package org.habittracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.habittracker.model.Habit;
import org.habittracker.repository.HabitRepository;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        org.habittracker.ui.AppLauncher.main(args);
    }
}