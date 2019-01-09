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

import java.util.Random;

public class PipeConnect extends AppCompatActivity {
    Dialog popup;

    MediaPlayer player;

    private Button start, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3;

    private int a2n, a3n, a4n, b1n, b2n, b3n, b4n, c1n, c2n, c3n, c4n, d1n, d2n, d3n;

    private int a2ctr = 0, a3ctr = 0, a4ctr = 0, b1ctr = 0, b2ctr = 0, b3ctr = 0, b4ctr = 0,
            c1ctr = 0, c2ctr = 0, c3ctr = 0, c4ctr = 0, d1ctr = 0, d2ctr = 0, d3ctr = 0;

    private int i, x, score, maxscore, xctr;

    int key[] = new int[14];

    int ans[] = new int[14];

    private TextView pipeScore;

    private boolean hit0 = false, hit1 = false, hit2 = false, hit3 = false, hit4 = false, hit5 = false, hit6 = false,
            hit7 = false, hit8 = false, hit9 = false, hit10 = false, hit11 = false, hit12 = false, hit13 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.greenTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pipe_connect);

        popup = new Dialog(this);

        pipeScore = findViewById(R.id.pipeScore);

        start = findViewById(R.id.a1);

        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);

        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        c4 = findViewById(R.id.c4);

        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);
        d3 = findViewById(R.id.d3);

        //randomize state
        Random rand = new Random();
        int z = rand.nextInt(4) + 1;

        switch (z){
            case 1:
                state1();
                break;
            case 2:
                state2();
                break;
            case 3:
                state3();
                break;
            case 4:
                state4();
                break;
        }


        //S = 1, L = 2;

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //compare key array with ans array

                playValve();

                if(ans[0] == key[0]){
                    hit0 = true;
                }if(ans[1] == key[1]){
                    hit1 = true;
                }if(ans[2] == key[2]){
                    hit2 = true;
                }if(ans[3] == key[3]){
                    hit3 = true;
                }if(ans[4] == key[4]){
                    hit4 = true;
                }if(ans[5] == key[5]){
                    hit5 = true;
                }if(ans[6] == key[6]){
                    hit6 = true;
                }if(ans[7] == key[7]){
                    hit7 = true;
                }if(ans[8] == key[8]){
                    hit8 = true;
                }if(ans[9] == key[9]){
                    hit9 = true;
                }if(ans[10] == key[10]){
                    hit10 = true;
                }if(ans[11] == key[11]){
                    hit11 = true;
                }if(ans[12] == key[12]){
                    hit12 = true;
                }if(ans[13] == key[13]){
                    hit13 = true;
                }

                if(hit1 && hit2 && hit3 && hit4 && hit5 && hit6 && hit7 && hit8 && hit9 && hit10 && hit11 && hit12 && hit13){
                    gameCleared();
                }

            }
        });

        //==================================================================
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a2ctr = rotate(a2, a2n, a2ctr);
                ans[0] = a2ctr;

//               check(0, a2ctr, a2);
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a3ctr = rotate(a3, a3n, a3ctr);
                ans[1] = a3ctr;
//                check(1, a3ctr, a3);
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a4ctr = rotate(a4, a4n, a4ctr);
                ans[2] = a4ctr;
//                check(2, a4ctr, a4);
            }
        });

        //==================================================================
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1ctr = rotate(b1, b1n, b1ctr);
                ans[3] = b1ctr;
//                check(3, b1ctr, b1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2ctr = rotate(b2, b2n, b2ctr);
                ans[4] = b2ctr;
//                check(4, b2ctr, b2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b3ctr = rotate(b3, b3n, b3ctr);
                ans[5] = b3ctr;
//                check(5, b3ctr, b3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b4ctr = rotate(b4, b4n, b4ctr);
                ans[6] = b4ctr;
//                check(6, b4ctr, b4);
            }
        });

        //==================================================================
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1ctr = rotate(c1, c1n, c1ctr);
                ans[7] = c1ctr;
//                check(7, c1ctr, c1);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2ctr = rotate(c2, c2n, c2ctr);
                ans[8] = c2ctr;
//                check(8, c2ctr, c2);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c3ctr = rotate(c3, c3n, c3ctr);
                ans[9] = c3ctr;
//                check(9, c3ctr, c3);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c4ctr = rotate(c4, c4n, c4ctr);
                ans[10] = c4ctr;
//                check(10, c4ctr, c4);
            }
        });

        //==================================================================
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d1ctr = rotate(d1, d1n, d1ctr);
                ans[11] = d1ctr;
//                check(11, d1ctr, d1);
            }
        });
        d2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d2ctr = rotate(d2, d2n, d2ctr);
                ans[12] = d2ctr;
//                check(12, d2ctr, d2);
            }
        });
        d3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d3ctr = rotate(d3, d3n, d3ctr);
                ans[13] = d3ctr;
//                check(13, d3ctr, d3);
            }
        });


    }

    public int rotate(Button btn, int type, int ctr){

        if(type == 1){
            i = pipeSrotate(btn, ctr);
        }
        else if(type == 2){
            i = pipeLrotate(btn, ctr);
        }
        return i;
    }

    public void state1(){
//        a2, b2, c1, c3, d1, d2, d3

        maxscore = 2400;

        key[0] = 1;
        key[4] = 1;
        key[7] = 4;
        key[9] = 1;
        key[11] = 3;
        key[12] = 2;
        key[13] = 3;


        b1.setVisibility(View.GONE);
        a3.setVisibility(View.GONE);
        b3.setVisibility(View.GONE);
        a4.setVisibility(View.GONE);
        b4.setVisibility(View.GONE);
        c4.setVisibility(View.GONE);

        c2.setBackgroundResource(R.drawable.pipex);

        a2ctr = pipeL(a2);
        ans[0] = a2ctr;
        a2n = 2;
        b2ctr = pipeS(b2);
        ans[4] = b2ctr;
        b2n = 1;
        c1ctr = pipeL(c1);
        ans[7] = c1ctr;
        c1n = 2;
        c3ctr = pipeL(c3);
        ans[9] = c3ctr;
        c3n = 2;
        d1ctr = pipeL(d1);
        ans[11] = d1ctr;
        d1n = 2;
        d2ctr = pipeL(d2);
        ans[12] = d2ctr;
        d2n = 2;
        d3ctr = pipeL(d3);
        ans[13] = d3ctr;
        d3n = 2;


    }

    public void state2(){
        //a2, a3, a4, b1, b2, b3, b4, c1, d1, d2, d3

        maxscore = 3300;

        key[0] = 2;
        key[1] = 2;
        key[2] = 1;
        key[3] = 4;
        key[4] = 2;
        key[5] = 2;
        key[6] = 2;
        key[7] = 1;
        key[11] = 3;
        key[12] = 2;
        key[13] = 2;

        c2.setVisibility(View.GONE);
        c3.setVisibility(View.GONE);
        c4.setVisibility(View.GONE);


        a2ctr = pipeS(a2);
        ans[0] = a2ctr;
        a2n = 1;
        a3ctr = pipeS(a3);
        ans[1] = a3ctr;
        a3n = 1;
        a4ctr = pipeL(a4);
        ans[2] = a4ctr;
        a4n = 2;

        b1ctr = pipeL(b1);
        ans[3] = b1ctr;
        b1n = 2;
        b2ctr = pipeS(b2);
        ans[4] = b2ctr;
        b2n = 1;
        b3ctr = pipeS(b3);
        ans[5] = b3ctr;
        b3n = 1;
        b4ctr = pipeL(b4);
        ans[6] = b4ctr;
        b4n = 2;

        c1ctr = pipeS(c1);
        ans[7] = c1ctr;
        c1n = 1;

        d1ctr = pipeL(d1);
        ans[11] = d1ctr;
        d1n = 2;
        d2ctr = pipeS(d2);
        ans[12] = d2ctr;
        d2n = 1;
        d3ctr = pipeS(d3);
        ans[13] = d3ctr;
        d3n = 1;



    }

    public void state3(){
        //a2 b1 b3 c1 c2 c3 d3

        maxscore = 2100;

        key[0] = 1;
        key[3] = 4;
        key[5] = 1;
        key[7] = 3;
        key[8] = 2;
        key[9] = 1;
        key[13] = 3;

        d1.setVisibility(View.GONE);
        d2.setVisibility(View.GONE);
        a3.setVisibility(View.GONE);
        a4.setVisibility(View.GONE);
        b4.setVisibility(View.GONE);
        c4.setVisibility(View.GONE);

        b2.setBackgroundResource(R.drawable.pipex);


        a2ctr = pipeL(a2);
        ans[0] = a2ctr;
        a2n = 2;
        b1ctr = pipeL(b1);
        ans[3] = b1ctr;
        b1n = 2;
        b3ctr = pipeL(b3);
        ans[5] = b3ctr;
        b3n = 2;
        c1ctr = pipeL(c1);
        ans[7] = c1ctr;
        c1n = 2;
        c2ctr = pipeL(c2);
        ans[8] = c2ctr;
        c2n = 2;
        c3ctr = pipeS(c3);
        ans[9] = c3ctr;
        c3n = 1;
        d3ctr = pipeL(d3);
        ans[13] = d3ctr;
        d3n = 2;


    }

    public void state4(){
        //a2 b1 b2 b3 b4 c1 c2 c4 d3

        maxscore = 2700;

        //1 0 0 4 2 4 1 3 0 0 2 0 0 3 0 ========= key array

        key[0] = 1;
        key[3] = 4;
        key[4] = 2;
        key[5] = 4;
        key[6] = 1;
        key[7] = 3;
        key[8] = 2;
        key[10] = 2;
        key[13] = 3;

        a3.setVisibility(View.GONE);
        a4.setVisibility(View.GONE);
        d1.setVisibility(View.GONE);
        d2.setVisibility(View.GONE);

        c3.setBackgroundResource(R.drawable.pipex);

        a2ctr = pipeL(a2);
        ans[0] = a2ctr;
        a2n = 2;
        b1ctr = pipeL(b1);
        ans[3] = b1ctr;
        b1n = 2;
        b2ctr = pipeL(b2);
        ans[4] = b2ctr;
        b2n = 2;
        b3ctr = pipeL(b3);
        ans[5] = b3ctr;
        b3n = 2;
        b4ctr = pipeL(b4);
        ans[6] = b4ctr;
        b4n = 2;
        c1ctr = pipeL(c1);
        ans[7] = c1ctr;
        c1n = 2;
        c2ctr = pipeS(c2);
        ans[8] = c2ctr;
        c2n = 1;
        c4ctr = pipeL(c4);
        ans[10] = c4ctr;
        c4n = 2;
        d3ctr = pipeL(d3);
        ans[13] = d3ctr;
        d3n = 2;

    }


    public int pipeL(Button btn){
        Random rand = new Random();
        x = rand.nextInt(4) + 1;

        switch (x){

            case 1:
                btn.setBackgroundResource(R.drawable.pipe_l_ne);
                break;
            case 2:
                btn.setBackgroundResource(R.drawable.pipe_l_nw);
                break;
            case 3:
                btn.setBackgroundResource(R.drawable.pipe_l_sw);
                break;
            case 4:
                btn.setBackgroundResource(R.drawable.pipe_l_se);
                break;

        }

        return x;


    }

    public int pipeLrotate(Button btn, int ctr){

        if(ctr < 4){
            ctr += 1;
        }else
            ctr = 1;

        //========================================================================
        // SFX
        if(player == null){
            player = MediaPlayer.create(this, R.raw.pipe_clank);
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
            player = MediaPlayer.create(this, R.raw.pipe_clank);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
        //=======================================================================

        switch (ctr){

            case 1:
                btn.setBackgroundResource(R.drawable.pipe_l_ne);
                break;
            case 2:
                btn.setBackgroundResource(R.drawable.pipe_l_nw);
                break;
            case 3:
                btn.setBackgroundResource(R.drawable.pipe_l_sw);
                break;
            case 4:
                btn.setBackgroundResource(R.drawable.pipe_l_se);
                break;
        }


        return ctr;
    }

    public int pipeS(Button btn){
        Random rand = new Random();
        x = rand.nextInt(2) + 1;

        switch (x){
            case 1:
                btn.setBackgroundResource(R.drawable.pipestraight_horz);
                break;
            case 2:
                btn.setBackgroundResource(R.drawable.pipestraight_vert);
                break;

        }
        return x;

    }

    public int pipeSrotate(Button btn, int ctr){

        if(ctr < 2){
            ctr += 1;
        }else
            ctr = 1;

        //========================================================================
        if(player == null){
            player = MediaPlayer.create(this, R.raw.pipe_clank);
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
            player = MediaPlayer.create(this, R.raw.pipe_clank);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
        //========================================================================
        switch (ctr){
            case 1:
                btn.setBackgroundResource(R.drawable.pipestraight_horz);
                break;
            case 2:
                btn.setBackgroundResource(R.drawable.pipestraight_vert);
                break;
        }

        return ctr;
    }

    public void playValve(){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.valve);
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
            player = MediaPlayer.create(this, R.raw.valve);
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }
    }

    public void gameCleared(){

        if(player == null){
            player = MediaPlayer.create(this, R.raw.water);
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
            player = MediaPlayer.create(this, R.raw.water);
            player.start();

            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });
        }

        CountDownTimer gameClear = new CountDownTimer(1500, 1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(player != null){
                    if(player.isPlaying()){
                        player.stop();
                    }
                }
                score = 5000;
                Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
                startIntent.putExtra("SCORE", score);
                startActivity(startIntent);
            }
        }.start();

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
