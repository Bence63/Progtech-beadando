package org.habittracker;
import java.time.LocalDate;

public class Habit {
    private int id;
    private String name;
    private String type; // daily vagy weekly
    private boolean isImportant;
    private boolean isRewarded;
    private LocalDate createdAt;

}
