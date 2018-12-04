package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class QuickMath extends AppCompatActivity {
    Dialog popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_math);
        popup = new Dialog(this);

        int num1, num2, dum1, dum2, x, key, z;

        Random rand = new Random();
        x = 0;
        num1 = rand.nextInt(49) + 1;
        num2 = rand.nextInt(49 - num1) + 1;
        dum1 = rand.nextInt(49) + 1;
        dum2 = rand.nextInt(49) + 1;
        z = rand.nextInt(1) +1;

        ImageView operand = (ImageView) findViewById(R.id.operation);

        switch (z){
            case 1:
                x = num1 + num2;
                operand.setImageResource(R.drawable.plus);
                break;
            case 2:
                x = num1 - num2;
                operand.setImageResource(R.drawable.minus);
                break;

        }

        key = rand.nextInt(2) + 1;

        TextView fnum = (TextView)findViewById(R.id.num1);
        TextView snum = (TextView)findViewById(R.id.num2);
        TextView total = (TextView)findViewById(R.id.total);

        fnum.setText(Integer.toString(num1));
        snum.setText(Integer.toString(num2));

        Button btn1 = (Button)findViewById(R.id.choice4);
        Button btn2 = (Button)findViewById(R.id.choice5);
        Button btn3 = (Button)findViewById(R.id.choice6);

        switch (key){

            case 1:
                btn1.setText(Integer.toString(x));
                btn2.setText(Integer.toString(dum1));
                btn3.setText(Integer.toString(dum2));
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        popup.setContentView(R.layout.activity_game_cleared);
//                        popup.show();

                        popup.setContentView(R.layout.activity_game_cleared);
                        popup.show();

                        Button next = popup.findViewById(R.id.clearNext);
                        Button exit = popup.findViewById(R.id.clearExit);

                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                                startActivity(startIntent);

                            }
                        });
                        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                                startActivity(startIntent);

                            }
                        });
                    }
                });
                break;
            case 2:

                btn2.setText(Integer.toString(x));
                btn1.setText(Integer.toString(dum1));
                btn3.setText(Integer.toString(dum2));
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.setContentView(R.layout.activity_game_cleared);
                        popup.show();

                        Button next = popup.findViewById(R.id.clearNext);
                        Button exit = popup.findViewById(R.id.clearExit);

                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                                startActivity(startIntent);

                            }
                        });
                        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                                startActivity(startIntent);

                            }
                        });
                    }
                });
                break;
            case 3:
                btn3.setText(Integer.toString(x));
                btn2.setText(Integer.toString(dum1));
                btn1.setText(Integer.toString(dum2));
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.setContentView(R.layout.activity_game_cleared);
                        popup.show();

                        Button next = popup.findViewById(R.id.clearNext);
                        Button exit = popup.findViewById(R.id.clearExit);

                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                                startActivity(startIntent);

                            }
                        });
                        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        exit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                                startActivity(startIntent);

                            }
                        });
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
