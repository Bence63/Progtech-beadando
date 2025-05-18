package org.habittracker.decorator;

import org.habittracker.model.Habit;
import java.time.LocalDate;

public abstract class HabitDecorator extends Habit {
    protected Habit decoratedHabit;

    public HabitDecorator(Habit habit) {
        super(habit.getId(), habit.getName(), habit.getType(), habit.isImportant(), habit.isRewarded(), habit.getCreatedAt());
        this.decoratedHabit = habit;
    }

    @Override
    public boolean isImportant() {
        return decoratedHabit.isImportant();
    }

    @Override
    public boolean isRewarded() {
        return decoratedHabit.isRewarded();
    }
}