package com.falconfly.menu;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.EventHandler;

public class ExitAlert {

    private Stage alertWindow;
    private Label messageLabel;
    private Button buttonExitApply;
    private Button buttonExitCancel;

    public void display(String message) {

        alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);

        messageLabel = new Label();
        messageLabel.setText(message);
        messageLabel.setAlignment(Pos.CENTER);

        buttonExitApply = new Button("Yes");
        buttonExitApply.setOnAction(this::wasPressed);
        buttonExitApply.setPrefSize(100, 25);
        buttonExitApply.setMaxSize(100, 25);
        buttonExitApply.setMinSize(100, 25);

        buttonExitCancel = new Button("No");
        buttonExitCancel.setOnAction(this::wasPressed);
        buttonExitCancel.setPrefSize(100, 25);
        buttonExitCancel.setMaxSize(100, 25);
        buttonExitCancel.setMinSize(100, 25);

        HBox horizontalBox = new HBox();
        horizontalBox.setSpacing(25);
        horizontalBox.getChildren().addAll(this.buttonExitApply, this.buttonExitCancel);
        horizontalBox.setAlignment(Pos.CENTER);

        VBox verticalBox = new VBox();
        verticalBox.setSpacing(25);
        verticalBox.getChildren().addAll(this.messageLabel, horizontalBox);
        verticalBox.setAlignment(Pos.CENTER);

        Scene alertScene = new Scene(verticalBox);
        this.alertWindow.setScene(alertScene);
        this.alertWindow.setWidth(300);
        this.alertWindow.setHeight(150);
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
