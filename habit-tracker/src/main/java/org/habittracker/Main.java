package org.habittracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.habittracker.model.Habit;
import org.habittracker.repository.HabitRepository;

import java.time.LocalDate;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        DatabaseInitializer.initialize();

        HabitRepository repo = new HabitRepository();

        Habit habit = new Habit("Tanulás", "daily", true, false, LocalDate.now());
        repo.insert(habit);

        for (Habit h : repo.findAll()) {
            logger.info("Szokás: {} (típus: {})", h.getName(), h.getType());
        }

        // Később: Application.launch(App.class, args)
    }
}