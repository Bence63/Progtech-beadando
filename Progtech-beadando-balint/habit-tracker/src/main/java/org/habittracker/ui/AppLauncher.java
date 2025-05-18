package org.habittracker.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLauncher extends Application {

    private static final Logger logger = LogManager.getLogger(AppLauncher.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/habittracker/ui/MainView.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Habit Tracker");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (Exception e) {
            logger.error("Hiba a főképernyő betöltése közben", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}