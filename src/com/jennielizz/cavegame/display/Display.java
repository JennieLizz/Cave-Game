package com.jennielizz.cavegame.display;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

public class Display {

    public long window;

    public Display(int width, int height, String title) {
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

    public void BeginUpdateLoop() {
        GL.createCapabilities();
        GL11.glClearColor(0, 0, 0, 0);

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f
        };

        int[] indices = {
            0, 1, 3,
            3, 1, 2
        };

        RawModel model = loader.loadToVAO(vertices, indices);


        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            //Loop Begin
            renderer.prepare();
            shader.start();

            renderer.render(model);

            shader.stop();
            //Loop End


            GLFW.glfwSwapBuffers(window);

            GLFW.glfwPollEvents();
        }

        shader.cleanUp();
        loader.cleanUp();
    }

    public void Destroy() {
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
