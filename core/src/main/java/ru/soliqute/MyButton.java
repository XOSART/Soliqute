package ru.soliqute;

import static ru.soliqute.Main.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyButton {
    public float x, y, width, height, scrX, scrY;
    public Texture texture;
    public TextureRegion textureRegion;
    public BitmapFont font;
    public String text, check;

    public MyButton(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        check = "texture";
    }

    public MyButton(Texture texture, float y, float width, float height) {
        this.texture = texture;
        this.y = y;
        this.width = width;
        this.height = height;
        check = "texture";
        x = (SCR_WIDTH / 2) - (width / 2);
    }

    public MyButton(TextureRegion textureRegion, float x, float y, float width, float height) {
        this.textureRegion = textureRegion;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        check = "texture";
    }

    public MyButton(TextureRegion textureRegion, float y, float width, float height) {
        this.textureRegion = textureRegion;
        this.y = y;
        this.width = width;
        this.height = height;
        check = "texture";
        x = (SCR_WIDTH / 2) - (width / 2);
    }

    public MyButton(BitmapFont font, String text, float x, float y) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;
        check = "font";
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;
    }

    public MyButton(BitmapFont font, String text, float y) {
        this.font = font;
        this.text = text;
        this.y = y;
        check = "font";
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;
        x = (SCR_WIDTH / 2) - (width / 2);
    }


    public boolean hit(float tX, float tY) {
        if(check.equals("texture")) {
            return (tX > x && tX < x + width) && (tY > y && tY < y + height);
        } else {
            return (tX > x && tX < x + width) && (tY < y && tY > y - height);
        }
    }
}
