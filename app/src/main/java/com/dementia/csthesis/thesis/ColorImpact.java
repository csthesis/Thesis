package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class ColorImpact extends AppCompatActivity {
    //pause
    Dialog popup;

    MediaPlayer sht, bk, ms, ph;

    //screen size
    private int screenWidth;
    private int screenHeight;
    //images
    private TextView bar, bar1, bar2, bar3;
    private ImageView player;

    private ImageView life1;
    private ImageView life2;
    private ImageView life3;

    //imageview for projectiles
    private ImageView bluef;
    private ImageView greenf;
    private ImageView redf;

    //buttons
    private Button blue;
    private Button red;
    private Button green;

    //trigger
    private boolean contbar, contbar1 = false, contbar2 = false, contbar3 = false, contgen = true;
    private boolean contb = true;
    private boolean contr = true;
    private boolean contg = true;
    private int tick = 1, x1 = 0, x2 = 0, x3 = 0;

    //position
    private float barX1, barX2, barX3;
    private float barY1, barY2, barY3;

    //initialize variables for X Y axis of the image
    private float redX;
    private float redY;

    private float blueX;
    private float blueY;

    private float greenX;
    private float greenY;

    private float playerY;

    private int xbar, xbar1 =0 , xbar2 = 0, xbar3 = 0, z1 = 1, z2 = 2, z3 = 3;
    private int life;
    private int score;
    private float speed, firespeed;

    private TextView scoreText;

    //initialize class
    private Handler handler = new Handler();

    private int fireInt;

    private float topbar1, topbar2, topbar3, botbar1, botbar2, botbar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_impact);
        popup = new Dialog(this);

        bar1 = findViewById(R.id.bar1);
        bar2 = findViewById(R.id.bar2);
        bar3 = findViewById(R.id.bar3);

        player = findViewById(R.id.player);

        redf = findViewById(R.id.redF);
        bluef = findViewById(R.id.blueF);
        greenf = findViewById(R.id.greenF);

        blue = findViewById(R.id.blueFire);
        red = findViewById(R.id.redFire);
        green = findViewById(R.id.greenFire);

        life1 = findViewById(R.id.life1);
        life2 = findViewById(R.id.life2);
        life3 = findViewById(R.id.life3);

        scoreText = findViewById(R.id.scoreText);

        //get screen edge
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;

        life = 3;
        score = 0;

        speed = (float) (screenHeight * 0.0015);
        firespeed = (float) (screenHeight * 0.011);

        bar1.post(new Runnable() {
            @Override
            public void run() {
                barX1 = (player.getX() + player.getWidth() /2) - bar1.getWidth()/2;
                barY1 = 0 - bar1.getHeight() * 1;

                bar1.setX((player.getX() + player.getWidth() /2) - bar1.getWidth()/2);
                bar1.setY(0 - bar1.getHeight() * 1);

                topbar1 = bar1.getY();
                botbar1 = bar1.getY() - bar1.getHeight();
            }
        });

        bar2.post(new Runnable() {
            @Override
            public void run() {
                barX2 = (player.getX() + player.getWidth() /2) - bar2.getWidth()/2;
                barY2 = 0 - bar1.getHeight() * 2;

                bar2.setX((player.getX() + player.getWidth() /2) - bar2.getWidth()/2);
                bar2.setY(0 - bar1.getHeight() * 2);

                topbar2 = bar2.getY();
                botbar2 = bar2.getY() - bar2.getHeight();
            }
        });

        bar3.post(new Runnable() {
            @Override
            public void run() {
                barX3 = (player.getX() + player.getWidth() /2) - bar3.getWidth()/2;
                barY3 = 0 - bar1.getHeight() * 3;

                bar3.setX((player.getX() + player.getWidth() /2) - bar3.getWidth()/2);
                bar3.setY(0 - bar1.getHeight() * 3);

                topbar3 = bar3.getY();
                botbar3 = bar3.getY() - bar3.getHeight();
            }
        });



        //======================================================================================

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoot();
                //button delay
                blue.setEnabled(false);
                blue.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        blue.setEnabled(true);
                    }
                }, 500);

                fireInt = 2;

                //initial position relative to the player image
                blueX = (player.getX() + player.getWidth() /2) - bluef.getWidth()/2;
                blueY = (player.getY() - player.getHeight()/2);

                //set the position of the fire relative to the player image
                bluef.setX((player.getX() + player.getWidth() /2) - bluef.getWidth()/2);
                bluef.setY(player.getY() - player.getHeight()/2);

                //show image
                bluef.setVisibility(View.VISIBLE);

                //turn contb to false to stop the animation
                contb = true;
                handler.postDelayed(changePosBlue, tick);

            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoot();
                //button delay
                red.setEnabled(false);
                red.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        red.setEnabled(true);
                    }
                }, 500);

                fireInt = 1;

                //initial pos
                redX = (player.getX() + player.getWidth() /2) - redf.getWidth()/2;
                redY = (player.getY() - player.getHeight()/2);

                redf.setX((player.getX() + player.getWidth() /2) - redf.getWidth()/2);
                redf.setY(player.getY() - player.getHeight()/2);

                redf.setVisibility(View.VISIBLE);
                contr = true;
                handler.postDelayed(changePosRed, tick);
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoot();
                //button delay
                green.setEnabled(false);
                green.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        green.setEnabled(true);
                    }
                }, 500);

                fireInt = 3;

                //initial pos
                greenX = (player.getX() + player.getWidth() /2) - greenf.getWidth()/2;
                greenY = (player.getY() - player.getHeight()/2);

                greenf.setX((player.getX() + player.getWidth() /2) - greenf.getWidth()/2);
                greenf.setY(player.getY() - player.getHeight()/2);

                greenf.setVisibility(View.VISIBLE);
                contg = true;
                handler.postDelayed(changePosGreen, tick);
            }
        });

        //=======================================================================================


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                contbar1 = true;
                handler.postDelayed(barcp1, tick);
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                contbar2 = true;
                handler.postDelayed(barcp2, tick);

            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                contbar3 = true;
                handler.postDelayed(barcp3, tick);
            }
        }, 2000);




    }

    public int randBar(TextView bar){

        xbar = (int)(Math.random() * 3 + 1);

        switch (xbar){
            case 1:
                bar.setBackgroundColor(getResources().getColor(R.color.redFire));
                break;
            case 2:
                bar.setBackgroundColor(getResources().getColor(R.color.blueFire));
                break;
            case 3:
                bar.setBackgroundColor(getResources().getColor(R.color.greenFire));
                break;
        }

        return xbar;
    }

    private final Runnable barcp1 = new Runnable() {
        @Override
        public void run() {
            if(x1 == 0){
                barY1 = 0 - (bar1.getHeight() * 1);
                bar1.setVisibility(View.VISIBLE);
                xbar1 = randBar(bar1);
                x1 = 1;
            }

            if(botbar1 > topbar2 && botbar1 < botbar2 ){
                barReset(bar1, z1);
                bar1.setVisibility(View.VISIBLE);
            }
            if(botbar1 > topbar3 && botbar1 < botbar3){
                barReset(bar1, z1);
                bar1.setVisibility(View.VISIBLE);
            }

            playerY = player.getY();
            if(x1 == 1){
                barY1 += speed;
                topbar1 = bar1.getY();
                botbar1 = bar1.getY() + bar1.getHeight();
            }
//
//            red.setText("bar1 = " +barY1);

            if((bar1.getY() + bar1.getHeight()) > playerY){
                playerHit();
                barReset(bar1, z1);
                deducLife();

                x1 = 0;
            }

            bar1.setX(barX1);
            bar1.setY(barY1);

            if(contbar1){
                //loop handler
                handler.postDelayed(barcp1, tick);
            }
        }
    };

    private final Runnable barcp2 = new Runnable() {
        @Override
        public void run() {
            //reset y position
            if(x2 == 0){
                barY2 = 0 - (bar2.getHeight() * 2);
                bar2.setVisibility(View.VISIBLE);
                xbar2 = randBar(bar2);
                x2 = 1;
            }

            //check if bars are overlapping
            if(botbar2 > topbar3 && botbar2 < botbar3 ){
                barReset(bar2, z2);
                bar2.setVisibility(View.VISIBLE);
            }
            if(botbar2 > topbar1 && botbar2 < botbar1){
                barReset(bar2, z2);
                bar2.setVisibility(View.VISIBLE);
            }

            playerY = player.getY();
            //move bar
            if(x1 == 1){
                barY2 += speed;
                topbar2 = bar2.getY();
                botbar2 = bar2.getY() + bar2.getHeight();
            }

//            blue.setText("bar2 = " +barY2);

            if((bar2.getY() + bar2.getHeight()) > playerY){
                playerHit();
                barReset(bar2, z2);
                deducLife();

                x2 = 0;
            }

            bar2.setX(barX2);
            bar2.setY(barY2);
            if(contbar2){
                //loop handler
                handler.postDelayed(barcp2, tick);
            }
        }
    };

    private final Runnable barcp3 = new Runnable() {
        @Override
        public void run() {
            //x3 = 0:
            if(x3 == 0){
                barY3 = 0 - (bar1.getHeight() * 3);
                bar3.setVisibility(View.VISIBLE);
                xbar3 = randBar(bar3);
                x3 = 1;
            }
            if(botbar3 > topbar2 && botbar3 < botbar2 ){
                barReset(bar3, z3);
                bar3.setVisibility(View.VISIBLE);
            }
            if(botbar3 > topbar1 && botbar3 < botbar1){
                barReset(bar3, z3);
                bar3.setVisibility(View.VISIBLE);
            }

            playerY = player.getY();
            if(x1 == 1){
                barY3 += speed;
                topbar3 = bar3.getY();
                botbar3 = bar3.getY() + bar3.getHeight();
            }

//            green.setText("bar3 = " +barY3);

            if((bar3.getY() + bar3.getHeight()) > playerY){
                playerHit();
                barReset(bar3, z3);
                deducLife();

                x3 = 0;
            }

            bar3.setX(barX3);
            bar3.setY(barY3);
            if(contbar3){
                //loop handler
                handler.postDelayed(barcp3, tick);
            }
        }
    };

    public void barReset(TextView img, int count){
        img.setX((player.getX() + player.getWidth() /2) - img.getWidth()/2);
        img.setY(0 - (img.getHeight() * count));

        if(count == 1){
            barX1 = (player.getX() + player.getWidth() /2) - img.getWidth()/2;
            barY1 = 0 - (img.getHeight() * count);
        }
        if(count == 2){
            barX2 = (player.getX() + player.getWidth() /2) - img.getWidth()/2;
            barY2 = 0 - (img.getHeight() * count);
        }
        if(count == 3){
            barX3 = (player.getX() + player.getWidth() /2) - img.getWidth()/2;
            barY3 = 0 - (img.getHeight() * count);
        }


        img.setVisibility(View.GONE);
    }

    public void scoreAdd() {
        score += 450;
        String x = new Integer(score).toString();
        scoreText.setText(x);

        if(score >= 5000){
            contbar1 = false;
            contbar2 = false;
            contbar3 = false;
            Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
            startIntent.putExtra("SCORE", score);
            startActivity(startIntent);
        }
    }

    public void deducLife(){

        //deduc life
        life -= 1;

        switch (life){
            case 2:
                life3.setImageResource(R.drawable.blackheart);
                break;
            case 1:
                life2.setImageResource(R.drawable.blackheart);
                break;
            case 0:
                life1.setImageResource(R.drawable.blackheart);
                contbar1 = false;
                contbar2 = false;
                contbar3 = false;
                if(score > 0){
                    Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);
                }else if (score == 0){
                    Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);
                }
                break;
        }
    }


    private final Runnable changePosRed = new Runnable() {
        @Override
        public void run() {
            //fire
            redY -= firespeed;
            if(redf.getY() < bar1.getY() + bar1.getHeight()){
                bar1hit();
                redX = (player.getX() + player.getWidth() /2) - redf.getWidth()/2;
                redY = player.getY() - player.getHeight()/2;
                contr = false;
                redf.setVisibility(View.INVISIBLE);
            }
            if(redf.getY() < bar2.getY() + bar2.getHeight()){
                bar2hit();
                redX = (player.getX() + player.getWidth() /2) - redf.getWidth()/2;
                redY = player.getY() - player.getHeight()/2;
                contr = false;
                redf.setVisibility(View.INVISIBLE);
            }
            if(redf.getY() < bar3.getY() + bar3.getHeight()){
                bar3hit();
                redX = (player.getX() + player.getWidth() /2) - redf.getWidth()/2;
                redY = player.getY() - player.getHeight()/2;
                contr = false;
                redf.setVisibility(View.INVISIBLE);
            }

            redf.setX(redX);
            redf.setY(redY);

            if(contr){
                handler.postDelayed(changePosRed, tick);
            }
        }
    };

    private final Runnable changePosBlue = new Runnable() {
        @Override
        public void run() {
            //fire speed value to decrement the Y position
            blueY -= firespeed;

            //check if the image has reached screen edge or bar Y axis
            if(bluef.getY() < bar1.getY()){
                bar1hit();
                //return fire img position to initial
                blueX = (player.getX() + player.getWidth() /2) - bluef.getWidth()/2;
                blueY = player.getY() - player.getHeight()/2;
                //stop the animation
                contb = false;
                //set the image to invisible
                bluef.setVisibility(View.INVISIBLE);
            }
            if(bluef.getY() < bar2.getY()){
                bar2hit();
                //return fire img position to initial
                blueX = (player.getX() + player.getWidth() /2) - bluef.getWidth()/2;
                blueY = player.getY() - player.getHeight()/2;
                //stop the animation
                contb = false;
                //set the image to invisible
                bluef.setVisibility(View.INVISIBLE);
            }
            if(bluef.getY() < bar3.getY()){
                bar3hit();
                //return fire img position to initial
                blueX = (player.getX() + player.getWidth() /2) - bluef.getWidth()/2;
                blueY = player.getY() - player.getHeight()/2;
                //stop the animation
                contb = false;
                //set the image to invisible
                bluef.setVisibility(View.INVISIBLE);
            }

            //move the image
            bluef.setX(blueX);
            bluef.setY(blueY);

            if(contb){
                //loop handler
                handler.postDelayed(changePosBlue, tick);
            }
        }
    };

    private final Runnable changePosGreen = new Runnable() {
        @Override
        public void run() {
            //fire
            greenY -= firespeed;
            if(greenf.getY() < bar1.getY()){
                bar1hit();
                //return fire img position to initial
                greenX = (player.getX() + player.getWidth() /2) - greenf.getWidth()/2;
                greenY = player.getY() - player.getHeight() /2;
                contg = false;
                greenf.setVisibility(View.INVISIBLE);
            }
            if(greenf.getY() < bar2.getY()){
                bar2hit();
                //return fire img position to initial
                greenX = (player.getX() + player.getWidth() /2) - greenf.getWidth()/2;
                greenY = player.getY() - player.getHeight() /2;
                contg = false;
                greenf.setVisibility(View.INVISIBLE);
            }
            if(greenf.getY() < bar3.getY()){
                bar3hit();
                //return fire img position to initial
                greenX = (player.getX() + player.getWidth() /2) - greenf.getWidth()/2;
                greenY = player.getY() - player.getHeight() /2;
                contg = false;
                greenf.setVisibility(View.INVISIBLE);
            }

            greenf.setX(greenX);
            greenf.setY(greenY);

            if(contg){
                handler.postDelayed(changePosGreen, tick);
            }
        }
    };



    public void bar1hit(){

        if(xbar1 == fireInt){
            brk();
            //reset the bar
            barReset(bar1, z1);

            x1 = 0;
            scoreAdd();
        }
        else {
            miss();
            deducLife();
        }
    }

    public void bar2hit(){

        if(xbar2 == fireInt){
            brk();
            //reset the bar
            barReset(bar2, z2);

            x2 = 0;
            scoreAdd();
        }
        else {
            miss();
            deducLife();
        }
    }

    public void bar3hit(){

        if(xbar3 == fireInt){
            brk();
            //reset the bar
            barReset(bar3, z3);

            x3 = 0;
            scoreAdd();
        }
        else {
            miss();
            deducLife();
        }
    }

    public void playerHit(){
        ph = MediaPlayer.create(this, R.raw.playerhit);
        if(!ph.isPlaying()){
            ph.start();
            ph.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    ph = null;
                }
            });
        }
        else if (ph.isPlaying()) {
            ph.stop();
            ph.start();
            ph.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    ph = null;
                }
            });
        }
    }

    public void shoot(){

        sht = MediaPlayer.create(this, R.raw.shoot);

        if(!sht.isPlaying()){
            sht.start();
            sht.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    sht = null;
                }
            });
        }
        else if (sht.isPlaying()) {
            sht.stop();
            sht.start();
            sht.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    sht = null;
                }
            });
        }
    }

    public void brk(){
        bk = MediaPlayer.create(this, R.raw.brek);
        if(!bk.isPlaying()){
            bk.start();
            bk.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    bk = null;
                }
            });
        }
        else if (bk.isPlaying()) {
            bk.stop();
            bk.start();
            bk.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    bk = null;
                }
            });
        }
    }

    public void miss(){
        ms = MediaPlayer.create(this, R.raw.miss);
        if(!ms.isPlaying()){
            ms.start();
            ms.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    ms = null;
                }
            });
        }
        else if (ms.isPlaying()) {
            ms.stop();
            ms.start();
            ms.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    ms = null;
                }
            });
        }
    }

    public void pause(View view){

        contbar1 = false;
        contbar2 = false;
        contbar3 = false;

        popup.setContentView(R.layout.activity_ingame_pause);
        popup.show();

        Button pauseClose = popup.findViewById(R.id.ingamePauseClose);
        Button returnGame = popup.findViewById(R.id.ingameReturn);
        Button main = popup.findViewById(R.id.ingameExit);

        pauseClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contbar1 = true;
                handler.postDelayed(barcp1, tick);
                contbar2 = true;
                handler.postDelayed(barcp2, tick);
                contbar3 = true;
                handler.postDelayed(barcp3, tick);
                popup.dismiss();


            }
        });
        returnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contbar1 = true;
                handler.postDelayed(barcp1, tick);
                contbar2 = true;
                handler.postDelayed(barcp2, tick);
                contbar3 = true;
                handler.postDelayed(barcp3, tick);

                popup.dismiss();

            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(startIntent);


            }
        });

        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onBackPressed(){
        pause(null);
    }




}
