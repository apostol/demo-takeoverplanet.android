package ru.dpankratov.projects.takeoverplanet.Graphics.Controllers;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector3;

import java.util.List;
import java.util.stream.Collectors;

import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules;
import ru.dpankratov.projects.takeoverplanet.Graphics.GameScreen;
import ru.dpankratov.projects.takeoverplanet.Graphics.IController;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DroneModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;

import static ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules.getMe;

public class DroneController implements IController {

    private final List<DroneModel> droneModels;
    //private Object lock;

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
            Circle droneC1 = new Circle(drone1.getX(),drone1.getY(), drone1.getSize()>50?50:drone1.getSize());

            //Проверяем столкновение встречных дронов
            for (DroneModel drone2: list) {
                Circle droneC2 = new Circle(drone2.getX(),drone2.getY(), drone2.getSize()>50?50:drone2.getSize());
                if(Intersector.overlaps(droneC1, droneC2)){
                    if (!GalaxyLogicRules.itIsMyDrones(drone2)){
                        //TODO: Вынсти в логику галактики
                        float tmp = drone1.getSize();
                        drone1.setSize(drone1.getSize() - drone2.getSize());
                        drone2.setSize(drone2.getSize() - tmp);
                    }
                }
            }
            //проверяем столкновение дронов с планетой приемником
            List<PlanetModel> planets = GameScreen.model.getPlanetModels().values().stream().filter(p->p.id == drone1.getTo().id)
                    .collect(Collectors.toList());
            for(PlanetModel planet: planets){
                Circle pC = new Circle(planet.getX(), planet.getY(), PlanetModel.PLANET_RADIUS); //TODO: Использование безбожного хака!
                if(Intersector.overlaps(droneC1, pC)){
                    if (GalaxyLogicRules.itIsMyPlanet(planet)){
                        //TODO: Вынсти в логику галактики
                        planet.setDrones(planet.getDroids() + drone1.getSize());
                        drone1.setSize(0);
                    }else {
                        //TODO: Вынсти в логику галактики
                        float tmp = planet.getDroids();
                        planet.setDrones(planet.getDroids() - drone1.getSize());
                        drone1.setSize(drone1.getSize() - tmp);
                        if (drone1.getSize() > 0) {
                            planet.setOwner(getMe().getDisplayName());
                            planet.setOwnerId(getMe().getUid());
                            planet.setDrones(drone1.getSize());
                        }
                    }
                }
            }
        }
        droneModels.removeIf(v -> v.getSize() <= 0);
    }

    public void send(int fromPlanetId, int toPlanetId){
        PlanetModel fromPlanet = GameScreen.model.getPlanetModels().getOrDefault(fromPlanetId, null);
        PlanetModel toPlanet = GameScreen.model.getPlanetModels().getOrDefault(toPlanetId, null);
        send(fromPlanet, toPlanet);
    }

    public void send(PlanetModel fromPlanet, PlanetModel toPlanet) {
        if (fromPlanet !=null && toPlanet!=null && fromPlanet.getDroids()>0) {
            Vector3 from = new Vector3(fromPlanet.getPosition());
            Vector3 to = new Vector3(toPlanet.getPosition());
            int size = GalaxyLogicRules.getDronesToSend(fromPlanet.droids);
            fromPlanet.setDrones(fromPlanet.getDroids()-size);
            droneModels.add(
                    new DroneModel(
                            fromPlanet, toPlanet,
                            new Vector3(from),
                            new Vector3(to.sub(from).nor().scl(new Vector3(100, 100, 0))),
                            new Vector3(0, 0, 0),
                            size));
        }
    }
}
