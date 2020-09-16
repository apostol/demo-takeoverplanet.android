package ru.dpankratov.projects.takeoverplanet.Graphics;

import com.badlogic.gdx.Gdx;

import ru.dpankratov.projects.takeoverplanet.Graphics.Controllers.DisasterController;
import ru.dpankratov.projects.takeoverplanet.Graphics.Controllers.DroneController;
import ru.dpankratov.projects.takeoverplanet.Graphics.Controllers.IController;
import ru.dpankratov.projects.takeoverplanet.Graphics.Controllers.PlanetController;
import ru.dpankratov.projects.takeoverplanet.Graphics.Controllers.PortalController;
import ru.dpankratov.projects.takeoverplanet.R;

public class GalaxyController implements IController {

    private final GalaxyModel model;

    private final PlanetController planetController;
    private final DisasterController disasterController;
    private final PortalController portalController;
    private final DroneController droneController;

    public GalaxyController(GalaxyModel galaxyModel) {
        this.model = galaxyModel;
        this.disasterController = new DisasterController(model.getDisasterModels());
        this.droneController = new DroneController(model.getDroneModels());
        this.planetController = new PlanetController(model.getPlanetModels());
        this.portalController = new PortalController(model.getPortalModels());
    }

    @Override
    public void update(float deltaTime) {
        disasterController.update(deltaTime);
        droneController.update(deltaTime);
        planetController.update(deltaTime);
        portalController.update(deltaTime);
    }

    @Override
    public void Start() {
        disasterController.Start();
        droneController.Start();
        planetController.Start();
        portalController.Start();
    }

    @Override
    public void Stop() {
        disasterController.Stop();
        droneController.Stop();
        planetController.Stop();
        portalController.Stop();
    }

    public PlanetController getPlanetController() {
        return planetController;
    }


    public DisasterController getDisasterController() {
        return disasterController;
    }

    public PortalController getPortalController() {
        return portalController;
    }

    public DroneController getDroneController() {
        return droneController;
    }


}
