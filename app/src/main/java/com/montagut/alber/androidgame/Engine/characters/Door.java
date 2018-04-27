package com.montagut.alber.androidgame.Engine.characters;

import com.montagut.alber.androidgame.Engine.GameEngine;

public class Door extends Character
{

    private int sceneToLoad;

    public Door(GameEngine gameEngine, int x, int y, int sceneToLoad)
    {
        super(gameEngine, x, y);
        this.sceneToLoad = sceneToLoad;
    }

    private static final int[][] ANIMATIONS = new int[][]{
            new int[]{56}
    };

    public int getSceneToLoad(){
        return this.sceneToLoad;
    }

    @Override
    int[][] getAnimations()
    {
        return ANIMATIONS;
    }


    @Override
    void updatePhysics(int delta)
    {

    }

    @Override
    void updateCollisionRect()
    {
        collisionRect.set(x, y, x + 24, y + 32 );
    }
}
