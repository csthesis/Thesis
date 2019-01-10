package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class CompoundWords extends AppCompatActivity {
    Dialog popup;
    dataviz mydb;
    Random rand = new Random();
    private String id, fw, sw,w1,w2;
    private TextView t1,t2,skurs;
    private int  life = 3,x,x1,x2,x3;
    private CountDownTimer cTimer;
    private Button b[] = new Button[3];
    private Integer[] arr;
    private ImageView im,l1,l2,l3;
    private int scoreValue = 0,t = 50;
    private long mTimeLeftInMills = 30000;
    private ProgressBar mProgressBar;
    private int p = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compound_words);
        mydb = new dataviz(this);
        im = findViewById(R.id.imgs);
        t1 = findViewById(R.id.w1);
        t2 = findViewById(R.id.w2);
        b[0] = findViewById(R.id.ch1);
        b[1] = findViewById(R.id.ch2);
        b[2] = findViewById(R.id.ch3);
        skurs = findViewById(R.id.skur);
        l1 = findViewById(R.id.l1);
        l2 = findViewById(R.id.l2);
        l3 = findViewById(R.id.l3);

        popup = new Dialog(this);

        mProgressBar = findViewById(R.id.progressBar2);
        mProgressBar.setProgress(p);
        startTimer();


        retur();




    }


    public void dbOpen(){
        datavizword datavizword = com.dementia.csthesis.thesis.datavizword.getInstance(getApplicationContext());
        datavizword.open();

        id = String.valueOf(x);
        String column = "Firstword";
        fw = datavizword.getData(id,column);
        String column2 = "Secondword";
        sw = datavizword.getData(id, column2);
        String column3 = "Wrong";
        w1 = datavizword.getData(id,column3);
        String column4 = "Wrong2";
        w2 = datavizword.getData(id,column4);

        im.setImageBitmap(datavizword.getImg(id));

        datavizword.close();


    }
    public void skorz(){


            scoreValue += 1000;
            String scoreString = new Integer(scoreValue).toString();
            skurs.setText(scoreString);
            randomz();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                retur();
            }
        },500);



    }
    public void dedak(){
        life -= 1;
        switch (life) {
            case 2:
                l3.setImageResource(R.drawable.blackheart);
                break;

            case 1:
                l2.setImageResource(R.drawable.blackheart);
                break;

            case 0:
                l1.setImageResource(R.drawable.blackheart);

                popup.setContentView(R.layout.activity_game_over);
                popup.show();
                popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView sg = popup.findViewById(R.id.finalScore);
                String n = new Integer(scoreValue).toString();
                sg.setText(n);

                Button exit = popup.findViewById(R.id.mainMenu);

                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(startIntent);
                        finish();

                    }
                });


        }
    }
    public void retur(){
        arr = new Integer[]{0,1,2};

        x = t;
        if (t == 50) {
            x = rand.nextInt(20);
        }
        List<Integer> list = Arrays.asList(arr);
        Collections.shuffle(list);
        x1 = arr[0];
        x2 = arr[1];
        x3 = arr[2];
        dbOpen();
        t1.setText(fw);
        t2.setText("");
        b[x1].setText(sw);
        b[x2].setText(w1);
        b[x3].setText(w2);
        b[x1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setText(sw);

                skorz();




            }
        });
        b[x2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setText(w1);
                dedak();

            }
        });
        b[x3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.setText(w2);
                dedak();
            }
        });

    }
    public void startTimer() {
        cTimer = new CountDownTimer(mTimeLeftInMills, 1000) {
            TextView uraz = findViewById(R.id.uraz);
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMills = millisUntilFinished;
                String n = new Long(millisUntilFinished / 1000).toString();
                uraz.setText(n);
                p++;

                mProgressBar.setProgress((int)p*100/(30000 /1000));

            }

            public void onFinish() {
                p++;
                mProgressBar.setProgress(100);
                if(scoreValue <= 0){
                    Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
                    startIntent.putExtra("SCORE", scoreValue);
                    startActivity(startIntent);

                }else if (scoreValue > 0){
                    Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
                    startIntent.putExtra("SCORE", scoreValue);
                    startActivity(startIntent);
                }
            }
        }.start();
    }
    public void randomz(){
        for (int i=0;i<=1;i++){
            switch (i){
                case 0:
                    t = rand.nextInt(20);

                case 1:
                    if(t == x){
                        i -= 1;
                    }
            }
        }
    }

    public void pause(View v){
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

                Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(startIntent);
                finish();
            }
        });

        //make the bg transparent
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onBackPressed(){
        pause(null );
    }
//    public void finlscr(){
//
//        f = k * 30;
//        scoreValue -= f;
//
//
//    }

}
