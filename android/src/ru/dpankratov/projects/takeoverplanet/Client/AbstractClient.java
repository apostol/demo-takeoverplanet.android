package ru.dpankratov.projects.takeoverplanet.Client;

import androidx.core.util.Consumer;

import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Screens.GameScreen;

public abstract class AbstractClient implements IClient {
    private Consumer<GalaxyModel> handler;
    private final GalaxyModel galaxy;
    protected boolean isStarted = false;

    protected AbstractClient(GalaxyModel galaxy) {
        this.galaxy = galaxy;
        isStarted = true;
    }

    public GalaxyModel getGalaxy() {
        return galaxy;
    }

    @Override
    public void OnGalaxyUpdate(Consumer<GalaxyModel> handler) {
        this.handler = handler;
    } //TODO: Перспектива

    @Override
    public void SendDrones(int fromPlanetId, int toPlanetId) {
        GameScreen.controller.getDroneController().send(fromPlanetId, toPlanetId);
    }

    @Override
    public void run() {
        while (!GalaxyLogicRules.isGameOver() && isStarted) {
            try {
                Thread.sleep(500);
                //handler.accept(galaxy); //TODO: Перспектива
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
