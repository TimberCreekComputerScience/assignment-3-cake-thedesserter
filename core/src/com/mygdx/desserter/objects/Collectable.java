package com.mygdx.desserter.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Collectable {
    public float x, y;
    private Texture texture;
    private Sprite sprite;
    int size = (Gdx.graphics.getWidth() / 10);
    public Rectangle hitBox;

    public Collectable(float x, float y, String img, float width, float height) {
        this.x = x;
        this.y = y;
        texture = new Texture(img);
        sprite = new Sprite(texture);
        sprite.setSize(width, height);
        sprite.setPosition(x, y);
        hitBox = new Rectangle(x, y, size, size);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public boolean doesHit(Rectangle check) {
        return check.overlaps(hitBox);
    }

    public void destroy(ArrayList array) {
        array.remove(this);
    }
}