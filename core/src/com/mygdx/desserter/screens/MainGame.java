package com.mygdx.desserter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.desserter.objects.FallingObject;
import com.mygdx.desserter.manager.FallingPool;
import com.mygdx.desserter.GameController;

import java.util.ArrayList;
import java.util.Random;

public class MainGame implements Screen {
    SpriteBatch batch;
    OrthographicCamera camera;

    public static Texture img;

    ArrayList<FallingObject> chips;
    float lastBoulder = 0, boulderPerSecond = 1f;
    FallingPool pool;

    public static int width = 1200;
    public static int height = 800;

    Random r;

    GameController myGame;
    public MainGame(GameController g){
        myGame = g;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        img = new Texture("cake.png");
        chips = new ArrayList<FallingObject>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 800);
        pool = new FallingPool();
        r = new Random();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        for (FallingObject c : chips)
            c.draw(batch);

        batch.end();

        checkProjectiles: for (int j=0; j<chips.size(); j++) {
            chips.get(j).update(Gdx.graphics.getDeltaTime());

            if (chips.get(j).getHitbox().x > 1200 + chips.get(j).getHitbox().width) {
                pool.free(chips.remove(j));
                continue checkProjectiles;
            }

            if (chips.get(j).getHitbox().y < 0 - chips.get(j).getHitbox().height) {
                pool.free(chips.remove(j));
                continue checkProjectiles;
            }
        }


        lastBoulder+=Gdx.graphics.getDeltaTime();
        if (lastBoulder >= 1 / boulderPerSecond) {
            lastBoulder -= 1 / boulderPerSecond;
            int size = (int) (Math.random()*100) + 25;
            FallingObject temp = pool.obtain();
            Vector3 vector = createPos();
            temp.reset((int)vector.x, (int)vector.y, size, vector.z);
            chips.add( temp);
        }

        if (Gdx.input.isTouched()) {
            Vector3 touched = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touched);
        }

    }

    public Vector3 createPos() {
        int x;
        int z;   // push
        int y = (int) (Math.random() * ((width + 50) - (height - 200))) + (height - 200);
        if (y >= 800) {
            x = (int)(Math.random() * (height - 0)) + 0;
            z = (int)(Math.random() * (800 - -800)) + -800;
        }
        else {
            if (r.nextBoolean()) {
                x = (int)(Math.random() * (-10 - -50)) + -10;        // left to right
                z = (int)(Math.random() * (800 - 200)) + 200;
            }
            else {
                x = (int)(Math.random() * ((width + 50) - (width + 10))) + (width + 10);      // right to left
                z = (int)(Math.random() * (-200 - -800)) + -800;
            }
        }
        return new Vector3(x, y, z);
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
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
