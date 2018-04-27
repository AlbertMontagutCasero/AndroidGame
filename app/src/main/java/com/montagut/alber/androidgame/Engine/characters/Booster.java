package com.montagut.alber.androidgame.Engine.characters;


import com.montagut.alber.androidgame.Engine.GameEngine;

public class Booster extends Character
{

    private int secondsDuration;

    private static final int[][] ANIMATIONS = new int[][]{
            new int[]{54,52,53,55}
    };

    public Booster(GameEngine gameEngine, int x, int y, int secondsDuration)
    {
        super(gameEngine, x, y);
        this.secondsDuration = secondsDuration;
    }

    public int getSecondsDuration(){
        return this.secondsDuration;
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
        collisionRect.set(x, y, x + 16, y + 16);
    }
}
