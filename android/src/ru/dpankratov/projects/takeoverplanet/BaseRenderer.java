package ru.dpankratov.projects.takeoverplanet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import ru.dpankratov.projects.takeoverplanet.Graphics.Views.IView;

public abstract class BaseRenderer implements IView {

    private static final int CAMERA_WIDTH = 320;
    private static final int CAMERA_HEIGHT = 480;
    private static final Viewport viewPort = new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
    protected final SpriteBatch spriteBatch = new SpriteBatch();
    protected final ShapeRenderer shapeRenderer = new ShapeRenderer();
    private int width;
    private int height;
    private float ppuX;
    private float ppuY;

    public static Camera getCamera(){
        return viewPort.getCamera();
    }
    public static Viewport getViewPort() {return viewPort; }

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
        viewPort.update (this.width, this.height, true);
    }

    @Override
    public void render() {
        //spriteBatch.setProjectionMatrix(getCamera().combined);
        //shapeRenderer.setProjectionMatrix(getCamera().combined);
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
