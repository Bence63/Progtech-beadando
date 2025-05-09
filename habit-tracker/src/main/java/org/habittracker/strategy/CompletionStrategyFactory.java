package org.habittracker.strategy;

public class CompletionStrategyFactory {
    public static CompletionStrategy getStrategy(String type) {
        return switch (type.toLowerCase()) {
            case "daily" -> new DailyCompletion();
            case "weekly" -> new WeeklyCompletion();
            case "monthly" -> new MonthlyCompletion();
            default -> throw new IllegalArgumentException("Ismeretlen típus: " + type);
        };
    }
}
