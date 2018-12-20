package com.dementia.csthesis.thesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ChoiceScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_screen);

        Intent startIntent = getIntent();
        Bundle bundle = getIntent().getExtras();

        String category = startIntent.getStringExtra("Test_Category");

        TextView tv = findViewById(R.id.choiceCategLabel);
        tv.setText(category);

        final int ctr = startIntent.getIntExtra("CTR", 0);

        ImageView categImg = findViewById(R.id.categImg);
        TextView game1 = findViewById(R.id.game1);
        TextView game2 = findViewById(R.id.game2);


        if (ctr==0){
            categImg.setImageResource(R.drawable.ear);
            game1.setText("Sound Recognition");
            game2.setText("Listening Bingo");
        }
        else if(ctr==1){
            categImg.setImageResource(R.drawable.eye);
            game1.setText("Color Match");
            game2.setText("Match Three");
        }
        else if(ctr==2){
            categImg.setImageResource(R.drawable.book);
            game1.setText("Hangman");
            game2.setText("WordHunt");
        }
        else if(ctr==3){
            categImg.setImageResource(R.drawable.calculator);
            game1.setText("Tic-Tac-Toe");
            game2.setText("Quick Math");
        }
        else if(ctr==4){
            categImg.setImageResource(R.drawable.brain);
            game1.setText("Card Pair");
            game2.setText("Simon Says");
        }
        else if(ctr==5){
            categImg.setImageResource(R.drawable.reflex);
            game1.setText("Whack-a-Mole");
            game2.setText("Color Impact");
        }

        Button play = findViewById(R.id.choicePlay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ctr <= 2){
                    Intent startIntent = new Intent(getApplicationContext(), SoundRecog.class);
                    startActivity(startIntent);
                    finish();
                }
                else if(ctr > 2){

                    Intent startIntent = new Intent(getApplicationContext(), QuickMath.class);
                    startActivity(startIntent);
                    finish();
                }


            }
        });

    }

    public void onBackPressed(){}
}
