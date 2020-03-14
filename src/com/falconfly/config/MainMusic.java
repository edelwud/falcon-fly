package com.falconfly.config;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainMusic {
    private Map<String, Media> music = new HashMap<String, Media>();
    public static MainMusic instance;
    private Media musicMedia;
    public static MediaPlayer mediaPlayer;


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

    public void getRandomMediaPlayer() {
        musicMedia = this.getRandomMedia();

        mediaPlayer = new MediaPlayer(musicMedia);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
        mediaPlayer.setOnEndOfMedia(()-> {
            mediaPlayer.stop();
            this.getRandomMediaPlayer();
        });
    }
    public static MainMusic getInstance() {
        return instance;
    }

}
