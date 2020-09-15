package ru.dpankratov.projects.takeoverplanet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class GameLauncher extends AndroidFragmentApplication {

    public static TakeOverPlanet theGame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useGyroscope = false;
        config.useAccelerometer = false;
        config.useCompass = false;
        config.useWakelock = true;
        config.useRotationVectorSensor = false;
        theGame = new TakeOverPlanet();
        return initializeForView(theGame, config);
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}