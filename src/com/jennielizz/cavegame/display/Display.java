package com.jennielizz.cavegame.display;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import com.jennielizz.cavegame.engine.Manager;
import com.jennielizz.cavegame.game.Cube;
import com.jennielizz.cavegame.launcher.Launcher;

public class Display {

    public static long window;

    public Display(int width, int height, String title, String launchArguments) {
        if (!GLFW.glfwInit()) {
            System.out.println("GLFW failed to initialize!");
            System.exit(1);
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_FALSE);

        System.out.println("Display created!");
        System.out.println("Width: " + width);
        System.out.println("Height: " + height);

        if (width == 0 || height == 0) {
            System.out.println("Width and height must be greater than 0!");
            System.exit(1);
        }

        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
        if (window == 0) {
            System.out.println("Failed to create window!");
            System.exit(1);
        }

        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE) {
                GLFW.glfwSetWindowShouldClose(window, true);
            }
        });

        try ( MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            GLFW.glfwSetWindowPos(window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        }
        
        GLFW.glfwMakeContextCurrent(window);

        GLFW.glfwSwapInterval(1);

        GLFW.glfwShowWindow(window);
    }

    public void beginUpdateLoop() {
        GL.createCapabilities();
        GL11.glClearColor(0, 0, 0, 0);

        switch (Manager.launchArguments) {
            case "game":
                new Cube(window);
                break;
            case "launcher":
                new Launcher(window);
                break;
            default:
                System.out.println("Invalid launch argument!");
                System.exit(1);
        }

        while (!GLFW.glfwWindowShouldClose(window)) {

            // Render/Update
            switch (Manager.launchArguments) {
                case "game":
                    Cube.updateCube();
                    break;
                case "launcher":
                    Launcher.updateLauncher();
                    break;
                default:
                    System.out.println("Invalid launch argument!");
                    System.exit(1);
            }
            // End Render/Update

            GLFW.glfwPollEvents();
            GLFW.glfwSwapBuffers(window);
        }
    }

    public void destroy() {
        switch (Manager.launchArguments) {
            case "game":
                Cube.cleanUp();
                break;
            case "launcher":
                Launcher.cleanUp();
                break;
            default:
                System.out.println("Invalid launch argument!");
                System.exit(1);
        }

        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
