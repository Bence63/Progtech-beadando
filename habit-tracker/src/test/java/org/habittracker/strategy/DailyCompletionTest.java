package org.habittracker.strategy;

import org.habittracker.model.Habit;
import org.habittracker.model.HabitRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DailyCompletionTest {

    private final DailyCompletion strategy = new DailyCompletion();

    @Test
    void shouldReturnTrueIfRecordExistsForGivenDate() {
        Habit habit = new Habit("Test", "daily", false, false, LocalDate.now());
        habit.setId(1); // 💡 ID egyezés miatt szükséges

        HabitRecord record = new HabitRecord(1, LocalDate.now(), true);
        List<HabitRecord> records = List.of(record);

        boolean result = strategy.isCompleted(habit, records, LocalDate.now());

        assertTrue(result);
    }


    @Test
    void shouldReturnFalseIfNoRecordExistsForGivenDate() {
        Habit habit = new Habit("Test", "daily", false, false, LocalDate.now());
        List<HabitRecord> records = List.of();  // üres lista

        boolean result = strategy.isCompleted(habit, records, LocalDate.now());

        assertFalse(result);
    }
}
