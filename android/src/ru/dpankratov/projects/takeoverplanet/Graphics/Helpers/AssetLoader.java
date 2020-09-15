package ru.dpankratov.projects.takeoverplanet.Graphics.Helpers;

import android.content.res.AssetManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetLoader {
    public static Texture bg, planet, menubg;
    public static Skin skin;
    public static TextureAtlas atlas;

    public static void load() {
        bg = new Texture(Gdx.files.internal("background_alt.jpg"));
        menubg = new Texture(Gdx.files.internal("menubg.png"));
        planet = new Texture(Gdx.files.internal("planet_A.png"));
        atlas = new TextureAtlas("default/skin/uiskin.atlas");
        skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        skin.addRegions(atlas);
    }

    public static void dispose() {
        // Мы должны избавляться от текстур, когда заканчивает работать с объектом в котором есть текстуры
        bg.dispose();
        menubg.dispose();
        planet.dispose();
        skin.dispose();
        atlas.dispose();
    }
}
