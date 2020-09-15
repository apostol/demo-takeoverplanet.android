package ru.dpankratov.projects.takeoverplanet.Client;

import androidx.core.util.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Screens.GameScreen;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;

public class LocalClient extends Stage implements InputProcessor, IClient {

    private PlanetModel fromPlanet;
    private PlanetModel toPlanet;
    private Vector3 pressDown = new Vector3(0, 0, 0);
    private Vector3 pressUp = new Vector3(0, 0, 0);

    private final GalaxyModel galaxy;

    public LocalClient(GalaxyModel galaxy) {
        this.galaxy = galaxy;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //переводим экранные координаты в координаты относительно камеры и мира
        pressDown = getCamera().unproject(new Vector3(screenX, screenY, 0));
        Gdx.app.log("Touch Down coordinates: ", pressDown.toString());
        fromPlanet = findNearestPlanetByPoint(pressDown);
        pressDown = fromPlanet != null ? new Vector3(fromPlanet.getPosition()) : pressDown;
        Gdx.app.log("Touch Down Planet: ", String.valueOf(fromPlanet.id));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        //переводим экранные координаты в координаты относительно камеры и мира
        pressUp = getCamera().unproject(new Vector3(screenX, screenY, 0));
        Gdx.app.log("From planet position:", pressDown.toString());
        Gdx.app.log("To position:", pressUp.toString());
        toPlanet = findNearestPlanetByPoint(pressUp);
        if (fromPlanet != null && toPlanet != null
                && fromPlanet.getOwnerId().equalsIgnoreCase(GalaxyLogicRules.getMe().getUid())) {
            GameScreen.controller.getDroneController().send(fromPlanet, toPlanet);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Gdx.app.log("Touch Dragged", screenX + ":" + screenY + ":" + pointer);
        return false;
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
}
