package ru.dpankratov.projects.takeoverplanet.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;

public class GalaxyLogicController implements IController {

    private GalaxyModel model;
    private GalaxyController controller;

    public GalaxyLogicController(GalaxyModel galaxyModel, GalaxyController galaxyController) {
        this.model = galaxyModel;
        this.controller = galaxyController;
    }

    /***
     * Находим ближайшую планету по выбранному направлению
     * @param pressDown - тока нажатия (планета от которй посылаются дроны)
     * @param pressUp - точка отжатия
     * @return
     */
    public PlanetModel findNearestPlanetByVector(Vector3 pressDown, Vector3 pressUp) {
        Vector3 v3 = new Vector3(pressUp.sub(pressDown).nor()); //нормализуем
        Vector2 v2 = new Vector2(v3.x, v3.y); //приводим в плоскость
        float minWeight = Float.MAX_VALUE;
        PlanetModel result = null;
        for(PlanetModel planet:model.getPlanetModels().values()){
            Vector2 p2 = new Vector2(planet.getPosition().x, planet.getPosition().y);
            float weight = Math.abs(v2.angle(p2)); //вычисляем угол
            Gdx.app.log("Weight:", String.valueOf(weight));
            if (weight < minWeight && weight != 0) {
                minWeight = weight;
                result = planet;
            }
        }
        return result;
    }

    /**
     * Послатьдронов на планету
     * @param fromPlanet с какой планеты посылаем
     * @param toPlanet на какую планету
     */
    public void sendDrones(PlanetModel fromPlanet, PlanetModel toPlanet) {
        if (fromPlanet.getOwnerId().equalsIgnoreCase(GalaxyLogicRules.getMe().getUid())
                && GalaxyLogicRules.PlanetCanSendDrones(fromPlanet.droids))
        {
            int size = GalaxyLogicRules.getDronesToSend(fromPlanet.droids);
            fromPlanet.droids -= size;
            controller.getDroneController().send(fromPlanet, toPlanet);
        }
    }

    /**
     * Находим ближайшую планету от точки нажатия
     * @param pressDown - точка нажатия
     * @return
     */
    public PlanetModel findNearestPlanetByPoint(Vector3 pressDown) {
        float minDst = Float.MAX_VALUE;
        PlanetModel result = null;
        for(PlanetModel planet:model.getPlanetModels().values()){
            float dst = planet.getPosition().dst(pressDown);
            if (dst < minDst) {
                minDst = dst;
                result = planet;
            }
        }
        return result;
    }


    @Override
    public void update(float deltaTime) {

    }
}
