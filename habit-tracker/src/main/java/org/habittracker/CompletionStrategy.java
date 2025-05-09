package org.habittracker;

import java.time.LocalDate;
import java.util.List;

public interface CompletionStrategy {
    boolean isCompleted(Habit habit, List<HabitRecord> records, LocalDate date);
}
