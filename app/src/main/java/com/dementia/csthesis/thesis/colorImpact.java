package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;

public class colorImpact extends AppCompatActivity {
    //pause
    Dialog popup;

    //screen size
    private int screenWidth;
    private int screenHeight;

    //images
    private ImageView bar;
    private ImageView player;

    private ImageView life1;
    private ImageView life2;
    private ImageView life3;

    //imageview for projectiles
    private ImageView bluef;
    private ImageView greenf;
    private ImageView redf;

    //buttons
    private Button blue;
    private Button red;
    private Button green;

    //trigger
    private boolean contbar = true;
    private boolean contb = true;
    private boolean contr = true;
    private boolean contg = true;
    private int tick = 1;

    //position
    private float barX;
    private float barY;

    //initialize variables for X Y axis of the image
    private float redX;
    private float redY;

    private float blueX;
    private float blueY;

    private float greenX;
    private float greenY;

    private float playerX;
    private float playerY;

    private int xbar;
    private int life;
    private int score;
    private TextView scoreText;

    //initialize class
    private Handler handler = new Handler();
    private Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_impact);
        popup = new Dialog(this);

        bar = (ImageView)findViewById(R.id.bar);
        player = (ImageView)findViewById(R.id.player);

        redf = (ImageView)findViewById(R.id.redF);
        bluef = (ImageView)findViewById(R.id.blueF);
        greenf = (ImageView)findViewById(R.id.greenF);

        blue = (Button)findViewById(R.id.blueFire);
        red = (Button)findViewById(R.id.redFire);
        green = (Button)findViewById(R.id.greenFire);

        life1 = (ImageView)findViewById(R.id.life1);
        life2 = (ImageView)findViewById(R.id.life2);
        life3 = (ImageView)findViewById(R.id.life3);

        scoreText = (TextView)findViewById(R.id.scoreText);

        //get screen edge
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;

        life = 3;
        score = 0;

        randBar();
        bar.post(new Runnable() {
            @Override
            public void run() {
                barX = (player.getX() + player.getWidth() /2) - bar.getWidth()/2;
                barY = 0;

                bar.setX((player.getX() + player.getWidth() /2) - bar.getWidth()/2);
                bar.setY(0);
            }
        });

        handler.postDelayed(barcp, tick);

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button delay
                blue.setEnabled(false);
                blue.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        blue.setEnabled(true);
                    }
                }, 500);

                //initial position relative to the player image
                blueX = (player.getX() + player.getWidth() /2) - bluef.getWidth()/2;
                blueY = (player.getY() - player.getHeight()/2);

                //set the position of the fire relative to the player image
                bluef.setX((player.getX() + player.getWidth() /2) - bluef.getWidth()/2);
                bluef.setY(player.getY() - player.getHeight()/2);

                //show image
                bluef.setVisibility(View.VISIBLE);
                //turn contb to false to stop the animation
                contb = true;
                handler.postDelayed(changePosBlue, tick);

            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button delay
                red.setEnabled(false);
                red.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        red.setEnabled(true);
                    }
                }, 500);

                //initial pos
                redX = (player.getX() + player.getWidth() /2) - redf.getWidth()/2;
                redY = (player.getY() - player.getHeight()/2);

                redf.setX((player.getX() + player.getWidth() /2) - redf.getWidth()/2);
                redf.setY(player.getY() - player.getHeight()/2);

                redf.setVisibility(View.VISIBLE);
                contr = true;
                handler.postDelayed(changePosRed, tick);
            }
        });

        green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button delay
                green.setEnabled(false);
                green.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        green.setEnabled(true);
                    }
                }, 500);

                //initial pos
                greenX = (player.getX() + player.getWidth() /2) - greenf.getWidth()/2;
                greenY = (player.getY() - player.getHeight()/2);

                greenf.setX((player.getX() + player.getWidth() /2) - greenf.getWidth()/2);
                greenf.setY(player.getY() - player.getHeight()/2);

                greenf.setVisibility(View.VISIBLE);
                contg = true;
                handler.postDelayed(changePosGreen, tick);
            }
        });

    }



    public void randBar(){

        xbar = (int)(Math.random() * 3 + 1);
        switch (xbar){
            case 1:
                bar.setImageResource(R.drawable.redbar);
                break;
            case 2:
                bar.setImageResource(R.drawable.bluebar);
                break;
            case 3:
                bar.setImageResource(R.drawable.greenbar);
                break;
        }
    }

    private final Runnable barcp = new Runnable() {
        @Override
        public void run() {
            playerY = player.getY();
            barY += 10;
            if((bar.getY() + bar.getHeight()) > playerY){
                randBar();

                //right hit
                bar.setX((player.getX() + player.getWidth() /2) - bar.getWidth()/2);
                bar.setY(0);
                barX = (player.getX() + player.getWidth() /2) - bar.getWidth()/2;
                barY = 0;

                deducLife();
            }

            bar.setX(barX);
            bar.setY(barY);
            if(contbar == true){
                //loop handler
                handler.postDelayed(barcp, tick);
            }
        }
    };

    private final Runnable changePosBlue = new Runnable() {
        @Override
        public void run() {
            //fire speed; value to decrement the Y position
            blueY -= 25;

            //check if the image has reached screen edge or bar Y axis
            if(bluef.getY() < bar.getY()){

                //return fire img position to initial
                blueX = (player.getX() + player.getWidth() /2) - bluef.getWidth()/2;
                blueY = player.getY() - player.getHeight()/2;

                //stop the animation
                contb = false;

                //set the image to invisible
                bluef.setVisibility(View.INVISIBLE);

                //correct hit
                if(xbar == 2){

                    //randomize next bar color
                    randBar();

                    //reset the bar
                    bar.setX((player.getX() + player.getWidth() /2) - bar.getWidth()/2);
                    bar.setY(0);
                    barX = (player.getX() + player.getWidth() /2) - bar.getWidth()/2;
                    barY = 0;
                    scoreAdd();
                }
                else deducLife();
            }
            //move the image
            bluef.setX(blueX);
            bluef.setY(blueY);

            if(contb == true){
                //loop handler
                handler.postDelayed(changePosBlue, tick);
            }
        }
    };

    private void scoreAdd() {
        score += 50;

        String x = new Integer(score).toString();
        scoreText.setText(x);
    }

    private void deducLife(){
        //deduc life
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
                popup.setContentView(R.layout.activity_game_cleared);
                popup.show();
                popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView sg = popup.findViewById(R.id.scoreGained);
                String x = new Integer(score).toString();
                sg.setText(x);

                contbar = false;
                break;
        }
    }
    private final Runnable changePosRed = new Runnable() {
        @Override
        public void run() {
            //fire
            redY -= 30;
            if(redf.getY() < bar.getY()){
                redX = (player.getX() + player.getWidth() /2) - redf.getWidth()/2;
                redY = player.getY() - player.getHeight()/2;
                contr = false;
                redf.setVisibility(View.INVISIBLE);

                if(xbar == 1){
                    randBar();
                    //reset the bar
                    bar.setX((player.getX() + player.getWidth() /2) - bar.getWidth()/2);
                    bar.setY(0);
                    barX = (player.getX() + player.getWidth() /2) - bar.getWidth()/2;
                    barY = 0;

                    scoreAdd();
                }
                else deducLife();
            }
            redf.setX(redX);
            redf.setY(redY);

            if(contr == true){
                handler.postDelayed(changePosRed, tick);
            }
        }
    };

    private final Runnable changePosGreen = new Runnable() {
        @Override
        public void run() {
            //fire
            greenY -= 30;
            if(greenf.getY() < bar.getY()){
                greenX = (player.getX() + player.getWidth() /2) - greenf.getWidth()/2;
                greenY = player.getY() - player.getHeight()/2;
                contg = false;
                greenf.setVisibility(View.INVISIBLE);

                if(xbar == 3){
                    randBar();
                    //reset the bar
                    bar.setX((player.getX() + player.getWidth() /2) - bar.getWidth()/2);
                    bar.setY(0);
                    barX = (player.getX() + player.getWidth() /2) - bar.getWidth()/2;
                    barY = 0;
                    scoreAdd();
                }
                else deducLife();
            }
            greenf.setX(greenX);
            greenf.setY(greenY);

            if(contg == true){
                handler.postDelayed(changePosGreen, tick);
            }
        }
    };



    public void pause(View v){
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
}
