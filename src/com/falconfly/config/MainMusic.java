package com.falconfly.config;

import com.falconfly.menu.MainEnvironmentLoader;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

public class MainMusic {

    private static final Logger LOGGER = Logger.getLogger(MainMusic.class.getSimpleName());

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
        } catch (Exception ex) {
            LOGGER.info(ex.toString());
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
