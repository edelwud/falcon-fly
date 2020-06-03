package com.falconfly.engine;

import com.falconfly.config.MainLogger;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.opengl.GL11.*;

public class EngineWindow {
    // Window identification
    public long id;

    // Maximal values
    private int MAX_WIDTH = 1920;
    private int MAX_HEIGHT = 1080;

    private String title;
    private int width;
    private int height;
    private boolean vSync;

    private MainLogger engineWindowLogger;
    private GLFWVidMode videomode;
    public IntBuffer bufferedWidth;
    public IntBuffer bufferedHeight;

    // Singleton
    public static EngineWindow windowInstance;

    private EngineWindow(String title, int width, int height, boolean vSync) throws IOException {
        engineWindowLogger = new MainLogger("./store/logs/application_log_engine.txt", EngineWindow.class.getSimpleName());
        this.width = width;
        this.height = height;
        this.title = title;
        this.vSync = vSync;
    }

    public static EngineWindow getWindowInstance(String title, int width, int height, boolean vSync) throws IOException {
        if (windowInstance == null) {
            windowInstance = new EngineWindow(title, width, height, vSync);
        }
        return windowInstance;
    }

    private void windowSizeChanged(long window, int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void create() {
        if (!GLFW.glfwInit()) {
            System.err.println("GLFW cannot initialize");
            System.exit(-1);
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);

        this.id = GLFW.glfwCreateWindow(this.width, this.height, this.title, 0, 0);
        if (this.id == 0) {
            System.err.println("GLFW cannot create window");
            System.exit(-1);
        }

        try(MemoryStack memory = MemoryStack.stackPush()) {
            this.bufferedWidth = BufferUtils.createIntBuffer(1);
            this.bufferedHeight = BufferUtils.createIntBuffer(1);
            GLFW.glfwGetWindowSize(this.id, this.bufferedWidth, this.bufferedHeight);
        } catch (Exception e) {
            engineWindowLogger.logger.info(e.toString());
        }
        // Getting video mode of primary monitor
        this.videomode = GLFW.glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowSizeCallback(this.id, this::windowSizeChanged);

        // Setting primitives
        GLFW.glfwSetWindowTitle(this.id, this.title);
        GLFW.glfwSetWindowSize(this.id, this.width, this.height);

        // Creating context
        glfwMakeContextCurrent(this.id);
        GL.createCapabilities();

        // Creating window viewport: x, y - offset
        GL11.glViewport(0, 0, this.bufferedWidth.get(0), this.bufferedHeight.get(0));

        // For text renderer
//        glMatrixMode(GL_PROJECTION);
//        glLoadIdentity();
//        glOrtho(0.0, width, height, 0.0, -1.0, 1.0);
//        glMatrixMode(GL_MODELVIEW);

        // Enables depth
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glEnable(GL_DEPTH_TEST);

        if (this.vSync) glfwSwapInterval(1);
        glfwShowWindow(id);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
    }

    public void setClearColor(float r, float g, float b, float alpha) {
        glClearColor(r, g, b, alpha);
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

    public boolean isvSync() {
        return vSync;
    }

    public static EngineWindow getInstance() {
        return windowInstance;
    }

    public void setvSync(boolean vSync) {
        this.vSync = vSync;
    }
}
