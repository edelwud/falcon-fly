package com.falconfly.menu;

import com.falconfly.config.MainFont;
import com.falconfly.config.MainGlobals;
import com.falconfly.config.MainLogger;
import com.falconfly.config.MainMusic;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.logging.Logger;

public class Statistics {

    MainFont fonts;

    Button buttonStatisticsBack;

    MenuStorageLoader storageLoader = new MenuStorageLoader();

    public void invoke(Scene sceneStatistics, Stage windowMain) {
        this.fonts = MainFont.getInstance();

        BackgroundImage mainBackgroundImg = new BackgroundImage(
                new Image(storageLoader.Load("images/Statistics").get(5), MainGlobals.WIDTH, MainGlobals.HEIGHT,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label mainBackground = new Label();
        mainBackground.setMaxSize(MainGlobals.WIDTH, MainGlobals.HEIGHT);
        mainBackground.setBackground(new Background(mainBackgroundImg));

        BackgroundImage borderBottomRightImg = new BackgroundImage(
                new Image(storageLoader.Load("images/Statistics").get(2), MainGlobals.WIDTH, MainGlobals.HEIGHT,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderBottomRight = new Label();
        borderBottomRight.setMaxSize(MainGlobals.WIDTH, MainGlobals.HEIGHT);
        borderBottomRight.setBackground(new Background(borderBottomRightImg));

        ScaleTransition stLabel1 = new ScaleTransition(Duration.seconds(1.34), borderBottomRight);
        stLabel1.setFromX(1);
        stLabel1.setFromY(1);
        stLabel1.setToX(1.031);
        stLabel1.setToY(1.031);
        ParallelTransition parallelTransition1 = new ParallelTransition(stLabel1);
        parallelTransition1.setCycleCount(Timeline.INDEFINITE);
        parallelTransition1.setAutoReverse(true);
        parallelTransition1.play();

        BackgroundImage borderBottomLeftImg = new BackgroundImage(
                new Image(storageLoader.Load("images/Statistics").get(1), MainGlobals.WIDTH, MainGlobals.HEIGHT,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderBottomLeft = new Label();
        borderBottomLeft.setMaxSize(MainGlobals.WIDTH, MainGlobals.HEIGHT);
        borderBottomLeft.setBackground(new Background(borderBottomLeftImg));

        ScaleTransition stLabel2 = new ScaleTransition(Duration.seconds(1.2), borderBottomLeft);
        stLabel2.setFromX(1);
        stLabel2.setFromY(1);
        stLabel2.setToX(1.027);
        stLabel2.setToY(1.027);
        ParallelTransition parallelTransition2 = new ParallelTransition(stLabel2);
        parallelTransition2.setCycleCount(Timeline.INDEFINITE);
        parallelTransition2.setAutoReverse(true);
        parallelTransition2.play();

        BackgroundImage borderUpRightTransparentImg = new BackgroundImage(
                new Image(storageLoader.Load("images/Statistics").get(4), MainGlobals.WIDTH, MainGlobals.HEIGHT,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderUpRightTransparent = new Label();
        borderUpRightTransparent.setMaxSize(MainGlobals.WIDTH, MainGlobals.HEIGHT);
        borderUpRightTransparent.setBackground(new Background(borderUpRightTransparentImg));

        ScaleTransition stLabel4 = new ScaleTransition(Duration.seconds(1), borderUpRightTransparent);
        stLabel4.setFromX(1);
        stLabel4.setFromY(1);
        stLabel4.setToX(1.03);
        stLabel4.setToY(1.03);
        ParallelTransition parallelTransition4 = new ParallelTransition(stLabel4);
        parallelTransition4.setCycleCount(Timeline.INDEFINITE);
        parallelTransition4.setAutoReverse(true);
        parallelTransition4.play();

        BackgroundImage borderUpRightImg = new BackgroundImage(
                new Image(storageLoader.Load("images/Statistics").get(3), MainGlobals.WIDTH, MainGlobals.HEIGHT,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderUpRight = new Label();
        borderUpRight.setMaxSize(MainGlobals.WIDTH, MainGlobals.HEIGHT);
        borderUpRight.setBackground(new Background(borderUpRightImg));

        ScaleTransition stLabel5 = new ScaleTransition(Duration.seconds(3), borderUpRight);
        stLabel5.setFromX(1);
        stLabel5.setFromY(1);
        stLabel5.setToX(1.04);
        stLabel5.setToY(1.04);
        ParallelTransition parallelTransition5 = new ParallelTransition(stLabel5);
        parallelTransition5.setCycleCount(Timeline.INDEFINITE);
        parallelTransition5.setAutoReverse(true);
        parallelTransition5.play();

        BackgroundImage backButtonBackground = new BackgroundImage(
                new Image(storageLoader.Load("images/Statistics").get(0), MainGlobals.WIDTH / 4, MainGlobals.HEIGHT / 4,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonStatisticsBack = new Button();
        buttonStatisticsBack.setOnAction((e)->{
            try {
                Stage tempStage = new Stage();
                (new MainMenu()).start(tempStage);
                windowMain.close();
            } catch (Exception ex) {
                MainGlobals.LOGGER.logger.info(ex.toString());
            }
        });
        buttonStatisticsBack.setBackground(new Background(backButtonBackground));
        buttonStatisticsBack.setPrefSize(MainGlobals.WIDTH * 0.24,MainGlobals.HEIGHT * 0.14);
        buttonStatisticsBack.setMaxSize(MainGlobals.WIDTH * 0.24,MainGlobals.HEIGHT * 0.14);
        buttonStatisticsBack.setMinSize(MainGlobals.WIDTH * 0.24,MainGlobals.HEIGHT * 0.14);

        buttonStatisticsBack.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), buttonStatisticsBack);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.1);
            st.setToY(1.1);
            st.play();
        });

        buttonStatisticsBack.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), buttonStatisticsBack);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        StackPane root = new StackPane(mainBackground);
        root.getChildren().addAll(borderBottomRight, borderBottomLeft,
                borderUpRightTransparent, borderUpRight);
        root.setMargin(buttonStatisticsBack, new Insets(MainGlobals.HEIGHT * 0.82, 0, 0, MainGlobals.WIDTH * 0.66));
        root.getChildren().add(buttonStatisticsBack);

        sceneStatistics = new Scene(root, MainGlobals.WIDTH, MainGlobals.HEIGHT);
        windowMain.setScene(sceneStatistics);
        windowMain.setWidth(MainGlobals.WIDTH);
        windowMain.setHeight(MainGlobals.HEIGHT);
        windowMain.setTitle("Statistics");
        windowMain.setFullScreen(true);
        windowMain.setFullScreenExitHint("");
        windowMain.show();
    }
}
