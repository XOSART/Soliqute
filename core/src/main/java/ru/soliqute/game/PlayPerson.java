package ru.soliqute.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

import ru.soliqute.Main;

public class PlayPerson {
    public final float PLAY_PERSON_WIDTH = 90;
    public final float PLAY_PERSON_HEIGHT = 160;

    private Main main;
    public int phase, nPhaseUD, nPhaseRL;
    private long timeLastPhase, timePhaseInterval;

    private final Texture playPersonUpArray, playPersonDownArray,
        playPersonRightArray, playPersonLeftArray;
    public TextureRegion[] texturePlayPersonUp = new TextureRegion[8],
        texturePlayPersonDown = new TextureRegion[8],
        texturePlayPersonRight = new TextureRegion[8],
        texturePlayPersonLeft = new TextureRegion[8];

    public PlayPerson(Main main) {
        this.main = main;
        playPersonUpArray = main.playPersonUpArray;
        playPersonDownArray = main.playPersonDownArray;
        playPersonRightArray = main.playPersonRightArray;
        playPersonLeftArray = main.playPersonLeftArray;

        timePhaseInterval = 100;
        nPhaseUD = 8;
        nPhaseRL = 4;

        for (int i = 0; i < 8; i++) {
            texturePlayPersonUp[i] = new TextureRegion(playPersonUpArray,
                i * (800 / 8), 0, 800 / 8, 178);
        }

        for (int i = 0; i < 8; i++) {
            texturePlayPersonDown[i] = new TextureRegion(playPersonDownArray,
                i * (800 / 8), 0, 800 / 8, 178);
        }

        for (int i = 0; i < 8; i++) {
            texturePlayPersonRight[i] = new TextureRegion(playPersonRightArray,
                i * (800 / 8), 0, 800 / 8, 178);
        }

        for (int i = 0; i < 8; i++) {
            texturePlayPersonLeft[i] = new TextureRegion(playPersonLeftArray,
                i * (800 / 8), 0, 800 / 8, 178);
        }
    }

    public void move() {
        if (TimeUtils.millis() > timeLastPhase + timePhaseInterval) {
            timeLastPhase = TimeUtils.millis();
            phase++;
            if (phase == nPhaseUD) {
                phase = 0;
            }
        }
    }
}
