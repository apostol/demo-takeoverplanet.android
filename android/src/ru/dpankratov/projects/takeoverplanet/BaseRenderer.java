package ru.dpankratov.projects.takeoverplanet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.AssetLoader;
import ru.dpankratov.projects.takeoverplanet.Graphics.Views.IView;

public class BaseRenderer implements IView {

    private static final int CAMERA_WIDTH = 320; //Gdx.graphics.getWidth();
    private static final int CAMERA_HEIGHT = 480; //Gdx.graphics.getHeight();
    private static final OrthographicCamera camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
    protected final SpriteBatch spriteBatch = new SpriteBatch();
    protected final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private int width;
    private int height;
    private float ppuX;
    private float ppuY;

    public static OrthographicCamera getCamera(){
        return camera;
    }

    @Override
    public void create() {
        this.width = CAMERA_WIDTH;
        this.height = CAMERA_HEIGHT;
        this.ppuX = (float)width / CAMERA_WIDTH;
        this.ppuY = (float)height / CAMERA_HEIGHT;
        resize(CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.ppuX = (float)width / CAMERA_WIDTH;
        this.ppuY = (float)height / CAMERA_HEIGHT;
        //this.width *= (float) camera.viewportWidth / (float) width;
        //this.height *= (float) camera.viewportHeight / (float) height;
        camera.setToOrtho(false, this.width, this.height);
        camera.update();
    }

    @Override
    public void render() {
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
        spriteBatch.dispose();
    }
}
