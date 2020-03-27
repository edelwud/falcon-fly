package com.falconfly.menu;

import com.falconfly.config.MainFont;
import com.falconfly.config.MainGlobals;
import com.falconfly.config.MainMusic;
import com.falconfly.engine.main.Main;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class Settings {
    private static final Logger LOGGER = Logger.getLogger(Settings.class.getSimpleName());

    private double tempMUSIC_VOLUME;

    private Scene scene;
    private Stage stage;

    MenuStorageLoader storageLoader = new MenuStorageLoader();

    MainFont fonts;
    MainMusic music;

    Button buttonSettingsBack;
    Button buttonSettingsApply;

    Button buttonMusicAction;
    Button buttonVolumeUp;
    Button buttonVolumeDown;
    Slider musicVolume;
    Label labelMusicSettings;
    private static boolean musicFlag = true;


    public void invoke(Scene sceneSettings, Stage windowMain) {
        this.fonts = MainFont.getInstance();
        this.music = MainMusic.getInstance();

        scene = sceneSettings;
        stage = windowMain;

        int resHeight = MainGlobals.HEIGHT;
        int resWidth  = MainGlobals.WIDTH;

        BackgroundImage centerBackground = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(10), resWidth, resHeight,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label mainBackground = new Label();
        mainBackground.setMaxSize(resWidth, resHeight);
        mainBackground.setBackground(new Background(centerBackground));

        BackgroundImage borderTransparentBottomLeftImg = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(5), resWidth, resHeight,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderTransparentBottomLeft = new Label();
        borderTransparentBottomLeft.setMaxSize(resWidth, resHeight);
        borderTransparentBottomLeft.setBackground(new Background(borderTransparentBottomLeftImg));

        ScaleTransition stLabel1 = new ScaleTransition(Duration.seconds(1), borderTransparentBottomLeft);
        stLabel1.setFromX(1);
        stLabel1.setFromY(1);
        stLabel1.setToX(1.03);
        stLabel1.setToY(1.03);
        stLabel1.play();
        ParallelTransition parallelTransition1 = new ParallelTransition(stLabel1);
        parallelTransition1.setCycleCount(Timeline.INDEFINITE);
        parallelTransition1.setAutoReverse(true);
        parallelTransition1.play();

        BackgroundImage borderTransparentUpRightImg = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(6), resWidth, resHeight,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderTransparentUpRight = new Label();
        borderTransparentUpRight.setMaxSize(resWidth, resHeight);
        borderTransparentUpRight.setBackground(new Background(borderTransparentUpRightImg));

        ScaleTransition stLabel2 = new ScaleTransition(Duration.seconds(0.7), borderTransparentUpRight);
        stLabel2.setFromX(1);
        stLabel2.setFromY(1);
        stLabel2.setToX(1.03);
        stLabel2.setToY(1.03);
        stLabel2.play();
        ParallelTransition parallelTransition2 = new ParallelTransition(stLabel2);
        parallelTransition2.setCycleCount(Timeline.INDEFINITE);
        parallelTransition2.setAutoReverse(true);
        parallelTransition2.play();

        BackgroundImage borderMainUpLeftImg = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(3), resWidth, resHeight,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderMainUpLeft = new Label();
        borderMainUpLeft.setMaxSize(resWidth, resHeight);
        borderMainUpLeft.setBackground(new Background(borderMainUpLeftImg));

        ScaleTransition stLabel3 = new ScaleTransition(Duration.seconds(1.4), borderMainUpLeft);
        stLabel3.setFromX(1);
        stLabel3.setFromY(1);
        stLabel3.setToX(1.03);
        stLabel3.setToY(1.03);
        stLabel3.play();
        ParallelTransition parallelTransition3 = new ParallelTransition(stLabel3);
        parallelTransition3.setCycleCount(Timeline.INDEFINITE);
        parallelTransition3.setAutoReverse(true);
        parallelTransition3.play();

        BackgroundImage borderMainBottomLeftImg = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(2), resWidth, resHeight,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderMainBottomLeft = new Label();
        borderMainBottomLeft.setMaxSize(resWidth, resHeight);
        borderMainBottomLeft.setBackground(new Background(borderMainBottomLeftImg));

        ScaleTransition stLabel4 = new ScaleTransition(Duration.seconds(1.2), borderMainBottomLeft);
        stLabel4.setFromX(1);
        stLabel4.setFromY(1);
        stLabel4.setToX(1.03);
        stLabel4.setToY(1.03);
        stLabel4.play();
        ParallelTransition parallelTransition4 = new ParallelTransition(stLabel4);
        parallelTransition4.setCycleCount(Timeline.INDEFINITE);
        parallelTransition4.setAutoReverse(true);
        parallelTransition4.play();

        BackgroundImage borderMainUpRightImg = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(4), resWidth, resHeight,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderMainUpRight = new Label();
        borderMainUpRight.setMaxSize(resWidth, resHeight);
        borderMainUpRight.setBackground(new Background(borderMainUpRightImg));

        ScaleTransition stLabel5 = new ScaleTransition(Duration.seconds(1.2), borderMainUpRight);
        stLabel5.setFromX(1);
        stLabel5.setFromY(1);
        stLabel5.setToX(1.03);
        stLabel5.setToY(1.03);
        stLabel5.play();
        ParallelTransition parallelTransition5 = new ParallelTransition(stLabel5);
        parallelTransition5.setCycleCount(Timeline.INDEFINITE);
        parallelTransition5.setAutoReverse(true);
        parallelTransition5.play();

        BackgroundImage backButtonBackground = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(1),resWidth * 0.17,resHeight * 0.17,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonSettingsBack = new Button();
        buttonSettingsBack.setOnAction((e)->{
            try {
                Stage tempStage = new Stage();
                (new MainMenu()).start(tempStage);
                windowMain.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttonSettingsBack.setBackground(new Background(backButtonBackground));
        buttonSettingsBack.setPrefSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.15);
        buttonSettingsBack.setMaxSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.15);
        buttonSettingsBack.setMinSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.15);

        buttonSettingsBack.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), buttonSettingsBack);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });

        buttonSettingsBack.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), buttonSettingsBack);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        BackgroundImage applyButtonBackground = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(0),resWidth * 0.17,resHeight * 0.17,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonSettingsApply = new Button();
        buttonSettingsApply.setBackground(new Background(applyButtonBackground));
        buttonSettingsApply.setOnAction(this::handleApply);
        buttonSettingsApply.setPrefSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.15);
        buttonSettingsApply.setMaxSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.15);
        buttonSettingsApply.setMinSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.15);
        buttonSettingsApply.setDisable(true);

        buttonSettingsApply.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), buttonSettingsApply);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });

        buttonSettingsApply.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), buttonSettingsApply);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        labelMusicSettings = new Label("Music Settings");
        labelMusicSettings.setFont(this.fonts.getFont("BN Jinx"));

        buttonMusicAction = new Button();
        buttonMusicAction.setText("On/Off");
        buttonMusicAction.setOnAction(this::handleMusicAction);
        buttonMusicAction.setPrefSize(MainGlobals.WIDTH * 0.1, MainGlobals.HEIGHT * 0.08);
        buttonMusicAction.setMaxSize(MainGlobals.WIDTH * 0.1, MainGlobals.HEIGHT * 0.08);
        buttonMusicAction.setMinSize(MainGlobals.WIDTH * 0.1, MainGlobals.HEIGHT * 0.08);
        buttonMusicAction.setFont(this.fonts.getFont("BN Jinx"));

        buttonVolumeUp = new Button();
        buttonVolumeUp.setText("Up");
        buttonVolumeUp.setOnAction(this::handleMusicUp);
        buttonVolumeUp.setPrefSize(MainGlobals.WIDTH * 0.09, MainGlobals.HEIGHT * 0.08);
        buttonVolumeUp.setMaxSize(MainGlobals.WIDTH * 0.09, MainGlobals.HEIGHT * 0.08);
        buttonVolumeUp.setMinSize(MainGlobals.WIDTH * 0.09, MainGlobals.HEIGHT * 0.08);
        buttonVolumeUp.setFont(this.fonts.getFont("BN Jinx"));

        buttonVolumeDown = new Button();
        buttonVolumeDown.setText("Down");
        buttonVolumeDown.setOnAction(this::handleMusicDown);
        buttonVolumeDown.setPrefSize(MainGlobals.WIDTH * 0.09, MainGlobals.HEIGHT * 0.08);
        buttonVolumeDown.setMaxSize(MainGlobals.WIDTH * 0.09, MainGlobals.HEIGHT * 0.08);
        buttonVolumeDown.setMinSize(MainGlobals.WIDTH * 0.09, MainGlobals.HEIGHT * 0.08);
        buttonVolumeDown.setFont(this.fonts.getFont("BN Jinx"));


        musicVolume = new Slider();
        musicVolume.valueProperty().addListener(event ->{
            if(musicVolume.isValueChanging()) {
                this.buttonSettingsApply.setDisable(false);
            }
            MainGlobals.MUSIC_VOLUME = musicVolume.getValue();
            this.music.mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
        });
        musicVolume.setMin(0);
        musicVolume.setMax(1);
        musicVolume.setValue(MainGlobals.MUSIC_VOLUME);
        musicVolume.setOrientation(Orientation.HORIZONTAL);
        musicVolume.setPrefSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.02);
        musicVolume.setMaxSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.02);
        musicVolume.setMinSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.02);


        HBox horizontalSettingsBox = new HBox();
        horizontalSettingsBox.setSpacing(25);
        horizontalSettingsBox.getChildren().addAll(buttonMusicAction, musicVolume, buttonVolumeDown, buttonVolumeUp);
        horizontalSettingsBox.setAlignment(Pos.CENTER_LEFT);

        VBox verticalSettingsBox = new VBox();
        verticalSettingsBox.setSpacing(25);
        verticalSettingsBox.getChildren().addAll(labelMusicSettings, horizontalSettingsBox);

        VBox mainSettingsBox = new VBox();
        mainSettingsBox.setSpacing(0);
        mainSettingsBox.getChildren().addAll(buttonSettingsApply, buttonSettingsBack);

        BorderPane layout = new BorderPane();

        layout.setCenter(verticalSettingsBox);
        BorderPane.setAlignment(verticalSettingsBox, Pos.CENTER);
        BorderPane.setMargin(verticalSettingsBox, new Insets(MainGlobals.HEIGHT * 0.375,0, 0,MainGlobals.WIDTH * 0.15 + 50));

        layout.setBottom(mainSettingsBox);
        BorderPane.setAlignment(mainSettingsBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(mainSettingsBox, new Insets(0,0,20,5));

        StackPane root = new StackPane(mainBackground);
        root.getChildren().addAll(borderTransparentBottomLeft, borderTransparentUpRight,
                borderMainBottomLeft, borderMainUpLeft, borderMainUpRight);
        root.getChildren().add(layout);

        sceneSettings = new Scene(root, MainGlobals.WIDTH, MainGlobals.HEIGHT);
        windowMain.setScene(sceneSettings);
        windowMain.setWidth(MainGlobals.WIDTH);
        windowMain.setHeight(MainGlobals.HEIGHT);
        windowMain.setTitle("Settings");
        windowMain.setFullScreen(true);
        windowMain.show();
    }

    private void handleMusicUp(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.buttonVolumeUp) {
            this.buttonSettingsApply.setDisable(false);
            if(MainGlobals.MUSIC_VOLUME <= 0.9) {
                MainGlobals.MUSIC_VOLUME += 0.1;
                musicVolume.setValue(MainGlobals.MUSIC_VOLUME);
                this.music.mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
            }
            else {
                MainGlobals.MUSIC_VOLUME = 1;
                musicVolume.setValue(MainGlobals.MUSIC_VOLUME);
                this.music.mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
            }
        }
    }

    private void handleMusicDown(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.buttonVolumeDown) {
            this.buttonSettingsApply.setDisable(false);
            if (MainGlobals.MUSIC_VOLUME >= 0.1) {
                MainGlobals.MUSIC_VOLUME -= 0.1;
                musicVolume.setValue(MainGlobals.MUSIC_VOLUME);
                this.music.mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
            } else {
                MainGlobals.MUSIC_VOLUME = 0;
                musicVolume.setValue(MainGlobals.MUSIC_VOLUME);
                this.music.mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
            }
        }
    }

    private void handleMusicAction(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.buttonMusicAction) {
            if(this.musicFlag) {
                tempMUSIC_VOLUME = MainGlobals.MUSIC_VOLUME;
                musicVolume.setValue(0);
                this.music.mediaPlayer.stop();
                this.musicFlag = false;
            }
            else {
                MainGlobals.MUSIC_VOLUME = tempMUSIC_VOLUME;
                musicVolume.setValue(MainGlobals.MUSIC_VOLUME);
                this.music.mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
                this.music.mediaPlayer.play();
                this.musicFlag = true;
            }
        }
    }

    private void handleApply(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.buttonSettingsApply) {
            storageLoader = new MenuStorageLoader();
            File environmentFile = new File(storageLoader.Load("").get(0).substring(7));
            try {
                PrintWriter writerEnvironment = new PrintWriter(environmentFile);
                writerEnvironment.print(MainGlobals.MUSIC_VOLUME);
                writerEnvironment.close();
            }
            catch (Exception ex) {
                LOGGER.info(ex.toString());
            }
            this.invoke(this.scene, this.stage);
        }
    }
}
