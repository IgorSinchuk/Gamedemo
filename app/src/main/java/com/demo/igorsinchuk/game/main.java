package com.demo.igorsinchuk.game;


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


    //background
    private ImageView planet;
    private ImageView planet2;
    private ImageView planet3;
    private ImageView planet5;

    private ImageView backstar;
    private ImageView backstar2;
    private ImageView backstar3;
    private ImageView backstar4;
    private ImageView backstar5;
    private ImageView backstar6;

    private ImageView meteor;

    private ImageView spacecoin;
    private ImageView spacecoinred;

    private ImageView powerup;

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
    private int planet5X;
    private int planet5Y;

    private int backstarX;
    private int backstarY;
    private int backstar2X;
    private int backstar2Y;
    private int backstar3X;
    private int backstar3Y;
    private int backstar4X;
    private int backstar4Y;
    private int backstar5X;
    private int backstar5Y;

    private int meteorX;
    private int meteorY;

    private int spacecoinX;
    private int spacecoinY;
    private int spacecoinredX;
    private int spacecoinredY;

    private int powerupX;
    private int powerupY;

    //speed
    /**private int rocketSpeed;
    private int planetSpeed;
    private int planet2Speed;
    private int planet3Speed;
    private int planet5Speed;
    private int meteorSpeed;
    private int spacecoinSpeed;
    private int spacecoinredSpeed;
    private int powerupSpeed;
     */



    //score
    private int score = 0;

    //font
    Typeface tp;


    //initialize class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    //music (sfx)
    private music sfx;

    //static check
    private boolean actionFlag = false;
    private boolean startFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sfx = new music(this);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);


        rocket = (ImageView) findViewById(R.id.rocket);


        planet = (ImageView) findViewById(R.id.planet);
        planet2 = (ImageView) findViewById(R.id.planet2);
        planet3 = (ImageView) findViewById(R.id.planet3);
        planet5 = (ImageView) findViewById(R.id.planet5);

        backstar = (ImageView) findViewById(R.id.backstar);
        backstar2 = (ImageView) findViewById(R.id.backstar2);
        backstar3 = (ImageView) findViewById(R.id.backstar3);
        backstar4 = (ImageView) findViewById(R.id.backstar4);
        backstar5 = (ImageView) findViewById(R.id.backstar5);
        backstar6 = (ImageView) findViewById(R.id.backstar6);

        meteor = (ImageView) findViewById(R.id.meteor);

        spacecoin = (ImageView) findViewById(R.id.spacecoin);
        spacecoinred = (ImageView) findViewById(R.id.spacecoinred);

        powerup = (ImageView) findViewById(R.id.powerup);


        //custom font
        tp = Typeface.createFromAsset(getAssets(), "ARCADECLASSIC.TTF");
        scoreLabel.setTypeface(tp);
        startLabel.setTypeface(tp);

        //get screen size
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        //Nexus 4 width:768 height:1184

        /**rocketSpeed = Math.round(screenHeight/60f);
        planetSpeed= Math.round(screenWidth/60f);
        planet2Speed = Math.round(screenWidth/60f);
        planet3Speed = Math.round(screenWidth/60f);
        planet5Speed = Math.round(screenWidth/60f);
        spacecoinSpeed = Math.round(screenWidth/60f);
        spacecoinredSpeed = Math.round(screenWidth/60f);
        meteorSpeed = Math.round(screenWidth/60f);
        powerupSpeed = Math.round(screenWidth/60f);

        Log.v("SPEED_ROCKET", rocketSpeed+"");
        Log.v("SPEED_PLANET", planetSpeed+"");
        Log.v("SPEED_PLANET2", planet2Speed+"");
        Log.v("SPEED_PLANET3", planet3Speed+"");
        Log.v("SPEED_PLANET5", planet5Speed+"");
        Log.v("SPEED_SPACECOIN", spacecoinSpeed+"");
        Log.v("SPEED_SPACECOINRED", spacecoinredSpeed+"");
        Log.v("SPEED_METEOR", meteorSpeed+"");
        Log.v("SPEED_POWERUP", powerupSpeed+"");
        */



        //move to out of screen
        planet.setX(-80f);
        planet.setY(-80f);
        planet2.setX(-80f);
        planet2.setY(-80f);
        planet3.setX(-80f);
        planet3.setY(-80f);
        planet5.setX(-80f);
        planet5.setY(-80f);

        backstar.setX(-80f);
        backstar.setY(-80f);
        backstar2.setX(-80f);
        backstar2.setY(-80f);
        backstar3.setX(-80f);
        backstar3.setY(-80f);
        backstar4.setX(-80f);
        backstar4.setY(-80f);
        backstar5.setX(-80f);
        backstar5.setY(-80f);
        backstar6.setX(-80f);
        backstar6.setY(-80f);

        meteor.setX(-80f);
        meteor.setY(-80f);

        spacecoin.setX(-80f);
        spacecoin.setY(-80f);
        spacecoinred.setX(-80f);
        spacecoinred.setY(-80f);

        powerup.setX(-80f);
        powerup.setY(-80f);


        scoreLabel.setText("Score : 0");


    }

    public void changePos() {

        hitCheck();


        //background objects
        //planets
        planetX -= 4;
        if (planetX < 0) {
            planetX = screenWidth + 510;
            planetY = (int) Math.floor(Math.random() * (frameHeight - planet.getHeight()));
        }
        planet.setX(planetX);
        planet.setY(planetY);


        planet2X -= 6;
        if (planet2X < 0) {
            planet2X = screenWidth + 810;
            planet2Y = (int) Math.floor(Math.random() * (frameHeight - planet2.getHeight()));
        }
        planet2.setX(planet2X);
        planet2.setY(planet2Y);


        planet3X -= 1;
        if (planet3X < 0) {
            planet3X = screenWidth + 410;
            planet3Y = (int) Math.floor(Math.random() * (frameHeight - planet3.getHeight()));
        }
        planet3.setX(planet3X);
        planet3.setY(planet3Y);


        planet5X -= 2;
        if (planet5X < 0) {
            planet5X = screenWidth + 20;
            planet5Y = (int) Math.floor(Math.random() * (frameHeight - planet5.getHeight()));
        }
        planet5.setX(planet5X);
        planet5.setY(planet5Y);


        //death object meteor
        meteorX -= 25;
        if (meteorX < 0) {
            meteorX = screenWidth + 700;
            meteorY = (int) Math.floor(Math.random() * (frameHeight - meteor.getHeight()));
        }
        meteor.setX(meteorX);
        meteor.setY(meteorY);


        //score objects
        spacecoinX -= 20;
        if (spacecoinX < 0) {
            spacecoinX = screenWidth + 10;
            spacecoinY = (int) Math.floor(Math.random() * (frameHeight - spacecoin.getHeight()));
        }
        spacecoin.setX(spacecoinX);
        spacecoin.setY(spacecoinY);

        spacecoinredX -= 18;
        if (spacecoinredX < 0) {
            spacecoinredX = screenWidth + 3000;
            spacecoinredY = (int) Math.floor(Math.random() * (frameHeight - spacecoinred.getHeight()));
        }
        spacecoinred.setX(spacecoinredX);
        spacecoinred.setY(spacecoinredY);

        powerupX -= 48;
        if (powerupX < 0) {
            powerupX = screenWidth + 6000;
            powerupY = (int) Math.floor(Math.random() * (frameHeight - powerup.getHeight()));
        }
        powerup.setX(powerupX);
        powerup.setY(powerupY);


        //move rocket
        if (actionFlag == true) {
            //touching
            rocketY -= 20;
        } else {
            //releasing
            rocketY += 20;
        }

        //check rocket position
        if (rocketY < 0) rocketY = 0;

        if (rocketY > frameHeight - rocketSize) rocketY = frameHeight - rocketSize;

        rocket.setY(rocketY);

        scoreLabel.setText("Score : " + score);
    }

    public void hitCheck() {

        //spaceCoin

        int starCenterX = spacecoinX + spacecoin.getWidth() / 2;
        int starCenterY = spacecoinY + spacecoin.getHeight() / 2;

        if (0 <= starCenterX && starCenterX <= rocketSize &&
                rocketY <= starCenterY && starCenterY <= rocketY + rocketSize) {

            score += 10;
            spacecoinX = -20;
            sfx.playHitSound();
        }

        //spacecoinRed

        int magicCenterX = spacecoinredX + spacecoinred.getWidth() / 2;
        int magicCenterY = spacecoinredY + spacecoinred.getHeight() / 2;

        if (0 <= magicCenterX && magicCenterX <= rocketSize &&
                rocketY <= magicCenterY && magicCenterY <= rocketY + rocketSize) {

            score += 30;
            spacecoinredX = -10;
            sfx.playHitSound();
        }

        //powerUP
        int powerCenterX = powerupX + powerup.getWidth() / 2;
        int powerCenterY = powerupY + powerup.getHeight() / 2;

        if (0 <= powerCenterX && powerCenterX <= rocketSize &&
                rocketY <= powerCenterY && powerCenterY <= rocketY + rocketSize) {

            //stop timer
            score += 70;
            powerCenterX = -10;
            sfx.playPowerupSound();
        }

        //meteor
        int blackCenterX = meteorX + meteor.getWidth() / 2;
        int blackCenterY = meteorY + meteor.getHeight() / 2;

        if (0 <= blackCenterX && blackCenterX <= rocketSize &&
                rocketY <= blackCenterY && blackCenterY <= rocketY + rocketSize) {

            //stop timer
            timer.cancel();
            timer = null;
            sfx.playOverSound();


            //show result
            Intent intent = new Intent(getApplicationContext(), result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }

    }



    //onTouch evnt
    public boolean onTouchEvent(MotionEvent me) {
        if (startFlag == false) {

            startFlag = true;

            final FrameLayout frame = (FrameLayout) findViewById(R.id.frame);

            frameHeight = frame.getHeight();

            rocketY = (int)rocket.getY();

            rocketSize = rocket.getHeight();

            startLabel.setVisibility(View.GONE);
            sfx.playStartSound();

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


