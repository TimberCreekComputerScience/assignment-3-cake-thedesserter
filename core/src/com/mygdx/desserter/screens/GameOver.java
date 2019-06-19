package com.mygdx.desserter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.desserter.GameController;

public class GameOver implements Screen {
    SpriteBatch batch;
    GameController myGame;

    OrthographicCamera camera;

    public GameOver(GameController g)
    {
        myGame = g;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1000, 600);

    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);

        if(Gdx.input.justTouched()) {
            Vector3 touched = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touched);

            myGame.setScreen(new MainMenu(myGame));


        }

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

    }
}
