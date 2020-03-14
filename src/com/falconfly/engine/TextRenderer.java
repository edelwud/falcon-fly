package com.falconfly.engine;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.stb.STBEasyFont.stb_easy_font_print;

class TextRenderer {
	private int quads;

	public void PrintString(float x, float y, String text) {
		text += " ";
		ByteBuffer charBuffer = BufferUtils.createByteBuffer(text.length() * 270);

		quads = stb_easy_font_print(x, y, text, null, charBuffer);
		glEnableClientState(GL_VERTEX_ARRAY);
		glVertexPointer(2, GL_FLOAT, 16, charBuffer);
	}

	public void setTextColor(int r, int g, int b) {
		glColor3f(r / 255f, g / 255f, b / 255f);
	}

	public void Draw(int zoom) {
		float scaleFactor = 1.0f + zoom * 0.25f;
		glPushMatrix();
		glScalef(scaleFactor, scaleFactor, 1f);
		glTranslatef(4.0f, 4.0f, 0f);
		glDrawArrays(GL_QUADS, 0, quads * 4);
		glPopMatrix();
	}
}