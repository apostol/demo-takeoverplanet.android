package ru.dpankratov.projects.takeoverplanet.Client;

import androidx.core.util.Consumer;

import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;

public interface IClient extends Runnable {
    GalaxyModel getGalaxy();
    void OnGalaxyUpdate(Consumer<GalaxyModel> handler);
    void SendDrones(int fromPlanetId, int toPlanetId);
}
