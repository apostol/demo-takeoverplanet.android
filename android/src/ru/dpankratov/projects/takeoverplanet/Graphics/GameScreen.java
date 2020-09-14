package ru.dpankratov.projects.takeoverplanet.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import ru.dpankratov.projects.takeoverplanet.Client.LocalClient;


public class GameScreen implements Screen {

    public static GalaxyModel model;
    public static GalaxyRenderer view;
    public static GalaxyController controller;
    public static GalaxyLogicController logic;
    private LocalClient input;
    private float runTime; //время с момента запуска игры
    public static boolean isPaused;
    public static boolean isStarted;

    public GameScreen(){
        isPaused = false;
        isStarted = true;
        model = GalaxyModelGenerator.SingleGame((byte)2);
        view = new GalaxyRenderer(model);
        controller = new GalaxyController(model);
        logic = new GalaxyLogicController(model, controller);
        input = new LocalClient(model);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(input);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1); //указываем цвет
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //заполняем экран цветом
        runTime += delta;
        logic.update(delta);
        model.update(delta);
        controller.update(delta);
        view.render();
    }

    @Override
    public void resize(int width, int height) {
        view.resize(width, height);
    }

    @Override
    public void pause() {
        isPaused = true;
        view.pause();
    }

    @Override
    public void resume() {
        isPaused = false;
        view.resume();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        isStarted = false;
        Gdx.input.setInputProcessor(null);
    }
}

