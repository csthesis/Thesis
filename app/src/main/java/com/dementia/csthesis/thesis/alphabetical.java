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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class alphabetical extends AppCompatActivity {
    WordDatabaseOpenHelper myDb;

    Dialog popup;

    private Button btn1, btn2;

    private ArrayList<String> strArr = new ArrayList<String>();


    private int life = 3, score = 0, count, ctr, int1, int2;

    private ImageView life1, life2, life3;
    private TextView scoretv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabetical);

        popup = new Dialog(this);

        myDb = new WordDatabaseOpenHelper(this);

        life1 = findViewById(R.id.life17);
        life2 = findViewById(R.id.life18);
        life3 = findViewById(R.id.life19);


        scoretv = findViewById(R.id.alpscore);

        btn1 = findViewById(R.id.tv1);
        btn2 = findViewById(R.id.tv2);

        randText();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(btn1.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(btn2.getText().toString());
            }
        });


    }

    public void randText(){
        Random rand = new Random();
        strArr.clear();

        int1 = rand.nextInt(39) + 1;
        int2 = rand.nextInt(39) + 1;
        for(ctr = 1; ctr > 0; ctr++){
            if(int1 == int2){
                int2 = rand.nextInt(40 - 1) + 1;
            }
            else
                break;
        }

        WordDatabaseAccess wordDatabaseAccess = WordDatabaseAccess.getInstance(getApplicationContext());
        wordDatabaseAccess.open();

        String id1 = String.valueOf(int1);
        String id2 = String.valueOf(int2);
        String tb = "allwords";
        String col = "Word";

        strArr.add(0, wordDatabaseAccess.getString(id1, tb, col));
        strArr.add(1, wordDatabaseAccess.getString(id2, tb, col));

        btn1.setText(strArr.get(0));
        btn2.setText(strArr.get(1));

        Collections.sort(strArr);

    }

    public void check(String btntx){
        if(btntx == strArr.get(0)){
            hit();
        }
        else
            deduc();

    }

    public void hit(){
        score += 300 * life;
        String scorestring = new Integer(score).toString();
        scoretv.setText(scorestring);
        count += 1;
        if(count <= 8){
            randText();
        }
        else {
            Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
            startIntent.putExtra("SCORE", score);
            startActivity(startIntent);
        }

    }
    public void deduc(){
        count += 1;
        randText();
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
