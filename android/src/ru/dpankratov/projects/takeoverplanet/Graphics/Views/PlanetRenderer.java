package ru.dpankratov.projects.takeoverplanet.Graphics.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Map;

import ru.dpankratov.projects.takeoverplanet.BaseRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.AssetLoader;
import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.Font;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;

public class PlanetRenderer extends BaseRenderer implements IView {

    private Map<Integer, PlanetModel> planetModels;

    BitmapFont font;
    GlyphLayout glyphLayout;

    public PlanetRenderer(Map<Integer, PlanetModel> planetModels) {
        this.planetModels = planetModels;
        this.font = new Font(40).get();
        this.glyphLayout = new GlyphLayout();
        create();
    }


    @Override
    public void create() {
    }

    @Override
    public void resize(int width, int height) {
        //TODO
    }

    @Override
    public void render() {
        for (PlanetModel planet : planetModels.values()) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(planet.getX(), planet.getY(), planet.getPlanetRadius());
            shapeRenderer.end();
            if (planet.getCountDronesPercent()>0) {
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.GRAY);
                int segment = (int) (3.6 * planet.getCountDronesPercent());
                shapeRenderer.arc(planet.getX(), planet.getY(), planet.getPlanetRadius(), 0, segment);
                shapeRenderer.end();
            }

            spriteBatch.begin();
            //установим цвет шрифта - cyan
            font.setColor(Color.CYAN);
            glyphLayout.setText(font, planet.getOwner());
            font.draw(spriteBatch, glyphLayout, planet.getX(), planet.getY()+planet.getPlanetRadius()+5);
            if (!planet.getOwnerId().isEmpty()) { //TODO: Фу.... тут нет места этому коду.
                glyphLayout.setText(font, String.valueOf(planet.getDroids()));
                font.draw(spriteBatch, glyphLayout, planet.getX() - glyphLayout.width/2, planet.getY() - glyphLayout.height/2);
            }
            spriteBatch.end();
        }
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
        font.dispose();
    }

    @Override
    public void Stop() {

    }
}
