package org.habittracker.decorator;

import org.habittracker.model.Habit;

public class RewardedHabitDecorator extends HabitDecorator {

    public RewardedHabitDecorator(Habit habit) {
        super(habit);
    }

    @Override
    public boolean isRewarded() {
        return true;
    }
}