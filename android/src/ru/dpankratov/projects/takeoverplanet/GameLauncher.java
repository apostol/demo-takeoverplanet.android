package ru.dpankratov.projects.takeoverplanet;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import ru.dpankratov.projects.takeoverplanet.TakeOverPlanet;

public class GameLauncher extends AndroidApplication {
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useGyroscope = false;
        config.useAccelerometer = false;
        config.useCompass = false;
        config.useWakelock = true;
        config.useRotationVectorSensor = false;
        initialize(new TakeOverPlanet(), config);
    }
}