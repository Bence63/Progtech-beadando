package org.habittracker.model;

import java.time.LocalDate;

public class HabitRecord {
    private int id;
    private int habitId;
    private LocalDate date;
    private boolean completed;


    public HabitRecord(int id, int habitId, LocalDate date, boolean completed) {
        this.id = id;
        this.habitId = habitId;
        this.date = date;
        this.completed = completed;
    }


    public HabitRecord(int habitId, LocalDate date, boolean completed) {
        this(-1, habitId, date, completed);
    }

    public int getId() {
        return id;
    }
    public int getHabitId() {
        return habitId;
    }
    public LocalDate getDate() {
        return date;
    }
    public boolean isCompleted() {
        return completed;
    }


    public void setId(int id) {
        this.id = id;
    }
}
