package com.falconfly.config;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class MainMusic {

    private Map<String, Media> music = new HashMap<String, Media>();
    private Map<String, Media> musicGameplay = new HashMap<String, Media>();
    public static MainMusic instance;
    private Media musicMedia;
    public static MediaPlayer mediaPlayer;

    public MainMusic() {
        instance = this;
    }
    public void addMedia(String musicPath) throws IOException {
        try {
            Media tempMusic = new Media(musicPath);
            music.put(musicPath, tempMusic);
        } catch (Exception ex) {
            MainGlobals.LOGGER.logger.info(ex.toString());
            return;
        }
    }
    public void addGameplayMedia(String musicPath) throws IOException {
        try {
            Media tempMusic = new Media(musicPath);
            musicGameplay.put(musicPath, tempMusic);
        } catch (Exception ex) {
            MainGlobals.LOGGER.logger.info(ex.toString());
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

    public Media getGameplayRandomMedia() {
        Set<String> tempString = musicGameplay.keySet();
        Object[] musicPath = tempString.toArray();
        int randomNumber = new Random().nextInt(musicPath.length);
        Object randomPath = musicPath[randomNumber];
        return musicGameplay.get(randomPath);
    }

    public void getRandomMediaPlayer(boolean flag) {

       if(MainGlobals.musicSwitcher == 0)
           musicMedia = this.getRandomMedia();
       else
           musicMedia = this.getGameplayRandomMedia();

        mediaPlayer = new MediaPlayer(musicMedia);
        mediaPlayer.play(); //AutoPlay
        mediaPlayer.setMute(!flag);
        mediaPlayer.setVolume(MainGlobals.MUSIC_VOLUME);
        mediaPlayer.setOnEndOfMedia(()-> {
            mediaPlayer.stop();
            this.getRandomMediaPlayer(flag); //true
        });
    }
    public static MainMusic getInstance() {
        return instance;
    }

}
