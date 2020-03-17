package com.falconfly.game;

import com.falconfly.engine.TextRenderer;
import com.falconfly.engine.graph.Mesh;
import com.falconfly.engine.input.FileReader;
import com.falconfly.menu.MenuStorageLoader;
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

	private ShaderProgram shaderProgram;

	public Renderer() {

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
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void render(EngineWindow window, Mesh mesh) {
		clear();

		shaderProgram.bind();
		// Draw the mesh
		glBindVertexArray(mesh.getVaoId());
		glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);

		// Restore state
		glBindVertexArray(0);

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