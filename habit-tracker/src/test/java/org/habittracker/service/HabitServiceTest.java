package org.habittracker.service;

import org.habittracker.model.Habit;
import org.habittracker.repository.HabitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HabitServiceTest {

    private HabitService service;

    @BeforeEach
    public void setup() {
        service = new HabitService(new HabitRepository());
    }

    @Test
    void testAddHabit() {
        service.addHabit("Olvasás", "daily", true, true);
        List<Habit> habits = service.getAllHabits();
        assertTrue(habits.stream().anyMatch(h -> h.getName().equals("Olvasás")));
    }
}