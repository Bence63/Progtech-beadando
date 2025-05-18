package org.habittracker.decorator;

import org.habittracker.model.Habit;

public class ImportantHabitDecorator extends HabitDecorator {

    public ImportantHabitDecorator(Habit habit) {
        super(habit);
    }

    @Override
    public boolean isImportant() {
        return true;
    }
}