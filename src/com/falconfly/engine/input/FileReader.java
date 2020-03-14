package com.falconfly.engine.input;

import org.lwjgl.*;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;
import java.util.Scanner;

import static java.nio.file.Files.readAllBytes;
import static org.lwjgl.BufferUtils.*;

import static org.lwjgl.BufferUtils.createByteBuffer;

public class FileReader {

	private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
		ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
		buffer.flip();
		newBuffer.put(buffer);
		return newBuffer;
	}

	public ByteBuffer getResource(String resource, int size) {
		ByteBuffer buffer = createByteBuffer(size);

		try {
			byte[] bytes = readAllBytes(Paths.get(resource));
			buffer.put(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		buffer.flip();
		return buffer;
	}
}
