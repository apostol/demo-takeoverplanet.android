package ru.dpankratov.projects.takeoverplanet.Graphics;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.AssetLoader;
import ru.dpankratov.projects.takeoverplanet.Graphics.Views.DisasterRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Views.DroneRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Views.PlanetRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Views.PortalRenderer;

public class GalaxyRenderer implements ApplicationListener {

    private static final int CAMERA_WIDTH = 320; //Gdx.graphics.getWidth();
    private static final int CAMERA_HEIGHT = 480; //Gdx.graphics.getHeight();
    public final static OrthographicCamera camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);

    private final GalaxyModel galaxyModel;
    private SpriteBatch spriteBatch;

    private int width;
    private int height;
    private float ppuX;	// pixels per unit on the X axis
    private float ppuY;	// pixels per unit on the Y axis

    private final PlanetRenderer planetRenderer;
    private final DisasterRenderer disasterRenderer;
    private final PortalRenderer portalRenderer;
    private final DroneRenderer droneRenderer;


    public GalaxyRenderer(GalaxyModel galaxyModel){
        this.galaxyModel = galaxyModel;
        this.width = CAMERA_WIDTH;
        this.height = CAMERA_HEIGHT;
        resize(CAMERA_WIDTH, CAMERA_HEIGHT);

        spriteBatch = new SpriteBatch();
        planetRenderer = new PlanetRenderer(galaxyModel.getPlanetModels());
        disasterRenderer = new DisasterRenderer(galaxyModel.getDisasterModels());
        portalRenderer = new PortalRenderer(galaxyModel.getPortalModels());
        droneRenderer = new DroneRenderer(galaxyModel.getDroneModels());
    }

    @Override
    public void create() {
        //TODO
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
        //this.width *= (float) camera.viewportWidth / (float) width;
        //this.height *= (float) camera.viewportHeight / (float) height;
        camera.setToOrtho(false, this.width, this.height);
        camera.zoom = (float) camera.viewportWidth / (float) width;
        camera.update();
    }

    @Override
    public void render() {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();spriteBatch.disableBlending();
        spriteBatch.draw(AssetLoader.bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.enableBlending();spriteBatch.end();

        planetRenderer.render();
        disasterRenderer.render();
        portalRenderer.render();
        droneRenderer.render();
    }

    @Override
    public void pause() {
        //TODO
    }

    @Override
    public void resume() {
        //TODO
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
