package com.falconfly.game;

import com.falconfly.engine.IGameLogic;
import com.falconfly.engine.EngineWindow;
import com.falconfly.engine.TextRenderer;
import com.falconfly.engine.graph.Mesh;
import com.falconfly.engine.input.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.stb.STBTTBakedChar;

import static org.lwjgl.glfw.GLFW.*;

public class FalconFly implements IGameLogic {
	private int g = 10;

	private int dx = 0;
	private int dy = 0;

	private int x = 0;
	private int y = 0;

	private int step = 50;

	private final Renderer renderer;

	private Mesh mesh;

	private STBTTBakedChar.Buffer cdata;
	private final TextRenderer textRenderer;

	public FalconFly() {
		renderer = new Renderer();
		textRenderer = new TextRenderer();
	}

	@Override
	public void init() throws Exception {
		renderer.init();

		float[] positions = new float[]{
				-0.5f, 0.5f, 0.5f,
				-0.5f, -0.5f, 0.5f,
				0.5f, -0.5f, 0.5f,
				0.5f, 0.5f, 0.5f,
		};

		float[] colours = new float[]{
				0.5f, 0.5f, 0.0f,
				0.0f, 0.5f, 0.5f,
				0.5f, 0.0f, 0.5f,
				0.5f, 0.5f, 0.0f,
		};

		int[] indices = new int[]{
				0, 1, 3, 3, 1, 2,
		};

		mesh = new Mesh(positions, colours, indices);

		textRenderer.fontSize = 120;
		cdata = textRenderer.Init();
	}

	@Override
	public void input(EngineWindow window) {
		if (Keyboard.keyDown(GLFW_KEY_UP)) {
			if (y > (window.getHeight() - 60)) dy = 0;
			else dy = step - g;
		} else {
			if (y > 0) dy = -g;
			else dy = 0;
		}

		if (Keyboard.keyDown(GLFW_KEY_LEFT)) {
			if (y == 0) dx = -1;
			else dx = 0;
		} else if (Keyboard.keyDown(GLFW_KEY_RIGHT)) {
			if (y == 0) dx = 1;
			else dx = 0;
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
		renderer.render(window, mesh);
		textRenderer.setTextColor(100, 100, 200);
		float width = textRenderer.stringWidth("ZDAROVA", 0, 7);
		textRenderer.DrawString(x, window.getHeight() - 60 - y, "ZDAROVA", cdata);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
	}

}