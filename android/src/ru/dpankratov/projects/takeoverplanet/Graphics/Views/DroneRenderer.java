package ru.dpankratov.projects.takeoverplanet.Graphics.Views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.List;

import ru.dpankratov.projects.takeoverplanet.BaseRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DroneModel;

public class DroneRenderer extends BaseRenderer implements IView {

    private final ShapeRenderer shape;

    private List<DroneModel> droneModels;
    BitmapFont font;
    GlyphLayout glyphLayout;


    public DroneRenderer(List<DroneModel> droneModels) {
        this.shape = new ShapeRenderer();
        this.droneModels = droneModels;
        this.font = new BitmapFont();
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
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            float size = droneModel.getSize();
            if(size>0) {
                shapeRenderer.circle(droneModel.getX(), droneModel.getY(), size>50?50:size);
            }
            shapeRenderer.end();

            spriteBatch.begin();
            //установим цвет шрифта - cyan
            font.setColor(Color.RED);
            font.getData().setScale(2);
            glyphLayout.setText(font, String.valueOf(droneModel.getSize()));
            font.draw(spriteBatch, glyphLayout, droneModel.getX()-glyphLayout.width/2, droneModel.getY()+glyphLayout.height/2);
            spriteBatch.end();
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
        font.dispose();
    }
}