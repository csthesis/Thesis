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
import android.widget.TextView;

public class ColorMatch extends AppCompatActivity {
    Dialog popup;

    private Button cbase;
    private int basenum;
    private Button base;

    private boolean match = false;

    private int rand;
    private int x;

    private Button a1;
    private int a1num;
    private Button a2;
    private int a2num;
    private Button a3;
    private int a3num;
    private Button a4;
    private int a4num;

    private Button b1;
    private int b1num;
    private Button b2;
    private int b2num;
    private Button b3;
    private int b3num;
    private Button b4;
    private int b4num;

    private Button c1;
    private int c1num;
    private Button c2;
    private int c2num;
    private Button c3;
    private int c3num;
    private Button c4;
    private int c4num;

    private Button d1;
    private int d1num;
    private Button d2;
    private int d2num;
    private Button d3;
    private int d3num;
    private Button d4;
    private int d4num;

    private TextView score;
    private int scoreValue = 0;

    private int btnValue;

    private TextView timer;
    private int ctimer = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_match);

        score = (TextView)findViewById(R.id.colorpairscore);
        timer = (TextView)findViewById(R.id.colormatchTimer);

        //popup declaration
        popup = new Dialog(this);

        //generate random color grid
        randomColor();

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = a1num;
                check();
                hit();
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = a2num;
                check();
                hit();
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = a3num;
                check();
                hit();
            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = a4num;
                check();
                hit();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = b1num;
                check();
                hit();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = b2num;
                check();
                hit();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = b3num;
                check();
                hit();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = b4num;
                check();
                hit();
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = c1num;
                check();
                hit();
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = c2num;
                check();
                hit();
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = c3num;
                check();
                hit();
            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = c4num;
                check();
                hit();
            }
        });

        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = d1num;
                check();
                hit();
            }
        });

        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = d2num;
                check();
                hit();
            }
        });

        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = d3num;
                check();
                hit();
            }
        });

        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnValue = d4num;
                check();
                hit();
            }
        });

        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                String x = new Long(millisUntilFinished / 1000).toString();
                timer.setText(x);

            }

            public void onFinish() {
                popup.setContentView(R.layout.activity_game_cleared);
                popup.show();

                //set score text on game clear screen
                TextView sg = popup.findViewById(R.id.scoreGained);
                String x = new Integer(scoreValue).toString();
                sg.setText(x);

                Button clearExit = popup.findViewById(R.id.clearExit);
                Button clearNext = popup.findViewById(R.id.clearNext);

                clearNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                        startActivity(startIntent);
                        finish();
                    }
                });

                clearExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(startIntent);
                        finish();
                    }
                });

                popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }.start();


        }


    public void check(){
        if(btnValue == basenum){
            match = true;
        }
    }

    public void hit(){
        if (match == true){
            scoreValue += 250;
            String scorestring = new Integer(scoreValue).toString();
            score.setText(scorestring);
            match = false;
            randomColor();
        }
        else{
            scoreValue -= 50;
            String scorestring = new Integer(scoreValue).toString();
            score.setText(scorestring);
            randomColor();
        }
    }

    public void randomColor(){
        //base color
        base = (Button)findViewById(R.id.colorBase);
        colorRand();
        basenum = x;

        //column A
        a1 = (Button)findViewById(R.id.a1);
        base = a1;
        colorRand();
        a1num = x;
        a2 = (Button)findViewById(R.id.a2);
        base = a2;
        colorRand();
        a2num = x;
        a3 = (Button)findViewById(R.id.a3);
        base = a3;
        colorRand();
        a3num = x;
        a4 = (Button)findViewById(R.id.a4);
        base = a4;
        colorRand();
        a4num = x;

        //column B
        b1 = (Button)findViewById(R.id.b1);
        base = b1;
        colorRand();
        b1num = x;
        b2 = (Button)findViewById(R.id.b2);
        base = b2;
        colorRand();
        b2num = x;
        b3 = (Button)findViewById(R.id.b3);
        base = b3;
        colorRand();
        b3num  = x;
        b4 = (Button)findViewById(R.id.b4);
        base = b4;
        colorRand();
        b4num = x;

        //column C
        c1 = (Button)findViewById(R.id.c1);
        base = c1;
        colorRand();
        c1num = x;
        c2 = (Button)findViewById(R.id.c2);
        base = c2;
        colorRand();
        c2num = x;
        c3 = (Button)findViewById(R.id.c3);
        base = c3;
        colorRand();
        c3num = x;
        c4 = (Button)findViewById(R.id.c4);
        base = c4;
        colorRand();
        c4num =x;

        //column D
        d1 = (Button)findViewById(R.id.d1);
        base = d1;
        colorRand();
        d1num = x;
        d2 = (Button)findViewById(R.id.d2);
        base = d2;
        colorRand();
        d2num = x;
        d3 = (Button)findViewById(R.id.d3);
        base = d3;
        colorRand();
        d3num = x;
        d4 = (Button)findViewById(R.id.d4);
        base = d4;
        colorRand();
        d4num = x;

    }

    public int colorRand() {
        rand = (int) (Math.random() * 6 + 1);
        x = rand;
        switch (rand) {
            case 1:
                base.setBackgroundColor(getResources().getColor(R.color.redMatch));
                break;

            case 2:
                base.setBackgroundColor(getResources().getColor(R.color.orangeMatch));
                break;

            case 3:
                base.setBackgroundColor(getResources().getColor(R.color.yellowMatch));
                break;

            case 4:
                base.setBackgroundColor(getResources().getColor(R.color.greenMatch));
                break;

            case 5:
                base.setBackgroundColor(getResources().getColor(R.color.blueMatch));
                break;

            case 6:
                base.setBackgroundColor(getResources().getColor(R.color.violetMatch));
                break;
        }
        return x;
    }






    public void pause(View v){
        popup.setContentView(R.layout.activity_ingame_pause);
        popup.show();

        Button pauseClose = popup.findViewById(R.id.ingamePauseClose);
        Button returnGame = popup.findViewById(R.id.ingameReturn);
        Button main = popup.findViewById(R.id.ingameExit);
        Button sett = popup.findViewById(R.id.ingameSettings);

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
        sett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                popup.setContentView(R.layout.activity_main_settings);
//                popup.show();
            }
        });
        //make the bg transparent
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

}

