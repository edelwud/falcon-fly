package com.falconfly.menu;

import com.falconfly.config.MainFont;
import com.falconfly.config.MainGlobals;
import com.falconfly.config.MainMusic;
import com.falconfly.engine.main.Main;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Settings {

    private double tempMUSIC_VOLUME = MainGlobals.MUSIC_VOLUME;

    private Scene scene;
    private Stage stage;

    private int restoreWIDTH = MainGlobals.WIDTH;
    private int restoreHEIGHT = MainGlobals.HEIGHT;

    MainEnvironmentLoader environmentLoader;
    MenuStorageLoader storageLoader;

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
    private boolean applyFlag = true;

    ChoiceBox<String> screenSizes;
    Label labelScreen;



    public void invoke(Scene sceneSettings, Stage windowMain) {
        this.fonts = MainFont.getInstance();
        this.music = MainMusic.getInstance();

        scene = sceneSettings;
        stage = windowMain;

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

        buttonSettingsApply = new Button();
        buttonSettingsApply.setText("Apply");
        buttonSettingsApply.setOnAction(this::handleApply);
        buttonSettingsApply.setPrefSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonSettingsApply.setMaxSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonSettingsApply.setMinSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonSettingsApply.setFont(this.fonts.getFont("BN Jinx"));
        buttonSettingsApply.setDisable(true);

        buttonSettingsBack = new Button();
        buttonSettingsBack.setOnAction((e)->{
            try {
                if(this.applyFlag) {
                    MainGlobals.MUSIC_VOLUME = this.tempMUSIC_VOLUME;
                    musicVolume.setValue(this.tempMUSIC_VOLUME);
                    this.music.mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
                    this.applyFlag = true;
                }
                Stage tempStage = new Stage();
                (new MainMenu()).start(tempStage);
                windowMain.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttonSettingsBack.setText("Back");
        buttonSettingsBack.setPrefSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonSettingsBack.setMaxSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonSettingsBack.setMinSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonSettingsBack.setFont(this.fonts.getFont("BN Jinx"));

        screenSizes = new ChoiceBox<>();
        screenSizes.setValue(Integer.toString(MainGlobals.WIDTH) + "x" + Integer.toString(MainGlobals.HEIGHT));
        for(int i = 0; i < MainGlobals.listSizes.size(); i++) {
            screenSizes.getItems().add(MainGlobals.listSizes.get(i).getKey() + "x" + MainGlobals.listSizes.get(i).getValue());
        }
        screenSizes.setOnAction(this::getChoice);
        screenSizes.setPrefSize(MainGlobals.WIDTH * 0.6 + 50, MainGlobals.HEIGHT * 0.08);
        screenSizes.setMaxSize(MainGlobals.WIDTH * 0.6 + 50, MainGlobals.HEIGHT * 0.08);
        screenSizes.setMinSize(MainGlobals.WIDTH * 0.6 + 50, MainGlobals.HEIGHT * 0.08);

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

        HBox horizontalSettingsBox = new HBox();
        horizontalSettingsBox.setSpacing(25);
        horizontalSettingsBox.getChildren().addAll(buttonMusicAction, musicVolume, buttonVolumeDown, buttonVolumeUp);
        horizontalSettingsBox.setAlignment(Pos.CENTER_LEFT);

        labelScreen = new Label("Screen resolution");
        labelScreen.setFont(this.fonts.getFont("BN Jinx"));

        VBox verticalSettingsBox = new VBox();
        verticalSettingsBox.setSpacing(25);
        verticalSettingsBox.getChildren().addAll(labelMusicSettings, horizontalSettingsBox, labelScreen, screenSizes);

        VBox mainSettingsBox = new VBox();
        mainSettingsBox.setSpacing(25);
        mainSettingsBox.getChildren().addAll(buttonSettingsApply, buttonSettingsBack);

        BorderPane layout = new BorderPane();

        layout.setCenter(verticalSettingsBox);
        BorderPane.setAlignment(verticalSettingsBox, Pos.CENTER);
        BorderPane.setMargin(verticalSettingsBox, new Insets(MainGlobals.HEIGHT * 0.375,0, 0,MainGlobals.WIDTH * 0.15 + 50));

        layout.setBottom(mainSettingsBox);
        BorderPane.setAlignment(mainSettingsBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(mainSettingsBox, new Insets(0,0,20,25));


        sceneSettings = new Scene(layout, MainGlobals.WIDTH, MainGlobals.HEIGHT);
        windowMain.setScene(sceneSettings);
        windowMain.setWidth(MainGlobals.WIDTH);
        windowMain.setHeight(MainGlobals.HEIGHT);
        //windowMain.setMaximized(true);
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

    private void getChoice(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.screenSizes) {
            buttonSettingsApply.setDisable(false);
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
            this.applyFlag = false;
            for(int i = 0; i < MainGlobals.listSizes.size(); i++)
            {

                if(this.screenSizes.getSelectionModel().isSelected(i)) {
                    MainGlobals.WIDTH = Integer.parseInt(MainGlobals.listSizes.get(i).getKey());
                    MainGlobals.HEIGHT = Integer.parseInt(MainGlobals.listSizes.get(i).getValue());
                    storageLoader = new MenuStorageLoader();
                    File environmentFile = new File(storageLoader.Load("").get(0).substring(7));
                    try {
                        PrintWriter writerEnvironment = new PrintWriter(environmentFile);
                        writerEnvironment.print(MainGlobals.listSizes.get(i).getKey() + ' ');
                        writerEnvironment.println(MainGlobals.listSizes.get(i).getValue() + ' ');
                        writerEnvironment.print(MainGlobals.MUSIC_VOLUME);
                        writerEnvironment.close();
                    }
                    catch (Exception ex) {
                        ex.fillInStackTrace();
                    }
                    this.invoke(this.scene, this.stage);
                    break;
                }
            }
        }
    }

}
