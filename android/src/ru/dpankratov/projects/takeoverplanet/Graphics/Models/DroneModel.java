package ru.dpankratov.projects.takeoverplanet.Graphics.Models;

import com.badlogic.gdx.math.Vector3;

public class DroneModel implements IModel {
    private PlanetModel from;
    private PlanetModel to;
    private Vector3 position;
    private Vector3 velocity;
    private Vector3 acceleration;
    private int size;

    public DroneModel(PlanetModel from, PlanetModel to, Vector3 position, Vector3 velocity, Vector3 acceleration, int size) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.size = size;
        this.from = from;
        this.to = to;
    }

    @Override
    public void update(float deltaTime) {
        velocity.add(acceleration.cpy().scl(deltaTime));
        position.add(velocity.cpy().scl(deltaTime));
    }

    @Override
    public void Stop() {

    }

    public int getDroneRadius(){
        return Math.min(getSize(), 50);
    }
    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public int getSize() { return size; }

    public Vector3 getVelocity() {
        return velocity;
    }

    public Vector3 getPosition() {
        return position;
    }

    public PlanetModel getFrom() {
        return from;
    }

    public PlanetModel getTo() {
        return to;
    }

    public void setSize(int v) {
        size = v;
    }
}
