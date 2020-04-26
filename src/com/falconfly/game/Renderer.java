package com.falconfly.game;

import com.falconfly.engine.EngineWindow;
import com.falconfly.engine.GameItem;
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
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class Renderer {

	private int vboId;

	private int vaoId;

	private static final float FOV = (float) Math.toRadians(60.0f);

	private static final float Z_NEAR = 0.01f;

	private static final float Z_FAR = 1000.f;

	private float specularPower;

	private final Transformation transformation;

	private ShaderProgram shaderProgram;

	public Renderer() {
		transformation = new Transformation();
		specularPower = 10f;
	}

	public void init() throws Exception {
		shaderProgram = new ShaderProgram();

		Charset charset = StandardCharsets.ISO_8859_1;

		ByteBuffer vertex = new FileReader().getResource(new MenuStorageLoader().Load("shaders").get(1).substring(8), 512 * 512);
		String vertexContent = charset.decode(vertex).toString();

		ByteBuffer fragment = new FileReader().getResource(new MenuStorageLoader().Load("shaders").get(0).substring(8), 512 * 512);
		String fragmentContent = charset.decode(fragment).toString();

		shaderProgram.createVertexShader(vertexContent);
		shaderProgram.createFragmentShader(fragmentContent);
		shaderProgram.link();

		// Create uniforms for modelView and projection matrices and texture
		shaderProgram.createUniform("projectionMatrix");
		shaderProgram.createUniform("modelViewMatrix");
		shaderProgram.createUniform("texture_sampler");
		// Create uniform for material
		shaderProgram.createMaterialUniform("material");
		// Create lighting related uniforms
		shaderProgram.createUniform("specularPower");
		shaderProgram.createUniform("ambientLight");
		shaderProgram.createPointLightUniform("pointLight");
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void render(EngineWindow window,
					   Vector<GameItem> gameItems,
					   Camera camera,
					   Vector3f ambientLight,
					   PointLight pointLight) {
		clear();

		shaderProgram.bind();
		// Update projection Matrix
		Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
		shaderProgram.setUniform("projectionMatrix", projectionMatrix);

		Matrix4f worldMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
		shaderProgram.setUniform("modelViewMatrix", projectionMatrix);

		// Update view Matrix
		Matrix4f viewMatrix = transformation.getViewMatrix(camera);

		// Update Light Uniforms
		shaderProgram.setUniform("ambientLight", ambientLight);
		shaderProgram.setUniform("specularPower", specularPower);
		// Get a copy of the light object and transform its position to view coordinates
		PointLight currPointLight = new PointLight(pointLight);
		Vector3f lightPos = currPointLight.getPosition();
		Vector4f aux = new Vector4f(lightPos, 1);
		aux.mul(viewMatrix);
		lightPos.x = aux.x;
		lightPos.y = aux.y;
		lightPos.z = aux.z;
		shaderProgram.setUniform("pointLight", currPointLight);

		shaderProgram.setUniform("texture_sampler", 0);
		// Render each gameItem
		for (GameItem gameItem : gameItems) {
			Mesh mesh = gameItem.getMesh();
			// Set model view matrix for this item
			Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
			shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
			// Render the mesh for this game item
			//shaderProgram.setUniform("colour", mesh.getColour());
			shaderProgram.setUniform("material", mesh.getMaterial());
			mesh.render();
		}

		shaderProgram.unbind();
	}

	public void cleanup() {
		if (shaderProgram != null) {
			shaderProgram.cleanup();
		}

		glDisableVertexAttribArray(0);

		// Delete the VBO
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glDeleteBuffers(vboId);

		// Delete the VAO
		glBindVertexArray(0);
		glDeleteVertexArrays(vaoId);
	}
}