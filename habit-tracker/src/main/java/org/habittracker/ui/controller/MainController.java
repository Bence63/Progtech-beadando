package org.habittracker.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.habittracker.model.Habit;
import org.habittracker.service.HabitService;

import java.io.IOException;
import java.time.LocalDate;

public class MainController {

    @FXML
    private ListView<Habit> habitListView;

    @FXML
    private DatePicker datePicker;

    private final HabitService habitService = new HabitService(new org.habittracker.repository.HabitRepository());

    @FXML
    public void initialize() {
        habitListView.getItems().addAll(habitService.getAllHabits());
        datePicker.setValue(LocalDate.now());
    }

    @FXML
    public void handleMarkCompleted() {
        Habit selected = habitListView.getSelectionModel().getSelectedItem();
        if (selected != null && datePicker.getValue() != null) {
            habitService.completeHabit(selected.getId(), datePicker.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Szokás teljesítve!");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleShowStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/habit-tracker/src/main/resources/org/habittracker/ui/StatisticsView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Statisztikák");
            stage.setScene(new Scene(root, 300, 200));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddHabit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/habit-tracker/src/main/resources/org/habittracker/ui/NewHabitDialog.fxml"));
            Parent root = loader.load();
            Stage dialog = new Stage();
            dialog.setTitle("Új szokás hozzáadása");
            dialog.setScene(new Scene(root));
            dialog.showAndWait();
            habitListView.getItems().setAll(habitService.getAllHabits());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}