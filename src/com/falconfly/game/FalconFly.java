package com.falconfly.game;

import com.falconfly.engine.IGameLogic;
import com.falconfly.engine.EngineWindow;
import com.falconfly.engine.TextRenderer;
import com.falconfly.engine.input.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.stb.STBTTBakedChar;

import static org.lwjgl.glfw.GLFW.*;

public class FalconFly implements IGameLogic {

	private int dx = 0;
	private int dy = 0;

	private int x = 0;
	private int y = 0;

	private int step = 5;

	private final Renderer renderer;

	private STBTTBakedChar.Buffer cdata;
	private final TextRenderer textRenderer;

	public FalconFly() {
		renderer = new Renderer();
		textRenderer = new TextRenderer();
	}

	@Override
	public void init() throws Exception {
		renderer.init();
		textRenderer.fontSize = 120;
		cdata = textRenderer.Init();
	}

	@Override
	public void input(EngineWindow window) {
		if (Keyboard.keyDown(GLFW_KEY_UP)) {
			dy = -step;
		} else if (Keyboard.keyDown(GLFW_KEY_DOWN)) {
			dy = step;
		} else {
			dy = 0;
		}

		if (Keyboard.keyDown(GLFW_KEY_LEFT)) {
			dx = -step;
		} else if (Keyboard.keyDown(GLFW_KEY_RIGHT)) {
			dx = step;
		} else {
			dx = 0;
		}

		if (Keyboard.keyPressed(GLFW_KEY_ESCAPE)) {
			glfwSetWindowShouldClose(window.id, true);
		}
	}

	@Override
	public void update(float interval) {
		x += dx;
		y += dy;
	}

	@Override
	public void render(EngineWindow window) {
		renderer.render(window);
		textRenderer.setTextColor(255, 0, 0);
		float width = textRenderer.stringWidth("ZDAROVA", 0, 7);
		textRenderer.DrawString((float) ((window.getWidth() - width) / 2 + x), (float) (window.getHeight() / 2 + y), "ZDAROVA", cdata);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
	}

}