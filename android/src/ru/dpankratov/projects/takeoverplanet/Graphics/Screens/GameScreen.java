package ru.dpankratov.projects.takeoverplanet.Graphics.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.input.GestureDetector;

import ru.dpankratov.projects.takeoverplanet.Client.LocalBot;
import ru.dpankratov.projects.takeoverplanet.Client.LocalClient;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyController;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModelGenerator;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyRenderer;

public class GameScreen implements Screen {

    public static GalaxyModel model;
    public static GalaxyRenderer view;
    public static GalaxyController controller;
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
        input = new LocalClient(model);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new GestureDetector(input));
    }

    @Override
    public void render(float delta) {
        if (!GalaxyLogicRules.isGameOver()) {
            runTime += delta;
            model.update(delta);
            controller.update(delta);
            view.render();
        }
    }

    public void Start(){
        LocalBot.poolBots.forEach(LocalBot::Start);
    }

    public void Stop(){
        LocalBot.poolBots.forEach(LocalBot::Stop);
        LocalBot.poolBots.clear();
        model.Stop();
        controller.Stop();
        view.Stop();
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
    public void hide()
    {
    }

    @Override
    public void dispose() {
        isStarted = false;
        model = null;
        view = null;
        controller = null;
        input = null;
    }
}

