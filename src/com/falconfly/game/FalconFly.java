package com.falconfly.game;

import com.falconfly.engine.GameItem;
import com.falconfly.engine.IGameLogic;
import com.falconfly.engine.EngineWindow;
import com.falconfly.engine.TextRenderer;
import com.falconfly.engine.graph.Camera;
import com.falconfly.engine.graph.Mesh;
import com.falconfly.engine.graph.OBJLoader;
import com.falconfly.engine.graph.Texture;
import com.falconfly.engine.input.Keyboard;
import com.falconfly.engine.input.MouseInput;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.IOException;

import static org.lwjgl.glfw.GLFW.*;

public class FalconFly implements IGameLogic {

	private Vector3f cameraInc;

	private GameItem[] gameItems;

	private final Renderer renderer;

	private Camera camera;

	private Mesh mesh;

	private final float CAMERA_POS_STEP = 0.01f;
	private final float MOUSE_SENSITIVITY = 0.1f;

	public FalconFly() throws IOException {
		cameraInc = new Vector3f();
		renderer = new Renderer();
		camera = new Camera();
	}

	@Override
	public void init() throws Exception {
		renderer.init();

		Mesh mesh = OBJLoader.loadMesh("models/grass");
		Texture texture = new Texture("models/grass");
		mesh.setTexture(texture);
		GameItem gameItem = new GameItem(mesh);
		gameItem.setScale(1.5f);
		gameItem.setPosition(0, -5, -2);

		Mesh meshOne = OBJLoader.loadMesh("models/grass");
		Texture textureOne = new Texture("models/grass");
		meshOne.setTexture(textureOne);
		GameItem gameItemOne = new GameItem(meshOne);
		gameItemOne.setScale(1.5f);
		gameItemOne.setPosition(-4, -5, -2);

		Mesh meshTwo = OBJLoader.loadMesh("models/grass");
		Texture textureTwo = new Texture("models/grass");
		meshTwo.setTexture(textureTwo);
		GameItem gameItemTwo = new GameItem(meshTwo);
		gameItemTwo.setScale(1.5f);
		gameItemTwo.setPosition(-4, -5, -6);

		Mesh meshThree = OBJLoader.loadMesh("models/grass");
		Texture textureThree = new Texture("models/grass");
		meshThree.setTexture(textureThree);
		GameItem gameItemThree = new GameItem(meshThree);
		gameItemThree.setScale(1.5f);
		gameItemThree.setPosition(0, -5, -6);
		gameItems = new GameItem[]{gameItem, gameItemOne, gameItemTwo, gameItemThree};

	}

//	@Override
//	public void input(EngineWindow window) {
//		displyInc = 0;
//		displxInc = 0;
//		displzInc = 0;
//		scaleInc = 0;
//		if (Keyboard.keyPressed(GLFW_KEY_UP)) {
//			displyInc = 1;
//		} else if (Keyboard.keyPressed(GLFW_KEY_DOWN)) {
//			displyInc = -1;
//		} else if (Keyboard.keyPressed(GLFW_KEY_LEFT)) {
//			displxInc = -1;
//		} else if (Keyboard.keyPressed(GLFW_KEY_RIGHT)) {
//			displxInc = 1;
//		} else if (Keyboard.keyPressed(GLFW_KEY_A)) {
//			displzInc = -1;
//		} else if (Keyboard.keyPressed(GLFW_KEY_Q)) {
//			displzInc = 1;
//		} else if (Keyboard.keyPressed(GLFW_KEY_Z)) {
//			scaleInc = -1;
//		} else if (Keyboard.keyPressed(GLFW_KEY_X)) {
//			scaleInc = 1;
//		}
//
//		if (Keyboard.keyPressed(GLFW_KEY_ESCAPE)) {
//			glfwSetWindowShouldClose(window.id, true);
//		}
//	}

	@Override
	public void input(EngineWindow window, MouseInput mouseInput) {
		cameraInc.set(0, 0, 0);
		if (Keyboard.keyPressed(GLFW_KEY_W)) {
			cameraInc.z = -1;
		} else if (Keyboard.keyPressed(GLFW_KEY_S)) {
			cameraInc.z = 1;
		}
		if (Keyboard.keyPressed(GLFW_KEY_A)) {
			cameraInc.x = -1;
		} else if (Keyboard.keyPressed(GLFW_KEY_D)) {
			cameraInc.x = 1;
		}
		if (Keyboard.keyPressed(GLFW_KEY_Z)) {
			cameraInc.y = -1;
		} else if (Keyboard.keyPressed(GLFW_KEY_X)) {
			cameraInc.y = 1;
		}
	}

	@Override
	public void update(float interval, MouseInput mouseInput) {
		// Update camera position
		camera.movePosition(cameraInc.x * CAMERA_POS_STEP,
				cameraInc.y * CAMERA_POS_STEP,
				cameraInc.z * CAMERA_POS_STEP);

		// Update camera based on mouse
		if (mouseInput.isRightButtonPressed()) {
			Vector2f rotVec = mouseInput.getDisplVec();
			camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
		}
	}

	@Override
	public void render(EngineWindow window) {
		renderer.render(window, gameItems, camera);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
		for (GameItem gameItem : gameItems) {
			gameItem.getMesh().cleanUp();
		}
	}

}