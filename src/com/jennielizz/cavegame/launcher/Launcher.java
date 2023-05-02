package com.jennielizz.cavegame.launcher;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector3f;

import com.jennielizz.cavegame.display.Renderer;
import com.jennielizz.cavegame.display.StaticShader;
import com.jennielizz.cavegame.display.Texture;
import com.jennielizz.cavegame.engine.Manager;
import com.jennielizz.cavegame.engine.RawModel;
import com.jennielizz.cavegame.engine.TextureLoader;
import com.jennielizz.cavegame.entites.Camera;
import com.jennielizz.cavegame.entites.Entity;

public class Launcher {
    
    static long window;

    static TextureLoader tloader;
    static StaticShader shader;
    static Renderer renderer;
    static Camera camera;
    static RawModel model;
    static Entity entity;

    public List<RawModel> models = new ArrayList<RawModel>();
    public static List<Entity> entities = new ArrayList<Entity>();
    
    public Launcher(long window) {
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
        };

        float[] textureCoords = {
            0,0,
            0,1,
            1,1,
            1,0
        };

        int[] indices = {
            0,1,3,
            3,1,2
        };

        for (int i=-20; i < 20; i++) {
            for (int b=6; b < 11; b++) {
                RawModel model = tloader.loadToVAO(vertices, textureCoords, indices);
                model.setTexture(new Texture(tloader.loadTexture("res/textures/background.png")));
                this.model = model;
                models.add(model);

                Entity entity = new Entity(model, new Vector3f(i,-b,-25),0,0,0,1);
                this.entity = entity;
                entities.add(entity);
            }
        }

    }

    public static void updateLauncher() {
        renderer.prepare();
        shader.start();
        shader.loadViewMatrix(camera);

        for (int i=0; i < entities.size(); i++) {
            renderer.render(entities.get(i), shader);
        }
        shader.stop();
    }

    public static void cleanUp() {
        shader.cleanUp();
        tloader.cleanUp();
    }
}
