package ru.dpankratov.projects.takeoverplanet.Client;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;

public class LocalBotLogic {
    private final Random RANDOM = new Random();

    public List<ClientAction> whatToDo(GalaxyModel galaxy, String ownerId) {
        List<ClientAction> actions = new CopyOnWriteArrayList<ClientAction>();
        List<PlanetModel> myPlanets = galaxy.getPlanetModels().values().stream().filter(p->p.ownerId.equalsIgnoreCase(ownerId)).collect(Collectors.toList());
        PlanetModel p = (PlanetModel) myPlanets.get(RANDOM.nextInt(myPlanets.size()));
        if (p.getDroids()>2 * myPlanets.size()) {
            //Укрепляем свои планеты
            for (PlanetModel n : myPlanets) {
                if (p.id != n.id) {
                    actions.add(new ClientAction(p.id, n.id, p.getDroidsToSend()));
                }
            }
        }
        //Захватываем остальные - на сколько хватит сил
        List<PlanetModel> enemies = galaxy.getPlanetModels().values().stream().filter(l -> l.getOwnerId().isEmpty() || !l.ownerId.equalsIgnoreCase(ownerId)).collect(Collectors.toList());
        if (enemies.size()>0) {
            PlanetModel e = (PlanetModel) enemies.get(RANDOM.nextInt(enemies.size()));
            actions.add(new ClientAction(p.id, e.id, p.getDroidsToSend()));
        }
        return actions;
    }
}
