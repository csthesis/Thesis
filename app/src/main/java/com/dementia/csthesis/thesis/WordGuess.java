package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class WordGuess extends AppCompatActivity {
    WordDatabaseOpenHelper myDb;


    Dialog popup;

    private int life, btnnum, score, ctr, lrand, q1num, q2num,
        q3num, q4num, q5num, q6num, q7num, index;

    private int letterchance, lettercount;

    private TextView q1, q2, q3, q4, q5, q6, q7;

    android.support.v7.widget.GridLayout qgrid4, qgrid5, qgrid6, qgrid7;

    private Button btnHolder, a1, a2, a3, a4, a5, b1, b2, b3, b4, b5;

    private ImageView img, life1, life2, life3;

    private String id, full, tb;

    private TextView label, scoreText;

    private ArrayList<Character> choices = new ArrayList<Character>();
    private ArrayList<Integer> intArr = new ArrayList<Integer>();

    private char[] c = {};

    private String ltr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


    private boolean q1done = false;
    private boolean q2done = false;
    private boolean q3done = false;
    private boolean q4done = false;
    private boolean q5done = false;
    private boolean q6done = false;
    private boolean q7done = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_guess);
        popup = new Dialog(this);

        myDb = new WordDatabaseOpenHelper(this);

        img = findViewById(R.id.img);

        //database test purposes
        label = findViewById(R.id.label);

        //hearts
        life1 = findViewById(R.id.life1);
        life2 = findViewById(R.id.life2);
        life3 = findViewById(R.id.life3);

        life = 3;

        //score
        scoreText = findViewById(R.id.wordGuessScore);

        //first row buttons
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);

        //second row buttons
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);

        qgrid4 = (android.support.v7.widget.GridLayout) findViewById(R.id.questionGrid4);
        qgrid5 = (android.support.v7.widget.GridLayout) findViewById(R.id.questionGrid5);
        qgrid6 = (android.support.v7.widget.GridLayout) findViewById(R.id.questionGrid6);
        qgrid7 = (android.support.v7.widget.GridLayout) findViewById(R.id.questionGrid7);

        qgrid4.setVisibility(View.INVISIBLE);
        qgrid5.setVisibility(View.INVISIBLE);
        qgrid6.setVisibility(View.INVISIBLE);
        qgrid7.setVisibility(View.INVISIBLE);

        //randomized number of letters, weighted
        Random rand = new Random();

        //change bound to 100
        letterchance = rand.nextInt(100) + 1;

        //setting up the word being guessed
        qSetup();

        //setting up choices grid
        choiceSetup();

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnnum = 0;
                btnHolder = a1;
                check();

            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnnum = 1;
                btnHolder = a2;
                check();
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnnum = 2;
                btnHolder = a3;
                check();
            }
        });


        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnnum = 3;
                btnHolder = a4;
                check();
            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnnum = 4;
                btnHolder = a5;
                check();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnnum = 5;
                btnHolder = b1;
                check();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnnum = 6;
                btnHolder = b2;
                check();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnnum = 7;
                btnHolder = b3;
                check();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnnum = 8;
                btnHolder = b4;
                check();
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnnum = 9;
                btnHolder = b5;
                check();
            }
        });

    }

    public void check(){
        if(q1num == intArr.get(btnnum) && !q1done){

            q1.setText("" +ltr.charAt(intArr.get(btnnum)));
            q1done = true;
            hit();
            end();
        }
        else if (q2num == intArr.get(btnnum) && !q2done) {

            q2.setText("" +ltr.charAt(intArr.get(btnnum)));
            q2done = true;
            hit();
            end();
        }
        else if (q3num == intArr.get(btnnum)&& !q3done){

            q3.setText("" +ltr.charAt(intArr.get(btnnum)));
            q3done = true;
            hit();
            end();
        }
        else if(q4num == intArr.get(btnnum)&& !q4done){

            q4.setText("" +ltr.charAt(intArr.get(btnnum)));
            q4done = true;
            hit();
            end();
        }
        else if(q5num == intArr.get(btnnum)&& !q5done) {

            q5.setText("" +ltr.charAt(intArr.get(btnnum)));
            q5done = true;
            hit();
            end();
        }
        else if (q6num == intArr.get(btnnum)&& !q6done){

            q6.setText("" +ltr.charAt(intArr.get(btnnum)));
            q6done = true;
            hit();
            end();
        }
        else if (q7num == intArr.get(btnnum)&& !q7done){

            q7.setText("" +ltr.charAt(intArr.get(btnnum)));
            q7done = true;
            hit();
            end();
        }
        else{
            deduc();
        }
    }
    public void deduc(){
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

    public void hit(){
        score += 500;
        String scorestring = new Integer(score).toString();
        scoreText.setText(scorestring);
        btnHolder.setVisibility(View.INVISIBLE);

    }

    public void end(){
        if(q1done && q2done && q3done && q4done && q5done && q6done && q7done){

            popup.setContentView(R.layout.activity_game_cleared);
            popup.show();

            popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView sg = popup.findViewById(R.id.finalScore);
            String x = new Integer(score).toString();
            sg.setText(x);

            Button exit = popup.findViewById(R.id.clearExit);
            Button next = popup.findViewById(R.id.clearNext);


            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                    startActivity(startIntent);
                    finish();
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                    startActivity(startIntent);
                    finish();
                }
            });

        }

    }

    public void qSetup(){

        //40% 4 letters
        if(letterchance <= 40) {
            qgrid4.setVisibility(View.VISIBLE);
            q1 = findViewById(R.id.q41);
            q2 = findViewById(R.id.q42);
            q3 = findViewById(R.id.q43);
            q4 = findViewById(R.id.q44);


            tb = "FOUR";
            dbOpen();

            c = full.toCharArray();

            q1.setText("_");
            q2.setText("_");
            q3.setText("_");
            q4.setText("_");


            lettercount = 4;

            q5done = true;
            q6done = true;
            q7done = true;
        }

        //30% 5 letters
        else if(letterchance > 40 && letterchance <= 70) {
            qgrid5.setVisibility(View.VISIBLE);
            q1 = findViewById(R.id.q51);
            q2 = findViewById(R.id.q52);
            q3 = findViewById(R.id.q53);
            q4 = findViewById(R.id.q54);
            q5 = findViewById(R.id.q55);


            tb = "FIVE";
            dbOpen();

            c = full.toCharArray();

            q1.setText("_");
            q2.setText("_");
            q3.setText("_");
            q4.setText("_");
            q5.setText("_");

            lettercount = 5;


            q6done = true;
            q7done = true;
        }

        //20% 6 letters
        else if(letterchance > 70 && letterchance <= 90) {
            qgrid6.setVisibility(View.VISIBLE);
            q1 = findViewById(R.id.q61);
            q2 = findViewById(R.id.q62);
            q3 = findViewById(R.id.q63);
            q4 = findViewById(R.id.q64);
            q5 = findViewById(R.id.q65);
            q6 = findViewById(R.id.q66);


            tb = "SIX";
            dbOpen();

            //split the full string int a char array;
            c = full.toCharArray();

            q1.setText("_");
            q2.setText("_");
            q3.setText("_");
            q4.setText("_");
            q5.setText("_");
            q6.setText("_");

            lettercount = 6;


            q7done = true;

        }

        //10% 7 letters
        else if(letterchance > 90 && letterchance <= 100) {
            qgrid7.setVisibility(View.VISIBLE);
            q1 = findViewById(R.id.q71);
            q2 = findViewById(R.id.q72);
            q3 = findViewById(R.id.q73);
            q4 = findViewById(R.id.q74);
            q5 = findViewById(R.id.q75);
            q6 = findViewById(R.id.q76);
            q7 = findViewById(R.id.q77);


            tb = "SEVEN";
            dbOpen();

            c = full.toCharArray();

            q1.setText("_");
            q2.setText("_");
            q3.setText("_");
            q4.setText("_");
            q5.setText("_");
            q6.setText("_");
            q7.setText("_");

            lettercount = 7;


        }

        }

    public void dbOpen(){
            WordDatabaseAccess wordDatabaseAccess = WordDatabaseAccess.getInstance(getApplicationContext());
            wordDatabaseAccess.open();

            Random rand = new Random();
            int x = rand.nextInt(10) + 1;
            id = String.valueOf(x);
            full = wordDatabaseAccess.getData(id, tb);
            img.setImageBitmap(wordDatabaseAccess.getImg(id, tb));

            wordDatabaseAccess.close();

        }

    public void choiceSetup(){

        switch (lettercount){

            case 4:{
                q1num = ltr.indexOf((c[0]));
                q2num = ltr.indexOf((c[1]));
                q3num = ltr.indexOf((c[2]));
                q4num = ltr.indexOf((c[3]));

                for(ctr = 0; ctr <= 3; ctr++){
                    choices.add(c[ctr]);
                }
                for(ctr = 4; ctr <= 9; ctr++){
                    Random rand = new Random();
                    lrand = rand.nextInt(25);
                    choices.add(ltr.charAt(lrand));
                }


                Collections.shuffle(choices);

                for(ctr = 0; ctr <= 9; ctr++){
                    intArr.add(ltr.indexOf(choices.get(ctr)));
                }

                //print shuffled choices to debug
                for(ctr = 0; ctr <= 9; ctr++){
                    Log.d("", "shuffled choices: " + choices.get(ctr));
                }
                //print shuffled intArray to debug
                for(ctr = 0; ctr <= 9; ctr++){
                    Log.d("", "shuffled IntArray: " + intArr.get(ctr));
                }

                letterSet();

                break;
            }

            case 5:{
                q1num = ltr.indexOf((c[0]));
                q2num = ltr.indexOf((c[1]));
                q3num = ltr.indexOf((c[2]));
                q4num = ltr.indexOf((c[3]));
                q5num = ltr.indexOf((c[4]));


                for(ctr = 0; ctr <= 4; ctr++){
                    choices.add(c[ctr]);
                }
                for(ctr = 5; ctr <= 9; ctr++){
                    Random rand = new Random();
                    lrand = rand.nextInt(25);

                    choices.add(ltr.charAt(lrand));
                }

                //print shuffled choices to debug
                for(ctr = 0; ctr <= 9; ctr++){
                    Log.d("", "shuffled choices: " + choices.get(ctr));
                }

                Collections.shuffle(choices);
                for(ctr = 0; ctr <= 9; ctr++){
                    intArr.add(ltr.indexOf(choices.get(ctr)));
                }

                letterSet();
                break;
            }

            case 6:{
                q1num = ltr.indexOf((c[0]));
                q2num = ltr.indexOf((c[1]));
                q3num = ltr.indexOf((c[2]));
                q4num = ltr.indexOf((c[3]));
                q5num = ltr.indexOf((c[4]));
                q6num = ltr.indexOf((c[5]));

                for(ctr = 0; ctr <= 5; ctr++){
                    choices.add(c[ctr]);
                }
                for(ctr = 6; ctr <= 9; ctr++){
                    Random rand = new Random();
                    lrand = rand.nextInt(25);

                    choices.add(ltr.charAt(lrand));
                }

                //print shuffled choices to debug
                for(ctr = 0; ctr <= 9; ctr++){
                    Log.d("", "shuffled choices: " + choices.get(ctr));
                }

                Collections.shuffle(choices);
                for(ctr = 0; ctr <= 9; ctr++){
                    intArr.add(ltr.indexOf(choices.get(ctr)));
                }

                letterSet();
                break;
            }

            case 7:{
                q1num = ltr.indexOf((c[0]));
                q2num = ltr.indexOf((c[1]));
                q3num = ltr.indexOf((c[2]));
                q4num = ltr.indexOf((c[3]));
                q5num = ltr.indexOf((c[4]));
                q6num = ltr.indexOf((c[5]));
                q7num = ltr.indexOf((c[6]));

                for(ctr = 0; ctr <= 6; ctr++){
                    choices.add(c[ctr]);
                }
                for(ctr = 7; ctr <= 9; ctr++){
                    Random rand = new Random();
                    lrand = rand.nextInt(25);

                    choices.add(ltr.charAt(lrand));
                }

                //print shuffled choices to debug
                for(ctr = 0; ctr <= 9; ctr++){
                    Log.d("", "shuffled choices: " + choices.get(ctr));
                }

                Collections.shuffle(choices);
                for(ctr = 0; ctr <= 9; ctr++){
                    intArr.add(ltr.indexOf(choices.get(ctr)));
                }

                letterSet();
                break;
            }

        }

    }

    public void letterSet(){

        a1.setText("" +choices.get(0));
        a2.setText("" +choices.get(1));
        a3.setText("" +choices.get(2));
        a4.setText("" +choices.get(3));
        a5.setText("" +choices.get(4));

        b1.setText("" +choices.get(5));
        b2.setText("" +choices.get(6));
        b3.setText("" +choices.get(7));
        b4.setText("" +choices.get(8));
        b5.setText("" +choices.get(9));

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

