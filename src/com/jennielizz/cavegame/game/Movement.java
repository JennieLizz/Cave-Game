package com.jennielizz.cavegame.game;

import org.lwjgl.glfw.GLFW;

import com.jennielizz.cavegame.entites.Camera;

public class Movement {
    
    public void moveCheck(long window, Camera camera) {
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_W) == GLFW.GLFW_PRESS) {
            camera.move(0, 0, -0.02f);
        }

        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_S) == GLFW.GLFW_PRESS) {
            camera.move(0, 0, 0.02f);
        }

        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_A) == GLFW.GLFW_PRESS) {
            camera.move(-0.02f, 0, 0);
        }

        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_D) == GLFW.GLFW_PRESS) {
            camera.move(0.02f, 0, 0);
        }

        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_SPACE) == GLFW.GLFW_PRESS) {
            camera.move(0, 0.02f, 0);
        }

        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_LEFT_SHIFT) == GLFW.GLFW_PRESS) {
            camera.move(0, -0.02f, 0);
        }
    }
}
