package org.habittracker.model;
import java.time.LocalDate;

public class Habit {
    private int id;
    private String name;
    private String type; // daily vagy weekly
    private boolean isImportant;
    private boolean isRewarded;
    private LocalDate createdAt;

    public Habit(int id, String name, String type, boolean isImportant, boolean isRewarded, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isImportant = isImportant;
        this.isRewarded = isRewarded;
        this.createdAt = createdAt;
    }

    public Habit(String name, String type, boolean isImportant, boolean isRewarded, LocalDate createdAt) {
        this(-1, name, type, isImportant, isRewarded, createdAt);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public boolean isImportant() { return isImportant; }
    public boolean isRewarded() { return isRewarded; }
    public LocalDate getCreatedAt() { return createdAt; }

    public void setId(int id) { this.id = id; }

}
