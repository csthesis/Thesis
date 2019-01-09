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

public class NumberMemory extends AppCompatActivity {

    private ImageView img1,img2,img3;
    private int x1,x2,x3;
    private Integer[] shuf;
    private Button ply,r1,r2,r3,b1,b2,b3,g1,g2,g3,n0,dn,rev;
    private int arr[] = {R.drawable.redc,R.drawable.bluec,R.drawable.greenc};
    private int f = 0,y,y1,y2,y3,scoreValue = 5000,k=0;
    private boolean red,blue,gree;
    private long mTimeLeftInMills = 30000;
    private ProgressBar mProgressBar;
    private TextView timer,scr,sl1,sl2,sl3;
    private int p = 0;
    private CountDownTimer cTimer;

    private Random rand = new Random();
    Dialog popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_memory);
        popup = new Dialog(this);
        shuf = new Integer[]{0,1,2,3,4,5,6,7,8,9};

        x1 = rand.nextInt(10);
        x2 = rand.nextInt(10);
        x3 = rand.nextInt(10);


        r1 = findViewById(R.id.n1);
        r2 = findViewById(R.id.n2);
        r3 = findViewById(R.id.n3);

        b1 = findViewById(R.id.n4);
        b2 = findViewById(R.id.n5);
        b3 = findViewById(R.id.n6);

        g1 = findViewById(R.id.n7);
        g2 = findViewById(R.id.n8);
        g3 = findViewById(R.id.n9);

        n0 = findViewById(R.id.n0);

        scr = findViewById(R.id.scorzz);

        sl1 = findViewById(R.id.slot1);
        sl2 = findViewById(R.id.slot2);
        sl3 = findViewById(R.id.slot3);

        ply = findViewById(R.id.playz);
        dn = findViewById(R.id.dan);
        rev = findViewById(R.id.extra2);

        String xz = new Integer(x1).toString();
        sl1.setText(xz);
        String xx = new Integer(x2).toString();
        sl2.setText(xx);
        String xc = new Integer(x3).toString();
        sl3.setText(xc);

        r1.setEnabled(false);
        r2.setEnabled(false);
        r3.setEnabled(false);
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        g1.setEnabled(false);
        g2.setEnabled(false);
        g3.setEnabled(false);
        n0.setEnabled(false);
        rev.setVisibility(View.INVISIBLE);




        ply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setEnabled(true);
                r2.setEnabled(true);
                r3.setEnabled(true);
                b1.setEnabled(true);
                b2.setEnabled(true);
                b3.setEnabled(true);
                g1.setEnabled(true);
                g2.setEnabled(true);
                g3.setEnabled(true);
                n0.setEnabled(true);

                sl1.setText("");
                sl2.setText("");
                sl3.setText("");

                ply.setEnabled(false);

                dn.setEnabled(true);
                rev.setVisibility(View.VISIBLE);
                rev.setText("Cancel");

            }
        });
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
                y = 1;
                detetks();

            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                y = 2;
                detetks();

            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                y = 3;
                detetks();


            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                y = 4;
                detetks();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                y = 5;
                detetks();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                y = 6;
                detetks();

            }
        });
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                y = 7;
                detetks();

            }
        });
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                y = 8;
                detetks();

            }
        });
        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                y = 9;
                detetks();

            }
        });
        n0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                y = 0;
                detetks();
            }
        });
        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shufs();
            }
        });
        rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sl1.setText("");
                sl2.setText("");
                sl3.setText("");


            }
        });





    }


    public void shufs(){
        chik();
        if (red == true && blue == true && gree == true) {

            Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
            startIntent.putExtra("SCORE", scoreValue);
            startActivity(startIntent);
            String scoreString = new Integer(scoreValue).toString();
            scr.setText(scoreString);

        }else{
            Toast.makeText(NumberMemory.this, "Wrong Pair!", Toast.LENGTH_SHORT).show();
            sl1.setText("");
            sl2.setText("");
            sl3.setText("");

            k += 1;
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

    
    public void detetks(){
        
     if(sl1.getText().toString().equals("")){
         String xz = new Integer(y).toString();
         sl1.setText(xz);
         y1 = y;
     }else if(sl2.getText().toString().equals("")){
         String xz = new Integer(y).toString();
         sl2.setText(xz);
         y2 = y;
     }else if(sl3.getText().toString().equals("")){
         String xz = new Integer(y).toString();
         sl3.setText(xz);
         y3 = y;
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
