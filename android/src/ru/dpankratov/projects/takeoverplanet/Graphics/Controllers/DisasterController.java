package ru.dpankratov.projects.takeoverplanet.Graphics.Controllers;

import java.util.List;

import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DisasterModel;

public class DisasterController implements IController {
    private List<DisasterModel> disasterModels;

    public DisasterController(List<DisasterModel> disasterModels) {
        this.disasterModels = disasterModels;
    }

    @Override
    public void update(float deltaTime) {

    }
}
