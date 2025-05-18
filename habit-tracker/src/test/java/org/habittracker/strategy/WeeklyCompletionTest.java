package org.habittracker.strategy;

import org.habittracker.model.Habit;
import org.habittracker.model.HabitRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeeklyCompletionTest {

    private final CompletionStrategy strategy = new WeeklyCompletion();

    @Test
    void shouldReturnTrueIfAlreadyCompletedThisWeek() {
        Habit habit = new Habit("Heti edzés", "weekly", false, false, LocalDate.now());
        habit.setId(1);

        // ugyanazon a héten történt korábbi teljesítés
        LocalDate today = LocalDate.of(2025, 5, 15); // csütörtök
        LocalDate earlier = LocalDate.of(2025, 5, 13); // kedd

        HabitRecord record = new HabitRecord(1, earlier, true);
        List<HabitRecord> records = List.of(record);

        boolean result = strategy.isCompleted(habit, records, today);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNoCompletionThisWeek() {
        Habit habit = new Habit("Heti edzés", "weekly", false, false, LocalDate.now());
        habit.setId(1);

        LocalDate today = LocalDate.of(2025, 5, 18); // vasárnap
        LocalDate lastWeek = LocalDate.of(2025, 5, 10); // előző szombat

        HabitRecord record = new HabitRecord(1, lastWeek, true);
        List<HabitRecord> records = List.of(record);

        boolean result = strategy.isCompleted(habit, records, today);

        assertFalse(result);
    }
}
