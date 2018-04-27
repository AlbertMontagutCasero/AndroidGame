package com.montagut.alber.androidgame.Engine;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.montagut.alber.androidgame.R;

public class Audio
{
    private MediaPlayer mediaPlayer;    // media player for background music
    private SoundPool soundPool;        // sound pool for sound effects
    private int[] fx;                   // sound effects holder
    private static final int[] fxRes = {R.raw.coin, R.raw.die, R.raw.pause};

    public Audio(Context context)
    {
        // Prepping the media player
        mediaPlayer = MediaPlayer.create(context, R.raw.music);
        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(0.25f, 0.25f);

        // Prepping the sound pool
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        fx = new int[fxRes.length];
        for (int i = 0; i < fxRes.length; i++)
        {
            fx[i] = soundPool.load(context, fxRes[i], 1);
        }
    }

    // Start & stop music
    public void startMusic()
    {
        mediaPlayer.start();
    }

    public void stopMusic()
    {
        mediaPlayer.pause();
    }

    private void playEffect(int n)
    {
        soundPool.play(fx[n], 1, 1, 1, 0, 1);
    }

    // Useful methods
    public void coin()
    {
        playEffect(0);
    }

    public void die()
    {
        playEffect(1);
    }

    public void pause()
    {
        playEffect(2);
    }
}