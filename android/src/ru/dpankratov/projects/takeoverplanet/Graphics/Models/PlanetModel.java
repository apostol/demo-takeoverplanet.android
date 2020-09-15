package ru.dpankratov.projects.takeoverplanet.Graphics.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

import java.util.Objects;

public class PlanetModel implements IModel {
    public int id;
    public int droids;
    public String owner;
    public String ownerId;
    public PlanetType type;

    @Override
    public boolean equals(Object o) {
        return id == ((PlanetModel) o).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getDroids() {
        return droids;
    }

    public float getMaxDroids(){
        switch (type){
            case TYPE_A: return 100f;
            case TYPE_B: return 200f;
            case TYPE_C: return 300f;
            case TYPE_D: return 400f;
        }
        return 100f;
    }

    public String getOwner() {
        return owner;
    }

    public PlanetType getType() {
        return type;
    }

    public void setOwner(String my) {
        this.owner = my;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String uid) {
        this.ownerId = uid;
    }

    public static float PLANET_RADIUS = 1f;
    private Vector3 position;

    public PlanetModel(int id, float x, float y, int droids, String owner, String ownerId, PlanetType type) {
        this.droids = droids;
        this.id = id;
        this.owner = owner;
        this.ownerId = ownerId;
        this.type = type;
        this.position = new Vector3(x,y,0);
    }

    public float getX() {
        return position.x;
    }
    public float getY() {
        return position.y;
    }
    public Vector3 getPosition(){
        return position;
    }

    @Override
    public void update(float deltaTime) {
    }

    public void setDrones(int count) {
        this.droids = count;
        Gdx.app.log("Planet ",  id + " drones: " + count);
    }
}
