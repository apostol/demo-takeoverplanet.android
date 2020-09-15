package ru.dpankratov.projects.takeoverplanet.Graphics;

import com.google.firebase.auth.FirebaseUser;

import ru.dpankratov.projects.takeoverplanet.AndroidLauncher;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.DroneModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Models.PlanetModel;
import ru.dpankratov.projects.takeoverplanet.Graphics.Screens.GameScreen;

public class GalaxyLogicRules {
    public static int DRONES_DIV = 2; //делитель
    public static int MIN_DRONES_ON_PLANET = 1; //count
    public static int DRONES_BORN_PERIOD = 1; //sec
    public static int DRONES_BORN_BY_PERIOD = 1; //Count


    public static String getMyPlanetName(){
        return "My";
    }

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

    private static String gameOverResult;
    private static boolean gameOver = false;
    public static void NewGame(){
        gameOver = false;
        gameOverResult = "";
        score = 0;
    }
    public static String getGameOverResult(){
        return gameOverResult;
    }

    public static boolean isGameOver(){
        if (!gameOver && GameScreen.model != null) {
            long count = GameScreen.model.getPlanetModels().values().stream().filter(p -> p.getOwnerId().equalsIgnoreCase(getMe().getUid())).count();
            if (count == 0) {
                gameOverResult = "Вы проиграли!";
                score = 0;
                gameOver = true;
            } else if (count == GameScreen.model.getPlanetModels().values().size()) {
                gameOverResult = "Вы выиграли!";
                score = 0;
                GameScreen.model.getPlanetModels().values().stream().filter(p -> p.getOwnerId().equalsIgnoreCase(getMe().getUid())).forEach(t->{
                    score+=t.getDroids();
                });
                gameOver = true;
            }
        }
        return gameOver;
    }

    private static int score;
    public static int getScore() {
        return score;
    }
}
