package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatchThree extends AppCompatActivity {
    Dialog popup;

    MediaPlayer player;

    private int addscore, xtr, row1, col1, num = 0, row = 0, col = 0, x ,y, container, score;

    private Button  a1,a2,a3,a4,a5,a6,a7,a8,
                    b1,b2,b3,b4,b5,b6,b7,b8,
                    c1,c2,c3,c4,c5,c6,c7,c8,
                    d1,d2,d3,d4,d5,d6,d7,d8,
                    e1,e2,e3,e4,e5,e6,e7,e8,
                    f1,f2,f3,f4,f5,f6,f7,f8,
                    g1,g2,g3,g4,g5,g6,g7,g8,
                    h1,h2,h3,h4,h5,h6,h7,h8;

    private int[][] intmatrix = new int[8][8];

    private int[][] colormatrix = new int[8][8];

    private Button[][] btnmatrix = new Button[8][8];

    private Boolean[][] visitedmatrix = new Boolean[8][8];

    private Button btn;

    private static int count;

    private TextView scoretv, timertv;

    private CountDownTimer cTimer;
    private long mTimeLeftInMills =21000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.yellowTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_three);

        //popup declaration
        popup = new Dialog(this);

        scoretv = findViewById(R.id.matchThreeScore);
        timertv = findViewById(R.id.matchThreeTimer);


        for(row = 0; row < 8; row++){
            for(col = 0; col < 8; col++){
                intmatrix[row][col] = 0;
            }
        }

        for(row = 0; row < 8; row++){
            for(col = 0; col < 8; col++){
                visitedmatrix[row][col] = false;
            }
        }


        row = 0;
        col = 0;

        declareButtons();
        startTimer();

//        for(row = 0; row < 8; row++){
//            for(col = 0; col < 8; col++){
//                btn = btnmatrix[row][col];
//                btn.setText("" +colormatrix[row][col]);
//            }
//        }

    }
    public void startTimer(){
        cTimer = new CountDownTimer(mTimeLeftInMills, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimeLeftInMills = millisUntilFinished;
                String tm = new Long(millisUntilFinished / 1000).toString();
                timertv.setText(tm);

            }

            public void onFinish() {
                if(score <= 1900){
                    Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);
                }else if (score > 1900){
                    Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);
                }
            }
        }.start();
    }


    public void declareButtons(){


        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        a6 = findViewById(R.id.a6);
        a7 = findViewById(R.id.a7);
        a8 = findViewById(R.id.a8);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);
        c5 = findViewById(R.id.c5);
        c6 = findViewById(R.id.c6);
        c7 = findViewById(R.id.c7);
        c8 = findViewById(R.id.c8);

        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);
        d3 = findViewById(R.id.d3);
        d4 = findViewById(R.id.d4);
        d5 = findViewById(R.id.d5);
        d6 = findViewById(R.id.d6);
        d7 = findViewById(R.id.d7);
        d8 = findViewById(R.id.d8);

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        e5 = findViewById(R.id.e5);
        e6 = findViewById(R.id.e6);
        e7 = findViewById(R.id.e7);
        e8 = findViewById(R.id.e8);

        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);
        f5 = findViewById(R.id.f5);
        f6 = findViewById(R.id.f6);
        f7 = findViewById(R.id.f7);
        f8 = findViewById(R.id.f8);

        g1 = findViewById(R.id.g1);
        g2 = findViewById(R.id.g2);
        g3 = findViewById(R.id.g3);
        g4 = findViewById(R.id.g4);
        g5 = findViewById(R.id.g5);
        g6 = findViewById(R.id.g6);
        g7 = findViewById(R.id.g7);
        g8 = findViewById(R.id.g8);

        h1 = findViewById(R.id.h1);
        h2 = findViewById(R.id.h2);
        h3 = findViewById(R.id.h3);
        h4 = findViewById(R.id.h4);
        h5 = findViewById(R.id.h5);
        h6 = findViewById(R.id.h6);
        h7 = findViewById(R.id.h7);
        h8 = findViewById(R.id.h8);

        setColor(a1);
        setColor(a2);
        setColor(a3);
        setColor(a4);
        setColor(a5);
        setColor(a6);
        setColor(a7);
        setColor(a8);

        setColor(b1);
        setColor(b2);
        setColor(b3);
        setColor(b4);
        setColor(b5);
        setColor(b6);
        setColor(b7);
        setColor(b8);

        setColor(c1);
        setColor(c2);
        setColor(c3);
        setColor(c4);
        setColor(c5);
        setColor(c6);
        setColor(c7);
        setColor(c8);

        setColor(d1);
        setColor(d2);
        setColor(d3);
        setColor(d4);
        setColor(d5);
        setColor(d6);
        setColor(d7);
        setColor(d8);

        setColor(e1);
        setColor(e2);
        setColor(e3);
        setColor(e4);
        setColor(e5);
        setColor(e6);
        setColor(e7);
        setColor(e8);

        setColor(f1);
        setColor(f2);
        setColor(f3);
        setColor(f4);
        setColor(f5);
        setColor(f6);
        setColor(f7);
        setColor(f8);

        setColor(g1);
        setColor(g2);
        setColor(g3);
        setColor(g4);
        setColor(g5);
        setColor(g6);
        setColor(g7);
        setColor(g8);

        setColor(h1);
        setColor(h2);
        setColor(h3);
        setColor(h4);
        setColor(h5);
        setColor(h6);
        setColor(h7);
        setColor(h8);

    }

    public void setColor(Button btn){
        if(col == 8){
            col = 0;
            row += 1;
        }
            genColor(btn);
            col += 1;


    }

    public void genColor(Button btn){
        Random rand = new Random();
        colormatrix[row][col] = rand.nextInt(4) + 1;
        btnmatrix[row][col] = btn;

        switch(colormatrix[row][col]){
            case 1:
                btn.setBackgroundResource(R.color.red);
                break;
            case 2:
                btn.setBackgroundResource(R.color.green);
                break;
            case 3:
                btn.setBackgroundResource(R.color.blue);
                break;
            case 4:
                btn.setBackgroundResource(R.color.yellow);
                break;

        }
    }

    public void onClick(View view){
        btn = findViewById(view.getId());
        count = 0;

        for(row = 0; row < 8; row++){
            for(col = 0; col < 8; col++){
                if(btn == btnmatrix[row][col]){
                    x = row;
                    y = col;
                    break;
                }

            }
        }

        addscore = flood(x, y);
        if(addscore > 2){
            score += 50 * addscore;
            String scorestring = new Integer(score).toString();
            scoretv.setText(scorestring);
            //sfx
            playMatch();
        }
        else
            //sfx
            playNoMatch();

        drop();
        updateColor();


//        for(row = 0; row < 8; row++){
//            for(col = 0; col < 8; col++){
//                btn = btnmatrix[row][col];
//                btn.setText("" +colormatrix[row][col]);
//            }
//        }

        //reset intmatrix
        for(row = 0; row < 8; row++){
            for(col = 0; col < 8; col++){
                intmatrix[row][col] = num;
            }
        }
        //reset visitedmatrix
        for(row = 0; row < 8; row++){
            for(col = 0; col < 8; col++){
                visitedmatrix[row][col] = false;
            }
        }
    }

    public void playMatch(){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.tilebreak);
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
            player = MediaPlayer.create(this, R.raw.tilebreak);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
    }

    public void playNoMatch(){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.nomatchtile);
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
            player = MediaPlayer.create(this, R.raw.nomatchtile);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
    }

    public int flood(int row, int col){
        count = 0;

        //check if boundary reached
        if(row < 0 || row > 7 || col < 0 || col > 7){
            return count;
        }


        count += 1;


        count = floodLeft(row, col, count);
        count = floodRight(row, col, count);

        count = floodUp(row, col, count);
        count = floodDown(row, col, count);



        return count;
    }

    public int floodUp(int row, int col, int count){
        row -= 1;
        if(row < 0 || row > 7 || col < 0 || col > 7){
            return count;
        }

        if(colormatrix[x][y] != colormatrix[row][col]){
            return count;
        }

        if(visitedmatrix[row][col]){
            return count;
        }

        count += 1;
        if(count == 2){
            row1 = row;
            col1 = col;
        }
        if(count > 2){
            visitedmatrix[x][y] = true;
            visitedmatrix[row1][col1] = true;
            visitedmatrix[row][col] = true;

//            btnmatrix[x][y].setBackgroundResource(R.color.textBlack);
//            btnmatrix[row1][col1].setBackgroundResource(R.color.textBlack);
//            btnmatrix[row][col].setBackgroundResource(R.color.textBlack);

            intmatrix[x][y] = 1;
            intmatrix[row1][col1] = 1;
            intmatrix[row][col] = 1;

        }


        count = floodUp(row, col, count);
        count = floodRight(row, col, count);
        count = floodLeft(row, col,count);




        return count;
    }

    public int floodDown(int row, int col, int count){
        row += 1;
        if(row < 0 || row > 7 || col < 0 || col > 7){
            return count;
        }

        if(colormatrix[x][y] != colormatrix[row][col]){
            return count;
        }

        if(visitedmatrix[row][col]){
            return count;
        }


        count += 1;
        if(count == 2){
            row1 = row;
            col1 = col;
        }
        if(count > 2){
            visitedmatrix[x][y] = true;
            visitedmatrix[row1][col1] = true;
            visitedmatrix[row][col] = true;

//            btnmatrix[x][y].setBackgroundResource(R.color.textBlack);
//            btnmatrix[row1][col1].setBackgroundResource(R.color.textBlack);
//            btnmatrix[row][col].setBackgroundResource(R.color.textBlack);

            intmatrix[x][y] = 1;
            intmatrix[row1][col1] = 1;
            intmatrix[row][col] = 1;

        }

            count = floodDown(row, col,count);
            count = floodRight(row, col, count);
            count = floodLeft(row, col, count);



        return count;

    }

    public int floodLeft(int row, int col, int count){
        col -= 1;
        if(row < 0 || row > 7 || col < 0 || col > 7){
            return count;
        }

        if(colormatrix[x][y] != colormatrix[row][col]){

            return count;
        }

        if(visitedmatrix[row][col]){
            return count;
        }

        count += 1;
        if(count == 2){
            row1 = row;
            col1 = col;
        }
        if(count > 2){
            visitedmatrix[x][y] = true;
            visitedmatrix[row1][col1] = true;
            visitedmatrix[row][col] = true;

//            btnmatrix[x][y].setBackgroundResource(R.color.textBlack);
//            btnmatrix[row1][col1].setBackgroundResource(R.color.textBlack);
//            btnmatrix[row][col].setBackgroundResource(R.color.textBlack);

            intmatrix[x][y] = 1;
            intmatrix[row1][col1] = 1;
            intmatrix[row][col] = 1;

        }

            count = floodUp(row, col, count);
            count = floodDown(row, col, count);
            count = floodLeft(row, col, count);



        return count;
    }

    public int floodRight(int row, int col, int count){
        col +=1;
        if(row < 0 || row > 7 || col < 0 || col > 7){
            return count;
        }

        if(colormatrix[x][y] != colormatrix[row][col]){
            return count;
        }

        if(visitedmatrix[row][col]){
            return count;
        }

        count += 1;
        if(count == 2){
            row1 = row;
            col1 = col;
        }
        if(count > 2){
            visitedmatrix[x][y] = true;
            visitedmatrix[row1][col1] = true;
            visitedmatrix[row][col] = true;

//            btnmatrix[x][y].setBackgroundResource(R.color.textBlack);
//            btnmatrix[row1][col1].setBackgroundResource(R.color.textBlack);
//            btnmatrix[row][col].setBackgroundResource(R.color.textBlack);

            intmatrix[x][y] = 1;
            intmatrix[row1][col1] = 1;
            intmatrix[row][col] = 1;

        }


            count = floodUp(row, col, count);
            count = floodDown(row, col, count);
            count = floodRight(row, col, count);


        return count;

    }

//            0 0 0 0 0 0 0 0
//            0 0 0 0 0 0 0 0
//            1 1 1 1 0 0 0 0
//            0 0 1 0 0 0 0 0
//            0 0 1 0 0 0 0 0
//            0 0 0 0 0 0 0 0
//            0 0 0 0 0 0 0 0
//            0 0 0 0 0 0 0 0

    public void drop(){
        for(col = 0; col < 8; col++){
            for(row = 0; row < 8; row++){
                if(intmatrix[row][col] == 1){

                    for(row1 = row; row1 >= 1; row1--){
                        //swapcolors
                        container = colormatrix[row1][col];
                        colormatrix[row1][col] = colormatrix[row1 - 1][col];
                        colormatrix[row1 - 1][col] = container;

                        container = intmatrix[row1][col];
                        intmatrix[row1][col] = intmatrix[row1 - 1][col];
                        intmatrix[row1 - 1][col] = container;

                    }

                }
            }

        }
    }

    public void updateColor(){
        Random rand = new Random();
        for(row = 0; row < 8; row++){
            for(col = 0; col < 8; col++){
                if(intmatrix[row][col] == 1){
                    colormatrix[row][col] = rand.nextInt(4) + 1;
                    intmatrix[row][col] = 0;
                }
                btn = btnmatrix[row][col];
                switch(colormatrix[row][col]){
                    case 1:
                        btn.setBackgroundResource(R.color.red);
                        break;
                    case 2:
                        btn.setBackgroundResource(R.color.green);
                        break;
                    case 3:
                        btn.setBackgroundResource(R.color.blue);
                        break;
                    case 4:
                        btn.setBackgroundResource(R.color.yellow);
                        break;
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
                finish();
                Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(startIntent);

            }
        });

        //make the bg transparent
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onBackPressed(){
        pause(null);
    }






























}
