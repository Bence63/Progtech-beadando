package org.habittracker.service;

import org.habittracker.model.Habit;
import org.habittracker.repository.HabitRepository;

import java.time.LocalDate;
import java.util.List;

public class HabitService {

    private final HabitRepository repository;

    public HabitService(HabitRepository repository) {
        this.repository = repository;
    }

    public void addHabit(String name, String type, boolean important, boolean rewarded) {
        Habit habit = new Habit(name, type, important, rewarded, LocalDate.now());
        repository.insert(habit);
    }

    public List<Habit> getAllHabits() {
        return repository.findAll();
    }
}
