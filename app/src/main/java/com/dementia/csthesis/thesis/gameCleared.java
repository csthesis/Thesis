package com.dementia.csthesis.thesis;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class gameCleared extends AppCompatActivity {


    private Button next, exit;
    private TextView scoreText;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_cleared);

        next = findViewById(R.id.clearNext);
        exit = findViewById(R.id.clearExit);
        scoreText = findViewById(R.id.finalScore);

        score = getIntent().getIntExtra("SCORE", 0);

        String scorestring = new Integer(score).toString();
        scoreText.setText(scorestring);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                startActivity(startIntent);
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(startIntent);
                finish();
            }
        });
    }
    public void onBackPressed(){}
}
