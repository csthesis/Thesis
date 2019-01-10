package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class colorTap extends AppCompatActivity {
    Dialog popup;
    private Button[] b = new Button[8];
    private int scoreValues;
    private int r, t, y, v,yez,add = 0;
    private int p = 0;
    private TextView score;
    private ImageView col;
    private Random rand = new Random();
    private CountDownTimer cTimer;
    private TextView timer;
    private long mTimeLeftInMills = 16000;
    private ProgressBar mProgressBar;
    private int color[] = {R.color.redMatch, R.color.orangeMatch, R.color.yellowMatch, R.color.greenMatch, R.color.blueMatch, R.color.cyanMatch, R.color.brownMatch, R.color.blueFire};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_tap);
        popup = new Dialog(this);


        r = rand.nextInt(3);
        t = 0;
        b[0] = findViewById(R.id.b1);
        b[1] = findViewById(R.id.b2);
        b[2] = findViewById(R.id.b3);
        b[3] = findViewById(R.id.b4);
        b[4] = findViewById(R.id.b5);
        b[5] = findViewById(R.id.b6);
        b[6] = findViewById(R.id.b7);
        b[7] = findViewById(R.id.b8);
        timer = findViewById(R.id.timers);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setProgress(p);
        startTimer();
        cols();

        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                cols();
                scorz();

            }

        });
        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cols();
                scorz();

            }
        });
        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cols();
                scorz();

            }
        });
        b[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cols();
                scorz();

            }
        });
        b[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cols();
                scorz();

            }
        });
        b[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cols();
                scorz();

            }
        });

        b[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cols();
                scorz();

            }
        });
        b[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cols();
                scorz();

            }
        });

    }

    public void scorz() {
        score = findViewById(R.id.skor);
        scoreValues += 250;

        String scoreStrings = new Integer(scoreValues).toString();
        score.setText(scoreStrings);


    }

    public void cols() {
        if (t == 0) {


            b[y].setVisibility(View.INVISIBLE);

            y = rand.nextInt(8);

            b[y].setVisibility(View.VISIBLE);
            b[y].setBackgroundColor(getResources().getColor(color[r]));


            r = rand.nextInt(8);


        }


    }

    public void startTimer() {
        cTimer = new CountDownTimer(mTimeLeftInMills, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimeLeftInMills = millisUntilFinished;
                String n = new Long(millisUntilFinished / 1000).toString();
                timer.setText(n);
                p++;
                yez = 15000;

                mProgressBar.setProgress((int)p*100/(yez /1000));

            }

            public void onFinish() {
                p++;
                mProgressBar.setProgress(100);
                if(scoreValues <= 0){
                    Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
                    startIntent.putExtra("SCORE", scoreValues);
                    startActivity(startIntent);

                }else if (scoreValues > 0){
                    Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
                    startIntent.putExtra("SCORE", scoreValues);
                    startActivity(startIntent);
                }
            }
        }.start();
    }

    public void pause(View view){
        cTimer.cancel();

        popup.setContentView(R.layout.activity_ingame_pause);
        popup.show();

        Button pauseClose = popup.findViewById(R.id.ingamePauseClose);
        Button returnGame = popup.findViewById(R.id.ingameReturn);
        Button main = popup.findViewById(R.id.ingameExit);

        pauseClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                popup.dismiss();
            }
        });
        returnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
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