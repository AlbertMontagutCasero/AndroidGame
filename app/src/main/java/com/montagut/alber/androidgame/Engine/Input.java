package com.montagut.alber.androidgame.Engine;

public class Input
{
    private boolean left, right, jump, pause;

    public void goLeft()
    {
        left = true;
        right = false;
    }

    public void goRight()
    {
        left = false;
        right = true;
    }
    public void disableInput() {}

    public void stopLR()
    {
        left = right = false;
    }

    public void stop()
    {
        left = right = jump = false;
    }

    public void jump()
    {
        jump = true;
    }

    public void clearJump()
    {
        jump = false;
    }

    public void pause()
    {
        pause = true;
    }

    public void clearPause()
    {
        pause = false;
    }

    public boolean isLeft()
    {
        return left;
    }

    public boolean isRight()
    {
        return right;
    }

    public boolean isJump()
    {
        return jump;
    }

    public boolean isJumpL()
    {
        return jump & left;
    }

    public boolean isJumpR()
    {
        return jump & right;
    }

    public boolean isJumpU()
    {
        return jump & !left & !right;
    }

    public boolean isPaused()
    {
        return pause;
    }

    public boolean isStoppedLR()
    {
        return !left & !right;
    }

    public boolean isStopped()
    {
        return !left & !right & !jump;
    }

    // needed for keyboard input
    private boolean keyboard;

    public void setKeyboard(boolean keyboard)
    {
        this.keyboard = keyboard;
    }

    public boolean isKeyboard()
    {
        return keyboard;
    }
}
