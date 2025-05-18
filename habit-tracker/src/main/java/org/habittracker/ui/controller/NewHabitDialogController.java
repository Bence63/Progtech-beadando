package org.habittracker.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.habittracker.service.HabitService;

public class NewHabitDialogController {

    @FXML
    private TextField nameField;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private CheckBox importantCheck;

    @FXML
    private CheckBox rewardedCheck;

    private final HabitService habitService = new HabitService(new org.habittracker.repository.HabitRepository());

    @FXML
    public void initialize() {
        typeChoiceBox.getItems().addAll("daily", "weekly", "monthly");
        typeChoiceBox.setValue("daily");
    }

    @FXML
    public void handleSave() {
        String name = nameField.getText();
        String type = typeChoiceBox.getValue();
        boolean important = importantCheck.isSelected();
        boolean rewarded = rewardedCheck.isSelected();

        if (name == null || name.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "A név mező nem lehet üres!");
            alert.showAndWait();
            return;
        }

        habitService.addHabit(name, type, important, rewarded);

        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
}