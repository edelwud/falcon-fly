package com.falconfly.menu;

import com.falconfly.config.MainFont;
import com.falconfly.config.MainGlobals;
import com.falconfly.config.MainMusic;
import com.falconfly.engine.Engine;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.io.File;

public class MainMenu extends Application {

    private static final int MENU_BUTTON_WIDTH = 600;
    private static final int MENU_BUTTON_HEIGHT = 105;

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
    MainMusic music;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String basePath = new File("").getAbsolutePath();

        MainGlobals.setListSizes();

        this.fonts = new MainFont();
        this.fonts.addFont("Alien Encounters", 60);
        this.fonts.addFont("BN Jinx", 30);

        this.music = new MainMusic();
        MenuStorageLoader loader = new MenuStorageLoader();
        for (String path:loader.Load("music")) {
            this.music.addMedia(path);
        }
        if(!MainGlobals.musicPlaying) {
        music.getRandomMediaPlayer();
        MainGlobals.musicPlaying = true;
        }

        windowMain = primaryStage;
        windowMain.setTitle("Falcon-Fly launcher");

        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        int resHeight = screenSize.height;
        int resWidth  = screenSize.width;

        BackgroundImage centerBackground = new BackgroundImage(
                new Image(loader.Load("images/Background").get(4), resWidth, resHeight,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        BackgroundImage rightBackground = new BackgroundImage(
                new Image(loader.Load("images/Background").get(0),resWidth * 1.25,resHeight * 1.25,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        BackgroundImage mainBorderBackgroundImg = new BackgroundImage(
                new Image(loader.Load("images/Background").get(1),resWidth * 1.25,resHeight * 1.25,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        BackgroundImage gameNameBackgroundImg = new BackgroundImage(
                new Image(loader.Load("images/Background").get(2),resWidth * 0.35,resHeight * 0.35,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        BackgroundImage gameMenuBackgroundImg = new BackgroundImage(
                new Image(loader.Load("images/Background").get(3),resWidth * 0.25,resHeight * 0.25,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label mainPicBackground = new Label();
        mainPicBackground.setMaxSize(resWidth, resHeight);
        mainPicBackground.setBackground(new Background(centerBackground));
        Label transparentBorderBackground = new Label();
        transparentBorderBackground.setMaxSize(resWidth, resHeight);
        transparentBorderBackground.setBackground(new Background(rightBackground));
        Label mainBorderBackground = new Label();
        mainBorderBackground.setMaxSize(resWidth, resHeight);
        mainBorderBackground.setBackground(new Background(mainBorderBackgroundImg));
        Label gameMenuBackground = new Label();
        gameMenuBackground.setMaxSize(resWidth, resHeight);
        gameMenuBackground.setBackground(new Background(gameMenuBackgroundImg));

        labelName = new Label();
        labelName.setMaxSize(resWidth * 0.35, resHeight * 0.35);
        labelName.setBackground(new Background(gameNameBackgroundImg));

        BackgroundImage playBackground = new BackgroundImage(
                new Image(loader.Load("images/Buttons").get(3),resWidth * 0.45,resHeight * 0.45,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonPlay = new Button();
        buttonPlay.setOnAction(this::handle);
        buttonPlay.setBackground(new Background(playBackground));
        buttonPlay.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonPlay.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonPlay.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);

        BackgroundImage settingsBackground = new BackgroundImage(
                new Image(loader.Load("images/Buttons").get(5),resWidth * 0.5,resHeight * 0.5,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonSettings = new Button();
        buttonSettings.setOnAction(this::handle);
        buttonSettings.setBackground(new Background(settingsBackground));
        buttonSettings.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonSettings.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonSettings.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);

        BackgroundImage exitBackground = new BackgroundImage(
                new Image(loader.Load("images/Buttons").get(1),resWidth * 0.5,resHeight * 0.5,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonExit = new Button();
        buttonExit.setOnAction(this::handle);
        buttonExit.setBackground(new Background(exitBackground));
        buttonExit.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonExit.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonExit.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);

        BackgroundImage statisticsBackground = new BackgroundImage(
                new Image(loader.Load("images/Buttons").get(7),resWidth * 0.5,resHeight * 0.5,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonStatistics = new Button();
        buttonStatistics.setOnAction(this::handle);
        buttonStatistics.setBackground(new Background(statisticsBackground));
        buttonStatistics.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonStatistics.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonStatistics.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);

        VBox verticalMenuBox = new VBox();
        verticalMenuBox.setSpacing(0);
        verticalMenuBox.getChildren().addAll(buttonPlay,buttonStatistics, buttonSettings, buttonExit);

        BorderPane layout = new BorderPane();
        layout.setRight(verticalMenuBox);
        BorderPane.setAlignment(verticalMenuBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(verticalMenuBox, new Insets(resHeight * 0.4,0,0,0));

        StackPane root = new StackPane(mainPicBackground, transparentBorderBackground, mainBorderBackground);
        StackPane.setAlignment(labelName, Pos.TOP_RIGHT);
        StackPane.setMargin(labelName, new Insets(30, 75, 0, 0));
        root.getChildren().add(labelName);
        StackPane.setAlignment(gameMenuBackground, Pos.CENTER);
        StackPane.setMargin(gameMenuBackground, new Insets(0, 0, resHeight * 0.35, resWidth * 0.75));
        root.getChildren().add(gameMenuBackground);
        root.getChildren().add(layout);

        Scene sceneMain = new Scene(root);
        windowMain.setScene(sceneMain);
        //windowMain.setMaximized(true);
        windowMain.setWidth(resWidth);
        windowMain.setHeight(resHeight);
        windowMain.setFullScreen(true);
        windowMain.show();
    }

     public void handle(ActionEvent eventMain) {

        if(eventMain.getSource() == buttonPlay) {
            new Engine().run();
        }

        if(eventMain.getSource() == buttonExit) {
            ExitAlert exitAlert = new ExitAlert();
            exitAlert.display("You really want to exit?");
        }

         if(eventMain.getSource() == buttonSettings) {
             (new Settings()).invoke(sceneSettings, windowMain);
         }

         if(eventMain.getSource() == buttonStatistics) {
             (new Statistics()).invoke(sceneSettings, windowMain);
         }


    }
}
