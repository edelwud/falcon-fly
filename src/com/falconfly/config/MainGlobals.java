package com.falconfly.config;

import com.falconfly.menu.MainEnvironmentLoader;

public class MainGlobals {
    MainEnvironmentLoader loader = new MainEnvironmentLoader();
    public static int WIDTH;
    public static int HEIGHT;
    public static double MUSIC_VOLUME;
    public static boolean musicPlaying = false;

    public MainGlobals() {
        WIDTH = Integer.parseInt(loader.getScreenSizes().get(0).getKey());
        HEIGHT = Integer.parseInt(loader.getScreenSizes().get(1).getKey());
        MUSIC_VOLUME = Integer.parseInt(loader.getVolume());
    }

    public static void setVolume(double tempMUSIC_VOLUME) {
        MUSIC_VOLUME = tempMUSIC_VOLUME;
    }

}
