package com.dementia.csthesis.thesis;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class TransitionScreen extends AppCompatActivity {

    public static float[] weights;
    public static String[] cat = {"Audio Game", "Visual Game", "Word Game", "Logic Game", "Memory Game", "Reflex Game"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_screen);


        //variable declaration
        int ctr, ctr2, intweightsum;

        float[] initWeights = {20, 20, 20, 20, 20, 20 };
        weights = initWeights;

        float randomweight, fltweightsum, deduc, add, x;

        fltweightsum = 0;
        //summation of weights
        for (ctr = 0; ctr < 6; ctr++) {
            fltweightsum = fltweightsum + weights[ctr];
        }

        //conversion of float to int
        intweightsum = Math.round(fltweightsum);
        //testing of weights
        Random rand = new Random();
        for (ctr = 0; ctr < 6; ctr++) {
            //random number generator
            randomweight = rand.nextInt(intweightsum) + 1;

            x = randomweight - weights[ctr];

            if (x <= 0) {
                //deducting weights to randomed weight
                deduc = TransitionScreen.weights[ctr] / 2;
                //assigning value to deduc
                TransitionScreen.weights[ctr] = deduc;
                //dividing the deduc value to 4
                add = deduc / 5;

                for(ctr2 = 0; ctr2 < 6; ctr2++){
                    if(ctr2 != ctr){
                        //adding value to other weights
                        weights[ctr2] = weights[ctr2] + add;
                    }
                }
                break;
            }
            else if (ctr == 5) {
                //no hits, reset the counter
                ctr = 0;
            }
        }

        //weighted random number generated

        final String category = cat[ctr];
        //switch activity and pass the text value of category array
        TextView tv = (TextView)findViewById(R.id.categLabel);
        tv.setText(category);


        Button play = (Button)findViewById(R.id.transitionPlay);
        final int finalCtr = ctr;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ChoiceScreen.class);
                //passing the chosen category
                //passing the ctr value
                startIntent.putExtra("Test_Category", category).putExtra("CTR", finalCtr);
                startActivity(startIntent);
                finish();



            }
        });

        ImageView img = (ImageView) findViewById(R.id.centerCateg);
        ImageView leftimg = (ImageView) findViewById(R.id.leftCateg);
        ImageView rightimg = (ImageView) findViewById(R.id.rightCateg);

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
            img.setImageResource(R.drawable.book);
//            leftimg.setImageResource(R.drawable.eye);
//            rightimg.setImageResource(R.drawable.calculator);
        }
        else if(ctr==3){
            img.setImageResource(R.drawable.calculator);
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
}
