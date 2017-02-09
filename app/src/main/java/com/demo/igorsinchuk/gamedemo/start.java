package com.demo.igorsinchuk.gamedemo;


import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

public class start extends AppCompatActivity {

    TextView start, info;

    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start = (TextView) findViewById(R.id.start);
        info = (TextView) findViewById(R.id.info);

        tf = Typeface.createFromAsset(getAssets(), "ARCADECLASSIC.TTF");
        start.setTypeface(tf);
        info.setTypeface(tf);

    }

    public void start(View view) {
        startActivity(new Intent(getApplicationContext(), main.class));
    }

    public void info(View view) {
        startActivity(new Intent(getApplicationContext(), info.class));
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
