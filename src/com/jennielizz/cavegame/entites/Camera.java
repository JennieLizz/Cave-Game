package com.jennielizz.cavegame.entites;

import org.joml.Vector3f;
import org.joml.Vector3fc;

public class Camera {
    
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    public Camera(Vector3fc position, float pitch, float yaw, float roll) {
        this.position = new Vector3f(position);
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public void move(float dx, float dy, float dz) {
        position.add(dx, dy, dz);
    }

    public void rotate(float dx, float dy, float dz) {
        pitch += dx;
        yaw += dy;
        roll += dz;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
