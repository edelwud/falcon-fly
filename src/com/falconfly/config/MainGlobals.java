package com.falconfly.config;

public class MainGlobals {
    public static int WIDTH = 1366;
    public static int HEIGHT = 768;
    public static double MUSIC_VOLUME = 0.1;
    public static boolean musicPlaying = false;

    public static void setScreenSize(int tempWIDTH, int tempHEIGHT) {
        WIDTH = tempWIDTH;
        HEIGHT = tempHEIGHT;
    }
    public static void setVolume(double tempMUSIC_VOLUME) {
        MUSIC_VOLUME = tempMUSIC_VOLUME;
    }

}
