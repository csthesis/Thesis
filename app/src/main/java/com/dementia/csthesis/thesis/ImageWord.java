package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class ImageWord extends AppCompatActivity {
    WordDatabaseOpenHelper myDb;
    Dialog popup;


    private String guesstext;

    private int life = 3, score = 0, ctr, key;

    private TextView guesstv, scoretv;

    private ImageView life1, life2, life3;

    private int guessx, wc1, wc2, count = 0;

    private Bitmap[] btmp = new Bitmap[3];
    private ImageButton[] btnarr = new ImageButton[3];

    private ArrayList<Integer> intArr = new ArrayList<Integer>();

    private ImageButton img1, img2, img3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_word);
        popup = new Dialog(this);
        myDb = new WordDatabaseOpenHelper(this);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);

        guesstv = findViewById(R.id.tv);
        scoretv = findViewById(R.id.wiscore);

        life1 = findViewById(R.id.life20);
        life2 = findViewById(R.id.life21);
        life3 = findViewById(R.id.life22);

        intArr.add(0, 0);
        intArr.add(1, 1);
        intArr.add(2, 2);

        randWord();

    }

    public void randWord(){
        Random rand = new Random();
        guessx = rand.nextInt(40 - 1) + 1;

        wc1 = rand.nextInt(40 - 1) + 1;
        for(ctr = 1; ctr > 0; ctr++){
            if(guessx == wc1){
                wc1 = rand.nextInt(40 - 1) + 1;
            }
            else
                break;
        }
        wc2 = rand.nextInt(40 - 1) + 1;
        for(ctr = 1; ctr > 0; ctr++){
            if(guessx == wc2 || wc2 == wc1){
                wc2 = rand.nextInt(40 - 1) + 1;
            }
            else
                break;
        }

        WordDatabaseAccess wordDatabaseAccess = WordDatabaseAccess.getInstance(getApplicationContext());
        wordDatabaseAccess.open();

        String id = String.valueOf(guessx);
        String tb = "allwords";
        String col = "Word";

        //get word from database
        guesstext = wordDatabaseAccess.getString(id, tb, col);
        guesstv.setText(guesstext);

        col = "Img";
        key = rand.nextInt(3);
        switch (key){
            case 0:
                //get img from database
                img1.setImageBitmap(wordDatabaseAccess.getbtmp(id, tb, col));

                //get img from database
                id = String.valueOf(wc1);
                img2.setImageBitmap(wordDatabaseAccess.getbtmp(id, tb, col));
                //get img from database

                id = String.valueOf(wc2);
                img3.setImageBitmap(wordDatabaseAccess.getbtmp(id, tb, col));
                break;
            case 1:
                //get img from database
                img2.setImageBitmap(wordDatabaseAccess.getbtmp(id, tb, col));

                //get img from database
                id = String.valueOf(wc1);
                img1.setImageBitmap(wordDatabaseAccess.getbtmp(id, tb, col));
                //get img from database

                id = String.valueOf(wc2);
                img3.setImageBitmap(wordDatabaseAccess.getbtmp(id, tb, col));
                break;
            case 2:
                //get img from database
                img3.setImageBitmap(wordDatabaseAccess.getbtmp(id, tb, col));

                //get img from database
                id = String.valueOf(wc1);
                img2.setImageBitmap(wordDatabaseAccess.getbtmp(id, tb, col));
                //get img from database

                id = String.valueOf(wc2);
                img1.setImageBitmap(wordDatabaseAccess.getbtmp(id, tb, col));
                break;

        }

        wordDatabaseAccess.close();

    }

    public void onClick(View view){
        switch (view.getId()){

            case R.id.img1:
                if(key == 0){
                    hit();
                }else
                    deduc();

                break;
            case R.id.img2:
                if(key == 1){
                    hit();
                }else
                    deduc();
                break;
            case R.id.img3:
                if(key == 2){
                    hit();
                }else
                    deduc();
                break;
        }


    }

    public void hit(){
        score += 1800;
        String scorestring = new Integer(score).toString();
        scoretv.setText(scorestring);

        count += 1;
        if(count < 5){
            randWord();
        }
        else {
            Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
            startIntent.putExtra("SCORE", score);
            startActivity(startIntent);
        }

    }
    public void deduc(){

        count += 1;
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

                popup.setContentView(R.layout.activity_game_over);
                popup.show();
                popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView sg = popup.findViewById(R.id.finalScore);
                String x = new Integer(score).toString();
                sg.setText(x);

                Button exit = popup.findViewById(R.id.mainMenu);

                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                        startActivity(startIntent);
                        finish();

                    }
                });



                break;
        }
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

        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onBackPressed(){
        pause(null);
    }
}
