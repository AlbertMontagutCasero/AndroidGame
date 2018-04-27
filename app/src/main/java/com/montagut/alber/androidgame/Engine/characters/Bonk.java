package com.montagut.alber.androidgame.Engine.characters;

import android.graphics.Rect;

import com.montagut.alber.androidgame.Engine.Audio;
import com.montagut.alber.androidgame.Engine.GameEngine;
import com.montagut.alber.androidgame.Engine.Input;
import com.montagut.alber.androidgame.Engine.Scene;

import java.util.Timer;
import java.util.TimerTask;

public class Bonk extends Character
{

    private static final int[][] ANIMATIONS = new int[][]{
            {12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 14, 14, 14, 14, 13}, // 0: standing by
            {6, 7, 8, 9},                         // 1: walking left
            {0, 1, 2, 3},                         // 2: walking right
            {47, 48, 49, 50, 51, 50, 49, 48},     // 3: dead
            {10},                                 // 4: Jumping left
            {4},                                  // 5: Jumping right
            {11},                                 // 6: Falling left
            {5},                                  // 7: Falling right
            {15},                                 // 8: Jumping/falling front
    };

    @Override
    int[][] getAnimations()
    {
        return ANIMATIONS;
    }

    private static final int[] NEW_STATES = {
            4, 8, 5,
            1, 0, 2,
            6, 8, 7
    };

    private int vx;         // vel-X is 1 or 2 (boosted velocity)
    private int vy;
    private boolean isJumping;
    private static final int MAX_FALL_VELOCITY = 4;
    private static final int JUMP_VELOCITY = -10;

    private static final int PAD_LEFT = 2;
    private static final int PAD_TOP = 0;
    private static final int COLLISION_WIDTH = 20;
    private static final int COLLISION_HEIGHT = 32;

    private int totalScore;
    private int life;
    private int boostedVelocity;
    private int normalVelocity;

    private Audio audio;

    public Bonk(GameEngine gameEngine, Audio audio, int x, int y)
    {
        super(gameEngine, x, y);

        this.totalScore = 0;
        this.life = 3;
        this.audio = audio;
        this.boostedVelocity = 4;
        this.normalVelocity = 2;

        reset(x, y);
    }

    private void reset(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.vx = normalVelocity;
    }

    private void changeState(int state)
    {
        if (this.state == state)
        {
            return;
        }
        this.state = state;
        this.sprite = 0;
    }

    public void die()
    {
        downLife();
        changeState(3);

        if (getLife() <= 0)
        {
            gameEngine.setGameOver(true);
            return;
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                changeState(0);
                reset(100, 0);
            }
        }, 3 * 1000);
    }

    public int getTotalScore()
    {
        return this.totalScore;
    }

    public void setTotalScore(int score)
    {
        this.totalScore = score;
    }

    public void addTotalScore(int scoreToAdd)
    {
        this.totalScore += scoreToAdd;
    }

    public int getLife()
    {
        return this.life;
    }

    public void downLife()
    {
        this.life--;
    }

    public void setVX(int newVX)
    {
        this.vx = newVX;
    }

    public int getVx()
    {
        return this.vx;
    }

    @Override
    void updatePhysics(int delta)
    {
        // If died, no physics
        if (state == 3 || (getLife() <= 0))
        {
            return;
        }

        // Analyze user input
        int vx = 0;
        Input input = gameEngine.getInput();
        if (input.isLeft())
        {
            vx = -this.vx;
        }
        else if (input.isRight())
        {
            vx = this.vx;
        }
        if (input.isJump())
        {
            if (!isJumping)
            {
                vy = JUMP_VELOCITY;
                isJumping = true;
            }
            input.clearJump();
        }
        if (input.isKeyboard())
        {
            input.setKeyboard(false);
        }

        // Apply physics and tests to scene walls and grounds
        Scene scene = gameEngine.getScene();

        // 1) detect wall to right
        int newX = x + vx;
        int newY = y;
        if (vx > 0)
        {
            int col = (newX + PAD_LEFT + COLLISION_WIDTH) / 16;
            int r1 = (newY + PAD_TOP) / 16;
            int r2 = (newY + PAD_TOP + COLLISION_HEIGHT - 1) / 16;
            for (int row = r1; row <= r2; row++)
            {
                if (scene.isWall(row, col))
                {
                    newX = col * 16 - PAD_LEFT - COLLISION_WIDTH - 1;
                    break;
                }
            }
        }

        // 2) detect wall to left
        if (vx < 0)
        {
            int col = (newX + PAD_LEFT) / 16;
            int r1 = (newY + PAD_TOP) / 16;
            int r2 = (newY + PAD_TOP + COLLISION_HEIGHT - 1) / 16;
            for (int row = r1; row <= r2; row++)
            {
                if (scene.isWall(row, col))
                {
                    newX = (col + 1) * 16 - PAD_LEFT;
                    break;
                }
            }
        }

        // 3) detect ground
        // physics (try fall and detect ground)
        vy++;
        if (vy > MAX_FALL_VELOCITY)
        {
            vy = MAX_FALL_VELOCITY;
        }
        newY = y + vy;
        if (vy >= 0)
        {
            int c1 = (newX + PAD_LEFT) / 16;
            int c2 = (newX + PAD_LEFT + COLLISION_WIDTH) / 16;
            int row = (newY + PAD_TOP + COLLISION_HEIGHT) / 16;
            for (int col = c1; col <= c2; col++)
            {
                if (scene.isGround(row, col))
                {
                    newY = row * 16 - PAD_TOP - COLLISION_HEIGHT;
                    vy = 0;
                    isJumping = false;
                    break;
                }
            }
        }

        // 4) detect ceiling
        if (vy < 0)
        {
            int c1 = (newX + PAD_LEFT) / 16;
            int c2 = (newX + PAD_LEFT + COLLISION_WIDTH) / 16;
            int row = (newY + PAD_TOP) / 16;
            for (int col = c1; col <= c2; col++)
            {
                if (scene.isWall(row, col))
                {
                    newY = (row + 1) * 16 - PAD_TOP;
                    vy = 0;
                    break;
                }
            }
        }

        //Update Score
        Coin coin;
        for (int i = 0; i < scene.getCoins().size(); i++)
        {
            coin = scene.getCoins().get(i);
            if (this.getCollisionRect().intersect(coin.getCollisionRect()))
            {
                this.addTotalScore(coin.getScoreValue());
                audio.coin();
                scene.getCoins().remove(i);
                break;
            }
        }

        Booster booster;
        for (int i = 0; i < scene.getBoosters().size(); i++)
        {
            booster = scene.getBoosters().get(i);
            if (this.getCollisionRect().intersect(booster.getCollisionRect()))
            {
                this.vx = boostedVelocity;
                scene.getBoosters().remove(i);

                Timer timer = new Timer();
                timer.schedule(new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        setVX(normalVelocity);
                    }
                }, booster.getSecondsDuration() * 1000);
                break;
            }
        }

        Enemy enemy;
        for (int i = 0; i < scene.getEnemies().size(); i++)
        {
            enemy = scene.getEnemies().get(i);
            if (this.getCollisionRect().intersect(enemy.getCollisionRect()))
            {
                this.die();
                return;
            }
        }

        Door door;
        for (int i = 0; i < scene.getDoors().size(); i++)
        {
            door = scene.getDoors().get(i);
            if (this.getCollisionRect().intersect(door.getCollisionRect()))
            {
                scene.changeToLevel(door.getSceneToLoad());
                return;
            }
        }

        // apply resulting physics
        x = newX;
        y = newY;

        // screen limits
        x = Math.max(x, -PAD_LEFT);
        x = Math.min(x, scene.getWidth() - COLLISION_WIDTH);
        y = Math.min(y, scene.getHeight() - COLLISION_HEIGHT);

        // state change
        int c = (vx < 0) ? 0 : ((vx == 0) ? 1 : 2);
        int r = (vy < 0) ? 0 : ((vy == 0) ? 1 : 2);
        changeState(NEW_STATES[r * 3 + c]);
    }

    @Override
    void updateCollisionRect()
    {
        collisionRect.set(
                x + PAD_LEFT,
                y + PAD_TOP,
                x + PAD_LEFT + COLLISION_WIDTH,
                y + PAD_TOP + COLLISION_HEIGHT
        );
    }

    @Override
    public Rect getCollisionRect()
    {
        return (state == 3) ? null : collisionRect;
    }
}