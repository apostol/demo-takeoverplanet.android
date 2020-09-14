package ru.dpankratov.projects.takeoverplanet.Graphics;

import com.google.firebase.auth.FirebaseUser;

import ru.dpankratov.projects.takeoverplanet.AndroidLauncher;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DroneModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;

public class GalaxyLogicRules {
    public static int DRONES_DIV = 2; //делитель
    public static int MIN_DRONES_ON_PLANET = 1; //count
    public static int DRONES_BORN_PERIOD = 1; //sec
    public static int DRONES_BORN_BY_PERIOD = 1; //Count


    public static boolean PlanetCanMakeDrones(float droids, float maxDroids){
        return droids < maxDroids;
    }

    public static boolean PlanetCanSendDrones(float droids){
        return droids>MIN_DRONES_ON_PLANET;
    }

    public static boolean isNextTurn(float deltaAggregator, int dronesBornPeriod){
        return deltaAggregator>dronesBornPeriod;
    }

    public static int getCountDronesToAddPlanetByTurn(PlanetModel planet) {
        return DRONES_BORN_BY_PERIOD;
    }

    public static int getDronesToSend(float dronesOnPlanet){
        return (int) dronesOnPlanet / DRONES_DIV;
    }

    public static FirebaseUser getMe(){
        return AndroidLauncher.getUser();
    }

    public static boolean itIsMyPlanet(PlanetModel planet) {
        return planet.getOwnerId().equalsIgnoreCase(getMe().getUid());
    }

    public static boolean itIsMyDrones(DroneModel drone){
        return drone.getFrom().getOwnerId().equalsIgnoreCase(getMe().getUid());
    }
}
