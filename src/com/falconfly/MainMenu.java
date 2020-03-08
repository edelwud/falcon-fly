package com.falconfly;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainMenu extends Application {

    Button buttonPlay;
    Button buttonSettings;
    Button buttonExit;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Falcon-Fly launcher");
        buttonPlay = new Button();
        buttonPlay.setText("Play");

        buttonSettings = new Button();
        buttonPlay.setText("Settings");

        buttonExit = new Button();
        buttonExit.setText("Exit");

        StackPane layout = new StackPane();
        layout.getChildren().addAll(buttonPlay,buttonSettings,buttonExit);

        Scene sceneMain = new Scene(layout, 1366, 768);
        primaryStage.setScene(sceneMain);
        primaryStage.show();
    }
}
