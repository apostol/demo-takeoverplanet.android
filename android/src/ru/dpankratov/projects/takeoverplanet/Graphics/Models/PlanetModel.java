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

    public int getMaxDroids(){
        switch (type){
            case TYPE_A: return 100;
            case TYPE_B: return 200;
            case TYPE_C: return 300;
            case TYPE_D: return 400;
        }
        return 100;
    }

    public int getPlanetRadius(){
        return getMaxDroids()/3;
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

    private boolean countDroids = false;
    private double countDroindsByMilli = 0.09; //360/4000;
    private float countMiili = 0;
    @Override
    public void update(float deltaTime) {
        if (countDroids) {
            countMiili += deltaTime*1000;
            Gdx.app.log("time: ", String.valueOf(countMiili));
        }
    }

    @Override
    public void Stop() {

    }

    public void setDrones(int count) {
        this.droids = count;
    }

    public float getCountDronesPercent(){
        return (float) (countMiili * countDroindsByMilli);
    }
    public int getDroidsToSend() {
        int count;
        if (countMiili > 0) {
            count = (int) (getCountDronesPercent() * getDroids()/100);
        } else {
            count = getDroids()/2;
        }
        countMiili = 0;
        return count;
    }

    public void resetCountDroids(){
        countDroids = false;
        countMiili = 0;
    }

    public void stopCountDroids(){
        countDroids = false;
    }

    public void startCountDroids() {
        countDroids = true;
        countMiili = 0;
    }
}
