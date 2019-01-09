package com.dementia.csthesis.thesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class TransitionScreen extends AppCompatActivity {

    WeightDatabaseOpenHelper myDb;

    private ArrayList<Float> weights = new ArrayList<Float>();

    private int r;

    public static String[] cat = {"Audio Game", "Visual Game", "Word Game", "Logic Game", "Memory Game", "Reflex Game"};

    private String[] col = {"AUDIO", "VISUAL", "WORD", "LOGIC", "MEMORY", "REFLEX"};
    private String column;

    private float container;

    private int choice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_screen);

        myDb = new WeightDatabaseOpenHelper(this);

        Random rand = new Random();

        //variable declaration
        int ctr, ctr2, intweightsum;

        float randomweight, fltweightsum, deduc, z, add, x;

        WeightDatabaseAccess weightDatabaseAccess = WeightDatabaseAccess.getInstance(getApplicationContext());
        weightDatabaseAccess.open();

        //database loading loop
        for(ctr = 0; ctr < 6; ctr++){
            column = col[ctr];
            weights.add(ctr, weightDatabaseAccess.getWeight(column));
        }

        weightDatabaseAccess.close();
        //=============================================================================
        //initiate weight sort here


        //=============================================================================


        for(ctr2 = 0; ctr2 < 6; ctr2++){
            Log.d("", "initial weights ===================== \t" +col[ctr2]+ "\t" + weights.get(ctr2));
        }


        //=============================================================================
        fltweightsum = 0;
        //summation of weights
        for (ctr = 0; ctr < 6; ctr++) {
            fltweightsum = fltweightsum + weights.get(ctr);
        }

        //conversion of float to int
        intweightsum = Math.round(fltweightsum);
        //testing of weights
        for (ctr = 0; ctr < 6; ctr++) {
            //random number generator
            randomweight = rand.nextInt(intweightsum) + 1;

            x = randomweight - weights.get(ctr);

            if (x <= 0) {
                //deducting weights to randomed weight
                container = weights.get(ctr);

                double d = container * 0.9;
                deduc = (float)d;
                double c = container - d;
                z = (float)c;


                //assigning value to deduc
                weights.set(ctr, z);
                container = weights.get(ctr);

                //dividing the deduc value to 5
                add = deduc / 5;

                for(ctr2 = 0; ctr2 < 6; ctr2++){
                    if(ctr2 != ctr){
                        //adding value to other weights
                        weights.set(ctr2, (weights.get(ctr2) + add));
                    }
                }
                break;
            }
            else if (ctr == 5) {
                //no hits, reset the counter
                ctr = 0;
            }
        }
        //=============================================================================


        weightDatabaseAccess.open();

        //database insert loop
        for(ctr2 = 0; ctr2 < 6; ctr2++){
            column = col[ctr2];
            weightDatabaseAccess.insertWeight(column, weights.get(ctr2));
        }

        weightDatabaseAccess.close();



        for(ctr2 = 0; ctr2 < 6; ctr2++){
            Log.d("", "deducted weights ===================== \t" +col[ctr2]+ "\t"  + weights.get(ctr2));
        }

        //weighted random number generated

        final String category = cat[ctr];
        //switch activity and pass the text value of category array
        TextView tv = findViewById(R.id.categLabel);
        tv.setText(category);


        Button play = findViewById(R.id.transitionPlay);
        final int finalCtr = ctr;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();

                Intent startIntent = new Intent();

                switch (finalCtr){
                    //Sound category ========================================
                    case 0:
                        //change bound depending on the number of finished games
                        r = rand.nextInt(5);

                        switch(r){

                            case 0:
                                startIntent = new Intent(getApplicationContext(), AnimalSounds.class);
                                break;
                            case 1:
                                startIntent = new Intent(getApplicationContext(), HumanSound.class);
                                break;
                            case 2:
                                startIntent = new Intent(getApplicationContext(), SoundEfects.class);
                                break;
                            case 3:
                                startIntent = new Intent(getApplicationContext(), SoundSorting.class);
                                break;
                            case 4:
                                startIntent = new Intent(getApplicationContext(), soundImage.class);
                                break;
                        }
                        startActivity(startIntent);
                        finish();
                        break;

                    //Visual category ========================================
                    case 1:
                        //change bound depending on the number of finished games
                        r = rand.nextInt(2);

                        switch(r){

                            case 0:
                                startIntent = new Intent(getApplicationContext(), ColorMatch.class);
                                break;
                            case 1:
                                startIntent = new Intent(getApplicationContext(), MatchThree.class);
                                break;
                        }
                        startActivity(startIntent);
                        finish();
                        break;

                    //Word category ========================================
                    case 2:
                        startIntent = new Intent(getApplicationContext(), WordGuess.class);
                        startActivity(startIntent);
                        finish();
                        break;

                    //Logic category ========================================
                    case 3:
                        //change bound depending on the number of finished games
                        r = rand.nextInt(3);

                        switch(r){

                            case 0:
                                startIntent = new Intent(getApplicationContext(), QuickMath.class);
                                break;
                            case 1:
                                startIntent = new Intent(getApplicationContext(), PipeConnect.class);
                                break;
                            case 2:
                                startIntent = new Intent(getApplicationContext(), Tictactoe.class);
                                break;
                        }
                        startActivity(startIntent);
                        finish();
                        break;

                    //Memory category ========================================
                    case 4:
                        startIntent = new Intent(getApplicationContext(), ColorPair.class);
                        startActivity(startIntent);
                        finish();
                        break;

                    //Reflex category ========================================
                    case 5:
                        //change bound depending on the number of finished games
//                        r = rand.nextInt(2);
                        r = 0;

                        switch(r){

                            case 0:
                                startIntent = new Intent(getApplicationContext(), ColorImpact.class);
                                break;
                            case 1:
                                startIntent = new Intent(getApplicationContext(), whackMole.class);
                                break;
                        }
                        startActivity(startIntent);
                        finish();
                        break;

                }


            }
        });

        ImageView img = findViewById(R.id.centerCateg);
        ImageView leftimg = findViewById(R.id.leftCateg);
        ImageView rightimg = findViewById(R.id.rightCateg);

        if (ctr==0){
            img.setImageResource(R.drawable.ear);
//            leftimg.setImageResource(R.drawable.reflex);
//            rightimg.setImageResource(R.drawable.eye);
        }
        else if(ctr==1){
            img.setImageResource(R.drawable.eye);
//            leftimg.setImageResource(R.drawable.ear);
//            rightimg.setImageResource(R.drawable.book);
        }
        else if(ctr==2){
            img.setImageResource(R.drawable.abc);
//            leftimg.setImageResource(R.drawable.eye);
//            rightimg.setImageResource(R.drawable.calculator);
        }
        else if(ctr==3){
            img.setImageResource(R.drawable.logic);
//            leftimg.setImageResource(R.drawable.book);
//            rightimg.setImageResource(R.drawable.brain);
        }
        else if(ctr==4){
            img.setImageResource(R.drawable.brain);
//            leftimg.setImageResource(R.drawable.calculator);
//            rightimg.setImageResource(R.drawable.reflex);
        }
        else if(ctr==5){
            img.setImageResource(R.drawable.reflex);
//            leftimg.setImageResource(R.drawable.brain);
//            rightimg.setImageResource(R.drawable.ear);
        }

    }

    public void onBackPressed(){
        Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(startIntent);
        finish();
    }
}
