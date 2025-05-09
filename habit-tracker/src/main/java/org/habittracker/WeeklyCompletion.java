package org.habittracker;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

public class WeeklyCompletion implements CompletionStrategy {

    @Override
    public boolean isCompleted(Habit habit, List<HabitRecord> records, LocalDate date) {
        WeekFields wf = WeekFields.of(Locale.getDefault());
        int targetWeek = date.get(wf.weekOfWeekBasedYear());
        int targetYear = date.getYear();

        return records.stream()
                .anyMatch(r -> {
                    int recordWeek = r.getDate().get(wf.weekOfWeekBasedYear());
                    int recordYear = r.getDate().getYear();
                    return r.getHabitId() == habit.getId()
                            && recordWeek == targetWeek
                            && recordYear == targetYear
                            && r.isCompleted();
                });
    }
}
