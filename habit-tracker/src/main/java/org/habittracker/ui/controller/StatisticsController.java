package org.habittracker.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.habittracker.repository.HabitRecordRepository;

public class StatisticsController {

    @FXML
    private Label completedCountLabel;

    private final HabitRecordRepository recordRepository = new HabitRecordRepository();

    @FXML
    public void initialize() {
        int count = recordRepository.countCompleted();
        completedCountLabel.setText(String.valueOf(count));
    }
}