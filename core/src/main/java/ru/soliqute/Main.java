package ru.soliqute;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ArrayMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ru.soliqute.menu.ScreenMenu;
import ru.soliqute.menu.ScreenSettings;


public class Main extends Game {
    public static final float SCR_WIDTH = 1600;
    public static final float SCR_HEIGHT = 900;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public BitmapFont font, fontBold;
    public Vector3 touch;

    public Texture background;

    public ScreenMenu screenMenu;
    public ArrayMap<String, Screen> screens = new ArrayMap<String, Screen>();

    @Override
    public void create() {
        batch = new SpriteBatch();
        touch = new Vector3();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
        font = new BitmapFont(Gdx.files.internal("fonts/PixeloidSans.fnt"));
        fontBold = new BitmapFont(Gdx.files.internal("fonts/PixeloidSans_Bold.fnt"));

        screenMenu = new ScreenMenu(this);
        screens.put("screenMenu", screenMenu);

        setScreen(screenMenu);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        fontBold.dispose();
    }
}
