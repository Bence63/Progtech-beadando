package org.habittracker.ui.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import org.habittracker.model.Habit;
import org.habittracker.service.HabitService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        datePicker.setValue(LocalDate.now());
        habitListView.setItems(FXCollections.observableArrayList(habitService.getPendingHabits(datePicker.getValue())));

        datePicker.valueProperty().addListener((obs, oldDate, newDate) -> {
            if (newDate != null) {
                habitListView.getItems().setAll(habitService.getPendingHabits(newDate));
            }
        });

        habitListView.setCellFactory(lv -> new CheckBoxListCell<>(habit -> {
            BooleanProperty selected = new SimpleBooleanProperty();

            selected.addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    habitService.completeHabit(habit, datePicker.getValue());
                    habitListView.getItems().setAll(habitService.getPendingHabits(datePicker.getValue()));

                    // felugró értesítés
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Szokás teljesítve");
                    alert.setHeaderText(null);
                    alert.setContentText("A(z) \"" + habit.getName() + "\" szokás teljesítve lett!");
                    alert.showAndWait();
                }
            });


            return selected;
        }) {
            @Override
            public void updateItem(Habit habit, boolean empty) {
                super.updateItem(habit, empty);
                if (habit != null && !empty) {
                    setText(habit.toString()); // vagy habit.getName()
                }
            }
        });

    }

    @FXML
    public void handleMarkCompleted() {
        Habit selected = habitListView.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();    // ← IDE KIVISSZÜK

        if (selected != null && date != null) {
            habitService.completeHabit(selected, date);

            // most már látja a 'date'-et
            habitListView.setItems(
                    FXCollections.observableArrayList(
                            habitService.getPendingHabits(date)
                    )
            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Szokás teljesítve!");
            alert.showAndWait();
        }
    }


    @FXML
    public void handleShowStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/habittracker/ui/StatisticsView.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/habittracker/ui/NewHabitDialog.fxml"));
            Parent root = loader.load();
            Stage dialog = new Stage();
            dialog.setTitle("Új szokás hozzáadása");
            dialog.setScene(new Scene(root));
            dialog.showAndWait();
            habitListView.getItems().setAll(habitService.getPendingHabits(datePicker.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}