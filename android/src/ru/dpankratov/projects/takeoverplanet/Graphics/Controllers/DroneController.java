package ru.dpankratov.projects.takeoverplanet.Graphics.Controllers;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;

import java.util.List;
import java.util.stream.Collectors;

import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules;
import ru.dpankratov.projects.takeoverplanet.Graphics.Screens.GameScreen;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DroneModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;

public class DroneController implements IController {

    private final List<DroneModel> droneModels;

    public DroneController(List<DroneModel> droneModels){
        this.droneModels = droneModels;
    }

    @Override
    public void update(float deltaTime) {
        //TODO: не оптимально. Алгоритм написан "в тупую".
        for (DroneModel drone1: droneModels) {
            List<DroneModel> list = droneModels.stream().filter(
                    v-> v.getFrom().id == drone1.getTo().id && v.getTo().id == drone1.getFrom().id)
                    .collect(Collectors.toList());
            Circle droneC1 = new Circle(drone1.getX(),drone1.getY(), drone1.getDroneRadius());

            //Проверяем столкновение встречных дронов
            for (DroneModel drone2: list) {
                Circle droneC2 = new Circle(drone2.getX(),drone2.getY(), drone1.getDroneRadius());
                if(Intersector.overlaps(droneC1, droneC2)){
                    if (!GalaxyLogicRules.itIsMyDrones(drone2)){
                        //TODO: Вынсти в логику галактики
                        int tmp = drone1.getSize();
                        drone1.setSize(drone1.getSize() - drone2.getSize());
                        drone2.setSize(drone2.getSize() - tmp);
                    }
                }
            }
            //проверяем столкновение дронов с планетой приемником
            List<PlanetModel> planets = GameScreen.model.getPlanetModels().values().stream().filter(p->p.id == drone1.getTo().id)
                    .collect(Collectors.toList());
            for(PlanetModel planet: planets){
                Circle pC = new Circle(planet.getX(), planet.getY(), planet.getPlanetRadius());
                if(Intersector.overlaps(droneC1, pC)){
                    if (drone1.getFrom().getOwnerId().equalsIgnoreCase(planet.ownerId)){ //Попали на свою планету
                        //TODO: Вынсти в логику галактики
                        planet.setDrones(planet.getDroids() + drone1.getSize());
                        drone1.setSize(0);
                    }else {
                        //TODO: Вынсти в логику галактики
                        int tmp = planet.getDroids();
                        planet.setDrones(planet.getDroids() - drone1.getSize());
                        drone1.setSize(drone1.getSize() - tmp);
                        if (drone1.getSize() > 0) {
                            planet.setOwner(drone1.getFrom().getOwner());
                            planet.setOwnerId(drone1.getFrom().getOwnerId());
                            planet.setDrones(drone1.getSize());
                        }
                    }
                }
            }
        }
        droneModels.removeIf(v -> v.getSize() <= 0);
    }

    @Override
    public void Start() {

    }

    @Override
    public void Stop() {
        droneModels.clear();
    }

    public void send(int fromPlanetId, int toPlanetId, int count){
        PlanetModel fromPlanet = GameScreen.model.getPlanetModels().getOrDefault(fromPlanetId, null);
        PlanetModel toPlanet = GameScreen.model.getPlanetModels().getOrDefault(toPlanetId, null);
        send(fromPlanet, toPlanet, count);
    }

    public void send(PlanetModel fromPlanet, PlanetModel toPlanet, int count) {
        if (fromPlanet !=null && toPlanet!=null && fromPlanet.getDroids()>0) {
            Vector3 from = new Vector3(fromPlanet.getPosition());
            Vector3 to = new Vector3(toPlanet.getPosition());
            //int size = GalaxyLogicRules.getDronesToSend(fromPlanet.droids);
            count = Math.min(count, fromPlanet.getDroids()-1);
            fromPlanet.setDrones(fromPlanet.getDroids()-count);
            droneModels.add(
                    new DroneModel(
                            fromPlanet, toPlanet,
                            new Vector3(from),
                            new Vector3(to.sub(from).nor().scl(new Vector3(100, 100, 0))),
                            new Vector3(0, 0, 0),
                            count));
        }
    }
}
