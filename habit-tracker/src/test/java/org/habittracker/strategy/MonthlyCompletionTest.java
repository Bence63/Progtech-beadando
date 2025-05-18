package org.habittracker.strategy;

import org.habittracker.model.Habit;
import org.habittracker.model.HabitRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyCompletionTest {

    private final CompletionStrategy strategy = new MonthlyCompletion();

    @Test
    void shouldReturnTrueIfCompletedThisMonth() {
        Habit habit = new Habit("Meditáció", "monthly", false, false, LocalDate.now());
        habit.setId(1);

        LocalDate today = LocalDate.of(2025, 5, 15);   // május
        LocalDate earlier = LocalDate.of(2025, 5, 3);  // ugyanaz a hónap

        HabitRecord record = new HabitRecord(1, earlier, true);
        List<HabitRecord> records = List.of(record);

        boolean result = strategy.isCompleted(habit, records, today);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfCompletedLastMonth() {
        Habit habit = new Habit("Meditáció", "monthly", false, false, LocalDate.now());
        habit.setId(1);

        LocalDate today = LocalDate.of(2025, 5, 15);    // május
        LocalDate lastMonth = LocalDate.of(2025, 4, 28); // április

        HabitRecord record = new HabitRecord(1, lastMonth, true);
        List<HabitRecord> records = List.of(record);

        boolean result = strategy.isCompleted(habit, records, today);

        assertFalse(result);
    }
}
