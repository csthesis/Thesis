package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ShapeImpact extends AppCompatActivity {

    MediaPlayer player;
    Dialog popup;
    private TextView bottombound, projectile;

    private Button crc, str, hrt, pnt, sqr;

    private ImageView life1, life2, life3;

    private TextView scoretv, crcb, strb, hrtb, pntb, sqrb;

    private float screenHeight, screenWidth, projspeed, projleftx, projmid, projrightx, bottomval;

    private int life, score, direction, randY;

    private Handler handler = new Handler();

    private boolean contprojr = false, contprojl = false, move = false;

    private int projint, num;

    private float crc1, crc2, str1, str2, hrt1, hrt2, pnt1, pnt2, sqr1, sqr2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_impact);

        popup = new Dialog(this);

        bottombound = findViewById(R.id.botbound);
        projectile = findViewById(R.id.proj);

        crc = findViewById(R.id.crc);
        str = findViewById(R.id.str);
        hrt = findViewById(R.id.hrt);
        pnt = findViewById(R.id.pnt);
        sqr = findViewById(R.id.sqr);

        crcb = findViewById(R.id.crcbound);
        strb = findViewById(R.id.strbound);
        hrtb = findViewById(R.id.hrtbound);
        pntb = findViewById(R.id.pntbound);
        sqrb = findViewById(R.id.sqrbound);

        life1 = findViewById(R.id.life9);
        life2 = findViewById(R.id.life10);
        life3 = findViewById(R.id.life11);

        scoretv = findViewById(R.id.scoreText3);

        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;


        life = 3;
        score = 0;

        projspeed = (float) (screenHeight * 0.002);

        projleftx = projectile.getX();
        projrightx = projectile.getX() + projectile.getWidth();

        bottombound.post(new Runnable() {
            @Override
            public void run() {
                bottomval = bottombound.getY() - projectile.getHeight();


                //set pos to out of screen
                randDir();
            }
        });

        crc.post(new Runnable() {
            @Override
            public void run() {
                crc1 = crc.getX();
                crc2 = crc1 + crc.getWidth();
            }
        });

        str.post(new Runnable() {
            @Override
            public void run() {
                str1 = str.getX();
                str2 = str1 + str.getWidth();
            }
        });

        hrt.post(new Runnable() {
            @Override
            public void run() {
                hrt1 = hrt.getX();
                hrt2 = hrt1 + hrt.getWidth();
            }
        });

        pnt.post(new Runnable() {
            @Override
            public void run() {
                pnt1 = pnt.getX();
                pnt2 = pnt1 + pnt.getWidth();
            }
        });

        sqr.post(new Runnable() {
            @Override
            public void run() {
                sqr1 = sqr.getX();
                sqr2 = sqr1 + sqr.getWidth();
            }
        });

        //=======================================================================
        crc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crc.setEnabled(false);
                crcb.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        crcb.setVisibility(View.INVISIBLE);
                        crc.setEnabled(true);
                    }
                },100);
                num = 0;
                check(num, crc1, crc2);
            }
        });

        str.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str.setEnabled(false);
                strb.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        strb.setVisibility(View.INVISIBLE);
                        str.setEnabled(true);
                    }
                },100);
                num = 1;
                check(num, str1, str2);
            }
        });

        hrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hrt.setEnabled(false);
                hrtb.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hrtb.setVisibility(View.INVISIBLE);
                        hrt.setEnabled(true);
                    }
                },100);
                num = 2;
                check(num, hrt1, hrt2);
            }
        });

        pnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pnt.setEnabled(false);
                pntb.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pntb.setVisibility(View.INVISIBLE);
                        pnt.setEnabled(true);
                    }
                },100);
                num = 3;
                check(num, pnt1, pnt2);
            }
        });

        sqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqr.setEnabled(false);
                sqrb.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sqrb.setVisibility(View.INVISIBLE);
                        sqr.setEnabled(true);
                    }
                },100);
                num = 4;
                check(num, sqr1, sqr2);
            }
        });
    }

    private final Runnable moveprojright = new Runnable() {
        @Override
        public void run() {

            //if projectile passes through edge of screen
            if(projectile.getX() > screenWidth){
                contprojr = false;
                resetprojr();
                randDir();
            }

            if(contprojr){
                projleftx += projspeed;
                projrightx += projspeed;

                projectile.setX(projleftx);
                projmid = projectile.getX() + (projectile.getWidth() / 2);

                //loop handler
                handler.postDelayed(moveprojright, 1);
            }
        }

    };

    private final Runnable moveprojleft = new Runnable() {
        @Override
        public void run() {

            if(projectile.getX() + projectile.getWidth() < 0){
                contprojl = false;
                resetprojl();
                randDir();
            }

            if(contprojl){
                projleftx -= projspeed;
                projrightx -= projspeed;

                projectile.setX(projleftx);
                projmid = projectile.getX() + (projectile.getWidth() / 2);

                //loop handler
                handler.postDelayed(moveprojleft, 1);
            }
        }

    };

    public void randshape(){
        Random rand = new Random();
        projint = rand.nextInt(5);
        switch(projint){
            case 0:
                projectile.setBackgroundResource(R.drawable.bilog);
                break;
            case 1:
                projectile.setBackgroundResource(R.drawable.star);
                break;
            case 2:
                projectile.setBackgroundResource(R.drawable.heart1);
                break;
            case 3:
                projectile.setBackgroundResource(R.drawable.tri);
                break;
            case 4:
                projectile.setBackgroundResource(R.drawable.square);
                break;

        }

    }

    public void randDir(){
        Random rand = new Random();
        direction = rand.nextInt(2);
        randshape();

        switch (direction){
            case 0:
                resetprojr();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        contprojr = true;
                        handler.postDelayed(moveprojright, 1);
                    }
                },100);
                break;
            case 1:
                resetprojl();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        contprojl = true;
                        handler.postDelayed(moveprojleft, 1);
                    }
                },100);
                break;
        }
    }


    public void resetprojl(){

        Random rand = new Random();
        randY = rand.nextInt((int) bottomval);
        projleftx = screenWidth;
        projrightx = projleftx + projectile.getWidth();
        projectile.setX(screenWidth + projectile.getWidth());
        projectile.setY((float) randY);
    }

    public void resetprojr(){

        Random rand = new Random();
        //random y value
        randY = rand.nextInt((int) bottomval);

        projleftx = 0 - projectile.getWidth();
        projrightx = 0;

        projectile.setX(0 - projectile.getWidth());
        projectile.setY((float) randY);
    }

    public void check(int btnint, float x1, float x2){
        if(btnint == projint){
            if(projmid > x1 && projmid < x2){
                score += 450;
                String x = new Integer(score).toString();
                scoretv.setText(x);

                contprojl = false;
                contprojr = false;

                randDir();
            }else
                deduc();

        }else if(btnint != projint){
            deduc();
        }
    }

    public void deduc(){

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
                contprojl = false;
                contprojr = false;

                if(score > 0){
                    Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);
                }else if (score < 2500){
                    Intent startIntent = new Intent(getApplicationContext(), gameOver.class);
                    startIntent.putExtra("SCORE", score);
                    startActivity(startIntent);
                }
                break;
        }
    }

    public void pause(View view){
        contprojl = false;
        contprojr = false;

        popup.setContentView(R.layout.activity_ingame_pause);
        popup.show();

        Button pauseClose = popup.findViewById(R.id.ingamePauseClose);
        Button returnGame = popup.findViewById(R.id.ingameReturn);
        Button main = popup.findViewById(R.id.ingameExit);

        pauseClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contprojl = true;
                contprojr = true;
                popup.dismiss();


            }
        });
        returnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contprojl = true;
                contprojr = true;
                popup.dismiss();

            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(startIntent);
            }
        });

        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onBackPressed(){
        pause(null);
    }
}
