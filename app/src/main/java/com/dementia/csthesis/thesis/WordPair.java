package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WordPair extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    Dialog popup;

    private Button a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4;
    private int a1num, a2num, a3num, a4num, b1num, b2num, b3num, b4num,
            c1num, c2num, c3num, c4num, d1num, d2num, d3num, d4num;

    private TextView score;
    private int scoreValue = 0;

    private int x,f =0,k = 0;

    private Button btn1;
    private Button base;

    private int btnInt;
    private int btnNum, btnNum1, btnNum2;
    private Button btn;

    private int btnValue1;
    private int btnValue2;

    private int ctr = 0;
    private int sc;

    private Integer[] shuffle;
    private  int xctr = 0;

    private boolean isDone = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_pair);
        popup = new Dialog(this);
        TextView lb = findViewById(R.id.label);
        shuffle = new Integer[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8};
        lb.setText("Word Pair");
        List<Integer> list = Arrays.asList(shuffle);
        Collections.shuffle(list);

        randomColor();
        score = findViewById(R.id.colorpairscore);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 1;
                btn = a1;
                btnInt = a1num;
                clicked();
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 2;
                btn = a2;
                btnInt = a2num;
                clicked();
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 3;
                btn = a3;
                btnInt = a3num;
                clicked();
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 4;
                btn = a4;
                btnInt = a4num;
                clicked();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 5;
                btn = b1;
                btnInt = b1num;
                clicked();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 6;
                btn = b2;
                btnInt = b2num;
                clicked();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 7;
                btn = b3;
                btnInt = b3num;
                clicked();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 8;
                btn = b4;
                btnInt = b4num;
                clicked();
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 9;
                btn = c1;
                btnInt = c1num;
                clicked();
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 10;
                btn = c2;
                btnInt = c2num;
                clicked();
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 11;
                btn = c3;
                btnInt = c3num;
                clicked();
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 12;
                btn = c4;
                btnInt = c4num;
                clicked();
            }
        });

        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 13;
                btn = d1;
                btnInt = d1num;
                clicked();
            }
        });

        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 14;
                btn = d2;
                btnInt = d2num;
                clicked();
            }
        });

        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 15;
                btn = d3;
                btnInt = d3num;
                clicked();
            }
        });

        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNum = 16;
                btn = d4;
                btnInt = d4num;
                clicked();
            }
        });

    }

    public void clicked(){
        if(ctr == 0 && isDone){
            btnValue1 = btnInt;
            ctr = 1;
            sc = btnInt;
            base = btn;
            btn1 = btn;

            btnNum1 = btnNum;

            btn1.setEnabled(false);
            //sc, base
            showColor();
        }
        else if (ctr == 1 && isDone){
            btnValue2 = btnInt;
            sc = btnInt;
            base = btn;
            ctr = 0;

            btnNum2 = btnNum;

            showColor();
            check();
        }
    }

    public void check(){

        if(btnValue1 == btnValue2){
            hit();
            isDone = false;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    inviButtons();
                    isDone = true;
                }
            },500);
        }
        else{

            isDone = false;
            //delay
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    btn1.setBackground(getDrawable(R.drawable.cardback));
                    base.setBackground(getDrawable(R.drawable.cardback));
                    base.setText("");
                    btn1.setText("");
                    btn1.setEnabled(true);
                    base.setEnabled(true);
                    k += 1;
                    isDone = true;
//                    enableButons();

                }
            }, 500);
        }
    }

    public void hit(){
        if(scoreValue != 8000){
            scoreValue += 1000;
            finlscr();
            k = 0;
            String scorestring = new Integer(scoreValue).toString();
            score.setText(scorestring);

            //enableButtons
        }
        if (scoreValue == 8000){
            Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
            startIntent.putExtra("SCORE", scoreValue);
            startActivity(startIntent);

        }

    }

    public void randomColor(){

        //column A
        a1 = findViewById(R.id.a1);
        base = a1;
        colorRand();
        a1num = x;
        a2 = findViewById(R.id.a2);
        base = a2;
        colorRand();
        a2num = x;
        a3 = findViewById(R.id.a3);
        base = a3;
        colorRand();
        a3num = x;
        a4 = findViewById(R.id.a4);
        base = a4;
        colorRand();
        a4num = x;

        //column B
        b1 = findViewById(R.id.b1);
        base = b1;
        colorRand();
        b1num = x;
        b2 = findViewById(R.id.b2);
        base = b2;
        colorRand();
        b2num = x;
        b3 = findViewById(R.id.b3);
        base = b3;
        colorRand();
        b3num  = x;
        b4 = findViewById(R.id.b4);
        base = b4;
        colorRand();
        b4num = x;

        //column C
        c1 = findViewById(R.id.c1);
        base = c1;
        colorRand();
        c1num = x;
        c2 = findViewById(R.id.c2);
        base = c2;
        colorRand();
        c2num = x;
        c3 = findViewById(R.id.c3);
        base = c3;
        colorRand();
        c3num = x;
        c4 = findViewById(R.id.c4);
        base = c4;
        colorRand();
        c4num =x;

        //column D
        d1 = findViewById(R.id.d1);
        base = d1;
        colorRand();
        d1num = x;
        d2 = findViewById(R.id.d2);
        base = d2;
        colorRand();
        d2num = x;
        d3 = findViewById(R.id.d3);
        base = d3;
        colorRand();
        d3num = x;
        d4 = findViewById(R.id.d4);
        base = d4;
        colorRand();
        d4num = x;

    }

    public void showColor(){

        switch (sc) {
            case 1:
                base.setBackgroundColor(getResources().getColor(R.color.bgColor));
                base.setText("Thank You!");
                base.setTextColor(getResources().getColor(R.color.redMatch));

                break;

            case 2:
                base.setBackgroundColor(getResources().getColor(R.color.bgColor));
                base.setText("Hello!");
                base.setTextColor(getResources().getColor(R.color.blueMatch));
                break;

            case 3:
                base.setBackgroundColor(getResources().getColor(R.color.bgColor));
                base.setText("Good Morning!");
                base.setTextColor(getResources().getColor(R.color.greenMatch));
                break;

            case 4:
                base.setBackgroundColor(getResources().getColor(R.color.bgColor));
                base.setText("I Love You!");
                base.setTextColor(getResources().getColor(R.color.orangeMatch));
                break;

            case 5:
                base.setBackgroundColor(getResources().getColor(R.color.bgColor));
                base.setText("Best Friend!");
                base.setTextColor(getResources().getColor(R.color.violetMatch));
                break;

            case 6:
                base.setBackgroundColor(getResources().getColor(R.color.bgColor));
                base.setText("Grand Mother!");
                base.setTextColor(getResources().getColor(R.color.brownMatch));
                break;

            case 7:
                base.setBackgroundColor(getResources().getColor(R.color.bgColor));
                base.setText("HAHAHAHA!");
                base.setTextColor(getResources().getColor(R.color.cyanMatch));
                break;

            case 8:
                base.setBackgroundColor(getResources().getColor(R.color.bgColor));
                base.setText("Sunset!");
                base.setTextColor(getResources().getColor(R.color.yellowMatch));
                break;
        }
    }

    public int colorRand() {

        x = shuffle[xctr];
        xctr += 1;

        return x;
    }

    private void inviButtons(){
        btn1.setVisibility(View.INVISIBLE);
        base.setVisibility(View.INVISIBLE);

    }

    public void pause(View v){
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
    public void finlscr(){

        f = k * 30;
        scoreValue -= f;


    }



}
