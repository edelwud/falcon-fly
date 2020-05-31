package com.falconfly.game;

import com.falconfly.engine.*;
import com.falconfly.engine.graph.*;
import com.falconfly.engine.input.FileReader;
import com.falconfly.menu.MenuStorageLoader;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

	private int vboId;

	private int vaoId;

	private static final float FOV = (float) Math.toRadians(60.0f);

	private static final float Z_NEAR = 0.01f;

	private static final float Z_FAR = 1000.f;

	private static final int MAX_POINT_LIGHTS = 5;

	private static final int MAX_SPOT_LIGHTS = 5;

	private float specularPower;

	private final Transformation transformation;

	private ShaderProgram sceneShaderProgram;

	private ShaderProgram skyBoxShaderProgram;

	public Renderer() {
		transformation = new Transformation();
		specularPower = 10f;
	}

	public void init() throws Exception {
		setupSkyBoxShader();
		setupSceneShader();
	}

	private void setupSkyBoxShader() throws Exception {
		skyBoxShaderProgram = new ShaderProgram();

		Charset charset = StandardCharsets.ISO_8859_1;

		ByteBuffer vertex = new FileReader().getResource(new MenuStorageLoader().Load("shaders/skybox").get(1).substring(8), 512 * 512);
		String vertexContent = charset.decode(vertex).toString();

		ByteBuffer fragment = new FileReader().getResource(new MenuStorageLoader().Load("shaders/skybox").get(0).substring(8), 512 * 512);
		String fragmentContent = charset.decode(fragment).toString();

		skyBoxShaderProgram.createVertexShader(vertexContent);
		skyBoxShaderProgram.createFragmentShader(fragmentContent);
		skyBoxShaderProgram.link();

		skyBoxShaderProgram.createUniform("projectionMatrix");
		skyBoxShaderProgram.createUniform("modelViewMatrix");
		skyBoxShaderProgram.createUniform("texture_sampler");
		skyBoxShaderProgram.createUniform("ambientLight");
	}

	private void setupSceneShader() throws Exception {

		sceneShaderProgram = new ShaderProgram();

		Charset charset = StandardCharsets.ISO_8859_1;

		ByteBuffer vertex = new FileReader().getResource(new MenuStorageLoader().Load("shaders/scene").get(1).substring(8), 512 * 512);
		String vertexContent = charset.decode(vertex).toString();

		ByteBuffer fragment = new FileReader().getResource(new MenuStorageLoader().Load("shaders/scene").get(0).substring(8), 512 * 512);
		String fragmentContent = charset.decode(fragment).toString();

		sceneShaderProgram.createVertexShader(vertexContent);
		sceneShaderProgram.createFragmentShader(fragmentContent);
		sceneShaderProgram.link();

		// Create uniforms for modelView and projection matrices and texture
		sceneShaderProgram.createUniform("projectionMatrix");
		sceneShaderProgram.createUniform("modelViewMatrix");
		sceneShaderProgram.createUniform("texture_sampler");
		// Create uniform for material
		sceneShaderProgram.createMaterialUniform("material");
		// Create lighting related uniforms
		sceneShaderProgram.createUniform("specularPower");
		sceneShaderProgram.createUniform("ambientLight");
		sceneShaderProgram.createPointLightListUniform("pointLights", MAX_POINT_LIGHTS);
		sceneShaderProgram.createSpotLightListUniform("spotLights", MAX_SPOT_LIGHTS);
		sceneShaderProgram.createDirectionalLightUniform("directionalLight");
	}

	private void renderSkyBox(EngineWindow window, Camera camera, Scene scene) {
		skyBoxShaderProgram.bind();

		skyBoxShaderProgram.setUniform("texture_sampler", 0);

		// Update projection Matrix
		Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
		skyBoxShaderProgram.setUniform("projectionMatrix", projectionMatrix);
		SkyBox skyBox = scene.getSkyBox();
		Matrix4f viewMatrix = transformation.getViewMatrix(camera);
		viewMatrix.m30(0);
		viewMatrix.m31(0);
		viewMatrix.m32(0);
		Matrix4f modelViewMatrix = transformation.getModelViewMatrix(skyBox, viewMatrix);
		skyBoxShaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
		skyBoxShaderProgram.setUniform("ambientLight", scene.getSceneLight().getAmbientLight());

		scene.getSkyBox().getMesh().render();

		skyBoxShaderProgram.unbind();
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void render(EngineWindow window, Camera camera, Scene scene) {
		clear();

		renderScene(window, camera, scene);

		renderSkyBox(window, camera, scene);

	}

	public void renderScene(EngineWindow window, Camera camera, Scene scene) {
		sceneShaderProgram.bind();

		// Update projection Matrix
		Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
		sceneShaderProgram.setUniform("projectionMatrix", projectionMatrix);

		// Update view Matrix
		Matrix4f viewMatrix = transformation.getViewMatrix(camera);

		SceneLight sceneLight = scene.getSceneLight();
		renderLights(viewMatrix, sceneLight);

		sceneShaderProgram.setUniform("texture_sampler", 0);
		// Render each gameItem
		Vector<GameItem> gameItems = scene.getGameItems();
		for (GameItem gameItem : gameItems) {
			Mesh mesh = gameItem.getMesh();
			// Set model view matrix for this item
			Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
			sceneShaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
			// Render the mesh for this game item
			sceneShaderProgram.setUniform("material", mesh.getMaterial());
			mesh.render();
		}

		sceneShaderProgram.unbind();
	}

	private void renderLights(Matrix4f viewMatrix, SceneLight sceneLight) {

		sceneShaderProgram.setUniform("ambientLight", sceneLight.getAmbientLight());
		sceneShaderProgram.setUniform("specularPower", specularPower);

		// Process Point Lights
		PointLight[] pointLightList = sceneLight.getPointLightList();
		int numLights = pointLightList != null ? pointLightList.length : 0;
		for (int i = 0; i < numLights; i++) {
			// Get a copy of the point light object and transform its position to view coordinates
			PointLight currPointLight = new PointLight(pointLightList[i]);
			Vector3f lightPos = currPointLight.getPosition();
			Vector4f aux = new Vector4f(lightPos, 1);
			aux.mul(viewMatrix);
			lightPos.x = aux.x;
			lightPos.y = aux.y;
			lightPos.z = aux.z;
			sceneShaderProgram.setUniform("pointLights", currPointLight, i);
		}

		// Process Spot Ligths
		SpotLight[] spotLightList = sceneLight.getSpotLightList();
		numLights = spotLightList != null ? spotLightList.length : 0;
		for (int i = 0; i < numLights; i++) {
			// Get a copy of the spot light object and transform its position and cone direction to view coordinates
			SpotLight currSpotLight = new SpotLight(spotLightList[i]);
			Vector4f dir = new Vector4f(currSpotLight.getConeDirection(), 0);
			dir.mul(viewMatrix);
			currSpotLight.setConeDirection(new Vector3f(dir.x, dir.y, dir.z));

			Vector3f lightPos = currSpotLight.getPointLight().getPosition();
			Vector4f aux = new Vector4f(lightPos, 1);
			aux.mul(viewMatrix);
			lightPos.x = aux.x;
			lightPos.y = aux.y;
			lightPos.z = aux.z;

			sceneShaderProgram.setUniform("spotLights", currSpotLight, i);
		}

		// Get a copy of the directional light object and transform its position to view coordinates
		DirectionalLight currDirLight = new DirectionalLight(sceneLight.getDirectionalLight());
		Vector4f dir = new Vector4f(currDirLight.getDirection(), 0);
		dir.mul(viewMatrix);
		currDirLight.setDirection(new Vector3f(dir.x, dir.y, dir.z));
		sceneShaderProgram.setUniform("directionalLight", currDirLight);
	}

	public void cleanup() {
		if (skyBoxShaderProgram != null) {
			skyBoxShaderProgram.cleanup();
		}
		if (sceneShaderProgram != null) {
			sceneShaderProgram.cleanup();
		}
	}
}