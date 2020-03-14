package com.falconfly.engine;

import com.falconfly.config.MainGlobals;
import com.falconfly.engine.input.Keyboard;
import com.falconfly.menu.MenuStorageLoader;
import org.lwjgl.stb.STBTTBakedChar;

import static java.lang.Thread.sleep;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.*;

public class Engine {
    public static int WIDTH = 1920;
    public static int HEIGHT = 1080;
    public static final String TITLE = "Falcon Fly";

    private EngineWindow window;
    private TextRenderer textRenderer;

    public void run() {
    	textRenderer = new TextRenderer();
        this.init();
    }

    public void init() {
        window = new EngineWindow(WIDTH, HEIGHT, TITLE);
        window.create();
        this.update();
    }

    public void update() {
		STBTTBakedChar.Buffer cdata = textRenderer.Init();

		requestFrame((int lastFrameRate) -> {
			glClear(GL_COLOR_BUFFER_BIT);

			if (Keyboard.keyPressed(GLFW_KEY_ESCAPE)) {
				glfwSetWindowShouldClose(window.id, true);
			}

			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0.0, window.getWidth(), window.getHeight(), 0.0, -1.0, 1.0);
			glMatrixMode(GL_MODELVIEW);

			glClear(GL_COLOR_BUFFER_BIT);
			glColor3f(255f / 255f, 0f / 255f, 0f / 255f);

			textRenderer.DrawString(0, 0, Integer.toString(lastFrameRate) + " FPS\nokey", cdata);

			Keyboard.handleKeyboardInput();
		});
    }

    public void requestFrame(Frame frame) {
        long secsPerFrame = 1000 / Frame.MAX_FRAME_RATE;
		int lastFrameRate = Frame.MAX_FRAME_RATE;

        while (!window.isCloseRequest()) {
            long startTime = System.currentTimeMillis(); // start fps catching

			frame.Render(lastFrameRate);

			/*
				FPS = sec / duration
				duration = (end - start) = sec / FPS
			 */

			try {
				long endLoopTime = System.currentTimeMillis();
				long duration = endLoopTime - startTime;

				if (duration < secsPerFrame)
					sleep(secsPerFrame - duration);

				long normalizedDelay = System.currentTimeMillis() - startTime;
				lastFrameRate = (int) (1000 / normalizedDelay);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
			this.window.update();

		}
		glDisableClientState(GL_VERTEX_ARRAY);
		this.window.destroy();
    }

    public EngineWindow getWindow() {
        return window;
    }
}
