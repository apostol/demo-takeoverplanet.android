package ru.dpankratov.projects.takeoverplanet.Graphics;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DisasterModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DroneModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.IModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PortalModel;

public class GalaxyModel implements IModel {
    private final Map<Integer, PlanetModel> planetModels = Collections.synchronizedMap(new TreeMap<>());
    private final List<DisasterModel> disasterModels = new CopyOnWriteArrayList<>();
    private final List<PortalModel> portalModels = new CopyOnWriteArrayList<>();
    private final List<DroneModel> droneModels = new CopyOnWriteArrayList<>();

    public Map<Integer, PlanetModel> getPlanetModels() {
        return planetModels;
    }
    public List<DisasterModel> getDisasterModels() {
        return disasterModels;
    }
    public List<PortalModel> getPortalModels() {
        return portalModels;
    }
    public List<DroneModel> getDroneModels() {
        return droneModels;
    }
    public void update(float delta){
        for (DroneModel droneModel : droneModels) {
            droneModel.update(delta);
        }
        for (PlanetModel planetModel: planetModels.values()) {
            planetModel.update(delta);
        }

        for (DisasterModel disasterModel: disasterModels) {
            disasterModel.update(delta);
        }
        for (PortalModel portalModel: portalModels) {
            portalModel.update(delta);
        }
    }

    @Override
    public void Stop() {
        planetModels.clear();
        droneModels.clear();
        disasterModels.clear();
        portalModels.clear();
    }
}
