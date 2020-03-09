package com.falconfly.menu;

import com.falconfly.config.MainFont;
import com.falconfly.config.MainGlobals;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Settings {
    MainFont fonts;

    public void invoke(Scene sceneSettings, Stage windowMain) {
        this.fonts = MainFont.getInstance();
        Label messageOne = new Label();
        messageOne.setText("Settings");
        messageOne.setPrefSize(700,100);
        messageOne.setMaxSize(700,100);
        messageOne.setMinSize(700,100);
        messageOne.setFont(new Font("Alien Encounters",100));
        messageOne.setAlignment(Pos.CENTER);
        StackPane layoutSettings = new StackPane();
        layoutSettings.getChildren().add(messageOne);
        sceneSettings = new Scene(layoutSettings,MainGlobals.WIDTH,MainGlobals.HEIGHT);
        windowMain.setScene(sceneSettings);
        //windowMain.setMaximized(true);
        windowMain.setTitle("Settings");
        windowMain.show();
    }
}
