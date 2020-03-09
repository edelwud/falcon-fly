package com.falconfly.menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Statistics {

    public void invoke(Scene sceneSettings, Stage windowMain) {
        Label messageOne = new Label();
        messageOne.setText("Statistics");
        messageOne.setPrefSize(700,100);
        messageOne.setMaxSize(700,100);
        messageOne.setMinSize(700,100);
        messageOne.setFont(new Font("Alien Encounters",100));
        messageOne.setAlignment(Pos.CENTER);
        StackPane layoutSettings = new StackPane();
        layoutSettings.getChildren().add(messageOne);
        sceneSettings = new Scene(layoutSettings,1366,768);
        windowMain.setScene(sceneSettings);
        //windowMain.setMaximized(true);
        windowMain.setTitle("Statistics");
        windowMain.show();
    }
}
