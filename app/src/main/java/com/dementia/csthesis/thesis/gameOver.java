package com.dementia.csthesis.thesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gameOver extends AppCompatActivity {

    private Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        mainMenu = findViewById(R.id.mainMenu);

        mainMenu.setOnClickListener(new View.OnClickListener() {
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
