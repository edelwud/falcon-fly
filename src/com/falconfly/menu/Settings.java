package com.falconfly.menu;

import com.falconfly.config.MainFont;
import com.falconfly.config.MainGlobals;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Settings {

    private static final int MENU_BUTTON_WIDTH = 500;
    private static final int MENU_BUTTON_HEIGHT = 100;

    MainFont fonts;

    Button buttonSettingsBack;


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
        buttonSettingsBack.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonSettingsBack.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonSettingsBack.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonSettingsBack.setFont(this.fonts.getFont("BN Jinx"));

        Label messageOne = new Label();
        messageOne.setText("Settings");
        messageOne.setPrefSize(700,100);
        messageOne.setMaxSize(700,100);
        messageOne.setMinSize(700,100);
        messageOne.setFont(new Font("Alien Encounters",100));
        messageOne.setAlignment(Pos.CENTER);

        HBox horizontalMenuBox = new HBox();
        horizontalMenuBox.getChildren().addAll(messageOne, buttonSettingsBack);

        BorderPane layout = new BorderPane();
        layout.setRight(horizontalMenuBox);
        BorderPane.setAlignment(horizontalMenuBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(horizontalMenuBox, new Insets(540,20,0,0));

        sceneSettings = new Scene(layout,MainGlobals.WIDTH,MainGlobals.HEIGHT);
        windowMain.setScene(sceneSettings);
        windowMain.setMaximized(true);
        windowMain.setTitle("Settings");
        windowMain.setFullScreen(true);
        windowMain.show();
    }
}
