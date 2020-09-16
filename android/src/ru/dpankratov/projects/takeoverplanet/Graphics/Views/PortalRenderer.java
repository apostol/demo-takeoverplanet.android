package ru.dpankratov.projects.takeoverplanet.Graphics.Views;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

import ru.dpankratov.projects.takeoverplanet.BaseRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PortalModel;

public class PortalRenderer extends BaseRenderer implements IView {

    private List<PortalModel> portalModels;

    public PortalRenderer(List<PortalModel> planetModels) {
        this.portalModels = planetModels;
    }

    @Override
    public void create() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void Stop() {

    }
}
