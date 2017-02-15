package com.demo.igorsinchuk.game;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;


public class result extends AppCompatActivity {

    private TextView textOver;
    private TextView again;

    //font
    Typeface tp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);
        textOver = (TextView) findViewById(R.id.textOver);
        again = (TextView) findViewById(R.id.again);


        //custom font
        tp = Typeface.createFromAsset(getAssets(), "ARCADECLASSIC.TTF");
        textOver.setTypeface(tp);
        scoreLabel.setTypeface(tp);
        highScoreLabel.setTypeface(tp);
        again.setTypeface(tp);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = settings.getInt("HIGH_SCORE", 0);

        if (score > highScore) {
            highScoreLabel.setText("High score : " + score);

            //save
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("HIGH_SCORE", score);
            editor.commit();

        } else {
            highScoreLabel.setText("High score : " + highScore);
        }
    }

    //textButton
    public void again(View view) {
        startActivity(new Intent(getApplicationContext(), main.class));
    }
    /*//animation
    public void blink (View view) {
        android.view.animation.Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        textOver.startAnimation(animation);
    }
    **/


    //disable return btn
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;

            }
        }

        return super.dispatchKeyEvent(event);
    }
}