package com.falconfly.engine;

import java.util.Vector;

public class Scene {
    private Vector<GameItem> gameItems;

    private SkyBox skyBox;

    private SceneLight sceneLight;

    public Scene() {
        gameItems = new Vector<>();
    }

    public Vector<GameItem> getGameItems() {
        return gameItems;
    }

    public void setGameItems(Vector<GameItem> gameItems) {
        this.gameItems = gameItems;
    }

    public void cleanup() {
    }

    public SkyBox getSkyBox() {
        return skyBox;
    }

    public void setSkyBox(SkyBox skyBox) {
        this.skyBox = skyBox;
    }

    public SceneLight getSceneLight() {
        return sceneLight;
    }

    public void setSceneLight(SceneLight sceneLight) {
        this.sceneLight = sceneLight;
    }
}