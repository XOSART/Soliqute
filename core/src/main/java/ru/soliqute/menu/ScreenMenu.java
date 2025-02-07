package ru.soliqute.menu;

import static ru.soliqute.Main.SCR_HEIGHT;
import static ru.soliqute.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import ru.soliqute.BlackEffect;
import ru.soliqute.Main;
import ru.soliqute.MyButton;

public class ScreenMenu implements Screen {
    private Main main;
    private BlackEffect blackEffect;
    private int phase, nPhase;
    private long timeLastPhase, timePhaseInterval;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Vector3 touch;
    private BitmapFont font, fontBold;

    private Texture backgroundAtlas, buttonAtlas;
    private TextureRegion[] background = new TextureRegion[2];
    private TextureRegion buttonPlayTexture;
    private MyButton buttonPlay, buttonSettings, buttonTouch;
    public Sound soundButtonClick1, soundButtonClick2;

    private ScreenText screenText;
    private ScreenSettings screenSettings;

    public ScreenMenu(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontBold = main.fontBold;

        screenText = new ScreenText(main);
        screenSettings = new ScreenSettings(main);
        main.screens.put("screenText", screenText);
        main.screens.put("screenSettings", screenSettings);

        backgroundAtlas = new Texture("sprites/background_menu.jpg");
        buttonAtlas = new Texture("sprites/buttons.png");
        buttonPlayTexture = new TextureRegion(buttonAtlas, 0, 0, 450, 450);
        background[0] = new TextureRegion(backgroundAtlas, 0, 0, 1020, 1148 / 2);
        background[1] = new TextureRegion(backgroundAtlas, 0, 1148 / 2, 1020, 1148 / 2);
        blackEffect = new BlackEffect();

        soundButtonClick1 = Gdx.audio.newSound(Gdx.files.internal("sounds/button_click_1.mp3"));
        soundButtonClick2 = Gdx.audio.newSound(Gdx.files.internal("sounds/button_click_2.mp3"));

        buttonPlay = new MyButton(buttonPlayTexture, 340, 260, 260);
        buttonSettings = new MyButton(fontBold, "НАСТРОЙКИ", 180);
        buttonTouch = new MyButton(fontBold, "НАЖМИ", 770);

        nPhase = 2;
        timePhaseInterval = 1000;
    }

    @Override
    public void show() {
        blackEffect.blackStart("", false);
    }

    @Override
    public void render(float delta) {

        // касания
        if(Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if(buttonSettings.hit(touch.x, touch.y)) {
                soundButtonClick1.play();
                blackEffect.blackStart("screenSettings", true);
                main.setScreen(screenSettings);
            }

            if(buttonTouch.hit(touch.x, touch.y)) {
                soundButtonClick1.play();
                blackEffect.blackStart("screenText", true);
                main.setScreen(screenText);
            }

            if(buttonPlay.hit(touch.x, touch.y)) {
                soundButtonClick2.play(0.3f);
            }
        }

        // события
        if (TimeUtils.millis() > timeLastPhase + timePhaseInterval) {
            timeLastPhase = TimeUtils.millis();
            phase++;
            if (phase == nPhase) {
                phase = 0;
            }
        }

        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(background[phase], 0, 0, SCR_WIDTH, SCR_HEIGHT);
        batch.draw(buttonPlay.textureRegion, buttonPlay.x, buttonPlay.y,
            buttonPlay.width, buttonPlay.height);

        buttonSettings.font.draw(batch, buttonSettings.text,
            buttonSettings.x, buttonSettings.y);
        buttonTouch.font.draw(batch, buttonTouch.text, buttonTouch.x, buttonTouch.y);

        if(blackEffect.isLife) {
            batch.draw(blackEffect.img, 0, 0, SCR_WIDTH, SCR_HEIGHT);
            if(blackEffect.go()) {
                blackEffect.isLife = false;
                if(blackEffect.intoBlack) {
                    main.setScreen(main.screens.get(blackEffect.screen));
                }
            }
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        backgroundAtlas.dispose();
        buttonAtlas.dispose();
        font.dispose();
        fontBold.dispose();
        soundButtonClick1.dispose();
        blackEffect.dispose();
    }
}
