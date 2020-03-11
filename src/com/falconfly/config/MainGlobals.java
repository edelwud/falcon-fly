package com.falconfly.config;

public class MainGlobals {
    public static int WIDTH = 1920;
    public static int HEIGHT = 1080;
    public static double MUSIC_VOLUME = 0.1;
    public static boolean musicPlaying = false;

    public static void setVolume(double tempMUSIC_VOLUME) {
        MUSIC_VOLUME = tempMUSIC_VOLUME;
    }

}
