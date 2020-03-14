package com.falconfly.menu;

import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.falconfly.menu.MenuStorageLoader;
import javafx.util.Duration;

import java.awt.*;
import java.beans.EventHandler;

public class ExitAlert {

    private Stage alertWindow;
    private Button buttonExitApply;
    private Button buttonExitCancel;
    private MenuStorageLoader loader = new MenuStorageLoader();

    public void display() {

        alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);

        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        int resHeight = screenSize.height;
        int resWidth  = screenSize.width;

        BackgroundImage centerBackground = new BackgroundImage(
                new Image(loader.Load("images/ExitWindow").get(4), resWidth, resHeight,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label mainPicBackground = new Label();
        mainPicBackground.setMaxSize(resWidth, resHeight);
        mainPicBackground.setBackground(new Background(centerBackground));

        BackgroundImage borderUpBackgroundImg = new BackgroundImage(
                new Image(loader.Load("images/ExitWindow").get(1), resWidth * 1.15, resHeight * 1.15,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderUpBackground = new Label();
        borderUpBackground.setMaxSize(resWidth, resHeight);
        borderUpBackground.setBackground(new Background(borderUpBackgroundImg));

        ScaleTransition stLabel = new ScaleTransition(Duration.seconds(1), borderUpBackground);
        stLabel.setFromX(1);
        stLabel.setFromY(1);
        stLabel.setToX(1.03);
        stLabel.setToY(1.03);
        stLabel.play();
        ParallelTransition parallelTransition = new ParallelTransition(stLabel);
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.setAutoReverse(true);
        parallelTransition.play();

        BackgroundImage borderBottomBackgroundImg = new BackgroundImage(
                new Image(loader.Load("images/ExitWindow").get(0), resWidth * 1.15, resHeight * 1.15,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label borderBottomBackground = new Label();
        borderBottomBackground.setMaxSize(resWidth, resHeight);
        borderBottomBackground.setBackground(new Background(borderBottomBackgroundImg));

        ScaleTransition stLabel2 = new ScaleTransition(Duration.seconds(2), borderBottomBackground);
        stLabel2.setFromX(1);
        stLabel2.setFromY(1);
        stLabel2.setToX(1.03);
        stLabel2.setToY(1.03);
        stLabel2.play();
        ParallelTransition parallelTransition2 = new ParallelTransition(stLabel2);
        parallelTransition2.setCycleCount(Timeline.INDEFINITE);
        parallelTransition2.setAutoReverse(true);
        parallelTransition2.play();

        BackgroundImage penBackgroundImg = new BackgroundImage(
                new Image(loader.Load("images/ExitWindow").get(5), resWidth * 0.2, resHeight * 0.2,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label pen = new Label();
        pen.setMaxSize(resWidth * 0.2, resHeight * 0.2);
        pen.setBackground(new Background(penBackgroundImg));

        ScaleTransition stLabel3 = new ScaleTransition(Duration.seconds(0.5), pen);
        stLabel3.setFromX(1);
        stLabel3.setFromY(1);
        stLabel3.setToX(1.03);
        stLabel3.setToY(1.03);
        stLabel3.play();
        ParallelTransition parallelTransition3 = new ParallelTransition(stLabel3);
        parallelTransition3.setCycleCount(Timeline.INDEFINITE);
        parallelTransition3.setAutoReverse(true);
        parallelTransition3.play();

        BackgroundImage exitTextImg = new BackgroundImage(
                new Image(loader.Load("images/ExitWindow").get(6), resWidth * 0.5, resHeight * 0.5,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        Label exitText = new Label();
        exitText.setMaxSize(resWidth * 0.5, resHeight * 0.5);
        exitText.setBackground(new Background(exitTextImg));

        ScaleTransition stLabel4 = new ScaleTransition(Duration.seconds(1), exitText);
        stLabel4.setFromX(1);
        stLabel4.setFromY(1);
        stLabel4.setToX(1.03);
        stLabel4.setToY(1.03);
        stLabel4.play();
        ParallelTransition parallelTransition4 = new ParallelTransition(stLabel4);
        parallelTransition4.setCycleCount(Timeline.INDEFINITE);
        parallelTransition4.setAutoReverse(true);
        parallelTransition4.play();

        double buttonsScale = 0.14;

        BackgroundImage buttonYesBackgroundWhiteImg = new BackgroundImage(
                new Image(loader.Load("images/ExitWindow").get(8), resWidth * buttonsScale, resHeight * buttonsScale,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        BackgroundImage buttonYesBackgroundRedImg = new BackgroundImage(
                new Image(loader.Load("images/ExitWindow").get(7), resWidth * buttonsScale, resHeight * buttonsScale,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonExitApply = new Button();
        buttonExitApply.setOnAction(this::wasPressed);
        buttonExitApply.setBackground(new Background(buttonYesBackgroundWhiteImg));
        buttonExitApply.setPrefSize(resWidth * buttonsScale, resHeight * buttonsScale);
        buttonExitApply.setMaxSize(resWidth * buttonsScale, resHeight * buttonsScale);
        buttonExitApply.setMinSize(resWidth * buttonsScale, resHeight * buttonsScale);

        buttonExitApply.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonExitApply);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.3);
            st.setToY(1.3);
            st.play();
        });

        buttonExitApply.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonExitApply);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        BackgroundImage buttonNoBackgroundWhiteImg = new BackgroundImage(
                new Image(loader.Load("images/ExitWindow").get(3), resWidth * buttonsScale, resHeight * buttonsScale,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        buttonExitCancel = new Button();
        buttonExitCancel.setOnAction(this::wasPressed);
        buttonExitCancel.setBackground(new Background(buttonNoBackgroundWhiteImg));
        buttonExitCancel.setPrefSize(resWidth * buttonsScale, resHeight * buttonsScale);
        buttonExitCancel.setMaxSize(resWidth * buttonsScale, resHeight * buttonsScale);
        buttonExitCancel.setMinSize(resWidth * buttonsScale, resHeight * buttonsScale);

        buttonExitCancel.setOnMouseEntered(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonExitCancel);
            st.setFromX(1);
            st.setFromY(1);
            st.setToX(1.3);
            st.setToY(1.3);
            st.play();
        });

        buttonExitCancel.setOnMouseExited(event -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(250), buttonExitCancel);
            st.setToX(1);
            st.setToY(1);
            st.play();
        });

        StackPane root = new StackPane(mainPicBackground);
        root.getChildren().add(borderUpBackground);
        root.getChildren().add(borderBottomBackground);
        root.setAlignment(pen, Pos.TOP_LEFT);
        root.setMargin(pen, new Insets(resHeight * 0.05, 0, 0, resWidth * 0.04));
        root.getChildren().add(pen);
        root.setAlignment(exitText, Pos.BOTTOM_RIGHT);
        root.setMargin(exitText, new Insets(0, resWidth * 0.01, resHeight * 0.01, 0));
        root.getChildren().add(exitText);
        root.setAlignment(this.buttonExitApply, Pos.BOTTOM_LEFT);
        root.setMargin(this.buttonExitApply, new Insets(0, 0, resHeight * 0.01, resWidth * 0.005));
        root.getChildren().add(this.buttonExitApply);
        root.setAlignment(this.buttonExitCancel, Pos.BOTTOM_LEFT);
        root.setMargin(this.buttonExitCancel, new Insets(0, 0, resHeight * 0.01, resWidth * 0.11));
        root.getChildren().add(this.buttonExitCancel);

        Scene alertScene = new Scene(root);
        this.alertWindow.setScene(alertScene);
        this.alertWindow.setFullScreen(true);
        this.alertWindow.showAndWait();
    }

    public void wasPressed(ActionEvent eventExit) {

        if(eventExit.getSource() == this.buttonExitApply) {
            System.exit(0);
        }

        if(eventExit.getSource() == this.buttonExitCancel) {
            this.alertWindow.close();
        }
    }
}