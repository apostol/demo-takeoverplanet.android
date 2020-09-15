package ru.dpankratov.projects.takeoverplanet.Graphics.Models;

public class PortalModel implements IModel {

    public long source;
    public long target;

    public PortalModel(int source, int target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public boolean equals(Object o) {
        return source == ((PortalModel) o).source && target == ((PortalModel) o).target;
    }

    @Override
    public void update(float deltaTime) {

    }

}

