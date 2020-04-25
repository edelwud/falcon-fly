package com.falconfly.game;

import com.falconfly.engine.GameItem;
import com.falconfly.engine.TextRenderer;
import com.falconfly.engine.graph.Camera;
import com.falconfly.engine.graph.Mesh;
import com.falconfly.engine.graph.Transformation;
import com.falconfly.engine.input.FileReader;
import com.falconfly.menu.MenuStorageLoader;
import org.joml.Matrix4f;
import org.lwjgl.stb.STBTTBakedChar;
import org.lwjgl.system.MemoryUtil;
import com.falconfly.engine.Utils;
import com.falconfly.engine.EngineWindow;
import com.falconfly.engine.graph.ShaderProgram;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Renderer {

	private int vboId;

	private int vaoId;

	private static final float FOV = (float) Math.toRadians(60.0f);

	private static final float Z_NEAR = 0.01f;

	private static final float Z_FAR = 1000.f;

	private final Transformation transformation;

	private ShaderProgram shaderProgram;

	public Renderer() {
		transformation = new Transformation();
	}

	public void init() throws Exception {
		shaderProgram = new ShaderProgram();

		Charset charset = StandardCharsets.ISO_8859_1;

		ByteBuffer vertex = new FileReader().getResource(new MenuStorageLoader().Load("shaders").get(1).substring(8), 512);
		String vertexContent = charset.decode(vertex).toString();

		ByteBuffer fragment = new FileReader().getResource(new MenuStorageLoader().Load("shaders").get(0).substring(8), 512);
		String fragmentContent = charset.decode(fragment).toString();

		shaderProgram.createVertexShader(vertexContent);
		shaderProgram.createFragmentShader(fragmentContent);
		shaderProgram.link();

		// Create uniforms for world and projection matrices
		shaderProgram.createUniform("projectionMatrix");
		shaderProgram.createUniform("modelViewMatrix");
		shaderProgram.createUniform("texture_sampler");
		// Create uniform for default colour and the flag that controls it
		shaderProgram.createUniform("colour");
		shaderProgram.createUniform("useColour");
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void render(EngineWindow window, GameItem[] gameItems, Camera camera) {
		clear();

		shaderProgram.bind();
		// Update projection Matrix
		Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
		shaderProgram.setUniform("projectionMatrix", projectionMatrix);

		Matrix4f worldMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(), Z_NEAR, Z_FAR);
		shaderProgram.setUniform("modelViewMatrix", projectionMatrix);

		// Update view Matrix
		Matrix4f viewMatrix = transformation.getViewMatrix(camera);

		shaderProgram.setUniform("texture_sampler", 0);
		// Render each gameItem
		for (GameItem gameItem : gameItems) {
			Mesh mesh = gameItem.getMesh();
			// Set model view matrix for this item
			Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
			shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
			// Render the mesh for this game item
			//shaderProgram.setUniform("colour", mesh.getColour());
			shaderProgram.setUniform("useColour", mesh.isTextured() ? 0 : 1);
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