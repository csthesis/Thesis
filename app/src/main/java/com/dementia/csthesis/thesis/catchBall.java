package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class catchBall extends AppCompatActivity {
    Dialog popup;
    MediaPlayer ballhit, ballmiss;
    private ImageView life1;
    private ImageView life2;
    private ImageView life3;

    private TextView b1, b2, b3;

    private Handler handler = new Handler();

    private RelativeLayout layout;
    private Button player;

    private float x, y ,screenWidth, screenHeight;

    private float b1yT, b1yB, b1xL, b1xR,b2yT, b2yB, b2xL, b2xR,  b3yT, b3yB, b3xL, b3xR;

    private float b1mid, b2mid, b3mid;

    private float playerY, playerxL, playerxR, b1speed, b2speed, b3speed, randX, rightBound;

    private int life = 3, score = 0, rx;

    private boolean contb1, contb2, contb3;

    private int b1x = 0, b2x = 1, b3x = 2;

    private int ball1 = 0, ball2 = 0 , ball3 = 0;

    private TextView scoretv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catch_ball);

        scoretv = findViewById(R.id.scr2);

        b1 = findViewById(R.id.ball1);
        b2 = findViewById(R.id.ball2);
        b3 = findViewById(R.id.ball3);


        layout = findViewById(R.id.layout);
        player = findViewById(R.id.player);

        //get screen edge
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;

        b1speed = (float) (screenHeight * 0.0025);
        b2speed = (float) (screenHeight * 0.0028);
        b3speed = (float) (screenHeight * 0.0023);

        life1 = findViewById(R.id.life13);
        life2 = findViewById(R.id.life14);
        life3 = findViewById(R.id.life15);

        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            x = event.getX();
            if(event.getAction() == MotionEvent.ACTION_MOVE){
                if(x > 0 && x < (screenWidth - player.getWidth() )){
                    player.setX(x);
                }
                playerxL = player.getX();
                playerxR = player.getX() + player.getWidth();
            }
                return true;

            }
        });


        b3.post(new Runnable() {

            @Override
            public void run() {
                rightBound = screenWidth - b3.getWidth();
                resetBall(b1);
                resetBall(b2);
                resetBall(b3);
            }
        });

        player.post(new Runnable() {

            @Override
            public void run() {
                playerY = player.getY();
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                contb1 = true;
                handler.postDelayed(b1cp, 1);
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                contb2 = true;
                handler.postDelayed(b2cp, 1);
            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                contb3 = true;
                handler.postDelayed(b3cp, 1);
            }
        }, 2000);


    }

    private final Runnable b1cp = new Runnable() {
        @Override
        public void run() {
            if(ball1 == 0){
                resetBall(b1);
                ball1 = 1;
            }

            if(ball1 == 1){
                b1xL = b1.getX();
                b1xR = b1.getX() + b1.getWidth();
                b1yT = b1.getY();
                b1yB = b1.getY() + b1.getHeight();
                b1mid = b1.getX() + (b1.getWidth() / 2);
                b1yT += b1speed;
                b1.setY(b1yT);
            }

            //if projectile hits playerY
            if(b1yB > playerY){
                if(playerxL < b1mid && b1mid < playerxR){
                    resetBall(b1);
                    hit(b1x);
                }
                if(b1yT > screenHeight){
                    resetBall(b1);
                    deduc();
                }
                ball1 = 0;
            }
            if(contb1){

                //loop handler
                handler.postDelayed(b1cp, 1);
            }
        }
    };

    private final Runnable b2cp = new Runnable() {
        @Override
        public void run() {
            if(ball2 == 0){
                resetBall(b2);
                ball2 = 1;
            }

            if(ball2 == 1){
                b2xL = b2.getX();
                b2xR = b2.getX() + b2.getWidth();
                b2yT = b2.getY();
                b2yB = b2.getY() + b2.getHeight();
                b2mid = b2.getX() + (b2.getWidth() / 2);
                b2yT += b2speed;
                b2.setY(b2yT);
            }

            //if projectile hits playerY
            if(b2yB > playerY){
                if(playerxL < b2mid && b2mid < playerxR){
                    resetBall(b2);
                    hit(b2x);
                }
                if(b2yT > screenHeight){
                    resetBall(b2);
                    deduc();
                }
                ball2 = 0;
            }
            if(contb1){
                //loop handler
                handler.postDelayed(b2cp, 1);
            }
        }
    };

    private final Runnable b3cp = new Runnable() {
        @Override
        public void run() {
            if(ball3 == 0){
                resetBall(b3);
                ball3 = 1;
            }

            if(ball3 == 1){
                b3xL = b3.getX();
                b3xR = b3.getX() + b3.getWidth();
                b3yT = b3.getY();
                b3yB = b3.getY() + b3.getHeight();
                b3mid = b3.getX() + (b3.getWidth() / 2);
                b3yT += b3speed;
                b3.setY(b3yT);
            }

            //if projectile hits playerY
            if(b3yB > playerY){
                if(playerxL < b3mid && b3mid < playerxR){
                    resetBall(b3);
                    hit(b3x);
                }
                if(b3yT > screenHeight){
                    resetBall(b3);
                    deduc();
                }
                ball3 = 0;
            }
            if(contb3){
                //loop handler
                handler.postDelayed(b3cp, 1);
            }
        }
    };

    public void hit(int z){
        switch(z){
            case 0:
                score += 400;
                break;
            case 1:
                score += 600;
                break;
            case 2:
                score += 250;
                break;
        }

        String scr = new Integer(score).toString();
        scoretv.setText(scr);

    }

    public void resetBall(TextView tv){
        Random rand = new Random();
        rx = rand.nextInt((int) rightBound);
        randX = (float) rx;

        tv.setX(randX);
        float p = 0 - (b3.getHeight() * 2);
        tv.setY(p);



    }

    public void deduc(){

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

                if(score >= 2000){
                    Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);
                }else if (score < 1000){
                    Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);
                }
                break;
        }
    }

    public void pause(View view){

        popup.setContentView(R.layout.activity_ingame_pause);
        popup.show();

        Button pauseClose = popup.findViewById(R.id.ingamePauseClose);
        Button returnGame = popup.findViewById(R.id.ingameReturn);
        Button main = popup.findViewById(R.id.ingameExit);

        pauseClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();


            }
        });
        returnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
