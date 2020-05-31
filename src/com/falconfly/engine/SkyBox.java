package com.falconfly.engine;

import com.falconfly.engine.graph.Material;
import com.falconfly.engine.graph.Mesh;
import com.falconfly.engine.graph.OBJLoader;
import com.falconfly.engine.graph.Texture;

public class SkyBox extends GameItem {

    public SkyBox(String objModel, String textureFile) throws Exception {
        super();
        Mesh skyBoxMesh = OBJLoader.loadMesh(objModel);
        Texture skyBoxtexture = new Texture(textureFile);
        skyBoxMesh.setMaterial(new Material(skyBoxtexture, 0.0f));
        setMesh(skyBoxMesh);
        setPosition(0, 0, 0);
    }
}
