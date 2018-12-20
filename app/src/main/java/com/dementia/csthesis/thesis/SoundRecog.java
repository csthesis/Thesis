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

public class SoundRecog extends AppCompatActivity {
    Dialog popup;
    MediaPlayer player;
    public static int x;

    private TextView soundTimer;
    private CountDownTimer cTimer;

    private long mTimeLeftInMills = 16000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_recog);
        popup = new Dialog(this);

        Random rand = new Random();

        x = rand.nextInt(8);


        Button person = findViewById(R.id.choice1);
        Button animal = findViewById(R.id.choice2);
        Button inst = findViewById(R.id.choice3);

        soundTimer = findViewById(R.id.soundRecogTimer);


        startTimer();

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (x <= 2){
                    gameCleared();
                }
                else
                    gameOver();
            }
        });

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x >=3 && x <= 5){
                    gameCleared();
                }
                else
                    gameOver();
            }
        });

        inst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( x > 5){
                    gameCleared();
                }
                else
                    gameOver();
            }
        });

    }

    public void startTimer(){

        cTimer = new CountDownTimer(mTimeLeftInMills, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMills = millisUntilFinished;
                String xtimer = new Long(millisUntilFinished / 1000).toString();
                soundTimer.setText(xtimer);
            }

            @Override
            public void onFinish() {
                gameOver();
            }

        }.start();

    }

    public void play(View v){
        switch (x){

            case 0:
                if(player == null){
                    player = MediaPlayer.create(this, R.raw.baby);
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
                    player = null;
                }
                break;


            case 1:

                if(player == null){
                    player = MediaPlayer.create(this, R.raw.haha);
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
                    player = null;
                }
                break;

            case 2:
                if(player == null){
                    player = MediaPlayer.create(this, R.raw.ty);
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
                    player = null;
                }
                break;

            case 3:
                if(player == null){
                    player = MediaPlayer.create(this, R.raw.cat);
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
                    player = null;
                }
                break;

            case 4:
                if(player == null){
                    player = MediaPlayer.create(this, R.raw.dog);
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
                    player = null;
                }
                break;

            case 5:
                if(player == null){
                    player = MediaPlayer.create(this, R.raw.rooster);
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
                    player = null;
                }
                break;

            case 6:
                if(player == null){
                    player = MediaPlayer.create(this, R.raw.violin);
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
                    player = null;
                }
                break;

            case 7:
                if(player == null){
                    player = MediaPlayer.create(this, R.raw.piano);
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
                    player = null;
                }
                break;
            case 8:
                if(player == null){
                    player = MediaPlayer.create(this, R.raw.guitar);
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
                    player = null;
                }
                break;
                        }


    }


    public void gameOver(){

        cTimer.cancel();
        int score = 0;
        if(player != null){
            if(player.isPlaying()){
                player.stop();
            }
        }
        Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
        startIntent.putExtra("SCORE", score);
        startActivity(startIntent);

    }

    public void gameCleared(){

        cTimer.cancel();
        int score = 2000;
        if(player != null){
            if(player.isPlaying()){
                player.stop();
            }
        }
        Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
        startIntent.putExtra("SCORE", score);
        startActivity(startIntent);


    }

    public void pause(View v){
        if(player != null){
            if(player.isPlaying()){
                player.stop();
            }
        }
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


        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onBackPressed(){
        pause(null);
    }
}
