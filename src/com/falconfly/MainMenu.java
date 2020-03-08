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

    Stage windowMain;
    Scene sceneSettings, sceneStatistics;

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

        windowMain = primaryStage;
        windowMain.setTitle("Falcon-Fly launcher");

        labelName = new Label();
        labelName.setText("Falcon-Fly");
        labelName.setPrefSize(500,100);
        labelName.setMaxSize(500,100);
        labelName.setMinSize(500,100);
        labelName.setFont(new Font("Alien Encounters",60));
        labelName.setAlignment(Pos.CENTER);

        buttonPlay = new Button();
        buttonPlay.setOnAction(this::handle);
        buttonPlay.setText("Play");
        buttonPlay.setPrefSize(500,100);
        buttonPlay.setMaxSize(500,100);
        buttonPlay.setMinSize(500,100);
        buttonPlay.setFont(new Font("BN Jinx",30));

        buttonSettings = new Button();
        buttonSettings.setOnAction(this::handle);
        buttonSettings.setText("Settings");
        buttonSettings.setPrefSize(500,100);
        buttonSettings.setMaxSize(500,100);
        buttonSettings.setMinSize(500,100);
        buttonSettings.setFont(new Font("BN Jinx",30));

        buttonExit = new Button();
        buttonExit.setOnAction(this::handle);
        buttonExit.setText("Exit");
        buttonExit.setPrefSize(500,100);
        buttonExit.setMaxSize(500,100);
        buttonExit.setMinSize(500,100);
        buttonExit.setFont(new Font("BN Jinx",30));

        buttonStatistics = new Button();
        buttonStatistics.setOnAction(this::handle);
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
        windowMain.setScene(sceneMain);
        windowMain.setMaximized(true);
        windowMain.show();
    }

     public void handle(ActionEvent eventMain) {

        if(eventMain.getSource() == buttonPlay) {
            System.out.println("Gameplay");
            // Create new Scene of gameplay
        }

        if(eventMain.getSource() == buttonExit) {
            System.exit(0); // exit
        }

         if(eventMain.getSource() == buttonSettings) {
             Label messageOne = new Label();
             messageOne.setText("Settings");
             messageOne.setPrefSize(700,100);
             messageOne.setMaxSize(700,100);
             messageOne.setMinSize(700,100);
             messageOne.setFont(new Font("Alien Encounters",100));
             messageOne.setAlignment(Pos.CENTER);
             StackPane layoutSettings = new StackPane();
             layoutSettings.getChildren().add(messageOne);
             sceneSettings = new Scene(layoutSettings,1366,768);
             windowMain.setScene(sceneSettings);
             //windowMain.setMaximized(true);
             windowMain.setTitle("Settings");
             windowMain.show();
         }

         if(eventMain.getSource() == buttonStatistics) {
             Label messageTwo = new Label();
             messageTwo.setText("Statistics");
             messageTwo.setPrefSize(700,100);
             messageTwo.setMaxSize(700,100);
             messageTwo.setMinSize(700,100);
             messageTwo.setFont(new Font("Alien Encounters",100));
             messageTwo.setAlignment(Pos.CENTER);
             StackPane layoutStatistics = new StackPane();
             layoutStatistics.getChildren().add(messageTwo);
             sceneStatistics = new Scene(layoutStatistics,1366,768);
             windowMain.setScene(sceneStatistics);
             //windowMain.setMaximized(true);
             windowMain.setTitle("Statistics");
             windowMain.show();

         }


    }
}
