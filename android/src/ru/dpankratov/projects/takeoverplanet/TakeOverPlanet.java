package ru.dpankratov.projects.takeoverplanet;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import ru.dpankratov.projects.takeoverplanet.Client.LocalBot;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules;
import ru.dpankratov.projects.takeoverplanet.Graphics.Screens.GameOverScreen;
import ru.dpankratov.projects.takeoverplanet.Graphics.Screens.GameScreen;
import ru.dpankratov.projects.takeoverplanet.Graphics.Helpers.AssetLoader;

public class TakeOverPlanet extends Game {

	/** Width of the game viewport. */
	public static final int WIDTH = 120 * 16 / 9;

	/** Height of the game viewport. */
	public static final int HEIGHT = 120;

	@Override
	public void create () {
		Gdx.app.log("Take Over Planet", "is started.");
		//Gdx.gl20.glViewport( 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
		Gdx.gl20.glViewport( 0, 0, WIDTH, HEIGHT );
		Gdx.gl20.glClearColor( 0, 0, 0, 1 );
		Gdx.gl20.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		AssetLoader.load();
		ShaderProgram.pedantic = false;
		NewGame();
	}

	public void NewGame(){
		if (gameOverScreen!=null) {
			gameOverScreen.dispose();
			gameOverScreen = null;
		}
		GalaxyLogicRules.NewGame();
		setScreen(getGameScreen());
	}

	@Override
	public void render() {
		super.render();
		if (GalaxyLogicRules.isGameOver() && gameScreen!=null) {
			Gdx.app.log("Finish", GalaxyLogicRules.getGameOverResult());
			setScreen(getGameOverScreen());
			if (gameScreen != null) {
				gameScreen.Stop();
				gameScreen = null;
			}

		}
	}

	GameScreen gameScreen;
	private GameScreen getGameScreen(){
		if (gameScreen == null){
			gameScreen = new GameScreen();
			gameScreen.Start();
		}
		return gameScreen;
	}

	GameOverScreen gameOverScreen;
	private GameOverScreen getGameOverScreen(){
		if (gameOverScreen == null){
			gameOverScreen = new GameOverScreen();
		}
		return gameOverScreen;
	}


	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
