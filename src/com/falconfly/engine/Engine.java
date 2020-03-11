package com.falconfly.engine;

import static org.lwjgl.opengl.GL11.*;

public class Engine {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;
    public static final String TITLE = "Falcon Fly";

    private EngineWindow window;

    public void run() {
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
