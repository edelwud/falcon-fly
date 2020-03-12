package com.falconfly.engine;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;

public class EngineWindow {
    // Window identification
    public long id;

    // Maximal values
    private int MAX_WIDTH = 1366;
    private int MAX_HEIGHT = 768;

    private int width;
    private int height;
    private String title;

    private GLFWVidMode videomode;
    public IntBuffer bufferedWidth;
    public IntBuffer bufferedHeight;

    // Singleton
    public static EngineWindow windowInstance;


    public EngineWindow(int width, int height, String title) {
        windowInstance = this;
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create() {
        if (!GLFW.glfwInit()) {
            System.err.println("GLFW cannot initialize");
            System.exit(-1);
        }
        this.id = GLFW.glfwCreateWindow(this.width, this.height, this.title, glfwGetPrimaryMonitor(), 0);
        if (this.id == 0) {
            System.err.println("GLFW cannot create window");
            System.exit(-1);
        }

        try(MemoryStack memory = MemoryStack.stackPush()) {
            this.bufferedWidth = BufferUtils.createIntBuffer(1);
            this.bufferedHeight = BufferUtils.createIntBuffer(1);
            GLFW.glfwGetWindowSize(this.id, this.bufferedWidth, this.bufferedHeight);
        } catch (Exception e) {
            System.err.println("Cannot alloc memory");
            System.exit(-1);
        }
        // Getting video mode of primary monitor
        this.videomode = GLFW.glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Setting primitives
        GLFW.glfwSetWindowTitle(this.id, this.title);
        GLFW.glfwSetWindowSize(this.id, this.width, this.height);

        // width / height
        GLFW.glfwSetWindowAspectRatio(this.id, this.width, this.height);

        // Centering window
        GLFW.glfwSetWindowPos(this.id,
                (this.videomode.width() - this.bufferedWidth.get(0)) / 2,
                (this.videomode.height() - this.bufferedHeight.get(0)) / 2);

        // Maximal values appending
        GLFW.glfwSetWindowSizeLimits(this.id, this.width, this.height, MAX_WIDTH, MAX_HEIGHT);

        // Creating context
        GLFW.glfwMakeContextCurrent(this.id);

        GL.createCapabilities();

        // Creating window viewport: x, y - offset
        GL11.glViewport(0, 0, this.bufferedWidth.get(0), this.bufferedHeight.get(0));
    }

    public void update() {
        // Clearing window each update
        GLFW.glfwPollEvents();
        GLFW.glfwSwapBuffers(this.id);
    }

    public void destroy() {
        GLFW.glfwDestroyWindow(this.id);
    }

    public boolean isCloseRequest() {
        return GLFW.glfwWindowShouldClose(this.id);
    }

    public static EngineWindow getWindowInstance() {
        return windowInstance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
