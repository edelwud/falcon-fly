package com.falconfly.menu;

import com.falconfly.config.MainFont;
import com.falconfly.config.MainGlobals;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Settings {


    private Scene scene;
    private Stage stage;

    private int restoreWIDTH = MainGlobals.WIDTH;
    private int restoreHEIGHT = MainGlobals.HEIGHT;

    MainEnvironmentLoader environmentLoader;
    MenuStorageLoader storageLoader;

    MainFont fonts;

    Button buttonSettingsBack;
    Button buttonScreenSizeApply;
    Button buttonScreenSizeCancel;

    ChoiceBox<String> screenSizes;



    public void invoke(Scene sceneSettings, Stage windowMain) {
        this.fonts = MainFont.getInstance();

        scene = sceneSettings;
        stage = windowMain;


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
        buttonSettingsBack.setText("Back to main menu");
        buttonSettingsBack.setPrefSize(MainGlobals.WIDTH * 0.33, MainGlobals.HEIGHT * 0.08);
        buttonSettingsBack.setMaxSize(MainGlobals.WIDTH * 0.33, MainGlobals.HEIGHT * 0.08);
        buttonSettingsBack.setMinSize(MainGlobals.WIDTH * 0.33, MainGlobals.HEIGHT * 0.08);
        buttonSettingsBack.setFont(this.fonts.getFont("BN Jinx"));

        buttonScreenSizeApply = new Button();
        buttonScreenSizeApply.setText("Apply");
        buttonScreenSizeApply.setOnAction(this::handleApply);
        buttonScreenSizeApply.setPrefSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonScreenSizeApply.setMaxSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonScreenSizeApply.setMinSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonScreenSizeApply.setFont(this.fonts.getFont("BN Jinx"));
        buttonScreenSizeApply.setDisable(true);

        buttonScreenSizeCancel = new Button();
        buttonScreenSizeCancel.setText("Cancel");
        buttonScreenSizeCancel.setOnAction(this::handleCancel);
        buttonScreenSizeCancel.setPrefSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonScreenSizeCancel.setMaxSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonScreenSizeCancel.setMinSize(MainGlobals.WIDTH * 0.3, MainGlobals.HEIGHT * 0.08);
        buttonScreenSizeCancel.setFont(this.fonts.getFont("BN Jinx"));
        buttonScreenSizeCancel.setDisable(true);


        screenSizes = new ChoiceBox<>();
        screenSizes.setValue(Integer.toString(MainGlobals.WIDTH) + "x" + Integer.toString(MainGlobals.HEIGHT));
        for(int i = 0; i < MainGlobals.listSizes.size(); i++) {
            screenSizes.getItems().add(MainGlobals.listSizes.get(i).getKey() + "x" + MainGlobals.listSizes.get(i).getValue());
        }
        screenSizes.setOnAction(this::getChoice);
        screenSizes.setPrefSize(MainGlobals.WIDTH * 0.6 + 50, MainGlobals.HEIGHT * 0.08);
        screenSizes.setMaxSize(MainGlobals.WIDTH * 0.6 + 50, MainGlobals.HEIGHT * 0.08);
        screenSizes.setMinSize(MainGlobals.WIDTH * 0.6 + 50, MainGlobals.HEIGHT * 0.08);

        VBox verticalTopMenuBox = new VBox();
        verticalTopMenuBox.getChildren().add(screenSizes);

        HBox horizontalMenuBox = new HBox();
        horizontalMenuBox.setSpacing(25);
        horizontalMenuBox.getChildren().addAll(buttonSettingsBack,buttonScreenSizeApply, buttonScreenSizeCancel);

        BorderPane layout = new BorderPane();

        layout.setCenter(verticalTopMenuBox);
        BorderPane.setAlignment(verticalTopMenuBox, Pos.CENTER);
        BorderPane.setMargin(verticalTopMenuBox, new Insets(MainGlobals.HEIGHT * 0.775,0, 0,MainGlobals.WIDTH * 0.15 + 50));

        layout.setBottom(horizontalMenuBox);
        BorderPane.setAlignment(horizontalMenuBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(horizontalMenuBox, new Insets(0,0,20,25));


        sceneSettings = new Scene(layout, MainGlobals.WIDTH, MainGlobals.HEIGHT);
        windowMain.setScene(sceneSettings);
        windowMain.setWidth(MainGlobals.WIDTH);
        windowMain.setHeight(MainGlobals.HEIGHT);
        //windowMain.setMaximized(true);
        windowMain.setTitle("Settings");
        windowMain.setFullScreen(true);
        windowMain.show();
    }

    private void getChoice(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.screenSizes) {
            buttonScreenSizeApply.setDisable(false);
            buttonScreenSizeCancel.setDisable(false);
        }
    }

    private void handleApply(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.buttonScreenSizeApply) {
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

    private void handleCancel(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.buttonScreenSizeCancel) {
            MainGlobals.WIDTH = this.restoreWIDTH;
            MainGlobals.HEIGHT = this.restoreHEIGHT;
            storageLoader = new MenuStorageLoader();
            File environmentFile = new File(storageLoader.Load("").get(0).substring(7));
            try {
                PrintWriter writerEnvironment = new PrintWriter(environmentFile);
                writerEnvironment.print(Integer.toString(this.restoreWIDTH) + ' ');
                writerEnvironment.println(Integer.toString(this.restoreHEIGHT));
                writerEnvironment.print(MainGlobals.MUSIC_VOLUME);
                writerEnvironment.close();
            }
            catch (Exception ex) {
                ex.fillInStackTrace();
            }
            screenSizes.setValue(Integer.toString(this.restoreWIDTH) + "x" + Integer.toString(this.restoreHEIGHT));
        }
    }
}
