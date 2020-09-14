package ru.dpankratov.projects.takeoverplanet.Graphics.Views;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

import ru.dpankratov.projects.takeoverplanet.Graphics.IView;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PortalModel;

public class PortalRenderer implements IView {

    private final ShapeRenderer shape;
    private List<PortalModel> portalModels;

    public PortalRenderer(List<PortalModel> planetModels) {
        this.shape = new ShapeRenderer();
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
}
