package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SoundEfects extends AppCompatActivity {
    private Button play;
    Dialog popup;
    MediaPlayer player;
    private TextView score, ct;


    public static int x, y, ccc = 0, l;
    String[] hums = {"Acoustic Guitar","Ambulance","Bass Guitar","Bell","Thunder","Piano","Violin","Whistle"};
    int[] arr = {R.raw.acc, R.raw.ambu, R.raw.bassgui, R.raw.bell, R.raw.kidlat, R.raw.piano, R.raw.violin,R.raw.whis};
    int scoreValue, f = 0, life = 3;
    Button[] b = new Button[3];
    Random rand = new Random();
    private ImageView life1, life2, life3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.violetTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_efects);
        b[0] = findViewById(R.id.button);
        b[1] = findViewById(R.id.button2);
        b[2] = findViewById(R.id.button3);
        play = findViewById(R.id.button4);

        popup = new Dialog(this);

//        b[0].setEnabled(false);
//        b[1].setEnabled(false);
//        b[2].setEnabled(false);



        life1 = findViewById(R.id.life1);
        life2 = findViewById(R.id.life2);
        life3 = findViewById(R.id.life3);

        x = rand.nextInt(8);

        process();

        /*  shuf = new Integer[]{0,1};*/

        score = findViewById(R.id.scr);

        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f == 1) {

                    if (x == 0 || x == 5 || x == 6) {

                        scring();
                        ctr();
                    }else if(x != 0 || x != 5 || x != 6){

                        deduc();
                        ctr();
                        randoms();
                        process();
                    }
                    if (player != null) {
                        stoping();
                    }
                }
            }
        });
        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f == 1) {

                    if (x == 1 || x == 4) {

                        scring();
                        ctr();
                    }else if(x != 1 || x != 4){

                        deduc();
                        ctr();
                        randoms();
                        process();
                    }
                    if (player != null) {
                        stoping();
                    }
                }
            }
        });
        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (f == 1) {

                    if (x == 2 || x == 3 || x == 7) {

                        scring();
                        ctr();
                    }else if(x != 2 || x != 3 || x != 7){

                        deduc();
                        ctr();
                        randoms();
                        process();
                    }
                    if (player != null) {
                        stoping();
                    }
                }
            }
        });


    }


    //arrangement
    public void ac() {



        b[0].setText(hums[0]);

        b[2].setText(hums[4]);

        b[1].setText(hums[7]);


    }

    public void amb() {


        b[1].setText(hums[1]);

        b[0].setText(hums[2]);

        b[2].setText(hums[6]);

    }

    public void bg() {


        b[0].setText(hums[0]);

        b[1].setText(hums[3]);

        b[2].setText(hums[2]);

    }

    public void bel(){

        b[1].setText(hums[4]);

        b[0].setText(hums[2]);


        b[2].setText(hums[3]);

    }

    public void kid() {


        b[0].setText(hums[6]);

        b[1].setText(hums[0]);

        b[2].setText(hums[4]);

    }

    public void pian() {


        b[0].setText(hums[5]);

        b[1].setText(hums[1]);

        b[2].setText(hums[2]);

    }

    public void viol() {


        b[0].setText(hums[6]);

        b[1].setText(hums[3]);

        b[2].setText(hums[4]);

    }public void pito() {


        b[0].setText(hums[2]);

        b[1].setText(hums[3]);

        b[2].setText(hums[7]);

    }





    //play button
    public void ply(View v) {


        if (player == null) {
            /*startTimer();*/
            player = MediaPlayer.create(this, arr[x]);
            player.start();
            f = 1;
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    player = null;
                }
            });

//            b[0].setEnabled(true);
//            b[1].setEnabled(true);
//            b[2].setEnabled(true);

        } else if (player.isPlaying()) {
            player.stop();
            player = null;
        }


    }

    //connection for arrangement

    public void process() {

        if (x == 0) {
            ac();

        } else if (x == 1) {
            amb();

        } else if (x == 2) {
            bg();

        } else if (x == 3) {
            bel();

        } else if (x == 4) {
            kid();

        } else if (x == 5) {
            pian();

        } else if (x == 6) {
            viol();

        }else if (x == 7) {
            pito();

        }

    }


    //score
    public void scring() {

        score = findViewById(R.id.scr);

        scoreValue += 1000;
        String scoreString = new Integer(scoreValue).toString();
        score.setText(scoreString);

        f = 0;
        randoms();

        process();

//     b[0].setEnabled(false);
//     b[1].setEnabled(false);
//     b[2].setEnabled(false);

    }

    //timer
    public void ctr() {


        ct = findViewById(R.id.count);

        ccc += 1;
        String cc = new Integer(ccc).toString();
        ct.setText(cc);
        cts();
    }


    //game over { INTENT ! BACKLAGS ! }


    public void stoping() {
        if (player.isPlaying()) {
            player.stop();

            player = null;
//        b[0].setEnabled(false);
//        b[1].setEnabled(false);
//        b[2].setEnabled(false);
//    }
        }


    }

    public void deduc() {
        life -= 1;
        switch (life) {
            case 2:
                life3.setImageResource(R.drawable.blackheart);
                break;

            case 1:
                life2.setImageResource(R.drawable.blackheart);
                break;

            case 0:
                life1.setImageResource(R.drawable.blackheart);

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



    public void randoms(){
        f = 0;
        if (x == 0){
            x = rand.nextInt(6 + 1)+1;
        }else if ( x == 1){
            x = rand.nextInt(5 + 1)+2;
        }else if ( x == 2) {
            x = rand.nextInt(4 + 1)+3;
        }else if ( x == 3) {
            x = rand.nextInt(3 + 1)+4;
        }else if ( x == 4) {
            x = rand.nextInt(2 + 1)+5;
        }else if ( x == 5) {
            x = rand.nextInt(1 + 1)+6;
        }else if ( x == 6) {
            x = rand.nextInt(6);
        }else if ( x == 7) {
            x = rand.nextInt(7);
        }
    }
    public void cts(){
        if(ccc == 5){

            if(life == 3){
                scoreValue += 300;
            }else if(life == 2){
                scoreValue += 200;
            }else if(life == 1){
                scoreValue += 100;
            }else if(life == 0){
                scoreValue += 0;
            }

            popup.setContentView(R.layout.activity_game_cleared);
            popup.show();
            popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView sg = popup.findViewById(R.id.finalScore);
            String n = new Integer(scoreValue).toString();
            sg.setText(n);

            Button next = popup.findViewById(R.id.clearNext);

            Button exit = popup.findViewById(R.id.clearExit);

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                    startActivity(startIntent);
                    finish();
                }
            });

            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(startIntent);
                    finish();

                }
            });
       /* if(player.isPlaying()){
            stoping();
        }*/
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