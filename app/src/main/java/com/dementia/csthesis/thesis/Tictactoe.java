package com.dementia.csthesis.thesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Tictactoe extends AppCompatActivity {
    private int turn;
    private int z = 9, i, x;
    String ba, bb, bc, bd, be, bf, bg, bh, bi;
    int scoreValue, ccc;
    private TextView ctr;
    private Button asd;
    Button b[] = new Button[10];
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tictactoe);


        b[0] = (Button) findViewById(R.id.b1);
        b[1] = (Button) findViewById(R.id.b2);
        b[2] = (Button) findViewById(R.id.b3);
        b[3] = (Button) findViewById(R.id.b4);
        b[4] = (Button) findViewById(R.id.b5);
        b[5] = (Button) findViewById(R.id.b6);
        b[6] = (Button) findViewById(R.id.b7);
        b[7] = (Button) findViewById(R.id.b8);
        b[8] = (Button) findViewById(R.id.b9);
        b[9] = null;




/*
        if (b[x].getText().toString().equals("")){
            z = 1;
        }else {
            z = 2;
        }*/
        turn = 1;
        b[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b[0].getText().toString().equals("")) {

                    if (turn == 1) {
                        turn = 2;
                        b[0].setText("X");
                        count();
                        if (ccc == 9) {
                            b[0].setText("X");
                            detect();
                        }
                        if (turn == 2) {

                            bot();
                        }


                    }
                    endGame();
                }
            }
        });
        b[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b[1].getText().toString().equals("")) {

                    if (turn == 1) {
                        turn = 2;
                        b[1].setText("X");
                        count();
                        if (ccc == 9) {
                            b[1].setText("X");
                            detect();
                        }
                        if (turn == 2) {
                            bot();
                        }


                    }

                    endGame();

                }

            }
        });
        b[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b[2].getText().toString().equals("")) {

                    if (turn == 1) {
                        turn = 2;
                        b[2].setText("X");
                        count();
                        if (ccc == 9) {
                            b[2].setText("X");
                            detect();
                        }
                        if (turn == 2) {
                            bot();
                        }


                    }
                    endGame();

                }
            }
        });
        b[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b[3].getText().toString().equals("")) {

                    if (turn == 1) {
                        turn = 2;

                        b[3].setText("X");
                        count();
                        if (ccc == 9) {
                            b[3].setText("X");
                            detect();
                        }
                        if (turn == 2) {
                            bot();
                        }


                    }
                    endGame();


                }
            }
        });
        b[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b[4].getText().toString().equals("")) {

                    if (turn == 1) {
                        turn = 2;
                        b[4].setText("X");
                        count();
                        if (ccc == 9) {
                            b[4].setText("X");
                            detect();
                        }
                        if (turn == 2) {
                            bot();
                        }


                    }
                    endGame();

                }
            }
        });
        b[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b[5].getText().toString().equals("")) {

                    if (turn == 1) {
                        turn = 2;
                        b[5].setText("X");
                        count();
                        if (ccc == 9) {
                            b[5].setText("X");
                            detect();
                        }
                        if (turn == 2) {
                            bot();
                        }


                    }
                    endGame();

                }
            }
        });
        b[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b[6].getText().toString().equals("")) {

                    if (turn == 1) {
                        turn = 2;
                        b[6].setText("X");
                        count();
                        if (ccc == 9) {
                            b[6].setText("X");
                            detect();
                        }
                        if (turn == 2) {
                            bot();
                        }


                    }
                    endGame();

                }
            }
        });
        b[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b[7].getText().toString().equals("")) {

                    if (turn == 1) {
                        turn = 2;
                        b[7].setText("X");
                        count();

                        if (ccc == 9) {
                            b[7].setText("X");
                            detect();
                        }

                        if (turn == 2) {
                            bot();
                        }


                    }
                    endGame();

                }
            }
        });
        b[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (b[8].getText().toString().equals("")) {

                    if (turn == 1) {
                        turn = 2;
                        b[8].setText("X");
                        count();
                        if (ccc == 9) {
                            b[8].setText("X");
                            detect();
                        }
                        if (turn == 2) {
                            bot();
                        }


                    }
                    endGame();

                }
            }
        });

    }
//if occupied all buttons
    public void detect() {
        if (ccc == 9) {

            x = rand.nextInt(9);

            if (b[x].getText().toString().equals("O")) {
                b[x].setText("O");
                ccc +=1;
                tie();
                turn = 1;
            } else if (b[x].getText().toString().equals("X")) {
                b[x].setText("X");
                ccc +=1;
                tie();
                turn = 1;
            }

        }
    }
    //counter for detect
    public void count() {

        ctr = findViewById(R.id.counter);

        ccc += 1;
        String cc = new Integer(ccc).toString();
        ctr.setText(cc);


    }
//minus score
    public void botscore() {

        TextView score = findViewById(R.id.score);
        if (scoreValue >= 1000) {
            scoreValue -= 1000;
            String scoreString = new Integer(scoreValue).toString();
            score.setText(scoreString);
            b[0].setText("");
            b[1].setText("");
            b[2].setText("");
            b[3].setText("");
            b[4].setText("");
            b[5].setText("");
            b[6].setText("");
            b[7].setText("");
            b[8].setText("");
            ccc = 0;

            ctr.setText("");
            turn = 1;
        }else if(scoreValue == 0){
            b[0].setText("");
            b[1].setText("");
            b[2].setText("");
            b[3].setText("");
            b[4].setText("");
            b[5].setText("");
            b[6].setText("");
            b[7].setText("");
            b[8].setText("");
            ccc = 0;

            ctr.setText("");
            turn = 1;
        }


    }
    public void tie(){
        if (ccc == 10){
            ccc = 0;
            ctr.setText("");
            turn = 1;
            compare();


        }
    }

//score
    public void scoring() {
        TextView score = findViewById(R.id.score);

        scoreValue += 1000;
        String scoreString = new Integer(scoreValue).toString();
        score.setText(scoreString);

        if (scoreValue == 1000 || scoreValue == 2000 || scoreValue == 3000) {

            b[0].setText("");
            b[1].setText("");
            b[2].setText("");
            b[3].setText("");
            b[4].setText("");
            b[5].setText("");
            b[6].setText("");
            b[7].setText("");
            b[8].setText("");
            ccc = 0;

            ctr.setText("");
            turn = 1;

            if(scoreValue == 3000){
                Intent startIntent = new Intent(getApplicationContext(), gameCleared.class);
                startIntent.putExtra("SCORE", scoreValue);
                startActivity(startIntent);


            }
        }




    }
//end
    public void endGame() {
        ba = b[0].getText().toString();
        bb = b[1].getText().toString();
        bc = b[2].getText().toString();
        bd = b[3].getText().toString();
        be = b[4].getText().toString();
        bf = b[5].getText().toString();
        bg = b[6].getText().toString();
        bh = b[7].getText().toString();
        bi = b[8].getText().toString();


        //player
        if (ba.equals("X") && bb.equals("X") && bc.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        if (ba.equals("X") && be.equals("X") && bi.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        if (ba.equals("X") && bd.equals("X") && bg.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        if (bb.equals("X") && be.equals("X") && bh.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        if (bc.equals("X") && bf.equals("X") && bi.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        if (bc.equals("X") && be.equals("X") && bg.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        if (bd.equals("X") && be.equals("X") && bf.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        if (bg.equals("X") && bh.equals("X") && bi.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }




        //bot

        if (ba.equals("O") && bb.equals("O") && bc.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        if (ba.equals("O") && be.equals("O") && bi.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        if (ba.equals("O") && bd.equals("O") && bg.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        if (bb.equals("O") && be.equals("O") && bh.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        if (bc.equals("O") && bf.equals("O") && bi.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        if (bd.equals("O") && be.equals("O") && bf.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        if (bg.equals("O") && bh.equals("O") && bi.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }

        if (bc.equals("O") && be.equals("O") && bg.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();


        }





    }

    public void bot() {


        int bott = 2;

//bot
        if (turn == bott) {
            for (i = 0; i <= 1; i++) {
                switch (i) {
                    case 0:

                        x = rand.nextInt(9);

                        break;
                    case 1:

                        if (b[x].getText().toString().equals("O")) {




                            i = -1;
                        } else if (b[x].getText().toString().equals("X")) {


                            i = -1;
                        }
                        else {
                            if(b[x].getText().toString().equals("")) {
                                b[x].setText("O");
                                turn = 1;
                                count();
                            }

                            /*else if(b[x].getText().toString().equals("O")){
                                b[x].setText("O");
                            }*/
                        }
                        break;
                }


            }


        }


    }



    public void compare(){
        ba = b[0].getText().toString();
        bb = b[1].getText().toString();
        bc = b[2].getText().toString();
        bd = b[3].getText().toString();
        be = b[4].getText().toString();
        bf = b[5].getText().toString();
        bg = b[6].getText().toString();
        bh = b[7].getText().toString();
        bi = b[8].getText().toString();


        //player
        if (ba.equals("X") && bb.equals("X") && bc.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
       else if (ba.equals("X") && be.equals("X") && bi.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
       else if (ba.equals("X") && bd.equals("X") && bg.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        else if (bb.equals("X") && be.equals("X") && bh.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        else if (bc.equals("X") && bf.equals("X") && bi.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        else if (bc.equals("X") && be.equals("X") && bg.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        else if (bd.equals("X") && be.equals("X") && bf.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }
        else if (bg.equals("X") && bh.equals("X") && bi.equals("X")) {
            Toast.makeText(Tictactoe.this, "Winner Player X!", Toast.LENGTH_SHORT).show();
            scoring();
        }




        //bot

        else if (ba.equals("O") && bb.equals("O") && bc.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        else if (ba.equals("O") && be.equals("O") && bi.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        else if (ba.equals("O") && bd.equals("O") && bg.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        else if (bb.equals("O") && be.equals("O") && bh.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        else if (bc.equals("O") && bf.equals("O") && bi.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        else if (bd.equals("O") && be.equals("O") && bf.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }
        else if (bg.equals("O") && bh.equals("O") && bi.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();
        }

        else if (bc.equals("O") && be.equals("O") && bg.equals("O")) {
            Toast.makeText(Tictactoe.this, "Winner Player O!", Toast.LENGTH_SHORT).show();
            botscore();

        }
        else{
            Toast.makeText(Tictactoe.this, "Tied Game!", Toast.LENGTH_SHORT).show();
            b[0].setText("");
            b[1].setText("");
            b[2].setText("");
            b[3].setText("");
            b[4].setText("");
            b[5].setText("");
            b[6].setText("");
            b[7].setText("");
            b[8].setText("");
        }
    }










}





