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

public class Statistics {

    private static final int MENU_BUTTON_WIDTH = 500;
    private static final int MENU_BUTTON_HEIGHT = 100;

    MainFont fonts;

    Button buttonStatisticsBack;


    public void invoke(Scene sceneStatistics, Stage windowMain) {
        this.fonts = MainFont.getInstance();

        buttonStatisticsBack = new Button();
        buttonStatisticsBack.setOnAction((e)->{
            try {
                Stage tempStage = new Stage();
                (new MainMenu()).start(tempStage);
                windowMain.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        buttonStatisticsBack.setText("Back to main menu");
        buttonStatisticsBack.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonStatisticsBack.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonStatisticsBack.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonStatisticsBack.setFont(this.fonts.getFont("BN Jinx"));

        Label messageOne = new Label();
        messageOne.setText("Statistics");
        messageOne.setPrefSize(700,100);
        messageOne.setMaxSize(700,100);
        messageOne.setMinSize(700,100);
        messageOne.setFont(new Font("Alien Encounters",100));
        messageOne.setAlignment(Pos.CENTER);

        HBox horizontalMenuBox = new HBox();
        horizontalMenuBox.getChildren().addAll(messageOne, buttonStatisticsBack);

        BorderPane layout = new BorderPane();
        layout.setRight(horizontalMenuBox);
        BorderPane.setAlignment(horizontalMenuBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(horizontalMenuBox, new Insets(540,20,0,0));

        sceneStatistics = new Scene(layout,MainGlobals.WIDTH,MainGlobals.HEIGHT);
        windowMain.setScene(sceneStatistics);
        windowMain.setWidth(MainGlobals.WIDTH);
        windowMain.setHeight(MainGlobals.HEIGHT);
        //windowMain.setMaximized(true);
        windowMain.setTitle("Statistics");
        windowMain.setFullScreen(true);
        windowMain.show();
    }
}
