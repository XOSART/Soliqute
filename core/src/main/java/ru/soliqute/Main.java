package ru.soliqute;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ArrayMap;

import ru.soliqute.menu.ScreenMenu;


public class Main extends Game {
    public static final float SCR_WIDTH = 1600;
    public static final float SCR_HEIGHT = 900;

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public BitmapFont font, fontBold;
    public Vector3 touch;

    public Texture background, backgroundAtlas, buttonAtlas,
        buttonMoveAtlas, textureButtonUse,
        playPersonUpArray, playPersonDownArray, playPersonRightArray, playPersonLeftArray;
    public Sound soundButtonClick1, soundButtonClick2;

    public ScreenMenu screenMenu;
    public ArrayMap<String, Screen> screens = new ArrayMap<String, Screen>();

    @Override
    public void create() {
        batch = new SpriteBatch();
        touch = new Vector3();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);

        backgroundAtlas = new Texture("sprites/background_menu.jpg");
        buttonAtlas = new Texture("sprites/buttons.png");
        buttonMoveAtlas = new Texture("sprites/buttons_move.png");
        textureButtonUse = new Texture("sprites/button_use.png");
        playPersonUpArray = new Texture("sprites/play_person_up.png");
        playPersonDownArray = new Texture("sprites/play_person_down.png");
        playPersonRightArray = new Texture("sprites/play_person_right.png");
        playPersonLeftArray = new Texture("sprites/play_person_left.png");

        font = new BitmapFont(Gdx.files.internal("fonts/PixeloidSans.fnt"));
        fontBold = new BitmapFont(Gdx.files.internal("fonts/PixeloidSans_Bold.fnt"));

        soundButtonClick1 = Gdx.audio.newSound(Gdx.files.internal("sounds/button_click_1.mp3"));
        soundButtonClick2 = Gdx.audio.newSound(Gdx.files.internal("sounds/button_click_2.mp3"));

        screenMenu = new ScreenMenu(this);
        screens.put("screenMenu", screenMenu);

        setScreen(screenMenu);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        fontBold.dispose();
        backgroundAtlas.dispose();
        buttonAtlas.dispose();
        buttonMoveAtlas.dispose();
        textureButtonUse.dispose();
        soundButtonClick1.dispose();
        soundButtonClick2.dispose();
    }
}
