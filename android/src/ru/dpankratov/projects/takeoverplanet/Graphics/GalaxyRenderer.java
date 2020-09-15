package ru.dpankratov.projects.takeoverplanet.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.dpankratov.projects.takeoverplanet.BaseRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.AssetLoader;
import ru.dpankratov.projects.takeoverplanet.Graphics.Views.DisasterRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Views.DroneRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Views.PlanetRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Views.PortalRenderer;

public class GalaxyRenderer extends BaseRenderer {
    private final PlanetRenderer planetRenderer;
    private final DisasterRenderer disasterRenderer;
    private final PortalRenderer portalRenderer;
    private final DroneRenderer droneRenderer;
    private final SpriteBatch spriteBatch;

    public GalaxyRenderer(GalaxyModel galaxyModel){
        planetRenderer = new PlanetRenderer(galaxyModel.getPlanetModels());
        disasterRenderer = new DisasterRenderer(galaxyModel.getDisasterModels());
        portalRenderer = new PortalRenderer(galaxyModel.getPortalModels());
        droneRenderer = new DroneRenderer(galaxyModel.getDroneModels());
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render() {
        super.render();
        Gdx.gl20.glClearColor( 0, 0, 0, 1 );
        Gdx.gl20.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        spriteBatch.begin();spriteBatch.disableBlending();
        spriteBatch.draw(AssetLoader.bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.enableBlending();spriteBatch.end();
        planetRenderer.render();
        disasterRenderer.render();
        portalRenderer.render();
        droneRenderer.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        spriteBatch.dispose();
    }
}
