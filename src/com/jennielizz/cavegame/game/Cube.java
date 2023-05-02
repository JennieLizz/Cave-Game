package com.jennielizz.cavegame.game;

import org.joml.Vector3f;

import com.jennielizz.cavegame.display.Renderer;
import com.jennielizz.cavegame.display.StaticShader;
import com.jennielizz.cavegame.display.Texture;
import com.jennielizz.cavegame.engine.RawModel;
import com.jennielizz.cavegame.engine.TextureLoader;
import com.jennielizz.cavegame.entites.Camera;
import com.jennielizz.cavegame.entites.Entity;

public class Cube {

    static long window;

    static TextureLoader tloader;
    static StaticShader shader;
    static Renderer renderer;
    static Camera camera;
    static RawModel model;
    static Entity entity;

    static Movement movement;
    
    public Cube(long window) {
        System.out.println("Cube scene loaded!");

        this.window = window;

        TextureLoader tloader = new TextureLoader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
        Camera camera = new Camera(new Vector3f(0,0,0), 0, 0, 0);

        this.tloader = tloader;
        this.shader = shader;
        this.renderer = renderer;
        this.camera = camera;

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
        model.setTexture(new Texture(tloader.loadTexture("res/textures/background.png")));
        this.model = model;

        Entity entity = new Entity(model, new Vector3f(0,0,-1),0,0,0,1);
        this.entity = entity;

        Movement movement = new Movement();
        this.movement = movement;
    }

    public static void updateCube() {
        renderer.prepare();
        shader.start();
        shader.loadViewMatrix(camera);

        entity.increaseRotation(0.04f, 0.04f, 0);
        movement.moveCheck(window, camera);

        renderer.render(entity, shader);
        shader.stop();
    }

    public static void cleanUp() {
        shader.cleanUp();
        tloader.cleanUp();
    }
}
