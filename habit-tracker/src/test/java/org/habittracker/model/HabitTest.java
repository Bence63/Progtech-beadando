package org.habittracker.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class HabitTest {

    @Test
    void constructorShouldSetFieldsCorrectly() {
        Habit habit = new Habit("Futás", "daily", true, false, LocalDate.of(2025, 5, 18));

        assertEquals("Futás", habit.getName());
        assertEquals("daily", habit.getType());
        assertTrue(habit.isImportant());
        assertFalse(habit.isRewarded());
        assertEquals(LocalDate.of(2025, 5, 18), habit.getCreatedAt());
    }
}
