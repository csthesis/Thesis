package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
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

public class numberImpact extends AppCompatActivity {
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
    private TextView num1proj, num2proj, num3proj;

    //buttons
    private Button num1f, num2f, num3f;

    //trigger
    private boolean contbar, contbar1 = false, contbar2 = false, contbar3 = false, contgen = true;
    private boolean contnum1 = true;
    private boolean contnum2 = true;
    private boolean contnum3 = true;
    private int tick = 1, x1 = 0, x2 = 0, x3 = 0;

    //position
    private float barX1, barX2, barX3;
    private float barY1, barY2, barY3;

    //initialize variables for X Y axis of the image
    private float num1x;
    private float num1y;

    private float num2x;
    private float num2y;

    private float num3x;
    private float num3y;

    private float playerY, bound;

    private int xbar, xbar1 =0 , xbar2 = 0, xbar3 = 0, z1 = 1, z2 = 2, z3 = 3;
    private int life;
    private int score;
    private float barspeed, firespeed;

    private TextView scoreText;

    //initialize class
    private Handler handler = new Handler();

    private int numInt;

    private int num1val, num2val, num3val;

    private int numx1, numx2, numz1, numz2, numz3, operand, total, xr1, xr2, xr3 , xtop, xbot, dum1, dum2, ctr;

    private Button[] btn = new Button[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_impact);
        popup = new Dialog(this);

        scoreText = findViewById(R.id.scoreText2);

        bar1 = findViewById(R.id.bar1);

        player = findViewById(R.id.player);

        num1proj = findViewById(R.id.num1);
        num2proj = findViewById(R.id.num2);
        num3proj = findViewById(R.id.num3);

        num1f = findViewById(R.id.num1fire);
        num2f = findViewById(R.id.num2fire);
        num3f = findViewById(R.id.num3fire);

        btn[0] = num1f;
        btn[1] = num2f;
        btn[2] = num3f;

        life1 = findViewById(R.id.life6);
        life2 = findViewById(R.id.life7);
        life3 = findViewById(R.id.life8);

        //get screen edge
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;

        life = 3;
        score = 0;

        barspeed = (float) (screenHeight * 0.0008);
        firespeed = (float) (screenHeight * 0.011);

        bar1.post(new Runnable() {
            @Override
            public void run() {
                barX1 = (player.getX() + player.getWidth() /2) - bar1.getWidth()/2;
                barY1 = 0 - bar1.getHeight() * 3;

                bar1.setX((player.getX() + player.getWidth() /2) - bar1.getWidth()/2);
                bar1.setY(0 - bar1.getHeight() * 3);
            }
        });


        num1f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoot();
                //button delay
                num1f.setEnabled(false);
                num1f.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        num1f.setEnabled(true);
                    }
                }, 500);

                num1val = Integer.parseInt(num1f.getText().toString());
                num1proj.setText("" +num1val);

                //initial position relative to the player image
                num1x = (player.getX() + player.getWidth() /2) - num1proj.getWidth()/2;
                num1y = (player.getY() - num1proj.getHeight() /2);

                //set the position of the fire relative to the player image
                num1proj.setX((player.getX() + player.getWidth() /2) - num1proj.getWidth()/2);
                num1proj.setY(player.getY() - num1proj.getHeight() /2);

                //show image
                num1proj.setVisibility(View.VISIBLE);

                //turn contb to false to stop the animation
                contnum1 = true;
                handler.postDelayed(changePosNum1, tick);

            }
        });

        num2f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoot();
                //button delay
                num2f.setEnabled(false);
                num2f.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        num2f.setEnabled(true);
                    }
                }, 500);

                num2val = Integer.parseInt(num2f.getText().toString());
                num2proj.setText("" +num2val);

                //initial pos
                num2x = (player.getX() + player.getWidth() /2) - num2proj.getWidth()/2;
                num2y = (player.getY() - num1proj.getHeight()/2);

                num2proj.setX((player.getX() + player.getWidth() /2) - num2proj.getWidth()/2);
                num2proj.setY(player.getY() - num1proj.getHeight()/2);

                num2proj.setVisibility(View.VISIBLE);
                contnum2 = true;
                handler.postDelayed(changePosNum2, tick);
            }
        });

        num3f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoot();
                //button delay
                num3f.setEnabled(false);
                num3f.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        num3f.setEnabled(true);
                    }
                }, 500);

                num3val = Integer.parseInt(num3f.getText().toString());
                num3proj.setText("" +num3val);

                //initial pos
                num3x = (player.getX() + player.getWidth() /2) - num3proj.getWidth()/2;
                num3y = (player.getY() - num1proj.getHeight()/2);

                num3proj.setX((player.getX() + player.getWidth() /2) - num3proj.getWidth()/2);
                num3proj.setY(player.getY() - num1proj.getHeight()/2);

                num3proj.setVisibility(View.VISIBLE);
                contnum3 = true;
                handler.postDelayed(changePosNum3, tick);
            }
        });
        //=======================================================================================


        contbar1 = true;
        handler.postDelayed(barcp1, tick);
    }

    public int randBar(TextView bar){
        Random rand = new Random();

        operand = rand.nextInt(2);
        //addition
        if(operand == 0){
            numx1 = rand.nextInt(30 - 10) + 10;
            numx2 = rand.nextInt(30 - 10) + 10;
            total = numx1 + numx2;

            bar.setText(""+numx1+" + "+numx2+" = ?");

        }
        else if(operand == 1){
            numx1 = rand.nextInt(30 - 20) + 20;
            numx2 = rand.nextInt(numx1 - 10) + 10;
            total = numx1 - numx2;

            bar.setText(""+numx1+" - "+numx2+" = ?");

        }

        xtop = total + 5;
        xbot = total - 5;

        dum1 = rand.nextInt((xtop - total) + 1) + total + 1;
        dum2 = rand.nextInt((total - 1) - xbot) + xbot;


        //=======================================================
        xr1 = rand.nextInt(3);
        btn[xr1].setText(String.valueOf(dum1));
        //=======================================================
        xr2 = rand.nextInt(3);
        for(ctr = 1; ctr > 0; ctr++){
            if(xr1 == xr2){
                xr2 = rand.nextInt(3);
            }else if(xr1 != xr2){
                ctr = 0;
                break;
            }
        }
        btn[xr2].setText(String.valueOf(dum2));
        //=======================================================

        xr3 = rand.nextInt(3);
        for(ctr = 1; ctr > 0; ctr++){
            if(xr3 == xr2 || xr3 == xr1){
                xr3 = rand.nextInt(3);
            }else if(xr3 != xr2 && xr3 != xr1){
                ctr = 0;
                break;
            }
        }
        btn[xr3].setText(String.valueOf(total));

        numInt = total;

        return total;

    }

    private final Runnable barcp1 = new Runnable() {
        @Override
        public void run() {
            if(x1 == 0){
                barY1 = 0 - (bar1.getHeight() * 1);
                bar1.setVisibility(View.VISIBLE);
                numz1 = randBar(bar1);
                x1 = 1;
            }

            playerY = player.getY();

            if(x1 == 1){
                barY1 += barspeed;
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



    private final Runnable changePosNum1= new Runnable() {
        @Override
        public void run() {
            //fire speed value to decrement the Y position
            num1y -= firespeed;

            //check if the image has reached screen edge or bar Y axis
            if(num1proj.getY() < (bar1.getY() + bar1.getHeight())){
                bar1hit(num1val);
                //return fire img position to initial
                num1x = (player.getX() + player.getWidth() /2) - num1proj.getWidth()/2;
                num1y = player.getY() - player.getHeight()/2;
                //stop the animation
                contnum1 = false;
                //set the image to invisible
                num1proj.setVisibility(View.INVISIBLE);
            }

            //move the image
            num1proj.setX(num1x);
            num1proj.setY(num1y);

            if(contnum1){
                //loop handler
                handler.postDelayed(changePosNum1, tick);
            }
        }
    };
    private final Runnable changePosNum2 = new Runnable() {
        @Override
        public void run() {
            //fire speed value to decrement the Y position
            num2y -= firespeed;

            //check if the image has reached screen edge or bar Y axis
            if(num2proj.getY() < (bar1.getY() + bar1.getHeight())){
                bar1hit(num2val);
                //return fire img position to initial
                num2x = (player.getX() + player.getWidth() /2) - num2proj.getWidth()/2;
                num2y = player.getY() - player.getHeight()/2;
                //stop the animation
                contnum2 = false;
                //set the image to invisible
                num2proj.setVisibility(View.INVISIBLE);
            }

            //move the image
            num2proj.setX(num2x);
            num2proj.setY(num2y);

            if(contnum2){
                //loop handler
                handler.postDelayed(changePosNum2, tick);
            }
        }
    };

    private final Runnable changePosNum3 = new Runnable() {
        @Override
        public void run() {
            //fire speed value to decrement the Y position
            num3y -= firespeed;

            //check if the image has reached screen edge or bar Y axis
            if(num3proj.getY() < (bar1.getY() + bar1.getHeight())){
                bar1hit(num3val);
                //return fire img position to initial
                num3x = (player.getX() + player.getWidth() /2) - num3proj.getWidth()/2;
                num3y = player.getY() - player.getHeight()/2;
                //stop the animation
                contnum3 = false;
                //set the image to invisible
                num3proj.setVisibility(View.INVISIBLE);
            }

            //move the image
            num3proj.setX(num3x);
            num3proj.setY(num3y);

            if(contnum3){
                //loop handler
                handler.postDelayed(changePosNum3, tick);
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
        score += 850;
        String x = new Integer(score).toString();
        scoreText.setText(x);

        if(score >= 5000){
            contbar1 = false;
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

    public void bar1hit(int qwe){

        if(qwe == numInt){
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

        if(contbar1){
            contbar1 = false;
        }

        popup.setContentView(R.layout.activity_ingame_pause);
        popup.show();

        Button pauseClose = popup.findViewById(R.id.ingamePauseClose);
        Button returnGame = popup.findViewById(R.id.ingameReturn);
        Button main = popup.findViewById(R.id.ingameExit);

        pauseClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!contbar1){
                    contbar1 = true;
                    handler.postDelayed(barcp1, tick);
                }
                popup.dismiss();


            }
        });
        returnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!contbar1){
                    contbar1 = true;
                    handler.postDelayed(barcp1, tick);
                }
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
