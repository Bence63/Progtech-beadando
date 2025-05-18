package org.habittracker.strategy;

import org.habittracker.model.Habit;
import org.habittracker.model.HabitRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompletionStrategyTest {

    private final Habit habit = new Habit(1, "Test", "daily", false, false, LocalDate.now());

    @Test
    void testDailyCompleted() {
        CompletionStrategy strategy = new DailyCompletion();
        LocalDate today = LocalDate.now();

        HabitRecord record = new HabitRecord(1, today, true);
        boolean result = strategy.isCompleted(habit, List.of(record), today);

        assertTrue(result);
    }

    @Test
    void testWeeklyNotCompleted() {
        CompletionStrategy strategy = new WeeklyCompletion();
        LocalDate today = LocalDate.now();
        LocalDate other = today.minusWeeks(1);

        HabitRecord record = new HabitRecord(1, other, true);
        boolean result = strategy.isCompleted(habit, List.of(record), today);

        assertFalse(result);
    }
}