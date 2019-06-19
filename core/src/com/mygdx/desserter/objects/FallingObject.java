package com.mygdx.desserter.objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.desserter.screens.MainGame;

public class FallingObject {

    private Sprite sprite;
    private Rectangle hitbox;
    private Vector2 velocity;
    private static float acceleration = -200;
    private float angle;
    boolean wasOnScreen;

    public FallingObject(int x, int y, int size, float push) {
        hitbox = new Rectangle(x, y, size, size);
        sprite = new Sprite(new Texture("cake.png"));
        velocity = new Vector2(push, 0);
        sprite.setPosition(x, y);
        sprite.setSize(size, size);
        sprite.setOriginCenter();
        angle = (float)Math.random()*10-5;
    }

    public void update(float deltaTime) {
        hitbox.x += velocity.x * deltaTime;
        hitbox.y += velocity.y * deltaTime;

        velocity.y += acceleration * deltaTime;

        isOnScreen();

        if (wasOnScreen) {
            if (hitbox.x > MainGame.width - hitbox.width) {
                hitbox.x = MainGame.width - hitbox.width;
                velocity.x *= -1;
            }
            if (hitbox.x < 0) {
                hitbox.x = 0;
                velocity.x *= -1;
            }
        }

        sprite.setPosition(hitbox.x, hitbox.y);


        sprite.rotate(angle);
    }

    public void draw(SpriteBatch b) {
        sprite.draw(b);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void reset(int x, int y, int size, float push) {
        hitbox = new Rectangle(x, y, size, size);
        velocity = new Vector2(push, 0);
        sprite.setPosition(x, y);
        sprite.setSize(size, size);
        sprite.setOriginCenter();
        angle = (float)Math.random()*10-5;
    }

    public void isOnScreen() {
        if (hitbox.x > 0 && hitbox.x < MainGame.width - hitbox.width) {
            wasOnScreen = true;
        }
    }
}
