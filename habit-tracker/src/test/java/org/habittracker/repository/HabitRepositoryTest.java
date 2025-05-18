package org.habittracker.repository;

import org.habittracker.model.Habit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HabitRepositoryTest {

    private HabitRepository repository;

    @BeforeEach
    void setup() {
        repository = new HabitRepository();
    }

    @Test
    void testInsertAndFindAll() {
        Habit habit = new Habit("Test CRUD", "daily", false, true, LocalDate.now());
        repository.insert(habit);

        List<Habit> habits = repository.findAll();
        boolean exists = habits.stream()
                .anyMatch(h -> h.getName().equals("Test CRUD") && h.isRewarded());

        assertTrue(exists);
    }
}