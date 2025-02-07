package ru.soliqute.menu;

import static ru.soliqute.Main.SCR_HEIGHT;
import static ru.soliqute.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;

import ru.soliqute.BlackEffect;
import ru.soliqute.Main;
import ru.soliqute.MyButton;

public class ScreenSettings implements Screen {
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
    private TextureRegion buttonBackTexture;

    private MyButton buttonBack;

    public ScreenSettings(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontBold = main.fontBold;

        backgroundAtlas = new Texture("sprites/background_menu.jpg");
        buttonAtlas = new Texture("sprites/buttons.png");
        background[0] = new TextureRegion(backgroundAtlas, 0, 0, 1020, 1148 / 2);
        background[1] = new TextureRegion(backgroundAtlas, 0, 1148 / 2, 1020, 1148 / 2);
        buttonBackTexture = new TextureRegion(buttonAtlas, 1800 / 4, 0, 450, 450);
        blackEffect = new BlackEffect();

        buttonBack = new MyButton(buttonBackTexture, 1400, 70, 150, 150);

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

            if(buttonBack.hit(touch.x, touch.y)) {
                main.screenMenu.soundButtonClick1.play();
                blackEffect.blackStart("screenMenu", true);
                main.setScreen(main.screenMenu);
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
        batch.draw(buttonBack.textureRegion, buttonBack.x, buttonBack.y,
            buttonBack.width, buttonBack.height);

        fontBold.draw(batch, "НАСТРОЙКИ", 0, 840, SCR_WIDTH, Align.center, false);

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
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundAtlas.dispose();
        buttonAtlas.dispose();
        font.dispose();
        fontBold.dispose();
        blackEffect.dispose();
    }
}
