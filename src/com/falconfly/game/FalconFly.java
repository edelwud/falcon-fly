package com.falconfly.game;

import com.falconfly.engine.*;
import com.falconfly.engine.graph.*;
import com.falconfly.engine.input.Keyboard;
import com.falconfly.engine.input.MouseInput;
import com.falconfly.menu.style.MainDeath;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;

public class FalconFly implements IGameLogic {

	private Vector3f cameraInc;
	private Vector3f ambientLight;

	private final Renderer renderer;
	private Camera camera;
	private Scene scene;

	private PointLight pointLight;
	private DirectionalLight directionalLight;
	private float lightAngle;

	private Gameplay gameplay;

	private GameItem birdFrame_1;
	private GameItem birdFrame_2;
	private GameItem birdFrame_3;
	private GameItem birdFrame_4;
	private GameItem birdFrame_5;

	private final float CAMERA_POS_STEP = 0.01f;
	private final float MOUSE_SENSITIVITY = 0.1f;

	private float step = 0.05f;

	/* Acceleration states: */
	/* 0.0003f - easy */
	/* 0.0005f - normal */
	/* 0.0007f - hard */
	private static float acceleration;

	private int frameState = 4;
	private int frameTime = 0;
	private int skip = 0;

	private static class OLine {

		private Vector<GameItem> line;

		public OLine(Mesh mesh, int count, float posX, float distance, float posY, float posZ) {
			line = new Vector<>();

			for (int i = 0; i < count; i++) {
				line.add(new GameItem(mesh));
			}

			for (GameItem obj : line) {
				obj.setScale(1f);
				obj.setPosition(posX, posY, posZ);
				posX += distance;
			}
		}
	}

	private static class OSurface {

		private Vector<OLine> surface;
		private final int LENGTH = 128;

		public OSurface(Mesh mesh, int count, float posX, float distanceX, float posY, float posZ, float distanceZ) {
			surface = new Vector<>();

			for (int i = 0; i < LENGTH; i++, posZ -= distanceZ) {
				surface.add(new OLine(mesh, count, posX, distanceX, posY, posZ));
			}
		}
	}

	public void regeneration(int pos, int quantity, float dPosZ, float place) {
		Vector<GameItem> gameItems = scene.getGameItems();
		for (; pos < quantity; pos++) {
			while (gameItems.get(pos).getPosition().z >= 10) {
				float dif = gameItems.get(pos).getPosition().z + dPosZ;
				gameItems.get(pos).setPosition(
						gameItems.get(pos).getPosition().x,
						gameItems.get(pos).getPosition().y,
						place + dif);
			}
		}
	}

	public FalconFly() throws Exception {
		cameraInc = new Vector3f();
		renderer = new Renderer();
		camera = new Camera();
		cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
	}

	@Override
	public void init() throws Exception {
		if (GameSetup.getDif() == 1) {
			acceleration = 0.0003f;
		} else if (GameSetup.getDif() == 2) {
			acceleration = 0.0005f;
		} else {
			acceleration = 0.0007f;
		}

		renderer.init();

		scene = new Scene();

		float reflectance = 1f;

		Mesh grassMesh = OBJLoader.loadMesh("models/grass");
		Texture grassTexture = new Texture("models/grass");
		Material grassMaterial = new Material(grassTexture, reflectance);
		grassMesh.setMaterial(grassMaterial);

		OSurface grassSurface = new OSurface(grassMesh, 4, -6, 3, -5, -3, 3);
		Vector<GameItem> allGrass = new Vector<>();
		for (int i = 0; i < grassSurface.LENGTH; i++) {
			allGrass.add(grassSurface.surface.get(i).line.get(0));
			allGrass.add(grassSurface.surface.get(i).line.get(1));
			allGrass.add(grassSurface.surface.get(i).line.get(2));
			allGrass.add(grassSurface.surface.get(i).line.get(3));
		}

		Mesh fenceMesh = OBJLoader.loadMesh("models/fence");
		Texture fenceTexture = new Texture("models/fence");
		Material fenceMaterial = new Material(fenceTexture, reflectance);
		fenceMesh.setMaterial(fenceMaterial);

		OSurface fenceSurface = new OSurface(fenceMesh, 2, -7,14, -5, -3, 2);
		Vector<GameItem> allFence = new Vector<>();
		for (int i = 0; i < fenceSurface.LENGTH; i++) {
			allGrass.add(fenceSurface.surface.get(i).line.get(0));
			allGrass.add(fenceSurface.surface.get(i).line.get(1));
		}

		Mesh humansRoadMesh = OBJLoader.loadMesh("models/humansRoad");
		Texture humansRoadTexture = new Texture("models/humansRoad");
		Material humansRoadMaterial = new Material(humansRoadTexture, reflectance);
		humansRoadMesh.setMaterial(humansRoadMaterial);

		OSurface humansRoadSurface = new OSurface(humansRoadMesh, 2, -10, 16, -5, -3, 4);
		Vector<GameItem> allHumansRoad = new Vector<>();
		for (int i = 0; i < humansRoadSurface.LENGTH; i++) {
			allHumansRoad.add(humansRoadSurface.surface.get(i).line.get(0));
			allHumansRoad.add(humansRoadSurface.surface.get(i).line.get(1));
		}

		Mesh borderMesh = OBJLoader.loadMesh("models/border");
		Texture borderTexture = new Texture("models/border");
		Material borderMaterial = new Material(borderTexture, reflectance);
		borderMesh.setMaterial(borderMaterial);

		OSurface borderSurface = new OSurface(borderMesh, 2, -10, 20, -5, -3, 4);
		Vector<GameItem> border = new Vector<>();
		for (int i = 0; i < borderSurface.LENGTH; i++) {
			border.add(borderSurface.surface.get(i).line.get(0));
			border.add(borderSurface.surface.get(i).line.get(1));
		}

		Mesh roadMesh = OBJLoader.loadMesh("models/road");
		Texture roadTexture = new Texture("models/road");
		Material roadMaterial = new Material(roadTexture, reflectance);
		roadMesh.setMaterial(roadMaterial);

		OSurface roadSurface = new OSurface(roadMesh, 2, -14, 24, -5, -3, 4);
		Vector<GameItem> road = new Vector<>();
		for (int i = 0; i < roadSurface.LENGTH; i++) {
			road.add(roadSurface.surface.get(i).line.get(0));
			road.add(roadSurface.surface.get(i).line.get(1));
		}

		Vector<GameItem> houses = new Vector<>();

		for (int i = 0; i < 128; i++)
		{
			int randomHouse = FalconFlyRandom.getRandomNumber(0, 6);

			switch (randomHouse)
			{
				case 0: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_1");
					Texture houseTexture = new Texture("models/house_1");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem leftHouse = new GameItem(houseMesh);
					leftHouse.setPosition(-19, -5, -1);
					leftHouse.setRotation(90, 90, 90);

					houses.add(leftHouse);

					break;
				}
				case 1: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_2");
					Texture houseTexture = new Texture("models/house_2");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem leftHouse = new GameItem(houseMesh);
					leftHouse.setPosition(-19, -5, -1);
					leftHouse.setRotation(90, 90, 90);

					houses.add(leftHouse);

					break;
				}
				case 2: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_3");
					Texture houseTexture = new Texture("models/house_3");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem leftHouse = new GameItem(houseMesh);
					leftHouse.setPosition(-19, -5, -1);
					leftHouse.setRotation(90, 90, 90);

					houses.add(leftHouse);

					break;
				}
				case 3: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_4");
					Texture houseTexture = new Texture("models/house_4");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem leftHouse = new GameItem(houseMesh);
					leftHouse.setPosition(-19, -5, -1);
					leftHouse.setRotation(90, 90, 90);

					houses.add(leftHouse);

					break;
				}
				case 4: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_5");
					Texture houseTexture = new Texture("models/house_5");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem leftHouse = new GameItem(houseMesh);
					leftHouse.setPosition(-19, -5, -1);
					leftHouse.setRotation(90, 90, 90);

					houses.add(leftHouse);

					break;
				}
				default: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_6");
					Texture houseTexture = new Texture("models/house_6");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem leftHouse = new GameItem(houseMesh);
					leftHouse.setPosition(-19, -5, -1);
					leftHouse.setRotation(90, 90, 90);

					houses.add(leftHouse);

					break;
				}
			}

			randomHouse = FalconFlyRandom.getRandomNumber(0, 6);

			switch (randomHouse)
			{
				case 0: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_1");
					Texture houseTexture = new Texture("models/house_1");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem rightHouse = new GameItem(houseMesh);
					rightHouse.setPosition(19, -5, -1);
					rightHouse.setRotation(-90, -90, 90);

					houses.add(rightHouse);

					break;
				}
				case 1: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_2");
					Texture houseTexture = new Texture("models/house_2");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem rightHouse = new GameItem(houseMesh);
					rightHouse.setPosition(19, -5, -1);
					rightHouse.setRotation(-90, -90, 90);

					houses.add(rightHouse);

					break;
				}
				case 2: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_3");
					Texture houseTexture = new Texture("models/house_3");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem rightHouse = new GameItem(houseMesh);
					rightHouse.setPosition(19, -5, -1);
					rightHouse.setRotation(-90, -90, 90);

					houses.add(rightHouse);

					break;
				}
				case 3: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_4");
					Texture houseTexture = new Texture("models/house_4");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem rightHouse = new GameItem(houseMesh);
					rightHouse.setPosition(19, -5, -1);
					rightHouse.setRotation(-90, -90, 90);

					houses.add(rightHouse);

					break;
				}
				case 4: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_5");
					Texture houseTexture = new Texture("models/house_5");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem rightHouse = new GameItem(houseMesh);
					rightHouse.setPosition(19, -5, -1);
					rightHouse.setRotation(-90, -90, 90);

					houses.add(rightHouse);

					break;
				}
				default: {
					Mesh houseMesh = OBJLoader.loadMesh("models/house_6");
					Texture houseTexture = new Texture("models/house_6");
					Material houseMaterial = new Material(houseTexture, reflectance);
					houseMesh.setMaterial(houseMaterial);

					GameItem rightHouse = new GameItem(houseMesh);
					rightHouse.setPosition(19, -5, -1);
					rightHouse.setRotation(-90, -90, 90);

					houses.add(rightHouse);

					break;
				}
			}
		}


		Mesh birdMeshFrame_1  = OBJLoader.loadMesh("models/falcon/frame_1");
		Texture birdTextureFrame_1 = new Texture("models/falcon/frame_1");
		Material birdMaterialFrame_1 = new Material(birdTextureFrame_1, reflectance);
		birdMeshFrame_1.setMaterial(birdMaterialFrame_1);
		birdFrame_1 = new GameItem(birdMeshFrame_1);
		birdFrame_1.setPosition(-3, -5, -7);

		Mesh birdMeshFrame_2  = OBJLoader.loadMesh("models/falcon/frame_2");
		Texture birdTextureFrame_2 = new Texture("models/falcon/frame_2");
		Material birdMaterialFrame_2 = new Material(birdTextureFrame_2, reflectance);
		birdMeshFrame_2.setMaterial(birdMaterialFrame_2);
		birdFrame_2 = new GameItem(birdMeshFrame_2);

		Mesh birdMeshFrame_3  = OBJLoader.loadMesh("models/falcon/frame_3");
		Texture birdTextureFrame_3 = new Texture("models/falcon/frame_3");
		Material birdMaterialFrame_3 = new Material(birdTextureFrame_3, reflectance);
		birdMeshFrame_3.setMaterial(birdMaterialFrame_3);
		birdFrame_3 = new GameItem(birdMeshFrame_3);

		Mesh birdMeshFrame_4  = OBJLoader.loadMesh("models/falcon/frame_4");
		Texture birdTextureFrame_4 = new Texture("models/falcon/frame_4");
		Material birdMaterialFrame_4 = new Material(birdTextureFrame_4, reflectance);
		birdMeshFrame_4.setMaterial(birdMaterialFrame_4);
		birdFrame_4 = new GameItem(birdMeshFrame_4);

		Mesh birdMeshFrame_5  = OBJLoader.loadMesh("models/falcon/frame_5");
		Texture birdTextureFrame_5 = new Texture("models/falcon/frame_5");
		Material birdMaterialFrame_5 = new Material(birdTextureFrame_5, reflectance);
		birdMeshFrame_5.setMaterial(birdMaterialFrame_5);
		birdFrame_5 = new GameItem(birdMeshFrame_5);

		Vector<GameItem> gameItems = new Vector<>();
		gameItems.add(birdFrame_1);
		gameItems.addAll(allGrass);
		gameItems.addAll(allFence);
		gameItems.addAll(allHumansRoad);
		gameItems.addAll(border);
		gameItems.addAll(road);

		boolean leftRight = true;
		float dz = 10;
		for (GameItem obj : houses) {
			if (leftRight) {
				obj.setPosition(obj.getPosition().x, obj.getPosition().y, obj.getPosition().z - dz);
				leftRight = false;
			}
			else {
				obj.setPosition(obj.getPosition().x, obj.getPosition().y, obj.getPosition().z - dz);
				dz += 10;
				leftRight = true;
			}
			gameItems.add(obj);
		}

		scene.setGameItems(gameItems);
		gameplay = new Gameplay(scene.getGameItems());

		float skyBoxScale = 500.0f;

		// Setup  SkyBox
		SkyBox skyBox = new SkyBox("models/skybox", "models/skybox");
		skyBox.setScale(skyBoxScale);
		skyBox.setPosition(0, 0 ,100);
		scene.setSkyBox(skyBox);

		setupLights();
	}

	private void setupLights() {
		SceneLight sceneLight = new SceneLight();
		scene.setSceneLight(sceneLight);

		// Ambient Light
		sceneLight.setAmbientLight(new Vector3f(1.0f, 1.0f, 1.0f));
		sceneLight.setSkyBoxLight(new Vector3f(1.0f, 1.0f, 1.0f));

		// Directional Light
		float lightIntensity = 0.3f;
		Vector3f lightDirection = new Vector3f(0, 1, 1);
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1, 1, 1), lightDirection, lightIntensity);
		directionalLight.setShadowPosMult(2);
		directionalLight.setOrthoCords(-10.0f, 10.0f, -10.0f, 10.0f, -1.0f, 200.0f);
		sceneLight.setDirectionalLight(directionalLight);
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
		if (Keyboard.keyPressed(GLFW_KEY_A) && this.skip == 0) {
			this.gameplay.goToLeft();
			this.skip = 5;
			//cameraInc.x = -1;
		} else if (Keyboard.keyPressed(GLFW_KEY_D) && this.skip == 0) {
			this.gameplay.goToRight();
			this.skip = 5;
			//cameraInc.x = 1;
		}
		if (Keyboard.keyPressed(GLFW_KEY_Z)) {
			cameraInc.y = -1;
		} else if (Keyboard.keyPressed(GLFW_KEY_X)) {
			cameraInc.y = 1;
		}

		Keyboard.handleKeyboardInput();
	}

	@Override
	public void update(float interval, MouseInput mouseInput, Engine engine) throws Exception {
		// Update camera position

		if(this.gameplay.isCollisionWithEnemy()) {
			System.out.println("The collision!");
			MainDeath windowDeath = new MainDeath(this.gameplay.getPath(), this.gameplay.getEnemyMovement(), engine);
			windowDeath.display();
			GameSetup.setIsLose(true);
			return;
		}
		camera.setPosition(0, 4.3f, 6.2f);
		//camera.movePosition(0.1f * cameraInc.x, 0.1f * cameraInc.y, 0.1f * cameraInc.z);

		Vector<GameItem> gameItems = scene.getGameItems();

		boolean firstFlag = true;
		for (GameItem obj : gameItems) {
			if(firstFlag) {
				firstFlag = false;
				continue;
			}
			obj.setPosition(obj.getPosition().x, obj.getPosition().y, obj.getPosition().z + step);
		}

		regeneration(0 + 1, 128 * 4, 3, -384);
		regeneration(128 * 4 + 1, 128 * 6, 2, -256);
		regeneration(128 * 6 + 1, 128 * 8, 4, -512);
		regeneration(128 * 8 + 1, 128 * 10, 4, -512);
		regeneration(128 * 10 + 1, 128 * 12, 4, -512);
		regeneration(128 * 12 + 1, 128 * 14, 10, -1280);
		if(gameplay.getPlayerPosition() == 1)
			gameItems.get(0).setPosition(-7.55f, gameItems.get(0).getPosition().y, gameItems.get(0).getPosition().z);
		else if(gameplay.getPlayerPosition() == 2)
			gameItems.get(0).setPosition(-2.55f, gameItems.get(0).getPosition().y, gameItems.get(0).getPosition().z);
		else
			gameItems.get(0).setPosition(2.45f, gameItems.get(0).getPosition().y, gameItems.get(0).getPosition().z);
		step += acceleration;

		// Update camera based on mouse
		if (mouseInput.isRightButtonPressed()) {
			Vector2f rotVec = mouseInput.getDisplVec();
			camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
		}
		if(this.skip != 0)
			this.skip--;
		this.gameplay.update(step - acceleration);

		if (frameState == 0) {
			switch (frameTime) {
				case 0:
				case 5:
					birdFrame_1.setPosition(gameItems.get(0).getPosition().x, gameItems.get(0).getPosition().y, gameItems.get(0).getPosition().z);
					gameItems.remove(0);
					gameItems.add(0, birdFrame_1);
					break;
				case 1:
				case 4:
					birdFrame_2.setPosition(gameItems.get(0).getPosition().x, gameItems.get(0).getPosition().y, gameItems.get(0).getPosition().z);
					gameItems.remove(0);
					gameItems.add(0, birdFrame_2);
					break;
				case 2:
					birdFrame_3.setPosition(gameItems.get(0).getPosition().x, gameItems.get(0).getPosition().y, gameItems.get(0).getPosition().z);
					gameItems.remove(0);
					gameItems.add(0, birdFrame_3);
					break;
				case 3:
					break;
				case 6:
				case 8:
					birdFrame_4.setPosition(gameItems.get(0).getPosition().x, gameItems.get(0).getPosition().y, gameItems.get(0).getPosition().z);
					gameItems.remove(0);
					gameItems.add(0, birdFrame_4);
					break;
				case 7:
					birdFrame_5.setPosition(gameItems.get(0).getPosition().x, gameItems.get(0).getPosition().y, gameItems.get(0).getPosition().z);
					gameItems.remove(0);
					gameItems.add(0, birdFrame_5);
					break;
			}
			frameTime++;
			if (frameTime == 9)
				frameTime = 0;
			frameState = 4;
		}
		frameState--;
	}

	@Override
	public void render(EngineWindow window) {
		renderer.render(window, camera, scene);
	}

	@Override
	public void cleanup() {
		renderer.cleanup();
		Vector<GameItem> gameItems = scene.getGameItems();
		for (GameItem gameItem : gameItems) {
			gameItem.getMesh().cleanUp();
		}
	}

}