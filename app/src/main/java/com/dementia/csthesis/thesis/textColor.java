package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class textColor extends AppCompatActivity {
    Dialog popup;

    private int color[] = {
            R.color.violetMatch,
            R.color.greenMatch,
            R.color.blueMatch,
            R.color.yellowMatch,
            R.color.orangeMatch,
            R.color.redMatch
    };

    private String text[] = {
            "Violet",
            "Green",
            "Blue",
            "Yellow",
            "Orange",
            "Red"
    };

    private Button tru, fals;

    private TextView top, bot, tscore;

    private TextView toptvtext, toptvcol, bottvtext, bottvcol;

    private ImageView life1, life2, life3;

    private int topCol, topText, botCol, botText, ctr, count = 1, life = 3, score = 0, match = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_color);

        popup = new Dialog(this);

        toptvtext = findViewById(R.id.toptvtext);
        toptvcol = findViewById(R.id.toptvcol);
        bottvtext = findViewById(R.id.bottvtext);
        bottvcol = findViewById(R.id.bottvcol);

        tscore = findViewById(R.id.scoretx);

        life1 = findViewById(R.id.life1);
        life2 = findViewById(R.id.life2);
        life3 = findViewById(R.id.life3);


        tru = findViewById(R.id.thru);
        fals = findViewById(R.id.falls);

        top = findViewById(R.id.top);
        bot = findViewById(R.id.bot);


        Random rand = new Random();

        match = rand.nextInt(2);
        if(match == 0){
            colorSetFalse();
        }else
            colorSetTrue();

        tru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topCol == botText){
                    hit();
                    check();
                }else
                    deduc();
            }
        });
        fals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topCol != botText){
                    hit();
                    check();
                }else
                    deduc();
            }
        });

    }

    public void check(){
        Random rand = new Random();

        //check if it has been 5 rounds
        count += 1;
        if(count <= 5){
            //randomize if the question will be true = 1 or false = 0
            match = rand.nextInt(2);
            if(match == 0){
                colorSetFalse();
            }else if(match == 1){
                colorSetTrue();
            }

        }else if(count > 5){
            Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
            startIntent.putExtra("SCORE", score);
            startActivity(startIntent);
        }
    }

    public void hit(){
        score += 1000;
        String scorestring = new Integer(score).toString();
        tscore.setText(scorestring);
    }

    public void deduc(){
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

                Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
                startIntent.putExtra("SCORE", score);
                startActivity(startIntent);

                break;
        }
    }



    public void colorSetTrue(){
        Random rand = new Random();
        //randomize the top text
        topText = rand.nextInt(6);
        top.setText(text[topText]);

        //randomize the top color until it does not match the top text
        topCol = rand.nextInt(6);
        top.setTextColor(getResources().getColor(color[topCol]));
        for(ctr = 1; ctr > 0;ctr++){
            if(topText == topCol){
                topCol = rand.nextInt(6);
                top.setTextColor(getResources().getColor(color[topCol]));
            }
            else if(topText != topCol){
                ctr = 0;
                break;
            }
        }

        //set botText equal to topCol
        botText = topCol;
        bot.setText(text[botText]);


        //randomize the bot color until it does not match the top color and bot text
        botCol = rand.nextInt(6);
        bot.setTextColor(getResources().getColor(color[botCol]));
        for(ctr = 1; ctr > 0;ctr++){
            if(botCol == topCol || botCol == botText){
                botCol = rand.nextInt(6);
                bot.setTextColor(getResources().getColor(color[botCol]));
            }
            else if(botCol != topCol && botCol != botText){
                ctr = 0;
                break;
            }

        }

        toptvtext.setText(""+topText);
        toptvcol.setText(""+topCol);
        bottvtext.setText(""+botText);
        bottvcol.setText(""+botCol);

    }

    public void colorSetFalse(){
        Random rand = new Random();
        topText = rand.nextInt(6);
        top.setText(text[topText]);

        //randomize the top color until it does not match the top text
        topCol = rand.nextInt(6);
        top.setTextColor(getResources().getColor(color[topCol]));
        for(ctr = 1; ctr > 0;ctr++){
            if(topText == topCol){
                topCol = rand.nextInt(6);
                top.setTextColor(getResources().getColor(color[topCol]));
            }
            else if(topText != topCol){
                ctr = 0;
                break;
            }
        }

        //randomize the bot text until it does not match the top color or top text
        botText = rand.nextInt(6);
        bot.setText(text[botText]);
        for(ctr = 1; ctr > 0;ctr++){
            if(botText == topCol || botText == topText){
                botText = rand.nextInt(6);
                bot.setText(text[botText]);
            }
            else if(botText != topCol && botText != topText){
                ctr = 0;
                break;
            }
        }

        //randomize the bot color until it does not match the top color and bot text
        botCol = rand.nextInt(6);
        bot.setTextColor(getResources().getColor(color[botCol]));
        for(ctr = 1; ctr > 0;ctr++){
            if(botCol == topCol || botCol == botText){
                botCol = rand.nextInt(6);
                bot.setTextColor(getResources().getColor(color[botCol]));
            }
            else if(botCol != topCol && botCol != botText){
                ctr = 0;
                break;
            }

        }

        toptvtext.setText(""+topText);
        toptvcol.setText(""+topCol);
        bottvtext.setText(""+botText);
        bottvcol.setText(""+botCol);


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
