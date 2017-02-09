package com.demo.igorsinchuk.gamedemo;


import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.Timer;
import java.util.TimerTask;

public class main extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;


    //stopWatch


    private ImageView rocket;
    private ImageView star;
    private ImageView magic;
    private ImageView black;

    //background
    private ImageView planet;
    private ImageView planet2;
    private ImageView planet3;
    private ImageView planet4;
    private ImageView planet5;

    private ImageView bstar1;
    private ImageView bstar2;

    //size
    private int frameHeight;
    private int rocketSize;
    private int screenWidth;
    private int screenHeight;



    //position

    private int rocketY;



    private int planetX;
    private int planetY;
    private int planet2X;
    private int planet2Y;
    private int planet3X;
    private int planet3Y;
    private int planet4X;
    private int planet4Y;
    private int planet5X;
    private int planet5Y;
    private int bstar1X;
    private int bstar1Y;
    private int bstar2X;
    private int bstar2Y;


    //score
    private int score = 0;

    //font
    Typeface tp;





    //initialize class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    //static check
    private boolean actionFlag = false;
    private boolean startFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);



        rocket = (ImageView) findViewById(R.id.rocket);


        planet = (ImageView) findViewById(R.id.planet);
        planet2 = (ImageView) findViewById(R.id.planet2);
        planet3 = (ImageView) findViewById(R.id.planet3);
        planet4 = (ImageView) findViewById(R.id.planet4);
        planet5 = (ImageView) findViewById(R.id.planet5);

        bstar1 = (ImageView) findViewById(R.id.bstar1);
        bstar2 = (ImageView) findViewById(R.id.bstar2);

        //custom font
        tp = Typeface.createFromAsset(getAssets(), "ARCADECLASSIC.TTF");
        scoreLabel.setTypeface(tp);

        //get screen size
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        //move to out of screen


        planet.setX(-80f);
        planet.setY(-80f);
        planet2.setX(-80f);
        planet2.setY(-80f);
        planet3.setX(-80f);
        planet3.setY(-80f);
        planet4.setX(-80f);
        planet4.setY(-80f);
        planet5.setX(-80f);
        planet5.setY(-80f);

        bstar1.setX(-80f);
        bstar1.setY(-80f);
        bstar2.setX(-80f);
        bstar2.setY(-80f);

        scoreLabel.setText("Score : 0");



    }

    public void changePos(){

        hitCheck();




        //background objects

        planetX -= 4;
        if (planetX < 0) {
            planetX = screenWidth + 510;
            planetY = (int) Math.floor(Math.random() * (frameHeight - planet.getHeight()));
        }
        planet.setX(planetX);
        planet.setY(planetY);



        planet2X -= 20;
        if (planet2X < 0) {
            planet2X = screenWidth + 210;
            planet2Y = (int) Math.floor(Math.random() * (frameHeight - planet2.getHeight()));
        }
        planet2.setX(planet2X);
        planet2.setY(planet2Y);


        planet3X -= 80;
        if (planet3X < 0) {
            planet3X = screenWidth + 410;
            planet3Y = (int) Math.floor(Math.random() * (frameHeight - planet3.getHeight()));
        }
        planet3.setX(planet3X);
        planet3.setY(planet3Y);


        planet4X -= 4;
        if (planet4X < 0) {
            planet4X = screenWidth + 510;
            planet4Y = (int) Math.floor(Math.random() * (frameHeight - planet4.getHeight()));
        }
        planet4.setX(planet4X);
        planet4.setY(planet4Y);


        planet5X -= 7;
        if (planet5X < 0) {
            planet5X = screenWidth + 20;
            planet5Y = (int) Math.floor(Math.random() * (frameHeight - planet5.getHeight()));
        }
        planet5.setX(planet5X);
        planet5.setY(planet5Y);


        bstar1X -= 3;
        if (bstar1X < 0) {
            bstar1X = screenWidth + 30;
            bstar1Y = (int) Math.floor(Math.random() * (frameHeight - bstar1.getHeight()));
        }
        bstar1.setX(bstar1X);
        bstar1.setY(bstar1Y);

        bstar2X -= 4;
        if (bstar2X < 0) {
            bstar2X = screenWidth + 510;
            bstar2Y = (int) Math.floor(Math.random() * (frameHeight - bstar2.getHeight()));
        }
        bstar2.setX(bstar2X);
        bstar2.setY(bstar2Y);





        //move rocket
        if (actionFlag ==true) {
            //touching
            rocketY -=20;
        } else {
            //releasing
            rocketY +=20;
        }

        //check rocket position
        if (rocketY <0) rocketY = 0;

        if (rocketY > frameHeight - rocketSize) rocketY = frameHeight - rocketSize;

        rocket.setY(rocketY);

        scoreLabel.setText("Score : " + score);
    }

    public void hitCheck() {
        //hitbox
        //star

        /*int starCenterX = starX + star.getWidth() / 2;
        int starCenterY = starY + star.getHeight() / 2;

        if (0 <= starCenterX && starCenterX <= rocketSize &&
                rocketY <= starCenterY && starCenterY <= rocketY + rocketSize) {

            score += 10;
            starX = -10;
        }

        //magic

        int magicCenterX = magicX + magic.getWidth() / 2;
        int magicCenterY = magicY + magic.getHeight() / 2;

        if (0 <= magicCenterX && magicCenterX <= rocketSize &&
                rocketY <= magicCenterY && magicCenterY <= rocketY + rocketSize) {

            score += 30;
            magicX = -10;
        }


        int blackCenterX = blackX + black.getWidth()/2;
        int blackCenterY = blackY + black.getHeight()/2;

        if (0 <= blackCenterX && blackCenterX <= rocketSize &&
                rocketY <= blackCenterY && blackCenterY <= rocketY + rocketSize) {

            //stop timer
            timer.cancel();
            timer = null;

            //show result

            Intent intent =  new Intent(getApplicationContext(), result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }
        **/

    }

    //onTouch
    public boolean onTouchEvent(MotionEvent me) {
        if (startFlag == false) {

            startFlag = true;

            final FrameLayout frame = (FrameLayout) findViewById(R.id.frame);

            frameHeight = frame.getHeight();

            rocketY = (int)rocket.getY();

            rocketSize = rocket.getHeight();

            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();

                        }
                    });
                }
            }, 0, 20);


        } else {

            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                actionFlag = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                actionFlag = false;
            }
        }

        return true;

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


