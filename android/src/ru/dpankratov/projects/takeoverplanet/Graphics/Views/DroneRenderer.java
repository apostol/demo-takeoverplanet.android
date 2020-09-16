package ru.dpankratov.projects.takeoverplanet.Graphics.Views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

import ru.dpankratov.projects.takeoverplanet.BaseRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.Font;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DroneModel;

public class DroneRenderer extends BaseRenderer implements IView {

    private List<DroneModel> droneModels;
    Font font;
    GlyphLayout glyphLayout;

    public DroneRenderer(List<DroneModel> droneModels) {
        this.droneModels = droneModels;
        this.font = new Font(20);
        this.glyphLayout = new GlyphLayout();
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
        for (DroneModel droneModel : droneModels) {
            float size = droneModel.getSize();
            if(size>0) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.circle(droneModel.getX(), droneModel.getY(), droneModel.getDroneRadius());
                shapeRenderer.end();
                spriteBatch.begin();
                //установим цвет шрифта - cyan
                font.get().setColor(Color.RED);
                glyphLayout.setText(font.get(), String.valueOf(droneModel.getSize()));
                font.get().draw(spriteBatch, glyphLayout, droneModel.getX()-glyphLayout.width/2, droneModel.getY()+glyphLayout.height/2);
                spriteBatch.end();
            }
        }
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