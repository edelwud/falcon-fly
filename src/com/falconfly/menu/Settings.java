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

public class Settings {

    private static final int MENU_BUTTON_WIDTH = 300;
    private static final int MENU_BUTTON_HEIGHT = 100;

    MainEnvironmentLoader environmentLoader;

    MainFont fonts;

    Button buttonSettingsBack;
    Button buttonScreenSizeApply;
    Button buttonScreenSizeCancel;

    ChoiceBox<String> screenSizes;



    public void invoke(Scene sceneSettings, Stage windowMain) {
        this.fonts = MainFont.getInstance();

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
        buttonSettingsBack.setPrefSize(MENU_BUTTON_WIDTH + 200,MENU_BUTTON_HEIGHT);
        buttonSettingsBack.setMaxSize(MENU_BUTTON_WIDTH + 200,MENU_BUTTON_HEIGHT);
        buttonSettingsBack.setMinSize(MENU_BUTTON_WIDTH + 200,MENU_BUTTON_HEIGHT);
        buttonSettingsBack.setFont(this.fonts.getFont("BN Jinx"));

        buttonScreenSizeApply = new Button();
        buttonScreenSizeApply.setText("Apply");
        buttonScreenSizeApply.setOnAction(this::handleApply);
        buttonScreenSizeApply.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonScreenSizeApply.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonScreenSizeApply.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonScreenSizeApply.setFont(this.fonts.getFont("BN Jinx"));
        buttonScreenSizeApply.setDisable(true);

        buttonScreenSizeCancel = new Button();
        buttonScreenSizeCancel.setText("Cancel");
        buttonScreenSizeCancel.setOnAction(this::handleCancel);
        buttonScreenSizeCancel.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonScreenSizeCancel.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonScreenSizeCancel.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonScreenSizeCancel.setFont(this.fonts.getFont("BN Jinx"));
        buttonScreenSizeCancel.setDisable(true);

        screenSizes = new ChoiceBox<>();
        environmentLoader = new MainEnvironmentLoader();
        screenSizes.setValue(MainGlobals.listSizes.get(0).getKey() + "x" + MainGlobals.listSizes.get(0).getValue());
        for(int i = 0; i < MainGlobals.listSizes.size(); i++) {
            screenSizes.getItems().add(MainGlobals.listSizes.get(i).getKey() + "x" + MainGlobals.listSizes.get(i).getValue());
        }
        screenSizes.setOnAction(this::getChoice);
        screenSizes.setPrefSize(MENU_BUTTON_WIDTH*2 + 20, MENU_BUTTON_HEIGHT);
        screenSizes.setMaxSize(MENU_BUTTON_WIDTH*2 + 20, MENU_BUTTON_HEIGHT);
        screenSizes.setMinSize(MENU_BUTTON_WIDTH*2 + 20, MENU_BUTTON_HEIGHT);

        HBox horizontalTopMenuBox = new HBox();
        horizontalTopMenuBox.getChildren().add(screenSizes);

        HBox horizontalCenterMenuBox = new HBox();
        horizontalCenterMenuBox.setSpacing(20);
        horizontalCenterMenuBox.getChildren().addAll(buttonScreenSizeApply, buttonScreenSizeCancel);

        HBox horizontalMenuBox = new HBox();
        horizontalMenuBox.getChildren().add(buttonSettingsBack);

        BorderPane layout = new BorderPane();

        layout.setTop(horizontalTopMenuBox);
        BorderPane.setAlignment(horizontalTopMenuBox, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(horizontalTopMenuBox, new Insets(0,0,0,20));

        layout.setBottom(horizontalMenuBox);
        BorderPane.setAlignment(horizontalMenuBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(horizontalMenuBox, new Insets(0,0,20,846));

        layout.setCenter(horizontalCenterMenuBox);
        BorderPane.setAlignment(horizontalCenterMenuBox, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(horizontalCenterMenuBox, new Insets(200,0,0,20));

        sceneSettings = new Scene(layout, MainGlobals.WIDTH, MainGlobals.HEIGHT);
        windowMain.setScene(sceneSettings);
        windowMain.setWidth(MainGlobals.WIDTH);
        windowMain.setHeight(MainGlobals.HEIGHT);
        //windowMain.setMaximized(true);
        windowMain.setTitle("Settings");
        //windowMain.setFullScreen(true);
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
                String tempStringOne = new String();
                tempStringOne = MainGlobals.listSizes.get(i).getKey() + "x" + MainGlobals.listSizes.get(i).getValue();
                if(tempStringOne.compareTo(this.screenSizes.getSelectionModel().toString()) == 0) {
                    MainGlobals.WIDTH = Integer.parseInt(MainGlobals.listSizes.get(i).getKey());
                    MainGlobals.HEIGHT = Integer.parseInt(MainGlobals.listSizes.get(i).getValue());
                    break;
                }
            }
        }
    }

    private void handleCancel(ActionEvent actionEvent) {
        if(actionEvent.getSource() == this.buttonScreenSizeCancel) {
           //
        }
    }
}
