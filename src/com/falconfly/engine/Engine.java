package com.falconfly.engine;

import com.falconfly.config.MainGlobals;
import com.falconfly.engine.input.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import java.util.Random;

import static java.lang.Thread.sleep;
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
        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        requestFrame(() -> {
            if (Keyboard.keyDown(GLFW.GLFW_KEY_RIGHT) || Keyboard.keyPressed(GLFW.GLFW_KEY_LEFT)
                    || Keyboard.keyPressed(GLFW.GLFW_KEY_UP) || Keyboard.keyPressed(GLFW.GLFW_KEY_DOWN)) {
                glClearColor(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat());
            }

            if (Keyboard.keyPressed(GLFW.GLFW_KEY_ESCAPE)) {
                GLFW.glfwSetWindowShouldClose(window.id, true);
            }

            Keyboard.handleKeyboardInput();
            GL11.glClear(GL_COLOR_BUFFER_BIT);
        });
    }

    public void requestFrame(Frame frame) {
        long secsPerFrame = 1000 / Frame.MAX_FRAME_RATE;

        while (!window.isCloseRequest()) {
            long startTime = System.currentTimeMillis(); // start fps catching
            long endLoopTime = System.currentTimeMillis();

            frame.Render();

            try {
                sleep(secsPerFrame - endLoopTime + startTime);
            } catch (Exception exc) {
                exc.printStackTrace();
            }

            this.window.update();
        }
        this.window.destroy();
    }

    public EngineWindow getWindow() {
        return window;
    }
}
