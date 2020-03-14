package com.falconfly.game;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;
import com.falconfly.engine.IGameLogic;
import com.falconfly.engine.EngineWindow;
import com.falconfly.engine.input.Keyboard;
import org.lwjgl.glfw.GLFW;

public class FalconFly implements IGameLogic {

	private int direction = 0;

	private float color = 0.0f;

	private final Renderer renderer;

	public FalconFly() {
		renderer = new Renderer();
	}

	@Override
	public void init() throws Exception {
		renderer.init();
	}

	@Override
	public void input(EngineWindow window) {
		if (Keyboard.keyPressed(GLFW_KEY_UP)) {
			System.out.println("OH");
		}
		Keyboard.handleKeyboardInput();
	}

	@Override
	public void update(float interval) {
		color += direction * 0.01f;
		if (color > 1) {
			color = 1.0f;
		} else if (color < 0) {
			color = 0.0f;
		}
	}

	@Override
	public void render(EngineWindow window) {
		window.setClearColor(color, color, color, 0.0f);
		renderer.render(window);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
	}

}