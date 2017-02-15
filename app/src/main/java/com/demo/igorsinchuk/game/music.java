package com.demo.igorsinchuk.game;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by igorsinchuk on 2/13/17.
 */

public class music {

    private static SoundPool soundPool;
    private static int explosionSound;
    private static int overSound;
    private int startSound;
    private int powerupSound;

    public music(Context context) {

        /*SoundPool (int maxStreams,
        int streamType,
        int scrQuality)**/
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);


        explosionSound = soundPool.load(context, R.raw.explosion, 1);
        overSound = soundPool.load(context, R.raw.over, 1);
        startSound = soundPool.load(context, R.raw.start, 1);
        powerupSound = soundPool.load(context, R.raw.powerup, 1);


    }

    public void playHitSound() {
        //play.(int soundID, float leftVolume,  float rightVolume, int priority, int loop, float rate)
        soundPool.play(explosionSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playOverSound() {
        soundPool.play(overSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playStartSound() {
        soundPool.play(startSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public void playPowerupSound() {
        soundPool.play(powerupSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}

