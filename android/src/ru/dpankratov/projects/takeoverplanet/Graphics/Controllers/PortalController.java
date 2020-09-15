package ru.dpankratov.projects.takeoverplanet.Graphics.Controllers;

import java.util.List;

import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PortalModel;

public class PortalController implements IController {
    private List<PortalModel> portalModels;

    public PortalController(List<PortalModel> portalModels) {
        this.portalModels = portalModels;
    }

    @Override
    public void update(float deltaTime) {

    }
}