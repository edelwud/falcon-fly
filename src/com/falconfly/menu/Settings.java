package com.falconfly.menu;

import com.falconfly.config.MainFont;
import com.falconfly.config.MainGlobals;
import com.falconfly.config.MainLogger;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class Settings {

    private double tempMUSIC_VOLUME;

    private Scene scene;
    private Stage stage;

    MenuStorageLoader storageLoader;

    MainFont fonts;
    MainMusic music;

    Button buttonSettingsBack;
    Button buttonSettingsApply;

    Button buttonDifficulty;
    Label labelDifficulty;

    Button buttonMusicAction;
    Button buttonVolumeUp;
    Button buttonVolumeDown;
    Slider musicVolume;
    Label labelMusicSettings;
    private static boolean musicFlag = true;

    private static int count = 0;

    private int difficultyFlag = MainGlobals.DIFFICULTY;

    public void invoke(Scene sceneSettings, Stage windowMain) {
        this.fonts = MainFont.getInstance();
        this.music = MainMusic.getInstance();

        scene = sceneSettings;
        stage = windowMain;

        tempMUSIC_VOLUME = MainGlobals.MUSIC_VOLUME;
        if(Integer.parseInt(MainEnvironmentLoader.getMusicFlag()) == 1)
            this.musicFlag = true;
        else
            this.musicFlag = false;

        count = 0;

        int resHeight = MainGlobals.HEIGHT;
        int resWidth  = MainGlobals.WIDTH;

        storageLoader = new MenuStorageLoader();

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
        buttonSettingsBack.setBackground(new Background(backButtonBackground));
        buttonSettingsBack.setPrefSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.11);
        buttonSettingsBack.setMaxSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.11);
        buttonSettingsBack.setMinSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.11);

        buttonSettingsBack.setOnAction((e)->{
            try {
                if(!buttonSettingsApply.isDisabled() && (count % 2 == 1)) {
                    MainGlobals.DIFFICULTY = this.difficultyFlag;
                    if(!this.musicFlag) {
                        musicVolume.setValue(MainGlobals.MUSIC_VOLUME);
                        this.music.mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
                        this.music.mediaPlayer.setMute(false);
                        this.musicFlag = true;
                    }
                    else {
                        this.music.mediaPlayer.setMute(true);
                        this.musicFlag = false;
                        this.musicVolume.setDisable(true);
                        this.buttonVolumeDown.setDisable(true);
                        this.buttonVolumeUp.setDisable(true);
                    }
                }
                Stage tempStage = new Stage();
                (new MainMenu()).start(tempStage);
                windowMain.close();
            } catch(Exception ex) {
                MainGlobals.LOGGER.logger.info(ex.toString());
            }
        });

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
        buttonSettingsApply.setPrefSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.13);
        buttonSettingsApply.setMaxSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.13);
        buttonSettingsApply.setMinSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.13);
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

        buttonDifficulty = new Button();
        buttonDifficulty.setOnAction(this::handleDifficultyAction);
        if (MainGlobals.DIFFICULTY == 1) {
            BackgroundImage difficultyEasyBackground = new BackgroundImage(
                    new Image(storageLoader.Load("images/Settings").get(7), resWidth * 0.3, resHeight * 0.3,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);
            buttonDifficulty.setBackground(new Background(difficultyEasyBackground));
        }
        else if (MainGlobals.DIFFICULTY == 2) {
            BackgroundImage difficultyNormalBackground = new BackgroundImage(
                    new Image(storageLoader.Load("images/Settings").get(9), resWidth * 0.3, resHeight * 0.3,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);
            buttonDifficulty.setBackground(new Background(difficultyNormalBackground));
        }
        else {
            BackgroundImage difficultyHardBackground = new BackgroundImage(
                    new Image(storageLoader.Load("images/Settings").get(8), resWidth * 0.3, resHeight * 0.3,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);
            buttonDifficulty.setBackground(new Background(difficultyHardBackground));
        }
        buttonDifficulty.setPrefSize(MainGlobals.WIDTH * 0.36, MainGlobals.HEIGHT * 0.19);
        buttonDifficulty.setMaxSize(MainGlobals.WIDTH * 0.36, MainGlobals.HEIGHT * 0.19);
        buttonDifficulty.setMinSize(MainGlobals.WIDTH * 0.36, MainGlobals.HEIGHT * 0.19);

        buttonDifficulty.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), buttonDifficulty);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });

        buttonDifficulty.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), buttonDifficulty);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        buttonMusicAction = new Button();
        buttonMusicAction.setOnAction(this::handleMusicAction);
        if (this.musicFlag) {
            BackgroundImage musicOffBackground = new BackgroundImage(
                    new Image(storageLoader.Load("images/Settings").get(12), resWidth * 0.12, resHeight * 0.12,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);
            buttonMusicAction.setBackground(new Background(musicOffBackground));
        }
        else {
            BackgroundImage musicOnBackground = new BackgroundImage(
                    new Image(storageLoader.Load("images/Settings").get(13), resWidth * 0.12, resHeight * 0.12,false,true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    BackgroundSize.DEFAULT);
            buttonMusicAction.setBackground(new Background(musicOnBackground));
        }
        buttonMusicAction.setPrefSize(MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12);
        buttonMusicAction.setMaxSize(MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12);
        buttonMusicAction.setMinSize(MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12);

        buttonMusicAction.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), buttonMusicAction);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.2);
            st.setToY(1.2);
            st.play();
        });

        buttonMusicAction.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), buttonMusicAction);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        BackgroundImage volumeUpBackground = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(14), resWidth * 0.12, resHeight * 0.12,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonVolumeUp = new Button();
        buttonVolumeUp.setOnAction(this::handleMusicUp);
        buttonVolumeUp.setBackground(new Background(volumeUpBackground));
        buttonVolumeUp.setPrefSize(MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12);
        buttonVolumeUp.setMaxSize(MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12);
        buttonVolumeUp.setMinSize(MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12);
        if(!this.musicFlag)
            buttonVolumeUp.setDisable(true);

        buttonVolumeUp.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), buttonVolumeUp);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.2);
            st.setToY(1.2);
            st.play();
        });

        buttonVolumeUp.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), buttonVolumeUp);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        BackgroundImage volumeDownBackground = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(11), resWidth * 0.12, resHeight * 0.12,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonVolumeDown = new Button();
        buttonVolumeDown.setOnAction(this::handleMusicDown);
        buttonVolumeDown.setBackground(new Background(volumeDownBackground));
        buttonVolumeDown.setPrefSize(MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12);
        buttonVolumeDown.setMaxSize(MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12);
        buttonVolumeDown.setMinSize(MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12);
        if(!this.musicFlag)
            buttonVolumeDown.setDisable(true);

        buttonVolumeDown.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), buttonVolumeDown);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.2);
            st.setToY(1.2);
            st.play();
        });

        buttonVolumeDown.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(100), buttonVolumeDown);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        musicVolume = new Slider();
        musicVolume.setMin(0);
        musicVolume.setMax(1);
        musicVolume.setValue(MainGlobals.MUSIC_VOLUME);
        musicVolume.setOrientation(Orientation.HORIZONTAL);
        musicVolume.setPrefSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.02);
        musicVolume.setMaxSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.02);
        musicVolume.setMinSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.02);
        if(!this.musicFlag)
            musicVolume.setDisable(true);
        musicVolume.valueProperty().addListener(event ->{
            if(musicVolume.isValueChanging()) {
                this.buttonSettingsApply.setDisable(false);
            }
            MainGlobals.MUSIC_VOLUME = musicVolume.getValue();
            this.music.mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
        });

        BackgroundImage difficultyLabelBackground = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(15), resWidth / 4.5, resHeight / 4.5,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label difficultyLabel = new Label();
        difficultyLabel.setMaxSize(resWidth / 4.5, resHeight / 4.5);
        difficultyLabel.setBackground(new Background(difficultyLabelBackground));

        ScaleTransition stLabelDifficulty = new ScaleTransition(Duration.seconds(2), difficultyLabel);
        stLabelDifficulty.setFromX(1);
        stLabelDifficulty.setFromY(1);
        stLabelDifficulty.setToX(1.07);
        stLabelDifficulty.setToY(1.07);
        ParallelTransition parallelTransitionDifficulty = new ParallelTransition(stLabelDifficulty);
        parallelTransitionDifficulty.setCycleCount(Timeline.INDEFINITE);
        parallelTransitionDifficulty.setAutoReverse(true);
        parallelTransitionDifficulty.play();

        BackgroundImage musicLabelBackground = new BackgroundImage(
                new Image(storageLoader.Load("images/Settings").get(16), resWidth / 4.5, resHeight / 4.5,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label musicLabel = new Label();
        musicLabel.setMaxSize(resWidth / 4.5, resHeight / 4.5);
        musicLabel.setBackground(new Background(musicLabelBackground));

        ScaleTransition stLabelMusic = new ScaleTransition(Duration.seconds(2), musicLabel);
        stLabelMusic.setFromX(1);
        stLabelMusic.setFromY(1);
        stLabelMusic.setToX(1.07);
        stLabelMusic.setToY(1.07);
        ParallelTransition parallelTransitionMusic = new ParallelTransition(stLabelMusic);
        parallelTransitionMusic.setCycleCount(Timeline.INDEFINITE);
        parallelTransitionMusic.setAutoReverse(true);
        parallelTransitionMusic.play();

        HBox horizontalSettingsBox = new HBox();
        horizontalSettingsBox.setSpacing(0);
        horizontalSettingsBox.getChildren().addAll(buttonMusicAction, musicVolume, buttonVolumeDown, buttonVolumeUp);
        horizontalSettingsBox.setAlignment(Pos.CENTER);

        VBox verticalSettingsBox = new VBox();
        verticalSettingsBox.setSpacing(MainGlobals.HEIGHT * 0.13);
        verticalSettingsBox.getChildren().addAll(buttonDifficulty, horizontalSettingsBox);

        VBox mainSettingsBox = new VBox();
        mainSettingsBox.setSpacing(0);
        mainSettingsBox.getChildren().addAll(buttonSettingsApply, buttonSettingsBack);

        BorderPane layout = new BorderPane();

        layout.setCenter(verticalSettingsBox);
        BorderPane.setAlignment(verticalSettingsBox, Pos.CENTER);
        BorderPane.setMargin(verticalSettingsBox, new Insets(MainGlobals.HEIGHT * 0.315,0, MainGlobals.HEIGHT * 0.05,MainGlobals.WIDTH * 0.28));
        layout.setBottom(mainSettingsBox);
        BorderPane.setAlignment(mainSettingsBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(mainSettingsBox, new Insets(0,0,MainGlobals.HEIGHT * 0.08,MainGlobals.WIDTH * -0.053));

        StackPane root = new StackPane(mainBackground);
        root.getChildren().addAll(borderTransparentBottomLeft, borderTransparentUpRight,
                borderMainBottomLeft, borderMainUpLeft, borderMainUpRight);
        root.setMargin(difficultyLabel, new Insets(0, MainGlobals.WIDTH * 0.208, MainGlobals.HEIGHT * 0.62, 0));
        root.getChildren().add(difficultyLabel);
        root.setMargin(musicLabel, new Insets(MainGlobals.HEIGHT * 0.03, MainGlobals.WIDTH * 0.25, 0, 0));
        root.getChildren().add(musicLabel);
        root.getChildren().add(layout);

        sceneSettings = new Scene(root, MainGlobals.WIDTH, MainGlobals.HEIGHT);
        windowMain.setScene(sceneSettings);
        windowMain.setWidth(MainGlobals.WIDTH);
        windowMain.setHeight(MainGlobals.HEIGHT);
        windowMain.setTitle("Settings");
        windowMain.setFullScreen(true);
        windowMain.show();
    }

    private void handleDifficultyAction(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.buttonDifficulty) {
            this.buttonSettingsApply.setDisable(false);

            if(MainGlobals.DIFFICULTY == 1) {
                MainGlobals.DIFFICULTY++;
                this.difficultyFlag = 1;
                BackgroundImage difficultyNormalBackground = new BackgroundImage(
                        new Image(storageLoader.Load("images/Settings").get(9), MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.3,false,true),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT);
                this.buttonDifficulty.setBackground(new Background(difficultyNormalBackground));
                return;
            }

            if(MainGlobals.DIFFICULTY == 2) {
                MainGlobals.DIFFICULTY++;
                this.difficultyFlag = 2;
                BackgroundImage difficultyHardBackground = new BackgroundImage(
                        new Image(storageLoader.Load("images/Settings").get(8), MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.3,false,true),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT);
                this.buttonDifficulty.setBackground(new Background(difficultyHardBackground));
                return;
            }

            if(MainGlobals.DIFFICULTY == 3) {
                MainGlobals.DIFFICULTY = 1;
                this.difficultyFlag = 3;
                BackgroundImage difficultyEasyBackground = new BackgroundImage(
                        new Image(storageLoader.Load("images/Settings").get(7), MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.3,false,true),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT);
                this.buttonDifficulty.setBackground(new Background(difficultyEasyBackground));
                return;
            }
        }
    }

    private void handleMusicUp(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.buttonVolumeUp) {
            this.buttonSettingsApply.setDisable(false);
            if (MainGlobals.MUSIC_VOLUME <= 0.9) {
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
            this.buttonSettingsApply.setDisable(false);
            count++;
            if (this.musicFlag) {
                tempMUSIC_VOLUME = MainGlobals.MUSIC_VOLUME;
                this.music.mediaPlayer.setMute(true);
                this.musicFlag = false;
                this.musicVolume.setDisable(true);
                this.buttonVolumeDown.setDisable(true);
                this.buttonVolumeUp.setDisable(true);
                BackgroundImage musicOnBackground = new BackgroundImage(
                        new Image(storageLoader.Load("images/Settings").get(13), MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12,false,true),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT);
                this.buttonMusicAction.setBackground(new Background(musicOnBackground));
            }
            else {
                this.musicVolume.setDisable(false);
                this.buttonVolumeDown.setDisable(false);
                this.buttonVolumeUp.setDisable(false);
                MainGlobals.MUSIC_VOLUME = tempMUSIC_VOLUME;
                musicVolume.setValue(MainGlobals.MUSIC_VOLUME);
                this.music.mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
                this.music.mediaPlayer.setMute(false);
                this.musicFlag = true;
                BackgroundImage musicOffBackground = new BackgroundImage(
                        new Image(storageLoader.Load("images/Settings").get(12), MainGlobals.WIDTH * 0.12, MainGlobals.HEIGHT * 0.12,false,true),
                        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                        BackgroundSize.DEFAULT);
                this.buttonMusicAction.setBackground(new Background(musicOffBackground));
            }
        }
    }

    private void handleApply(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.buttonSettingsApply) {
            storageLoader = new MenuStorageLoader();
            File environmentFile = new File(storageLoader.Load("").get(0).substring(7));
            try {
                PrintWriter writerEnvironment = new PrintWriter(environmentFile);
                if(this.musicFlag)
                    writerEnvironment.println("1");
                else
                    writerEnvironment.println("0");
                writerEnvironment.println(MainGlobals.DIFFICULTY);
                writerEnvironment.print(MainGlobals.MUSIC_VOLUME);
                writerEnvironment.close();
            }
            catch (Exception ex) {
                MainGlobals.LOGGER.logger.info(ex.toString());
            }
            this.invoke(this.scene, this.stage);
        }
    }
}
