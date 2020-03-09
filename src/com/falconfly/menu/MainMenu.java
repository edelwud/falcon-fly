package com.falconfly.menu;

import com.falconfly.config.MainFont;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.Stage;

public class MainMenu extends Application {

    Stage windowMain;
    Scene sceneSettings, sceneStatistics;

    Button buttonPlay;
    Button buttonSettings;
    Button buttonExit;
    Button buttonStatistics;
    Label labelName;
    MediaPlayer mediaPlayer;
    Media mediaFile;

    MainFont fonts;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.fonts = new MainFont();
        this.fonts.addFont("Alien Encounters", 60);
        this.fonts.addFont("BN Jinx", 30);

        windowMain = primaryStage;
        windowMain.setTitle("Falcon-Fly launcher");

        mediaFile = new Media("file:///D:/BSUIR/4cem/MyGame/Bet_On_it.mp3");
        mediaPlayer = new MediaPlayer(mediaFile);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.3);

        labelName = new Label();
        labelName.setText("Falcon-Fly");
        labelName.setPrefSize(500,100);
        labelName.setMaxSize(500,100);
        labelName.setMinSize(500,100);
        labelName.setFont(this.fonts.getFont("Alien Encounters"));
        labelName.setAlignment(Pos.CENTER);

        buttonPlay = new Button();
        buttonPlay.setOnAction(this::handle);
        buttonPlay.setText("Play");
        buttonPlay.setPrefSize(500,100);
        buttonPlay.setMaxSize(500,100);
        buttonPlay.setMinSize(500,100);
        buttonPlay.setFont(this.fonts.getFont("BN Jinx"));

        buttonSettings = new Button();
        buttonSettings.setOnAction(this::handle);
        buttonSettings.setText("Settings");
        buttonSettings.setPrefSize(500,100);
        buttonSettings.setMaxSize(500,100);
        buttonSettings.setMinSize(500,100);
        buttonSettings.setFont(this.fonts.getFont("BN Jinx"));

        buttonExit = new Button();
        buttonExit.setOnAction(this::handle);
        buttonExit.setText("Exit");
        buttonExit.setPrefSize(500,100);
        buttonExit.setMaxSize(500,100);
        buttonExit.setMinSize(500,100);
        buttonExit.setFont(this.fonts.getFont("BN Jinx"));

        buttonStatistics = new Button();
        buttonStatistics.setOnAction(this::handle);
        buttonStatistics.setText("Statistics");
        buttonStatistics.setPrefSize(500,100);
        buttonStatistics.setMaxSize(500,100);
        buttonStatistics.setMinSize(500,100);
        buttonStatistics.setFont(this.fonts.getFont("BN Jinx"));

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
             (new Settings()).invoke(sceneSettings, windowMain);
         }

         if(eventMain.getSource() == buttonStatistics) {
             (new Statistics()).invoke(sceneSettings, windowMain);

         }


    }
}
