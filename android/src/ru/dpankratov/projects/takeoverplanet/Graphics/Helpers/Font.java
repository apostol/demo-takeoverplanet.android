package ru.dpankratov.projects.takeoverplanet.Graphics.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Font {

    /** Name of the source font used. */
    private static final String FONT_NAME = "SourceCodePro-Regular.otf";

    /** The font generated for the game. */
    private final BitmapFont font;

    /** Constructor. */
    public Font(int size) {
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal(FONT_NAME));
        FreeTypeFontGenerator.FreeTypeFontParameter par = new FreeTypeFontGenerator.FreeTypeFontParameter();
        par.size = size;
        par.magFilter = Texture.TextureFilter.Linear;
        font = gen.generateFont(par);
        gen.dispose();
    }

    /** Returns the font generated. */
    public BitmapFont get() {
        return font;
    }
}

