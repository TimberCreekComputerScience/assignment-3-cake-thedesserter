package com.mygdx.desserter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.desserter.objects.FallingObject;
import com.mygdx.desserter.manager.FallingPool;
import com.mygdx.desserter.GameController;

import java.util.ArrayList;
import java.util.Random;

public class MainGame implements Screen {
    SpriteBatch batch;
    OrthographicCamera camera;

    public static int width = 1200;
    public static int height = 800;

    Random r;

    // making map
    ArrayList<Rectangle> edges;
    boolean[][] isBlocked;

    ArrayList<Sprite> sprites;


    GameController myGame;
    public MainGame(GameController g){
        myGame = g;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        r = new Random();

        // making map
//        edges = new ArrayList<Rectangle>();
//        edges.add(new Rectangle(0, height/17 * 7, width, height/17 * 2));
//        edges.add(new Rectangle(0, 0, width, height/17));
//        edges.add(new Rectangle(0,0, width/24, height));
//        edges.add(new Rectangle(width/24*15, 0, width/24, height));

        sprites = new ArrayList<Sprite>();

        // Blocking bottom
        isBlocked = new boolean[24][17];

        for(int i = 0; i<24; i++){
            isBlocked[i][0]=true;
        }
        // Blocking Top
        for(int i = 0; i<24; i++){
            isBlocked[i][16]=true;
        }
        // Blocking Top
        for(int i = 0; i<24; i++){
            isBlocked[i][15]=true;
        }
        // Blocking left
        for(int i = 0; i<17; i++){
            isBlocked[0][i]=true;
        }
        // Blocking right
        for(int i = 0; i<17; i++){
            isBlocked[23][i]=true;
        }

        createMap();


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        for (Sprite s : sprites) {
            s.draw(batch);
        }

        batch.end();





        if (Gdx.input.isTouched()) {
            Vector3 touched = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touched);
        }

    }

    public boolean inbounds(int x, int y){
        if(x>= 0 && x <24 & y>=0 && y < 17) return true;
        return false;
    }

    public void genmap() {
        for (int i=0; i<24; i++) {
            for (int j=0; j<17; j++) {
                if (isBlocked[i][j]) {
                    Sprite sprite = new Sprite(new Texture("cake.png"));
                    sprite.setSize(width/24, height/17);
                    sprite.setPosition(i * width/24, j * height/17);
                    sprite.draw(batch);
                }
            }
        }
    }

    public void createMap() {
        int rows[] = {2, 4, 6, 8, 10, 12, 14, 16};
        for (int r : rows) {
            createPlatform(r);
        }
    }

    public void createPlatform(int i) {
        ArrayList<Sprite> platforms = new ArrayList<Sprite>();
        ArrayList<Integer> holes = new ArrayList<Integer>();

        for (int j=0; j< 24; j++) {
            if (!isBlocked[j][i] && !isBlocked[j+1][i] && r.nextBoolean() && platforms.size() < 20 && holes.size() < 6) {
                int size = (int)(Math.random() * (5 - 2)) + 2;
                for (int k=0; k<size; k++) {
                    if (j+k < 23) {
                        Sprite sprite = new Sprite(new Texture("badlogic.jpg"));
                        sprite.setSize(width / 24, height / 17);
                        sprite.setPosition((j + k) * width / 24, i * height / 17);
                        sprites.add(sprite);
                        isBlocked[j + k][i] = true;
                        platforms.add(sprite);
                    }
                }
                if (j+size <= 23) {
                    isBlocked[j + size][i] = true;
                }
                holes.add(1);
            }
        }
        System.out.println(platforms.size());
        platforms.clear();
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
    }
}
