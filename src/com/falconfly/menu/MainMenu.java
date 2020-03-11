package com.falconfly.menu;

import com.falconfly.config.MainFont;
import com.falconfly.config.MainGlobals;
import com.falconfly.config.MainMusic;
import com.falconfly.engine.Engine;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.io.File;

public class MainMenu extends Application {

    private static final int MENU_BUTTON_WIDTH = 500;
    private static final int MENU_BUTTON_HEIGHT = 100;

    Stage windowMain;
    Scene sceneSettings, sceneStatistics;

    Button buttonPlay;
    Button buttonSettings;
    Button buttonExit;
    Button buttonStatistics;
    Label labelName;
    MediaPlayer mediaPlayer;
    Media mediaFile;

    MainFont fonts;
    MainMusic music;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String basePath = new File("").getAbsolutePath();

        MainGlobals.setListSizes();

        this.fonts = new MainFont();
        this.fonts.addFont("Alien Encounters", 60);
        this.fonts.addFont("BN Jinx", 30);

        this.music = new MainMusic();
        MenuStorageLoader loader = new MenuStorageLoader();
        for (String path:loader.Load("music")) {
            this.music.addMedia(path);
        }
        if(!MainGlobals.musicPlaying) {
        music.getRandomMediaPlayer();
        MainGlobals.musicPlaying = true;
        }

        windowMain = primaryStage;
        windowMain.setTitle("Falcon-Fly launcher");

        Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        int resHeight = screenSize.height;
        int resWidth  = screenSize.width;

        BackgroundImage centerBackground = new BackgroundImage(
                new Image(loader.Load("images/Background").get(4), resWidth, resHeight,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        BackgroundImage rightBackground = new BackgroundImage(
                new Image(loader.Load("images").get(1),500,768,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        labelName = new Label();
        labelName.setText("Falcon-Fly");
        labelName.setTextFill(Color.web("#fad41b"));
        labelName.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        labelName.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        labelName.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        labelName.setFont(this.fonts.getFont("Alien Encounters"));
        labelName.setAlignment(Pos.CENTER);

        buttonPlay = new Button();
        buttonPlay.setOnAction(this::handle);
        buttonPlay.setText("Play");
        buttonPlay.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonPlay.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonPlay.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonPlay.setFont(this.fonts.getFont("BN Jinx"));

        buttonSettings = new Button();
        buttonSettings.setOnAction(this::handle);
        buttonSettings.setText("Settings");
        buttonSettings.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonSettings.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonSettings.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonSettings.setFont(this.fonts.getFont("BN Jinx"));

        buttonExit = new Button();
        buttonExit.setOnAction(this::handle);
        buttonExit.setText("Exit");
        buttonExit.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonExit.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonExit.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonExit.setFont(this.fonts.getFont("BN Jinx"));

        buttonStatistics = new Button();
        buttonStatistics.setOnAction(this::handle);
        buttonStatistics.setText("Statistics");
        buttonStatistics.setPrefSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonStatistics.setMaxSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonStatistics.setMinSize(MENU_BUTTON_WIDTH,MENU_BUTTON_HEIGHT);
        buttonStatistics.setFont(this.fonts.getFont("BN Jinx"));

        Image mainMenuBorder = new Image(loader.Load("images/Background").get(1));
        Image backMenuBorder = new Image(loader.Load("images/Background").get(0));

        VBox verticalMenuBox = new VBox();
        verticalMenuBox.setSpacing(20);
        verticalMenuBox.getChildren().addAll(labelName, buttonPlay, buttonSettings,buttonStatistics, buttonExit);
        //verticalMenuBox.setBackground(new Background(rightBackground));

        //VBox menuBorders = new VBox();
        //menuBorders.getChildren().addAll(new ImageView(backMenuBorder), new ImageView(mainMenuBorder));

        BorderPane layout = new BorderPane();
        layout.setRight(verticalMenuBox);
        BorderPane.setAlignment(verticalMenuBox, Pos.BOTTOM_RIGHT);
        BorderPane.setMargin(verticalMenuBox, new Insets(40,20,0,0));
        layout.setBackground(new Background(centerBackground));
        //layout.setCenter(menuBorders);

        Scene sceneMain = new Scene(layout);
        windowMain.setScene(sceneMain);
        //windowMain.setMaximized(true);
        windowMain.setWidth(MainGlobals.WIDTH);
        windowMain.setHeight(MainGlobals.HEIGHT);
        windowMain.setFullScreen(true);
        windowMain.show();
    }

     public void handle(ActionEvent eventMain) {

        if(eventMain.getSource() == buttonPlay) {
            new Engine().run();
        }

        if(eventMain.getSource() == buttonExit) {
            System.exit(0); // exit
        }

         if(eventMain.getSource() == buttonSettings) {
             (new Settings()).invoke(sceneSettings, windowMain);
         }

         if(eventMain.getSource() == buttonStatistics) {
             (new Statistics()).invoke(sceneSettings, windowMain);
         }


    }
}
