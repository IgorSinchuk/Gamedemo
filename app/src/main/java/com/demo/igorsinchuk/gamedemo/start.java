package com.demo.igorsinchuk.gamedemo;


import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class start extends AppCompatActivity {

    private TextView startGame, about;

    private Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startGame = (TextView) findViewById(R.id.startGame);
        about = (TextView) findViewById(R.id.about);

        tf = Typeface.createFromAsset(getAssets(), "ARCADECLASSIC.TTF");
        startGame.setTypeface(tf);
        about.setTypeface(tf);


    }



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
