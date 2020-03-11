package com.falconfly.engine;

import com.falconfly.config.MainGlobals;

import static org.lwjgl.opengl.GL11.*;

public class Engine {
    public static int WIDTH = 640;
    public static int HEIGHT = 360;
    public static final String TITLE = "Falcon Fly";

    private EngineWindow window;

    public void run() {
        WIDTH = MainGlobals.WIDTH;
        HEIGHT = MainGlobals.HEIGHT;
        this.init();
    }

    public void init() {
        window = new EngineWindow(WIDTH, HEIGHT, TITLE);
        window.create();
        this.update();
    }

    public void update() {
        glClearColor(1.0f, 0.0f, 0.5f, 0.0f);

        while (!window.isCloseRequest()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            this.window.update();
        }

        this.window.destroy();
    }

    public EngineWindow getWindow() {
        return window;
    }
}
