package ru.dpankratov.projects.takeoverplanet.Graphics.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
    public static Texture bg, planet;

    public static void load() {
        bg = new Texture(Gdx.files.internal("background_alt.jpg"));
        planet = new Texture(Gdx.files.internal("planet_A.png"));

    }

    public static void dispose() {
        // Мы должны избавляться от текстур, когда заканчивает работать с объектом в котором есть текстуры
        bg.dispose();
        planet.dispose();
    }
}
