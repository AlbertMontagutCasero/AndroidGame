package com.montagut.alber.androidgame.Engine.characters;

import com.montagut.alber.androidgame.Engine.GameEngine;

public class Coin extends Character
{

    private int scoreValue;

    public Coin(GameEngine gameEngine, int x, int y)
    {
        super(gameEngine, x, y);
        this.sprite = (int) (Math.random() * 5);
        this.scoreValue = 10;
    }

    private static final int[][] ANIMATIONS = new int[][]{
            new int[]{29, 30, 31, 32, 33}
    };

    public int getScoreValue()
    {
        return this.scoreValue;
    }

    @Override
    int[][] getAnimations()
    {
        return ANIMATIONS;
    }

    @Override
    void updatePhysics(int delta)
    {}

    @Override
    void updateCollisionRect()
    {
        collisionRect.set(x, y, x + 12, y + 12);
    }
}
