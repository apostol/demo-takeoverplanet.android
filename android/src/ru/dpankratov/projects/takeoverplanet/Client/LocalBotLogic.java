package ru.dpankratov.projects.takeoverplanet.Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;

public class LocalBotLogic {
    private final Random RANDOM = new Random();

    public List<ClientAction> whatToDo(GalaxyModel galaxy, String ownerId) {
        List<ClientAction> actions = new LinkedList<ClientAction>();
        List<PlanetModel> myPlanets = galaxy.getPlanetModels().values().stream().filter(p->p.ownerId.equalsIgnoreCase(ownerId)).collect(Collectors.toList());
        PlanetModel p = (PlanetModel) myPlanets.get(RANDOM.nextInt(myPlanets.size()));
        if (p.getDroids()>2 * myPlanets.size()) {
            //Укрепляем свои планеты
            for (PlanetModel n : myPlanets) {
                if (p.id != n.id) {
                    actions.add(new ClientAction(p.id, n.id));
                }
            }
        } else {
            //Захватываем остальные - на сколько хватит сил
            Collection<PlanetModel> enemies = galaxy.getPlanetModels().values().stream().filter(l -> !l.ownerId.equalsIgnoreCase(ownerId) || l.ownerId.isEmpty()).collect(Collectors.toList());
            for (PlanetModel e : enemies) {
                actions.add(new ClientAction(p.id, e.id));
            }
        }
        return actions;
    }
}
