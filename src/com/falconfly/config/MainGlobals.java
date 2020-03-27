package com.falconfly.config;

import com.falconfly.menu.MainEnvironmentLoader;

import java.awt.*;
import java.io.IOException;

public class MainGlobals {

    public static MainLogger LOGGER;

    public static int WIDTH = 0;
    public static int HEIGHT = 0;
    public static double MUSIC_VOLUME = 0;;
    public static boolean musicPlaying = false;

    public static int DIFFICULTY = 1;

    public static void setVolume(double tempMUSIC_VOLUME) {
        MUSIC_VOLUME = tempMUSIC_VOLUME;
    }

    public static void setScreenSize(int tempWIDTH, int tempHEIGHT) {
        WIDTH = tempWIDTH;
        HEIGHT = tempHEIGHT;
    }

    public static void setDifficulty(int tempDIFFICULTY) {
        MainGlobals.DIFFICULTY = tempDIFFICULTY;
    }

}
