package org.habittracker.strategy;

import org.habittracker.model.Habit;
import org.habittracker.model.HabitRecord;

import java.time.LocalDate;
import java.util.List;

public interface CompletionStrategy {
    boolean isCompleted(Habit habit, List<HabitRecord> records, LocalDate date);
}
