package com.falconfly.menu;

import com.falconfly.config.MainGlobals;
import com.falconfly.config.MainLogger;
import com.falconfly.config.MainMusic;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainEnvironmentLoader {

    private static Scanner environmentFile;
    private static String volume;
    private static String difficulty;
    public static void openEnvironment() {
        MenuStorageLoader loader = new MenuStorageLoader();
        try {
            environmentFile = new Scanner(new File(loader.Load("").get(0).substring(8)));
        }
        catch(Exception ex) {
           MainGlobals.LOGGER.logger.info(ex.toString());
        }
    }

    public static String getVolume() {
        MainEnvironmentLoader.openEnvironment();
        while (MainEnvironmentLoader.environmentFile.hasNext()) {
            volume = MainEnvironmentLoader.environmentFile.next();
        }
        MainEnvironmentLoader.environmentFile.close();
        return volume;
    }

    public static String getDifficulty() {
        MainEnvironmentLoader.openEnvironment();
        difficulty = MainEnvironmentLoader.environmentFile.next();
        MainEnvironmentLoader.environmentFile.close();
        return difficulty;
    }
}
