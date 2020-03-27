package com.falconfly.menu;

import com.falconfly.config.MainGlobals;
import javafx.util.Pair;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainEnvironmentLoader {

    private static final Logger LOGGER = Logger.getLogger(MainEnvironmentLoader.class.getSimpleName());

    private static Scanner environmentFile;
    private static String volume;
    public static void openEnvironment() {
        MenuStorageLoader loader = new MenuStorageLoader();
        try {
            environmentFile = new Scanner(new File(loader.Load("").get(0).substring(7,loader.Load("").get(0).length())));
        }
        catch(Exception ex) {
            LOGGER.info(ex.toString());
        }
    }

    public static List<Pair<String, String>> getScreenSizes() {

        List<Pair<String, String>> screenSizes = new LinkedList<>();
        MainEnvironmentLoader.openEnvironment();
            screenSizes.add(new Pair<>(MainEnvironmentLoader.environmentFile.next(), MainEnvironmentLoader.environmentFile.next()));
        MainEnvironmentLoader.environmentFile.close();
        return screenSizes;
    }

    public static String getVolume() {

        MainEnvironmentLoader.openEnvironment();
        while(MainEnvironmentLoader.environmentFile.hasNext()) {
            volume = MainEnvironmentLoader.environmentFile.next();
        }
        MainEnvironmentLoader.environmentFile.close();
        return volume;
    }
}
