package ru.soliqute.game;

import static ru.soliqute.Main.SCR_HEIGHT;
import static ru.soliqute.Main.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import ru.soliqute.BlackEffect;
import ru.soliqute.Main;
import ru.soliqute.MyButton;

public class GameScreen1 implements Screen {
    public static Main main;
    public static PlayPerson playPerson;
    private BlackEffect blackEffect;
    public static int checkAnimate = 4;
    private int phase, nPhase;
    private long timeLastPhase, timePhaseInterval;

    public static SpriteBatch batch;
    public static OrthographicCamera camera;
    public static Vector3 touch;
    private BitmapFont font, fontBold;

    private Texture buttonAtlas, buttonMoveAtlas, textureButtonUse;
    private TextureRegion textureButtonPause, textureButtonUp,
        textureButtonDown, textureButtonLeft, textureButtonRight;

    public static MyButton buttonPause, buttonUse, buttonUp, buttonDown, buttonLeft, buttonRight;
    private Sound soundButtonClick1, soundButtonClick2;

    public GameScreen1(Main main) {
        this.main = main;
        batch = main.batch;
        camera = main.camera;
        touch = main.touch;
        font = main.font;
        fontBold = main.fontBold;

        blackEffect = new BlackEffect();
        playPerson = new PlayPerson(main);

        buttonAtlas = main.buttonAtlas;
        buttonMoveAtlas = main.buttonMoveAtlas;
        textureButtonUse = main.textureButtonUse;
        textureButtonPause = new TextureRegion(buttonAtlas, 900, 0, 450, 450);
        textureButtonUp = new TextureRegion(buttonMoveAtlas, 0, 0, 450, 450);
        textureButtonDown = new TextureRegion(buttonMoveAtlas, 900, 0, 450, 450);
        textureButtonLeft = new TextureRegion(buttonMoveAtlas, 1350, 0, 450, 450);
        textureButtonRight = new TextureRegion(buttonMoveAtlas, 450, 0, 450, 450);

        soundButtonClick1 = main.soundButtonClick1;
        soundButtonClick2 = main.soundButtonClick2;

        buttonPause = new MyButton(textureButtonPause, 1500, 800, 100, 100);
        buttonUse = new MyButton(textureButtonUse, 1270, 150, 170, 170);
        buttonUp = new MyButton(textureButtonUp, 150, 270, 150, 150);
        buttonDown = new MyButton(textureButtonDown, 150, 30, 150, 150);
        buttonLeft = new MyButton(textureButtonLeft, 30, 150, 150, 150);
        buttonRight = new MyButton(textureButtonRight, 270, 150, 150, 150);

        timePhaseInterval = 1000;
    }

    @Override
    public void show() {
        blackEffect.blackStart("", false);
        Gdx.input.setInputProcessor(new MyInputProcessor());
    }

    @Override
    public void render(float delta) {

        // касания
        if(Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
        }

        // события


        // отрисовка
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(buttonPause.textureRegion, buttonPause.x, buttonPause.y,
            buttonPause.width, buttonPause.height);
        batch.draw(buttonUse.texture, buttonUse.x, buttonUse.y, buttonUse.width, buttonUse.height);

        batch.draw(buttonUp.textureRegion, buttonUp.x, buttonUp.y, buttonUp.width, buttonUp.height);
        batch.draw(buttonDown.textureRegion, buttonDown.x, buttonDown.y,
            buttonDown.width, buttonDown.height);
        batch.draw(buttonLeft.textureRegion, buttonLeft.x, buttonLeft.y,
            buttonLeft.width, buttonLeft.height);
        batch.draw(buttonRight.textureRegion, buttonRight.x, buttonRight.y,
            buttonRight.width, buttonRight.height);

        try {
            if (checkAnimate == 0) {
                batch.draw(playPerson.texturePlayPersonUp[playPerson.phase],
                    (SCR_WIDTH / 2) - (playPerson.PLAY_PERSON_WIDTH / 2),
                    (SCR_HEIGHT / 2) - (playPerson.PLAY_PERSON_HEIGHT / 2),
                    playPerson.PLAY_PERSON_WIDTH, playPerson.PLAY_PERSON_HEIGHT);
                playPerson.move();
            } else if (checkAnimate == 1) {
                batch.draw(playPerson.texturePlayPersonDown[playPerson.phase],
                    (SCR_WIDTH / 2) - (playPerson.PLAY_PERSON_WIDTH / 2),
                    (SCR_HEIGHT / 2) - (playPerson.PLAY_PERSON_HEIGHT / 2),
                    playPerson.PLAY_PERSON_WIDTH, playPerson.PLAY_PERSON_HEIGHT);
                playPerson.move();
            } else if (checkAnimate == 2) {
                batch.draw(playPerson.texturePlayPersonRight[playPerson.phase],
                    (SCR_WIDTH / 2) - (playPerson.PLAY_PERSON_WIDTH / 2),
                    (SCR_HEIGHT / 2) - (playPerson.PLAY_PERSON_HEIGHT / 2),
                    playPerson.PLAY_PERSON_WIDTH, playPerson.PLAY_PERSON_HEIGHT);
                playPerson.move();
            } else if (checkAnimate == 3) {
                batch.draw(playPerson.texturePlayPersonLeft[playPerson.phase],
                    (SCR_WIDTH / 2) - (playPerson.PLAY_PERSON_WIDTH / 2),
                    (SCR_HEIGHT / 2) - (playPerson.PLAY_PERSON_HEIGHT / 2),
                    playPerson.PLAY_PERSON_WIDTH, playPerson.PLAY_PERSON_HEIGHT);
                playPerson.move();
            } else {
                batch.draw(playPerson.texturePlayPersonDown[0],
                    (SCR_WIDTH / 2) - (playPerson.PLAY_PERSON_WIDTH / 2),
                    (SCR_HEIGHT / 2) - (playPerson.PLAY_PERSON_HEIGHT / 2),
                    playPerson.PLAY_PERSON_WIDTH, playPerson.PLAY_PERSON_HEIGHT);
            }
        } catch (Exception exception) {
            batch.draw(playPerson.texturePlayPersonDown[0],
                (SCR_WIDTH / 2) - (playPerson.PLAY_PERSON_WIDTH / 2),
                (SCR_HEIGHT / 2) - (playPerson.PLAY_PERSON_HEIGHT / 2),
                playPerson.PLAY_PERSON_WIDTH, playPerson.PLAY_PERSON_HEIGHT);
        }

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
        font.dispose();
        fontBold.dispose();
        soundButtonClick1.dispose();
        soundButtonClick2.dispose();
        blackEffect.dispose();
    }

    private static class MyInputProcessor implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            touch.set(screenX, screenY, 0);
            camera.unproject(touch);

            if(buttonUp.hit(touch.x, touch.y)) {
                checkAnimate = 0;
            }

            if(buttonDown.hit(touch.x, touch.y)) {
                checkAnimate = 1;
            }

            if(buttonRight.hit(touch.x, touch.y)) {
                checkAnimate = 2;
            }

            if(buttonLeft.hit(touch.x, touch.y)) {
                checkAnimate = 3;
            }

            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            checkAnimate = 4;
            return false;
        }

        @Override
        public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            touch.set(screenX, screenY, 0);
            camera.unproject(touch);

            if(buttonUp.hit(touch.x, touch.y)) {
                checkAnimate = 0;
            }

            if(buttonDown.hit(touch.x, touch.y)) {
                checkAnimate = 1;
            }

            if(buttonRight.hit(touch.x, touch.y)) {
                checkAnimate = 2;
            }

            if(buttonLeft.hit(touch.x, touch.y)) {
                checkAnimate = 3;
            }

            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }
}
