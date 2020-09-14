package ru.dpankratov.projects.takeoverplanet.Client;

import androidx.core.util.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;

import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicController;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.GameScreen;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;

import static ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyRenderer.camera;

public class LocalClient extends GestureDetector.GestureAdapter implements InputProcessor, IClient {

    private PlanetModel fromPlanet;
    private PlanetModel toPlanet;
    private Vector3 pressDown = new Vector3(0, 0, 0);
    private Vector3 pressUp = new Vector3(0, 0, 0);

    private final GalaxyModel galaxy;

    public LocalClient(GalaxyModel galaxy) {
        this.galaxy = galaxy;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //переводим экранные координаты в координаты относительно камеры и мира
        pressDown = camera.unproject(new Vector3(screenX, screenY, 0));
        Gdx.app.log("Touch Down coordinates: ", pressDown.toString());
        fromPlanet = GameScreen.logic.findNearestPlanetByPoint(pressDown);
        pressDown = fromPlanet != null ? new Vector3(fromPlanet.getPosition()) : pressDown;
        Gdx.app.log("Touch Down Planet: ", String.valueOf(fromPlanet.id));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //переводим экранные координаты в координаты относительно камеры и мира
        pressUp = camera.unproject(new Vector3(screenX, screenY, 0));
        Gdx.app.log("From planet position:", pressDown.toString());
        Gdx.app.log("To position:", pressUp.toString());
        toPlanet = GameScreen.logic.findNearestPlanetByPoint(pressUp);
        if (fromPlanet != null && toPlanet != null) {
            GameScreen.controller.getDroneController().send(fromPlanet, toPlanet);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Gdx.app.log("Touch Dragged", screenX + ":" + screenY + ":" + pointer);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean zoom (float originalDistance, float currentDistance) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        Gdx.app.log("Zoom: ", String.valueOf(camera.zoom) );
        camera.zoom*=2;
        return super.tap(x, y, count, button);
    }

    private Consumer<GalaxyModel> handler; //TODO: перспектива


    public GalaxyModel getGalaxy() {
        return galaxy;
    }

    @Override
    public void OnGalaxyUpdate(Consumer<GalaxyModel> handler) {
        this.handler = handler;
    }

    @Override
    public void SendDrones(int fromPlanetId, int toPlanetId) {
        //
    }

    @Override
    public void run() {
        while (GameScreen.isStarted) {
            try {
                Thread.sleep(500);
                //handler.accept(galaxy); TODO: Перспектива
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
