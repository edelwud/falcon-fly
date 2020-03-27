package com.falconfly.config;

import com.falconfly.menu.MainEnvironmentLoader;

import java.awt.*;

public class MainGlobals {
    public static int WIDTH = 0;
    public static int HEIGHT = 0;
    public static double MUSIC_VOLUME = Double.parseDouble(MainEnvironmentLoader.getVolume());;
    public static boolean musicPlaying = false;

    public static void setVolume(double tempMUSIC_VOLUME) {
        MUSIC_VOLUME = tempMUSIC_VOLUME;
    }

    public static void setScreenSize(int tempWIDTH, int tempHEIGHT) {
        WIDTH = tempWIDTH;
        HEIGHT = tempHEIGHT;
    }

}
