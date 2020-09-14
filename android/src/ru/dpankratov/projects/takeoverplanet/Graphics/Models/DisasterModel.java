package ru.dpankratov.projects.takeoverplanet.Graphics.Models;

import java.util.Objects;

import ru.dpankratov.projects.takeoverplanet.Graphics.IModel;

public class DisasterModel implements IModel {

    public DisasterType type;
    public long planetId;
    public long sourcePlanetId;
    public long targetPlanetId;

    @Override
    public boolean equals(Object o) {
        DisasterModel _o = (DisasterModel) o;
        return planetId == _o.planetId && sourcePlanetId == _o.sourcePlanetId && targetPlanetId == _o.targetPlanetId && type == _o.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(planetId, sourcePlanetId, targetPlanetId);
    }

    public DisasterModel(long id, long source, long target, DisasterType type) {
        this.planetId = id;
        this.sourcePlanetId = source;
        this.targetPlanetId = target;
        this.type = type;
    }

    @Override
    public void update(float deltaTime) {

    }
}
