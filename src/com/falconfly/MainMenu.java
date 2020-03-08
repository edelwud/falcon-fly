package com.falconfly;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainMenu extends Application {

    Button buttonPlay;
    Button buttonSettings;
    Button buttonExit;
    Button buttonStatistics;
    Label labelName;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Falcon-Fly launcher");

        labelName = new Label();
        labelName.setText("Falcon-Fly");
        labelName.setPrefSize(500,100);
        labelName.setMaxSize(500,100);
        labelName.setMinSize(500,100);
        labelName.setFont(new Font("Alien Encounters",60));
        labelName.setAlignment(Pos.CENTER);

        buttonPlay = new Button();
        buttonPlay.setText("Play");
        buttonPlay.setPrefSize(500,100);
        buttonPlay.setMaxSize(500,100);
        buttonPlay.setMinSize(500,100);
        buttonPlay.setFont(new Font("BN Jinx",30));

        buttonSettings = new Button();
        buttonSettings.setText("Settings");
        buttonSettings.setPrefSize(500,100);
        buttonSettings.setMaxSize(500,100);
        buttonSettings.setMinSize(500,100);
        buttonSettings.setFont(new Font("BN Jinx",30));

        buttonExit = new Button();
        buttonExit.setText("Exit");
        buttonExit.setPrefSize(500,100);
        buttonExit.setMaxSize(500,100);
        buttonExit.setMinSize(500,100);
        buttonExit.setFont(new Font("BN Jinx",30));

        buttonStatistics = new Button();
        buttonStatistics.setText("Statistics");
        buttonStatistics.setPrefSize(500,100);
        buttonStatistics.setMaxSize(500,100);
        buttonStatistics.setMinSize(500,100);
        buttonStatistics.setFont(new Font("BN Jinx",30));

        VBox verticalMenuBox = new VBox();
        verticalMenuBox.setSpacing(20);
        verticalMenuBox.getChildren().addAll(labelName, buttonPlay, buttonSettings,buttonStatistics, buttonExit);

        BorderPane layout = new BorderPane();
        layout.setRight(verticalMenuBox);
        BorderPane.setAlignment(verticalMenuBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(verticalMenuBox, new Insets(40,20,0,0));

        Scene sceneMain = new Scene(layout);
        primaryStage.setScene(sceneMain);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
