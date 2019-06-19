package com.mygdx.desserter.manager;

import com.badlogic.gdx.utils.Pool;
import com.mygdx.desserter.objects.FallingObject;

public class FallingPool extends Pool<FallingObject> {

    @Override
    protected FallingObject newObject() {
        FallingObject temp = new FallingObject(0,0, 0, 0);
        return temp;
    }



}