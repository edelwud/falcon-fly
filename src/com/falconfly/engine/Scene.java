package com.falconfly.engine;

import java.util.Vector;

public class Scene {

    private Vector<GameItem> gameItems;

    private SkyBox skyBox;

    private SceneLight sceneLight;

    public Vector<GameItem> getGameItems() {
        return gameItems;
    }

    public void setGameItems(Vector<GameItem> gameItems) {
        this.gameItems = gameItems;
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