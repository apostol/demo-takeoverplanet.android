package ru.dpankratov.projects.takeoverplanet.Client;

import androidx.core.util.Consumer;

import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.GameScreen;

public abstract class AbstractClient implements IClient {
    private Consumer<GalaxyModel> handler;
    private final GalaxyModel galaxy;

    protected AbstractClient(GalaxyModel galaxy) {
        this.galaxy = galaxy;
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
        while (GameScreen.isStarted) {
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
