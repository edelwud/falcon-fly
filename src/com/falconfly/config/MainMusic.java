package com.falconfly.config;

import javafx.scene.media.Media;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainMusic {
    private Map<String, Media> music = new HashMap<String, Media>();
    public static MainMusic instance;

    public MainMusic() {
        instance = this;
    }
    public void addMedia(String musicPath) {
        try {
            Media tempMusic = new Media(musicPath);
            music.put(musicPath, tempMusic);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
    public Media getMedia(String musicPath) {
        return music.get(musicPath);
    }
    public Media getRandomMedia() {
        Set<String> tempString = music.keySet();
        Object[] musicPath = tempString.toArray();
        int randomNumber = new Random().nextInt(musicPath.length);
        Object randomPath = musicPath[randomNumber];
        return music.get(randomPath);
    }
}
