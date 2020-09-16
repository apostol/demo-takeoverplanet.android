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
    }

    public GalaxyModel getGalaxy() {
        return galaxy;
    }

    @Override
    public void OnGalaxyUpdate(Consumer<GalaxyModel> handler) {
        this.handler = handler;
    } //TODO: Перспектива

    @Override
    public void SendDrones(int fromPlanetId, int toPlanetId, int count) {
        if(GameScreen.controller!=null) {
            GameScreen.controller.getDroneController().send(fromPlanetId, toPlanetId, count);
        }
    }
}
