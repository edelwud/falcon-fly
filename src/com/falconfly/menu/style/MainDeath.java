package com.falconfly.menu.style;

import com.falconfly.config.MainGlobals;
import com.falconfly.engine.Engine;
import com.falconfly.engine.main.Main;
import com.falconfly.game.Gameplay;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.awt.*;

import java.util.List;
import java.util.Stack;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class MainDeath {

        private Stage windowDeath;
        private Scene sceneDeath;
        private Scene sceneSave;

        private Button buttonReplay;
        private Button buttonSave;
        private Button buttonExit;

        private Label labelScore;
        private Label labelMessage;
        private TextField textFieldName;

        private Button buttonOK;
        private Button buttonBack;

        private Engine engine;

        List<Pair<Number, Number>> pathDeath;
        List<Number> enemyPathDeath;

        public MainDeath(List<Pair<Number, Number>> path, List<Number> enemyPath, Engine engine) {
            this.enemyPathDeath = enemyPath;
            this.pathDeath = path;
            this.engine = engine;
        }

        public void display() {

            this.windowDeath = new Stage(); // always in MainMenu
            this.windowDeath.initModality(Modality.APPLICATION_MODAL);

            this.buttonExit = new Button("Exit");
            this.buttonExit.setOnAction(this::handleExit);

            this.buttonSave = new Button("Save");
            this.buttonSave.setOnAction(this::handleSave);

            this.buttonReplay = new Button("Replay");
            this.buttonReplay.setOnAction(this::handleExit);

            HBox horizontalBoxDeath = new HBox();
            horizontalBoxDeath.setSpacing(10);
            horizontalBoxDeath.setAlignment(Pos.CENTER);
            horizontalBoxDeath.getChildren().addAll(buttonSave, buttonReplay, buttonExit);

            this.labelScore = new Label("Score: 1024");
            this.labelMessage = new Label("What you like to do?");

            VBox verticalBoxDeath = new VBox();
            verticalBoxDeath.setSpacing(30);
            verticalBoxDeath.setAlignment(Pos.CENTER);
            verticalBoxDeath.getChildren().addAll(this.labelScore, this.labelMessage, horizontalBoxDeath);

            BorderPane layout = new BorderPane();
            layout.setCenter(verticalBoxDeath);
            BorderPane.setAlignment(verticalBoxDeath, Pos.BOTTOM_CENTER);

            StackPane root = new StackPane();
            root.getChildren().add(layout);

            this.sceneDeath = new Scene(root);
            this.windowDeath.setScene(this.sceneDeath);
            this.windowDeath.setWidth(450);//
            this.windowDeath.setHeight(550);//
            this.windowDeath.showAndWait();

        }

        public void handleSave(ActionEvent eventSave) {

            if(eventSave.getSource() == buttonSave) {


                this.labelScore = new Label("Score: 1024");
                this.textFieldName = new TextField();
                this.textFieldName.setPrefSize(250, 25);
                this.textFieldName.setMaxSize(250, 25);
                this.textFieldName.setMinSize(250, 25);
                this.textFieldName.setPromptText("Enter your name");
                this.labelMessage.setVisible(false);

                this.buttonOK = new Button("OK");
                this.buttonOK.setOnAction(this::handleOK);

                this.buttonBack = new Button("Back");
                this.buttonBack.setOnAction(this::handleBack);

                HBox horizontalBoxDeathSave = new HBox();
                horizontalBoxDeathSave.setSpacing(10);
                horizontalBoxDeathSave.setAlignment(Pos.CENTER);
                horizontalBoxDeathSave.getChildren().addAll(buttonOK, buttonBack);

                VBox verticalBoxDeathSave = new VBox();
                verticalBoxDeathSave.setSpacing(30);
                verticalBoxDeathSave.setAlignment(Pos.CENTER);
                verticalBoxDeathSave.getChildren().addAll(this.labelScore, this.textFieldName, horizontalBoxDeathSave);

                BorderPane layout = new BorderPane();
                layout.setCenter(verticalBoxDeathSave);
                BorderPane.setAlignment(verticalBoxDeathSave, Pos.BOTTOM_CENTER);

                StackPane root = new StackPane();
                root.getChildren().add(layout);

                this.sceneSave = new Scene(root);
                this.windowDeath.setScene(this.sceneSave);
            }
        }

        public void handleReplay(ActionEvent eventReply) throws Exception{

            if(eventReply.getSource() == buttonReplay) {

                //this.windowDeath.close();
                //glfwDestroyWindow(this.engine.getWindow().id);
                //new Main().main();
            }
        }

        public void handleExit(ActionEvent eventExit) {

            if(eventExit.getSource() == buttonExit) {

                this.windowDeath.close();
                //glfwDestroyWindow(this.engine.getWindow().id);
            }
        }

    public void handleOK(ActionEvent eventReply) {

        if(eventReply.getSource() == buttonOK) {

            //do something
            this.windowDeath.close();
        }
    }

    public void handleBack(ActionEvent eventReply) {

        if(eventReply.getSource() == buttonBack) {

            this.buttonExit = new Button("Exit");
            this.buttonExit.setOnAction(this::handleExit);

            this.buttonSave = new Button("Save");
            this.buttonSave.setOnAction(this::handleSave);

            this.buttonReplay = new Button("Replay");
            this.buttonReplay.setOnAction(this::handleExit);

            HBox horizontalBoxDeathBack = new HBox();
            horizontalBoxDeathBack.setSpacing(10);
            horizontalBoxDeathBack.setAlignment(Pos.CENTER);
            horizontalBoxDeathBack.getChildren().addAll(buttonSave, buttonReplay, buttonExit);

            this.labelScore = new Label("Score: 1024");
            this.labelMessage = new Label("What you like to do?");

            VBox verticalBoxDeathBack = new VBox();
            verticalBoxDeathBack.setSpacing(30);
            verticalBoxDeathBack.setAlignment(Pos.CENTER);
            verticalBoxDeathBack.getChildren().addAll(this.labelScore, this.labelMessage, horizontalBoxDeathBack);

            BorderPane layout = new BorderPane();
            layout.setCenter(verticalBoxDeathBack);
            BorderPane.setAlignment(verticalBoxDeathBack, Pos.BOTTOM_CENTER);

            StackPane root = new StackPane();
            root.getChildren().add(layout);

            this.sceneDeath = new Scene(root);
            this.windowDeath.setScene(this.sceneDeath);
        }
    }
}

