package com.montagut.alber.androidgame.Engine.characters;

import com.montagut.alber.androidgame.Engine.GameEngine;

public class Crab extends Enemy
{

    private int x0, x1, incX;

    public Crab(GameEngine gameEngine, int x0, int x1, int y)
    {
        super(gameEngine, x0, y - 5);
        this.x0 = x0;
        this.x1 = x1;
        this.incX = 1;
    }

    private static final int[][] ANIMATIONS = new int[][]{
            new int[]{16, 17, 16, 17, 16, 17, 18, 19, 18, 19, 18, 19}
    };

    @Override
    int[][] getAnimations()
    {
        return ANIMATIONS;
    }

    @Override
    void updatePhysics(int delta)
    {
        this.x += incX;
        if (x <= x0)
        {
            incX = 1;
        }
        if (x >= x1)
        {
            incX = -1;
        }
    }

    @Override
    void updateCollisionRect()
    {
        int top = y + 8 - ((sprite < 6) ? 8 : 0);
        int bottom = y + 22 + ((sprite >= 6) ? 8 : 0);
        collisionRect.set(x, top, x + 32, bottom);
    }

}