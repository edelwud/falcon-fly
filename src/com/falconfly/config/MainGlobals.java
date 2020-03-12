package com.falconfly.config;

import com.falconfly.menu.MainEnvironmentLoader;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainGlobals {
    public static int WIDTH = Integer.parseInt(MainEnvironmentLoader.getScreenSizes().get(0).getKey());
    public static int HEIGHT = Integer.parseInt(MainEnvironmentLoader.getScreenSizes().get(0).getValue());;
    public static double MUSIC_VOLUME = Double.parseDouble(MainEnvironmentLoader.getVolume());;
    public static boolean musicPlaying = false;
    public static boolean isListAdded = false;

    public static List<Pair<String, String>> listSizes = new LinkedList<>();

    public static void setListSizes()
    {
        if(!MainGlobals.isListAdded) {
            listSizes.add(new Pair<>("1920", "1080"));
            listSizes.add(new Pair<>("1600", "900"));
            listSizes.add(new Pair<>("1366", "768"));
            listSizes.add(new Pair<>("1280", "720"));
            MainGlobals.isListAdded = true;
        }
    }

    public static void setVolume(double tempMUSIC_VOLUME) {
        MUSIC_VOLUME = tempMUSIC_VOLUME;
    }

}
