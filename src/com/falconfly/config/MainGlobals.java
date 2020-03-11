package com.falconfly.config;

import com.falconfly.menu.MainEnvironmentLoader;

public class MainGlobals {
    public static int WIDTH = Integer.parseInt(MainEnvironmentLoader.getScreenSizes().get(0).getKey());
    public static int HEIGHT = Integer.parseInt(MainEnvironmentLoader.getScreenSizes().get(0).getValue());;
    public static double MUSIC_VOLUME = Double.parseDouble(MainEnvironmentLoader.getVolume());;
    public static boolean musicPlaying = false;


    public static void setVolume(double tempMUSIC_VOLUME) {
        MUSIC_VOLUME = tempMUSIC_VOLUME;
    }

}
