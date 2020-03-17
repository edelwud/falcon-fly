package com.falconfly.game;

import com.falconfly.engine.GameItem;
import com.falconfly.engine.IGameLogic;
import com.falconfly.engine.EngineWindow;
import com.falconfly.engine.TextRenderer;
import com.falconfly.engine.graph.Mesh;
import com.falconfly.engine.input.Keyboard;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class FalconFly implements IGameLogic {
	private int displxInc = 0;

	private int displyInc = 0;

	private int displzInc = 0;

	private int scaleInc = 0;

	private GameItem[] gameItems;

	private final Renderer renderer;
	private final TextRenderer textRenderer;

	private Mesh mesh;

	public FalconFly() {
		renderer = new Renderer();
		textRenderer = new TextRenderer();
		textRenderer.SetFontSize(50);
	}

	@Override
	public void init() throws Exception {
		textRenderer.Init();
		renderer.init();

		// Create the Mesh
		float[] positions = new float[]{
				-0.5f,  0.5f,  0.5f,
				-0.5f, -0.5f,  0.5f,
				0.5f, -0.5f,  0.5f,
				0.5f,  0.5f,  0.5f,
		};
		float[] colours = new float[]{
				0.5f, 0.0f, 0.0f,
				0.0f, 0.5f, 0.0f,
				0.0f, 0.0f, 0.5f,
				0.0f, 0.5f, 0.5f,
		};
		int[] indices = new int[]{
				0, 1, 3, 3, 1, 2,
		};
		Mesh mesh = new Mesh(positions, colours, indices);
		GameItem gameItem = new GameItem(mesh);
		gameItem.setPosition(0, 0, -2);
		gameItems = new GameItem[] { gameItem };
	}

	@Override
	public void input(EngineWindow window) {
		displyInc = 0;
		displxInc = 0;
		displzInc = 0;
		scaleInc = 0;
		if (Keyboard.keyPressed(GLFW_KEY_UP)) {
			displyInc = 1;
		} else if (Keyboard.keyPressed(GLFW_KEY_DOWN)) {
			displyInc = -1;
		} else if (Keyboard.keyPressed(GLFW_KEY_LEFT)) {
			displxInc = -1;
		} else if (Keyboard.keyPressed(GLFW_KEY_RIGHT)) {
			displxInc = 1;
		} else if (Keyboard.keyPressed(GLFW_KEY_A)) {
			displzInc = -1;
		} else if (Keyboard.keyPressed(GLFW_KEY_Q)) {
			displzInc = 1;
		} else if (Keyboard.keyPressed(GLFW_KEY_Z)) {
			scaleInc = -1;
		} else if (Keyboard.keyPressed(GLFW_KEY_X)) {
			scaleInc = 1;
		}

		if (Keyboard.keyPressed(GLFW_KEY_ESCAPE)) {
			glfwSetWindowShouldClose(window.id, true);
		}
	}

	@Override
	public void update(float interval) {
		for (GameItem gameItem : gameItems) {
			// Update position
			Vector3f itemPos = gameItem.getPosition();
			float posx = itemPos.x + displxInc * 0.01f;
			float posy = itemPos.y + displyInc * 0.01f;
			float posz = itemPos.z + displzInc * 0.01f;
			gameItem.setPosition(posx, posy, posz);

			// Update scale
			float scale = gameItem.getScale();
			scale += scaleInc * 0.05f;
			if (scale < 0) {
				scale = 0;
			}
			gameItem.setScale(scale);

			// Update rotation angle
			float rotation = gameItem.getRotation().z + 1.5f;
			if (rotation > 360) {
				rotation = 0;
			}
			gameItem.setRotation(0, 0, rotation);
		}
	}

	@Override
	public void render(EngineWindow window) {
		renderer.render(window, gameItems);
		textRenderer.DrawString(10, 10, "Neploho");
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
		for (GameItem gameItem : gameItems) {
			gameItem.getMesh().cleanUp();
		}
	}

}