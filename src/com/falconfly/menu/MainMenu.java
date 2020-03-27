package com.falconfly.menu;

import com.falconfly.config.MainFont;
import com.falconfly.config.MainGlobals;
import com.falconfly.config.MainMusic;
import com.falconfly.engine.main.Main;
import javafx.animation.*;
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
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.io.File;

public class MainMenu extends Application {

    private static double MENU_BUTTON_WIDTH = 0;
    private static double MENU_BUTTON_HEIGHT = 0;

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

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        MainGlobals.setScreenSize(screenSize.width, screenSize.height);

        String basePath = new File("").getAbsolutePath();

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

        int resHeight = MainGlobals.HEIGHT;
        int resWidth  = MainGlobals.WIDTH;

        MENU_BUTTON_WIDTH = resWidth * 0.4;
        MENU_BUTTON_HEIGHT = resHeight * 0.122;

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

        Path path = new Path();
        path.getElements().add(new MoveTo(resWidth * 0.5,resHeight / 2));
        path.getElements().add(new LineTo(resWidth * 0.54,resHeight / 2));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(1800));
        pathTransition.setPath(path);
        pathTransition.setNode(transparentBorderBackground);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(Timeline.INDEFINITE);
        pathTransition.setAutoReverse(true);
        pathTransition.play();

        Label mainBorderBackground = new Label();
        mainBorderBackground.setMaxSize(resWidth, resHeight);
        mainBorderBackground.setBackground(new Background(mainBorderBackgroundImg));

        Path path2 = new Path();
        path2.getElements().add(new MoveTo(resWidth * 0.5,resHeight / 2));
        path2.getElements().add(new LineTo(resWidth * 0.54,resHeight / 2));
        PathTransition pathTransition2 = new PathTransition();
        pathTransition2.setDuration(Duration.millis(1500));
        pathTransition2.setPath(path2);
        pathTransition2.setNode(mainBorderBackground);
        pathTransition2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition2.setCycleCount(Timeline.INDEFINITE);
        pathTransition2.setAutoReverse(true);
        pathTransition2.play();

        Label gameMenuBackground = new Label();
        gameMenuBackground.setMaxSize(resWidth, resHeight);
        gameMenuBackground.setBackground(new Background(gameMenuBackgroundImg));

        labelName = new Label();
        labelName.setMaxSize(resWidth * 0.35, resHeight * 0.35);
        labelName.setBackground(new Background(gameNameBackgroundImg));

        ScaleTransition stLabel = new ScaleTransition(Duration.seconds(3), labelName);
        stLabel.setFromX(1);
        stLabel.setFromY(1);
        stLabel.setToX(1.1);
        stLabel.setToY(1.1);
        stLabel.play();
        ParallelTransition parallelTransition = new ParallelTransition(stLabel);
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.setAutoReverse(true);
        parallelTransition.play();

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

        buttonPlay.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonPlay);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.1);
            st.setToY(1.1);
            //st.play();

            TranslateTransition tt = new TranslateTransition(Duration.millis(250), buttonPlay);
            tt.setFromX(0);
            tt.setToX(-50);

            ParallelTransition locParallelTransition = new ParallelTransition(st, tt);
            locParallelTransition.play();
        });

        buttonPlay.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonPlay);
            st.setToX(1);
            st.setToY(1);
            //st.play();

            TranslateTransition tt = new TranslateTransition(Duration.millis(250), buttonPlay);
            tt.setFromX(-50);
            tt.setToX(0);

            ParallelTransition locParallelTransition = new ParallelTransition(st, tt);
            locParallelTransition.play();
        });

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

        buttonSettings.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonSettings);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.1);
            st.setToY(1.1);
            //st.play();

            TranslateTransition tt = new TranslateTransition(Duration.millis(250), buttonSettings);
            tt.setFromX(0);
            tt.setToX(-35);

            ParallelTransition locParallelTransition = new ParallelTransition(st, tt);
            locParallelTransition.play();
        });

        buttonSettings.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonSettings);
            st.setToX(1);
            st.setToY(1);
            //st.play();

            TranslateTransition tt = new TranslateTransition(Duration.millis(250), buttonSettings);
            tt.setFromX(-35);
            tt.setToX(0);

            ParallelTransition locParallelTransition = new ParallelTransition(st, tt);
            locParallelTransition.play();
        });

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

        buttonExit.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonExit);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.1);
            st.setToY(1.1);
            //st.play();

            TranslateTransition tt = new TranslateTransition(Duration.millis(250), buttonExit);
            tt.setFromX(0);
            tt.setToX(-35);

            ParallelTransition locParallelTransition = new ParallelTransition(st, tt);
            locParallelTransition.play();
        });

        buttonExit.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonExit);
            st.setToX(1);
            st.setToY(1);
            //st.play();

            TranslateTransition tt = new TranslateTransition(Duration.millis(250), buttonExit);
            tt.setFromX(-35);
            tt.setToX(0);

            ParallelTransition locParallelTransition = new ParallelTransition(st, tt);
            locParallelTransition.play();
        });

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

        buttonStatistics.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonStatistics);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.1);
            st.setToY(1.1);
            //st.play();

            TranslateTransition tt = new TranslateTransition(Duration.millis(250), buttonStatistics);
            tt.setFromX(0);
            tt.setToX(-35);

            ParallelTransition locParallelTransition = new ParallelTransition(st, tt);
            locParallelTransition.play();
        });

        buttonStatistics.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonStatistics);
            st.setToX(1);
            st.setToY(1);
            //st.play();

            TranslateTransition tt = new TranslateTransition(Duration.millis(250), buttonStatistics);
            tt.setFromX(-35);
            tt.setToX(0);

            ParallelTransition locParallelTransition = new ParallelTransition(st, tt);
            locParallelTransition.play();
        });

        VBox verticalMenuBox = new VBox();
        verticalMenuBox.setSpacing(0);
        verticalMenuBox.getChildren().addAll(buttonPlay,buttonStatistics, buttonSettings, buttonExit);

        BorderPane layout = new BorderPane();
        layout.setRight(verticalMenuBox);
        BorderPane.setAlignment(verticalMenuBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(verticalMenuBox, new Insets(resHeight * 0.4,-25,0,0));

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
        windowMain.setMaximized(true);
        windowMain.setWidth(resWidth);
        windowMain.setHeight(resHeight);
        windowMain.setFullScreen(true);
        windowMain.setFullScreenExitHint("");
        windowMain.show();
    }

     public void handle(ActionEvent eventMain) {

        if(eventMain.getSource() == buttonPlay) {
            new Main().main();
        }

        if(eventMain.getSource() == buttonExit) {
            ExitAlert exitAlert = new ExitAlert();
            //this.windowMain.setFullScreen(false);
            exitAlert.display();
            windowMain.setFullScreen(true);
        }

        if(eventMain.getSource() == buttonSettings) {
            (new Settings()).invoke(sceneSettings, windowMain);
        }

        if(eventMain.getSource() == buttonStatistics) {
            (new Statistics()).invoke(sceneSettings, windowMain);
        }
    }
}
