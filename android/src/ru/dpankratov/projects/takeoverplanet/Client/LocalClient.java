package ru.dpankratov.projects.takeoverplanet.Client;

import androidx.core.util.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ru.dpankratov.projects.takeoverplanet.BaseRenderer;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Screens.GameScreen;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;

public class LocalClient implements GestureDetector.GestureListener, IClient {

    private PlanetModel fromPlanet;
    private PlanetModel toPlanet;
    private Vector3 pressDown = new Vector3(0, 0, 0);
    private Vector3 pressUp = new Vector3(0, 0, 0);

    private final GalaxyModel galaxy;

    public LocalClient(GalaxyModel galaxy) {
        this.galaxy = galaxy;
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
    public void SendDrones(int fromPlanetId, int toPlanetId, int count) {
        //
    }

    @Override
    public void Start() {
    }

    @Override
    public void Stop() {
    }

    @Override
    public void run() {
        while (!GalaxyLogicRules.isGameOver()) {
            try {
                Thread.sleep(500);
                //handler.accept(galaxy); TODO: Перспектива
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Находим ближайшую планету от точки нажатия
     * @param pressDown - точка нажатия
     * @return
     */
    public PlanetModel findNearestPlanetByPoint(Vector3 pressDown) {
        float minDst = Float.MAX_VALUE;
        PlanetModel result = null;
        for(PlanetModel planet:GameScreen.model.getPlanetModels().values()){
            float dst = planet.getPosition().dst(pressDown);
            if (dst < minDst) {
                minDst = dst;
                result = planet;
            }
        }
        return result;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        fromPlanet.resetCountDroids();
        return false;
    }

    private long pressedTime = 0;
    @Override
    public boolean longPress(float x, float y) {
        pressDown = BaseRenderer.getCamera().unproject(new Vector3(x, y, 0));
        Gdx.app.log("LongPress coordinates: ", pressDown.toString());
        fromPlanet = findNearestPlanetByPoint(pressDown);
        if (fromPlanet!=null) {
            pressDown = fromPlanet != null ? new Vector3(fromPlanet.getPosition()) : pressDown;
            Gdx.app.log("Touch Down Planet: ", String.valueOf(fromPlanet.id));
            fromPlanet.startCountDroids();
        }
        return false;
    }

    private double dronePercent = 0;
    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        Gdx.app.log("Flip velocity: ", velocityX +":" + velocityY);
        return false;
    }

    private boolean panOn = false;
    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        //переводим экранные координаты в координаты относительно камеры и мира
        Gdx.app.log("Pan: ", x +":" + y);
        if (!panOn) {
            pressDown = BaseRenderer.getCamera().unproject(new Vector3(x, y, 0));
            Gdx.app.log("Touch Down coordinates: ", pressDown.toString());
            fromPlanet = findNearestPlanetByPoint(pressDown);
            pressDown = fromPlanet != null ? new Vector3(fromPlanet.getPosition()) : pressDown;
            Gdx.app.log("Touch Down Planet: ", String.valueOf(fromPlanet.id));
            panOn = fromPlanet!=null;
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        Gdx.app.log("PanStop: ", x +":" + y);
        //переводим экранные координаты в координаты относительно камеры и мира
        pressUp = BaseRenderer.getCamera().unproject(new Vector3(x, y, 0));
        Gdx.app.log("From planet position:", pressDown.toString());
        Gdx.app.log("To position:", pressUp.toString());
        toPlanet = findNearestPlanetByPoint(pressUp);
        pressUp.set(0,0,0);
        pressDown.set(0,0,0);
        panOn = false;
        fromPlanet.stopCountDroids();

        if (fromPlanet != null && toPlanet != null
                && fromPlanet.getOwnerId().equalsIgnoreCase(GalaxyLogicRules.getMe().getUid())) {
            GameScreen.controller.getDroneController().send(fromPlanet, toPlanet, fromPlanet.getDroidsToSend());
        }
        return true;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
