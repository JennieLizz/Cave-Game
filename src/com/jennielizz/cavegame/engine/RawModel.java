package com.jennielizz.cavegame.engine;

import com.jennielizz.cavegame.display.Texture;

public class RawModel {

    public int vaoID;
    public int vertexCount;
    public Texture texture;

    public RawModel(int vaoID, int vertexCount) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;

        System.out.println("RawModel created!");
        System.out.println("vaoID: " + vaoID);
        System.out.println("vertexCount: " + vertexCount);
    }

    public RawModel(int vaoID, int vertexCount, Texture texture) {
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
        this.texture = texture;

        System.out.println("Model created!");
        System.out.println("vaoID: " + vaoID);
        System.out.println("vertexCount: " + vertexCount);
        System.out.println("texture: " + texture);
    }

    public RawModel(RawModel model, Texture texture) {
        this.vaoID = model.getVaoID();
        this.vertexCount = model.getVertexCount();
        this.texture = texture;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;

        System.out.println("Texture set!");
        System.out.println("texture: " + texture);
    }
}
