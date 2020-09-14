package ru.dpankratov.projects.takeoverplanet.Client;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyModel;

public class LocalBotLogic {
    public List<ClientAction> whatToDo(GalaxyModel galaxy, String ownerId) {
        List<ClientAction> actions = new LinkedList<ClientAction>();
        Collection<PlanetModel> myPlanets = galaxy.getPlanetModels().values().stream().filter(p->p.ownerId.equalsIgnoreCase(ownerId)).collect(Collectors.toList());

        Optional<PlanetModel> randomPlanet = myPlanets.stream().findAny();
        if (randomPlanet.isPresent()){
            PlanetModel p = randomPlanet.get();
            //Укрепляем свои планеты
            for (PlanetModel n : myPlanets) {
                if (p.id!=n.id) {
                    actions.add(new ClientAction(p.id, n.id));
                }
            }
            //Захватываем остальные
            Collection<PlanetModel> enemies = galaxy.getPlanetModels().values().stream().filter(l->!l.ownerId.equalsIgnoreCase(ownerId) || l.ownerId.isEmpty()).collect(Collectors.toList());
            for (PlanetModel e : enemies) {
                actions.add(new ClientAction(p.id, e.id));
            }
        }
        return actions;
    }
}
