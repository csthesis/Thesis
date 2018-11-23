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

import java.util.Random;

public class SoundRecog extends AppCompatActivity {
    Dialog popup;
    MediaPlayer player;
    public static int x;
//    public static String[] audio = {"baby", "haha", "ty", "cat", "dog", "rooster", "violin", "guitar", "piano"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_recog);
        popup = new Dialog(this);

        Random rand = new Random();

        x = rand.nextInt(8);


        Button person = (Button)findViewById(R.id.choice1);
        Button animal = (Button)findViewById(R.id.choice2);
        Button inst = (Button)findViewById(R.id.choice3);

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (x <= 2){
                    popup.setContentView(R.layout.activity_game_cleared);
                    popup.show();

                    Button next = popup.findViewById(R.id.clearNext);
                    Button exit = popup.findViewById(R.id.clearExit);
                    if(player != null){
                        if(player.isPlaying()){
                            player.stop();
                        }
                    }



                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                            startActivity(startIntent);
                            finish();

                        }
                    });
                    popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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
        });

        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(x >=3 && x <= 5){
                    popup.setContentView(R.layout.activity_game_cleared);
                    popup.show();

                    Button next = popup.findViewById(R.id.clearNext);
                    Button exit = popup.findViewById(R.id.clearExit);

                    if(player != null){
                        if(player.isPlaying()){
                            player.stop();
                        }
                    }

                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                            startActivity(startIntent);
                            finish();

                        }
                    });
                    popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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
        });

        inst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( x > 5){
                    popup.setContentView(R.layout.activity_game_cleared);
                    popup.show();

                    Button next = popup.findViewById(R.id.clearNext);
                    Button exit = popup.findViewById(R.id.clearExit);
                    if(player != null){
                        if(player.isPlaying()){
                            player.stop();
                        }
                    }

                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                            startActivity(startIntent);
                            finish();

                        }
                    });
                    popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

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
        });



    }

    public void play(View v){
        player = null;
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




    public void pause(View v){
        if(player != null){
            if(player.isPlaying()){
                player.stop();
            }
        }
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

        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onBackPressed(){
        if(player != null){
            if(player.isPlaying()){
                player.stop();
            }
        }
    }
}
