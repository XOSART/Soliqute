package ru.soliqute;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;


public class BlackEffect {
    private int phase, nPhase;
    private long timeLastPhase, timePhaseInterval = 60;
    public boolean isLife, intoBlack;

    public String screen;

    private Texture textureBlackAtlas;
    private TextureRegion[] textureBlack = new TextureRegion[4];
    public TextureRegion img;


    public BlackEffect() {
        textureBlackAtlas = new Texture("sprites/black_effect.png");
        textureBlack[3] = new TextureRegion(textureBlackAtlas, 0, 0, 1080 / 2, 608 / 2);
        textureBlack[2] = new TextureRegion(textureBlackAtlas, 1080 / 2, 0, 1080 / 2, 608 / 2);
        textureBlack[1] = new TextureRegion(textureBlackAtlas, 0, 608 / 2, 1080 / 2, 608 / 2);
        textureBlack[0] = new TextureRegion(textureBlackAtlas,
            1080 / 2, 608 / 2, 1080 / 2, 608 / 2);
    }

    public void blackStart(String screen, boolean intoBlack) {
        this.intoBlack = intoBlack;
        this.screen = screen;
        phase = 0;
        nPhase = 4;
        isLife = true;
        img = textureBlack[intoBlack?phase:nPhase-phase-1];
        timeLastPhase = TimeUtils.millis();
    }

    public boolean go() {
        if (TimeUtils.millis() > timeLastPhase + timePhaseInterval) {
            timeLastPhase = TimeUtils.millis();
            img = textureBlack[intoBlack?phase:nPhase-phase-1];
            if (++phase == nPhase) {
                return true;
            }
        }
        return false;
    }

    public void dispose() {
        textureBlackAtlas.dispose();
    }
}
