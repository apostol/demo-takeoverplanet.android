package ru.dpankratov.projects.takeoverplanet.Graphics.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

import ru.dpankratov.projects.takeoverplanet.BaseRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.AssetLoader;
import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.Font;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;

public class PlanetRenderer extends BaseRenderer implements IView {

    private Map<Integer, PlanetModel> planetModels;

    private static final int FRAME_COLS = 5;
    private static final int FRAME_ROWS = 4;

    Animation<TextureRegion> walkAnimation;
    TextureRegion[] walkFrames;
    TextureRegion currentFrame;
    BitmapFont font;
    GlyphLayout glyphLayout;
    float stateTime;
    int tileWidth, tileHeight;

    public PlanetRenderer(Map<Integer, PlanetModel> planetModels) {
        this.planetModels = planetModels;
        this.font = new Font(20).get();
        this.glyphLayout = new GlyphLayout();
        create();
    }


    @Override
    public void create() {
        tileWidth = AssetLoader.planet.getWidth()/FRAME_COLS;
        tileHeight = AssetLoader.planet.getHeight()/FRAME_ROWS;
        PlanetModel.PLANET_RADIUS = Math.max(tileHeight, tileWidth)/2; //TODO: Безбожный хак!
        TextureRegion[][] tmp = TextureRegion.split(AssetLoader.planet, tileWidth, tileHeight);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS-1]; //последний кадр пустой - убираем
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                if (index<FRAME_COLS * FRAME_ROWS-1)
                    walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation<TextureRegion>(0.2f, walkFrames);
        stateTime = 0f;
    }

    @Override
    public void resize(int width, int height) {
        //TODO
    }

    @Override
    public void render() {
        for (PlanetModel planet : planetModels.values()) {
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
            spriteBatch.begin();
            spriteBatch.draw(currentFrame, planet.getX() - tileWidth / 2, planet.getY() - tileHeight / 2);
            //установим цвет шрифта - cyan
            font.setColor(Color.CYAN);
            font.getData().setScale(2);
            glyphLayout.setText(font, planet.getOwner());
            font.draw(spriteBatch, glyphLayout, planet.getX(), planet.getY()+tileHeight);
            if (!planet.getOwnerId().isEmpty()) { //TODO: Фу.... тут нет места этому коду.
                glyphLayout.setText(font, String.valueOf(planet.getDroids()));
                font.draw(spriteBatch, glyphLayout, planet.getX() - tileWidth / 2, planet.getY() - tileHeight / 2);
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
        spriteBatch.dispose();
    }
}
