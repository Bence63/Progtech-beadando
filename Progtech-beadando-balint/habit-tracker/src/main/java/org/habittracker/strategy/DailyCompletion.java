package org.habittracker.strategy;

import org.habittracker.model.Habit;
import org.habittracker.model.HabitRecord;

import java.time.LocalDate;
import java.util.List;

public class DailyCompletion implements CompletionStrategy {

    @Override
    public boolean isCompleted(Habit habit, List<HabitRecord> records, LocalDate date) {
        return records.stream()
                .anyMatch(r ->
                        r.getHabitId() == habit.getId() &&
                                r.getDate().equals(date) &&
                                r.isCompleted()
                );
    }
}
