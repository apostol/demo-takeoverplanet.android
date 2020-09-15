package ru.dpankratov.projects.takeoverplanet.Graphics.Controllers;

import java.util.Map;

import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;

import static ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules.DRONES_BORN_PERIOD;

public class PlanetController implements IController {

    private Map<Integer, PlanetModel> planetModels;
    private float deltaAggregator;

    public PlanetController(Map<Integer, PlanetModel> planetModels) {
        this.planetModels = planetModels;
    }

    private final Object lock = new Object();
    @Override
    public void update(float deltaTime) {
        deltaAggregator+=deltaTime;
        if (GalaxyLogicRules.isNextTurn(deltaAggregator, DRONES_BORN_PERIOD)){
            for (PlanetModel planet : planetModels.values()) {
                if (!planet.getOwnerId().isEmpty()) {
                    if (GalaxyLogicRules.PlanetCanMakeDrones(planet.getDroids(), planet.getMaxDroids())) {
                        planet.setDrones(planet.getDroids() + GalaxyLogicRules.getCountDronesToAddPlanetByTurn(planet));
                    }
                }
            }
            deltaAggregator -= DRONES_BORN_PERIOD;
        }
    }

}
