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
import android.widget.Toast;

import java.util.Random;

public class ColorOrder extends AppCompatActivity {

    private ImageView img1,img2,img3;
    private int x1,x2,x3;
    private Integer[] shuf;
    private Button ply,r1,r2,r3,b1,b2,b3,g1,g2,g3,dn;
    private int arr[] = {R.drawable.redc,R.drawable.bluec,R.drawable.greenc};
    private int f = 0,y1,y2,y3,scoreValue = 5000,k=0;
    private boolean red,blue,gree;
    private long mTimeLeftInMills = 30000;
    private ProgressBar mProgressBar;
    private TextView timer,scr;
    private int p = 0;
    private CountDownTimer cTimer;

    private Random rand = new Random();
    Dialog popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_order);
        popup = new Dialog(this);
        shuf = new Integer[]{0,1,2};

        x1 = rand.nextInt(3);
        x2 = rand.nextInt(3);
        x3 = rand.nextInt(3);


        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);

        g1 = findViewById(R.id.g1);
        g2 = findViewById(R.id.g2);
        g3 = findViewById(R.id.g3);

        scr = findViewById(R.id.scorz);
        img1 = findViewById(R.id.card1);
        img2 = findViewById(R.id.card2);
        img3 = findViewById(R.id.card3);
        ply = findViewById(R.id.pley);
        dn = findViewById(R.id.done);


        img1.setImageResource(arr[shuf[x1]]);

        img2.setImageResource(arr[shuf[x2]]);

        img3.setImageResource(arr[shuf[x3]]);




        r1.setBackgroundResource(R.drawable.cardback);
        r2.setBackgroundResource(R.drawable.cardback);
        r3.setBackgroundResource(R.drawable.cardback);
        b1.setBackgroundResource(R.drawable.cardback);
        b2.setBackgroundResource(R.drawable.cardback);
        b3.setBackgroundResource(R.drawable.cardback);
        g1.setBackgroundResource(R.drawable.cardback);
        g2.setBackgroundResource(R.drawable.cardback);
        g3.setBackgroundResource(R.drawable.cardback);



        ply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(R.drawable.cardback);
                img2.setImageResource(R.drawable.cardback);
                img3.setImageResource(R.drawable.cardback);
                r1.setBackgroundResource(R.drawable.redc);
                r2.setBackgroundResource(R.drawable.redc);
                r3.setBackgroundResource(R.drawable.redc);
                b1.setBackgroundResource(R.drawable.bluec);
                b2.setBackgroundResource(R.drawable.bluec);
                b3.setBackgroundResource(R.drawable.bluec);
                g1.setBackgroundResource(R.drawable.greenc);
                g2.setBackgroundResource(R.drawable.greenc);
                g3.setBackgroundResource(R.drawable.greenc);

                ply.setEnabled(false);
//                timer = findViewById(R.id.timer);
//                mProgressBar = findViewById(R.id.progressBar);
//                mProgressBar.setProgress(p);
////                startTi();
                dn.setEnabled(true);
            }
        });
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img1.setImageResource(arr[0]);
                y1 = 0;

            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2.setImageResource(arr[0]);
                y2 = 0;

            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img3.setImageResource(arr[0]);
                y3 = 0;


            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(arr[1]);
                y1 = 1;

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2.setImageResource(arr[1]);
                y2 = 1;

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img3.setImageResource(arr[1]);
                y3 = 1;

            }
        });
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(arr[2]);
                y1 = 2;

            }
        });
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2.setImageResource(arr[2]);
                y2 = 2;

            }
        });
        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img3.setImageResource(arr[2]);
                y3 = 2;

            }
        });
        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shufs();
            }
        });





    }


    public void shufs(){
        chik();
        if (red == true && blue == true && gree == true) {
//            if(scoreValue < 5000) {
//                k -= 1;
//            }
//            scring();
            Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
            startIntent.putExtra("SCORE", scoreValue);
            startActivity(startIntent);
            String scoreString = new Integer(scoreValue).toString();
            scr.setText(scoreString);

        }else{
            Toast.makeText(ColorOrder.this, "Wrong Pair!", Toast.LENGTH_SHORT).show();
            img1.setImageResource(R.drawable.cardback);
            img2.setImageResource(R.drawable.cardback);
            img3.setImageResource(R.drawable.cardback);

            k++;
            scring();
        }
        }

    public void scring(){

        f = k * 70;
        scoreValue -= f;
        String scoreString = new Integer(scoreValue).toString();
        scr.setText(scoreString);
    }
public void chik(){

    if(x1 == y1){
        red = true;
    }else{
        red = false;
    }if(x2 == y2){
        blue = true;
    }else {
        blue = false;
    }if(x3 == y3){
        gree = true;
    }else {
        gree = false;
    }



}
//    public void startTi() {
//        cTimer = new CountDownTimer(mTimeLeftInMills, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMills = millisUntilFinished;
//                String n = new Long(millisUntilFinished / 1000).toString();
//                timer.setText(n);
//                p++;
//                yez = 30000;
//
//                mProgressBar.setProgress((int)p*100/(yez /1000));
//
//
//
//
//            }
//
//            public void onFinish() {
//                p++;
//                mProgressBar.setProgress(100);
//                Toast.makeText(ColorOrder.this, "Wrong Pair!", Toast.LENGTH_SHORT).show();
//                img1.setImageResource(R.drawable.cardback);
//                img2.setImageResource(R.drawable.cardback);
//                img3.setImageResource(R.drawable.cardback);
//
//                f += 1;
//            }
//        }.start();
//    }


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
