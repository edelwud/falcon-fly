package com.falconfly.engine.input;

import com.falconfly.engine.EngineWindow;
import org.lwjgl.glfw.GLFW;

public class Keyboard {
    private static EngineWindow window = EngineWindow.getInstance();
    public static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST]; // contains all keyboard keys

    public static boolean keyDown(int keyId) {
        return GLFW.glfwGetKey(window.id, keyId) == 1;
    }

    public static boolean keyPressed(int keyId) {
        if (!keys[keyId]) {
            keys[keyId] = true;
            return keyDown(keyId);
        }
        return false;
    }

    public static boolean keyReleased(int keyId) {
        if (keys[keyId]) {
            keys[keyId] = false;
            return !keyDown(keyId);
        }
        return false;
    }

    public static void handleKeyboardInput() {
        for (int i = 0; i < GLFW.GLFW_KEY_LAST; i++) {
            keys[i] = keyDown(i);
        }
    }
}
