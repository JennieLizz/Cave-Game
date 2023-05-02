package com.jennielizz.cavegame.display;

import java.io.IOException;
import java.nio.IntBuffer;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import com.jennielizz.cavegame.engine.RawModel;
import com.jennielizz.cavegame.engine.TextureLoader;
import com.jennielizz.cavegame.entites.Camera;
import com.jennielizz.cavegame.entites.Entity;

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

    public void BeginUpdateLoop() throws IOException {
        GL.createCapabilities();
        GL11.glClearColor(0, 0, 0, 0);

        TextureLoader tloader = new TextureLoader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
        Camera camera = new Camera(new Vector3f(0,0,0), 0, 0, 0);

        float[] vertices = {			
            -0.5f,0.5f,-0.5f,	
            -0.5f,-0.5f,-0.5f,	
            0.5f,-0.5f,-0.5f,	
            0.5f,0.5f,-0.5f,		
            
            -0.5f,0.5f,0.5f,	
            -0.5f,-0.5f,0.5f,	
            0.5f,-0.5f,0.5f,	
            0.5f,0.5f,0.5f,
            
            0.5f,0.5f,-0.5f,	
            0.5f,-0.5f,-0.5f,	
            0.5f,-0.5f,0.5f,	
            0.5f,0.5f,0.5f,
            
            -0.5f,0.5f,-0.5f,	
            -0.5f,-0.5f,-0.5f,	
            -0.5f,-0.5f,0.5f,	
            -0.5f,0.5f,0.5f,
            
            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,
            
            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f
            
    };
    
    float[] textureCoords = {
            
            0,0,
            0,1,
            1,1,
            1,0,			
            0,0,
            0,1,
            1,1,
            1,0,			
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0

            
    };
    
    int[] indices = {
            0,1,3,	
            3,1,2,	
            4,5,7,
            7,5,6,
            8,9,11,
            11,9,10,
            12,13,15,
            15,13,14,	
            16,17,19,
            19,17,18,
            20,21,23,
            23,21,22

    };

        RawModel model = tloader.loadToVAO(vertices, textureCoords, indices);
        model.setTexture(new Texture(tloader.loadTexture("res/textures/TheMostRevisedPerson.png")));

        Entity entity = new Entity(model, new Vector3f(0,0,-1),0,0,0,1);


        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            //Loop Begin
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);

            entity.increaseRotation(0.04f, 0.04f, 0);

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

            renderer.render(entity, shader);

            shader.stop();
            //Loop End


            GLFW.glfwSwapBuffers(window);

            GLFW.glfwPollEvents();
        }

        shader.cleanUp();
        tloader.cleanUp();
    }

    public void Destroy() {
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
