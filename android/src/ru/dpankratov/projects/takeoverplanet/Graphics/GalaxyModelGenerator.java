package ru.dpankratov.projects.takeoverplanet.Graphics;

import com.badlogic.gdx.Gdx;

import java.util.Random;

import ru.dpankratov.projects.takeoverplanet.Client.LocalBot;
import ru.dpankratov.projects.takeoverplanet.Client.LocalClient;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DisasterModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DisasterType;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetType;

import static ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules.getMe;
import static ru.dpankratov.projects.takeoverplanet.Graphics.GalaxyLogicRules.getMyPlanetName;

public class GalaxyModelGenerator {
    private static final Random RANDOM = new Random();
    private static final int PLANET_TYPE_SIZE = PlanetType.values().length;
    private static final PlanetType[] PLANET_TYPE_VALUES = PlanetType.values();
    private static final GalaxyModel galaxy = new GalaxyModel();

    public static GalaxyModel SingleGame(byte botsCount){
        botsCount = botsCount>10?10:botsCount;
        byte planetsCount = 6; //TODO: Вынести в настройки
        int _id = 1;
        LocalClient _local = new LocalClient(galaxy);
        galaxy.getPlanetModels().put(_id, getPlanet(_id, 0, getMyPlanetName(), getMe().getUid(), PlanetType.TYPE_A));
        new Thread(_local).start();
        _id++;
        for (int i = 0; i<planetsCount+1; i++){
            galaxy.getPlanetModels().put(_id, getPlanet(_id, 0,"","", randomPlanetType()));
            _id++;
        }
        for (int i = 0; i<botsCount; i++) {
            LocalBot _bot = new LocalBot(galaxy,"Bot_" + _id, String.valueOf(_id));
            galaxy.getPlanetModels().put(_id, getPlanet(_id, 0,_bot.getDisplayName(), _bot.getUid(), PlanetType.TYPE_A));
            _id++;
        }
        galaxy.getDisasterModels().add(new DisasterModel(1,1,3, DisasterType.BLACK_HOLE));
        return galaxy;
    }

    private static PlanetModel getPlanet(int id, int droids, String owner, String ownerId, PlanetType type){
        PlanetModel planet = new PlanetModel(id, 0, 0, droids, owner, ownerId, type);
        planet.getPosition().set((float)RandomizeWidth(planet.getMaxDroids()), (float)RandomizeHeight(planet.getMaxDroids()), 0);
        return planet;
    }

    private static PlanetType randomPlanetType()  {
        return PLANET_TYPE_VALUES[RANDOM.nextInt(PLANET_TYPE_SIZE)];
    }

    private static int RandomizeHeight(int size){
        return size/2 + RANDOM.nextInt(Gdx.graphics.getHeight() - size);
    }

    private static int RandomizeWidth(int size){
        return size/2 + RANDOM.nextInt(Gdx.graphics.getWidth() - size);
    }

}