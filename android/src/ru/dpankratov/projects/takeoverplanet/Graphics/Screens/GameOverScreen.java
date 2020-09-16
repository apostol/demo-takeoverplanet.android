package ru.dpankratov.projects.takeoverplanet.Graphics.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Locale;

import ru.dpankratov.projects.takeoverplanet.BaseRenderer;
import ru.dpankratov.projects.takeoverplanet.GameLauncher;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules;
import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.AssetLoader;
import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.Font;

public class GameOverScreen implements Screen {

    class GameOverRenderer extends BaseRenderer{
        Stage stage;

        public GameOverRenderer(){
            stage = new Stage(new ScreenViewport());
            Label.LabelStyle labelStyle = new Label.LabelStyle(new Font(60).get(), Color.WHITE);
            // table to organize all the labels
            Table table = new Table();
            table.setFillParent(true);
            table.add(new Label("Game over!" /*GalaxyLogicRules.getGameOverResult()*/, labelStyle)).expandX();
            table.row();
            if (GalaxyLogicRules.getScore()>0) {
                table.add(new Label(String.format(Locale.US, "Score: %d", GalaxyLogicRules.getScore()), labelStyle)).expandX();
            }
            stage.addActor(table);

            final TextButton button = new TextButton("New Game", AssetLoader.skin, "default");
            button.setWidth(200f);
            button.setHeight(100f);
            button.setPosition(Gdx.graphics.getWidth() /2 - 100f, Gdx.graphics.getHeight()/2 - 150f);
            button.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y){
                    GameLauncher.theGame.NewGame();
                }
            });
            stage.addActor(button);
            Gdx.input.setInputProcessor(stage);
        }

        @Override
        public void render() {
            super.render();
            Gdx.gl20.glClearColor( 0, 0, 0, 1 );
            Gdx.gl20.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
            spriteBatch.begin();
            spriteBatch.disableBlending();
            spriteBatch.draw(AssetLoader.bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            spriteBatch.enableBlending();
            stage.act(Gdx.graphics.getDeltaTime());
            stage.draw();
            spriteBatch.end();
        }

        @Override
        public void Stop() {

        }
    }

    BaseRenderer view = new GameOverRenderer();

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        view.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
