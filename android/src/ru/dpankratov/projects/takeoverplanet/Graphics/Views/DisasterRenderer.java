package ru.dpankratov.projects.takeoverplanet.Graphics.Views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

import ru.dpankratov.projects.takeoverplanet.Graphics.IView;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DisasterModel;

public class DisasterRenderer implements IView {

    private final SpriteBatch spriteBatch;
    private List<DisasterModel> disasterModels;

    public DisasterRenderer(List<DisasterModel> disasterModels) {
        this.spriteBatch = new SpriteBatch();
        this.disasterModels = disasterModels;
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
