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
import java.util.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13C.GL_TEXTURE2;
import static org.lwjgl.opengl.GL13C.glActiveTexture;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER;
import static org.lwjgl.opengl.GL30.glBindFramebuffer;

public class Renderer {

	private int vboId;

	private int vaoId;

	private static final float FOV = (float) Math.toRadians(60.0f);

	private static final float Z_NEAR = 0.01f;

	private static final float Z_FAR = 1000.f;

	private static final int MAX_POINT_LIGHTS = 5;

	private static final int MAX_SPOT_LIGHTS = 5;

	private float specularPower;

	private ShadowMap shadowMap;

	private final Transformation transformation;

	private ShaderProgram depthShaderProgram;

	private ShaderProgram sceneShaderProgram;

	private ShaderProgram skyBoxShaderProgram;

	public Renderer() {
		transformation = new Transformation();
		specularPower = 10f;
	}

	public void init() throws Exception {
		shadowMap = new ShadowMap();

		setupDepthShader();
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

		// Create uniforms for projection matrix
		skyBoxShaderProgram.createUniform("projectionMatrix");
		skyBoxShaderProgram.createUniform("modelViewMatrix");
		skyBoxShaderProgram.createUniform("texture_sampler");
		skyBoxShaderProgram.createUniform("ambientLight");
	}

	private void setupDepthShader() throws Exception {
		depthShaderProgram = new ShaderProgram();

		Charset charset = StandardCharsets.ISO_8859_1;

		ByteBuffer vertex = new FileReader().getResource(new MenuStorageLoader().Load("shaders/depth").get(1).substring(8), 512 * 512);
		String vertexContent = charset.decode(vertex).toString();

		ByteBuffer fragment = new FileReader().getResource(new MenuStorageLoader().Load("shaders/depth").get(0).substring(8), 512 * 512);
		String fragmentContent = charset.decode(fragment).toString();

		depthShaderProgram.createVertexShader(vertexContent);
		depthShaderProgram.createFragmentShader(fragmentContent);
		depthShaderProgram.link();

		depthShaderProgram.createUniform("orthoProjectionMatrix");
		depthShaderProgram.createUniform("modelLightViewMatrix");
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

		// Create uniforms for modelView and projection matrices
		sceneShaderProgram.createUniform("projectionMatrix");
		sceneShaderProgram.createUniform("modelViewMatrix");
		sceneShaderProgram.createUniform("texture_sampler");
		sceneShaderProgram.createUniform("normalMap");
		// Create uniform for material
		sceneShaderProgram.createMaterialUniform("material");
		// Create lighting related uniforms
		sceneShaderProgram.createUniform("specularPower");
		sceneShaderProgram.createUniform("ambientLight");
		sceneShaderProgram.createPointLightListUniform("pointLights", MAX_POINT_LIGHTS);
		sceneShaderProgram.createSpotLightListUniform("spotLights", MAX_SPOT_LIGHTS);
		sceneShaderProgram.createDirectionalLightUniform("directionalLight");

		// Create uniforms for shadow mapping
		sceneShaderProgram.createUniform("shadowMap");
		sceneShaderProgram.createUniform("orthoProjectionMatrix");
		sceneShaderProgram.createUniform("modelLightViewMatrix");
	}

	private void renderSkyBox(EngineWindow window, Camera camera, Scene scene) {
		SkyBox skyBox = scene.getSkyBox();
		if (skyBox != null) {
			skyBoxShaderProgram.bind();

			skyBoxShaderProgram.setUniform("texture_sampler", 0);

			Matrix4f projectionMatrix = transformation.getProjectionMatrix();
			skyBoxShaderProgram.setUniform("projectionMatrix", projectionMatrix);
			Matrix4f viewMatrix = transformation.getViewMatrix();
			viewMatrix.m30(0);
			viewMatrix.m31(0);
			viewMatrix.m32(0);
			Matrix4f modelViewMatrix = transformation.buildModelViewMatrix(skyBox, viewMatrix);
			skyBoxShaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
			skyBoxShaderProgram.setUniform("ambientLight", scene.getSceneLight().getSkyBoxLight());

			scene.getSkyBox().getMesh().render();

			skyBoxShaderProgram.unbind();
		}
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void render(EngineWindow window, Camera camera, Scene scene) {
		clear();

		renderDepthMap(window, camera, scene);

		glViewport(0, 0, window.getWidth(), window.getHeight());

		// Update projection and view atrices once per render cycle
		transformation.updateProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
		transformation.updateViewMatrix(camera);

		renderScene(window, camera, scene);

		renderSkyBox(window, camera, scene);

	}

	private void renderDepthMap(EngineWindow window, Camera camera, Scene scene) {
		// Setup view port to match the texture size
		glBindFramebuffer(GL_FRAMEBUFFER, shadowMap.getDepthMapFBO());
		glViewport(0, 0, ShadowMap.SHADOW_MAP_WIDTH, ShadowMap.SHADOW_MAP_HEIGHT);
		glClear(GL_DEPTH_BUFFER_BIT);

		depthShaderProgram.bind();

		DirectionalLight light = scene.getSceneLight().getDirectionalLight();
		Vector3f lightDirection = light.getDirection();

		float lightAngleX = (float)Math.toDegrees(Math.acos(lightDirection.z));
		float lightAngleY = (float)Math.toDegrees(Math.asin(lightDirection.x));
		float lightAngleZ = 0;
		Matrix4f lightViewMatrix = transformation.updateLightViewMatrix(new Vector3f(lightDirection).mul(light.getShadowPosMult()), new Vector3f(lightAngleX, lightAngleY, lightAngleZ));
		DirectionalLight.OrthoCoords orthCoords = light.getOrthoCoords();
		Matrix4f orthoProjMatrix = transformation.updateOrthoProjectionMatrix(orthCoords.left, orthCoords.right, orthCoords.bottom, orthCoords.top, orthCoords.near, orthCoords.far);

		depthShaderProgram.setUniform("orthoProjectionMatrix", orthoProjMatrix);
		Vector<GameItem> gameItems = scene.getGameItems();
		for (GameItem gameItem : gameItems) {
			List<GameItem> gameItemList = new LinkedList<>();
			gameItemList.add(gameItem);
			gameItem.getMesh().renderList(gameItemList, (GameItem item) -> {
				Matrix4f modelLightViewMatrix = transformation.buildModelViewMatrix(gameItem, lightViewMatrix);
				depthShaderProgram.setUniform("modelLightViewMatrix", modelLightViewMatrix);
			});
		}

		// Unbind
		depthShaderProgram.unbind();
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
	}

	public void renderScene(EngineWindow window, Camera camera, Scene scene) {
		sceneShaderProgram.bind();

		Matrix4f projectionMatrix = transformation.getProjectionMatrix();
		sceneShaderProgram.setUniform("projectionMatrix", projectionMatrix);
		Matrix4f orthoProjMatrix = transformation.getOrthoProjectionMatrix();
		sceneShaderProgram.setUniform("orthoProjectionMatrix", orthoProjMatrix);
		Matrix4f lightViewMatrix = transformation.getLightViewMatrix();

		Matrix4f viewMatrix = transformation.getViewMatrix();

		SceneLight sceneLight = scene.getSceneLight();
		renderLights(viewMatrix, sceneLight);

		sceneShaderProgram.setUniform("texture_sampler", 0);
		sceneShaderProgram.setUniform("normalMap", 1);
		sceneShaderProgram.setUniform("shadowMap", 2);

		// Render each mesh with the associated game Items
		Vector<GameItem> gameItems = scene.getGameItems();
		for (GameItem gameItem : gameItems) {
			sceneShaderProgram.setUniform("material", gameItem.getMesh().getMaterial());
			glActiveTexture(GL_TEXTURE2);
			glBindTexture(GL_TEXTURE_2D, shadowMap.getDepthMapTexture().getId());
			List<GameItem> gameItemList = new LinkedList<>();
			gameItemList.add(gameItem);
			gameItem.getMesh().renderList(gameItemList, (GameItem item) -> {
				Matrix4f modelViewMatrix = transformation.buildModelViewMatrix(gameItem, viewMatrix);
				sceneShaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
				Matrix4f modelLightViewMatrix = transformation.buildModelLightViewMatrix(gameItem, lightViewMatrix);
				sceneShaderProgram.setUniform("modelLightViewMatrix", modelLightViewMatrix);
			});
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