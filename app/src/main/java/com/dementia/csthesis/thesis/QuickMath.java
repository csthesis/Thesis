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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class QuickMath extends AppCompatActivity {
    Dialog popup;

    private  int score, xtop, xbot;

    private TextView mathTimer;

    private CountDownTimer cTimer;

    private int num1, num2, dum1, dum2, x, key, z, ctr = 0, life = 3;

    private long mTimeLeftInMills = 11000;

    private ImageView operand, life1, life2, life3;

    private TextView fnum, snum, tscore;

    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_math);
        popup = new Dialog(this);

        life1 = findViewById(R.id.life1);
        life2 = findViewById(R.id.life2);
        life3 = findViewById(R.id.life3);

        tscore = findViewById(R.id.scoreText);

        operand = findViewById(R.id.operation);

        fnum = findViewById(R.id.num1);
        snum = findViewById(R.id.num2);

        btn1 = findViewById(R.id.choice4);
        btn2 = findViewById(R.id.choice5);
        btn3 = findViewById(R.id.choice6);

        mathTimer = findViewById(R.id.quickMathTimer);

//        startTimer();


        genrand();
        assignKeys();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(key == 1) {
                    correct();
                    if (ctr != 3) {
                        genrand();
                        assignKeys();
                    }
                    else
                        gameClear();
                }
                else
                    deduclife();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(key == 2){
                    correct();
                    if (ctr != 3) {
                        genrand();
                        assignKeys();
                    }
                    else
                        gameClear();
                }
                else
                    deduclife();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(key == 3){
                    correct();
                    if (ctr != 3) {
                        genrand();
                        assignKeys();
                    }
                    else
                        gameClear();
                }
                else
                    deduclife();
            }
        });



        }


//    public void startTimer(){
//        cTimer = new CountDownTimer(mTimeLeftInMills, 1000){
//
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMills = millisUntilFinished;
//                String xtimer = new Long(millisUntilFinished / 1000).toString();
//                mathTimer.setText(xtimer);
//            }
//
//            @Override
//            public void onFinish() {
//                gameOver();
//            }
//        }.start();
//    }

    public void genrand(){
        ctr += 1;
        x = 0;
        Random rand = new Random();
        key = rand.nextInt(3) + 1;
        z = rand.nextInt(2) +1;

        switch (z){
            case 1:
                num1 = rand.nextInt(49 - 10) + 10;
                num2 = rand.nextInt(49 - 10) + 10;
                x = num1 + num2;
                operand.setImageResource(R.drawable.plus);
                fnum.setText(Integer.toString(num1));
                snum.setText(Integer.toString(num2));
                break;
            case 2:
                num1 = rand.nextInt(49 - 20) + 20;
                num2 = rand.nextInt(num1 - 10) + 10;
                x = num1 - num2;
                operand.setImageResource(R.drawable.minus);
                fnum.setText(Integer.toString(num1));
                snum.setText(Integer.toString(num2));
                break;
        }

        xtop = x + 5;
        xbot = x - 5;

        dum1 = rand.nextInt((xtop - x) + 1) + x + 1;


        dum2 = rand.nextInt((x - 1) - xbot) + xbot;

    }

    public void assignKeys(){
        switch (key){

            case 1:
                btn1.setText(Integer.toString(x));
                btn2.setText(Integer.toString(dum1));
                btn3.setText(Integer.toString(dum2));

                break;

            case 2:
                btn1.setText(Integer.toString(dum1));
                btn2.setText(Integer.toString(x));
                btn3.setText(Integer.toString(dum2));

                break;
            case 3:
                btn1.setText(Integer.toString(dum2));
                btn2.setText(Integer.toString(dum1));
                btn3.setText(Integer.toString(x));


                break;


        }
    }


    public void deduclife(){

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

                    Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);

                break;
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
//                startTimer();
                popup.dismiss();
            }
        });
        returnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startTimer();
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


        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


    public void correct(){
        score += 1000;
        String scorestring = new Integer(score).toString();
        tscore.setText(scorestring);
    }

    public void gameClear(){
//        score = 2000;

//        cTimer.cancel();

        Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
        startIntent.putExtra("SCORE", score);
        startActivity(startIntent);
    }

    public void gameOver(){

//        cTimer.cancel();
        Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
        startIntent.putExtra("SCORE", score);
        startActivity(startIntent);

    }

    public void onBackPressed(){
        pause(null);
    }
}
