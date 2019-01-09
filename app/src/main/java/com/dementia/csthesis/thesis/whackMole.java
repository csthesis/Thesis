package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class whackMole extends AppCompatActivity {
    Dialog popup;

    MediaPlayer player;

    private CountDownTimer cTimer;
    private long mTimeLeftInMills = 21000;

    private int b[] = {
            R.id.pos1,
            R.id.pos2,
            R.id.pos3,
            R.id.pos4,
            R.id.pos5,
            R.id.pos6,
            R.id.pos7,
            R.id.pos8,
            R.id.pos9,
            R.id.pos10,
            R.id.pos11
    };
    private Button btn[] = new Button[11];

    private int x = 0, ctr, score = 0, rbtChance;

    private boolean isOut[] = new boolean[11];

    private TextView scoretv, timer;

    private Handler handler = new Handler();

    private boolean f = true, isRabbit = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whack_mole);

        //popup declaration
        popup = new Dialog(this);

        scoretv = findViewById(R.id.scoreText);

        timer = findViewById(R.id.timer);

        for(ctr = 0; ctr < 10; ctr++){
            isOut[ctr] = false;
        }


        btn[0] = (Button) findViewById(b[0]);
        btn[1] = (Button) findViewById(b[1]);
        btn[2] = (Button) findViewById(b[2]);
        btn[3] = (Button) findViewById(b[3]);
        btn[4] = (Button) findViewById(b[4]);
        btn[5] = (Button) findViewById(b[5]);
        btn[6] = (Button) findViewById(b[6]);
        btn[7] = (Button) findViewById(b[7]);
        btn[8] = (Button) findViewById(b[8]);
        btn[9] = (Button) findViewById(b[9]);
        btn[10] = (Button) findViewById(b[10]);


        btn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[0]){
                    hit();
                }
            }
        });
        btn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[1]){
                    hit();
                }
            }
        });
        btn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[2]){
                    hit();
                }
            }
        });
        btn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[3]){
                    hit();
                }
            }
        });
        btn[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[4]){
                    hit();
                }
            }
        });
        btn[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[5]){
                    hit();
                }
            }
        });
        btn[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[6]){
                    hit();
                }
            }
        });
        btn[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[7]){
                    hit();
                }
            }
        });
        btn[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[8]){
                    hit();
                }
            }
        });
        btn[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[9]){
                    hit();
                }
            }
        });
        btn[10].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOut[10]){
                    hit();
                }
            }
        });

        startTimer();
        burrow();
        handler.postDelayed(randPos, 1000);

    }

    private final Runnable randPos = new Runnable() {

        @Override
        public void run() {
            Random rand = new Random();
            if(f){

                x = rand.nextInt(11);
                rbtChance = rand.nextInt(100);
                btn[x].setVisibility(View.VISIBLE);

                //10% chance that a rabbit appears instead of a mole
                if(rbtChance <= 10){
                    isRabbit = true;
                    btn[x].setBackground(getDrawable(R.drawable.rabbit));
                }
                else{
                    isRabbit = false;
                    btn[x].setBackground(getDrawable(R.drawable.mole));
                }

                isOut[x] = true;

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run(){
                    btn[x].setBackground(getDrawable(R.drawable.hole));
                    isOut[x] = false;

                    }
                }, 1000);

                handler.postDelayed(randPos, 2000);
            }
        }
    };


    public void startTimer(){
        cTimer = new CountDownTimer(mTimeLeftInMills, 1000){

            public void onTick(long millisUntilFinished) {
                mTimeLeftInMills = millisUntilFinished;
                String x = new Long(millisUntilFinished / 1000).toString();
                timer.setText(x);
            }

            public void onFinish(){
                cTimer.cancel();

                if(player != null && player.isPlaying()){
                    player.stop();
                }

                f = false;
                if(score == 0){
                    Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);
                }else if (score >= 1000){
                    Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);
                }
            }
        }.start();
    }


    public void hit(){

        if(player != null && player.isPlaying()){
            player.stop();
        }

        for(ctr = 0;ctr < 11; ctr++){
            isOut[ctr] = false;
        }

        if(isRabbit){
            score -= 100;
            rbt();
            btn[x].setBackground(getDrawable(R.drawable.hole));
            String xscore = new Integer(score).toString();
            scoretv.setText(xscore);
        }

        else if(!isRabbit){
            score += 500;
            //sfx
            moleSqueal();
            btn[x].setBackground(getDrawable(R.drawable.hole));
            String xscore = new Integer(score).toString();
            scoretv.setText(xscore);
        }
    }

    public void burrow(){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.burrow);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
        else if (player.isPlaying()) {
            player.stop();
            player = MediaPlayer.create(this, R.raw.burrow);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
    }

    public void moleSqueal(){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.mole);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
        else if (player.isPlaying()) {
            player.stop();
            player = MediaPlayer.create(this, R.raw.mole);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
    }

    public void rbt(){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.mole);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
        else if (player.isPlaying()) {
            player.stop();
            player = MediaPlayer.create(this, R.raw.mole);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
    }

    public void pause(View v){
        f = false;
        cTimer.cancel();
        popup.setContentView(R.layout.activity_ingame_pause);
        popup.show();

        if(player != null && player.isPlaying()){
            player.stop();
        }


        Button pauseClose = popup.findViewById(R.id.ingamePauseClose);
        Button returnGame = popup.findViewById(R.id.ingameReturn);
        Button main = popup.findViewById(R.id.ingameExit);

        pauseClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                f = true;
                handler.postDelayed(randPos, 1200);
                popup.dismiss();
            }
        });
        returnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                f = true;
                handler.postDelayed(randPos, 1200);
                popup.dismiss();
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(startIntent);
                finish();

            }
        });

        //make the bg transparent
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onBackPressed(){
        pause(null);
    }
}
