package org.habittracker.decorator;

import org.habittracker.model.Habit;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class HabitDecoratorTest {

    @Test
    void testImportantDecorator() {
        Habit base = new Habit("Test", "daily", false, false, LocalDate.now());
        Habit decorated = new ImportantHabitDecorator(base);
        assertTrue(decorated.isImportant());
    }

    @Test
    void testRewardedDecorator() {
        Habit base = new Habit("Test", "daily", false, false, LocalDate.now());
        Habit decorated = new RewardedHabitDecorator(base);
        assertTrue(decorated.isRewarded());
    }
}