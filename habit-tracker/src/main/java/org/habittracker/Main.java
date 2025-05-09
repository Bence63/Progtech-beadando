package org.habittracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        //logger.info("Info üzenet: a program elindult.");
        //logger.warn("Figyelmeztetés: valami nem oké.");
        //logger.error("Hiba: valami tényleg elromlott.");

        DatabaseInitializer.initialize();

        HabitRepository repo = new HabitRepository();

        Habit habit = new Habit("Tanulás", "daily", true, false, LocalDate.now());
        repo.insert(habit);

        for (Habit h : repo.findAll()) {
            logger.info("Szokás: {} (típus: {})", h.getName(), h.getType());


            // Később: Application.launch(App.class, args);
    }
}
}