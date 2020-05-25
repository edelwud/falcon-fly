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
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.awt.*;

import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class MainDeath {

        private Stage windowDeath;
        private Scene sceneDeath;

        private Button buttonReplay;
        private Button buttonSave;
        private Button buttonExit;

        private Engine engine;

        List<Pair<Number, Number>> pathDeath;
        List<Pair<Number, Number>> enemyPathDeath;

        public MainDeath(List<Pair<Number, Number>> path, List<Pair<Number, Number>> enemyPath, Engine engine) {
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

            StackPane root = new StackPane();
            root.getChildren().addAll(buttonSave, buttonExit, buttonReplay);

            this.sceneDeath = new Scene(root);
            this.windowDeath.setScene(this.sceneDeath);
            this.windowDeath.setWidth(800);
            this.windowDeath.setHeight(600);
            this.windowDeath.showAndWait();

        }

        public void handleSave(ActionEvent eventSave) {

            if(eventSave.getSource() == buttonSave) {


            }
        }

        public void handleReplay(ActionEvent eventReply) throws Exception {

            if(eventReply.getSource() == buttonReplay) {

                //this.windowDeath.close();
                //glfwDestroyWindow(this.engine.getWindow().id);
                //new Main().main();
            }
        }

        public void handleExit(ActionEvent eventExit) {

            if(eventExit.getSource() == buttonExit) {

                this.windowDeath.close();
                glfwDestroyWindow(this.engine.getWindow().id);
            }
        }
}

