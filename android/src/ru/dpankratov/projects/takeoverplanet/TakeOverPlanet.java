package ru.dpankratov.projects.takeoverplanet;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import ru.dpankratov.projects.takeoverplanet.Graphics.GameScreen;
import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.AssetLoader;

public class TakeOverPlanet extends Game {

	@Override
	public void create () {
		Gdx.app.log("Take Over Planet", "is started.");
		Gdx.gl20.glViewport( 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		Gdx.gl20.glClearColor( 0, 0, 0, 1 );
		Gdx.gl20.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		AssetLoader.load();
		ShaderProgram.pedantic = false;
		setScreen(new GameScreen());
	}


	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
