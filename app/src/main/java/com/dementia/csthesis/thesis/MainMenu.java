package com.dementia.csthesis.thesis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    Dialog popup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popup = new Dialog(this);

        Button start = (Button) findViewById(R.id.menuStart);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent startIntent = new Intent(getApplicationContext(), TransitionScreen.class);
                startActivity(startIntent);

            }
        });


    }

    public void ShowSettings(View v){
        popup.setContentView(R.layout.activity_main_settings);
        popup.show();

        Button closeBtn = popup.findViewById(R.id.settingsClose);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void ShowStats(View v){
        popup.setContentView(R.layout.activity_main_stats);
        popup.show();

        Button closeBtn = popup.findViewById(R.id.statsClose);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void ShowScores(View v){
        popup.setContentView(R.layout.activity_main_scores);
        popup.show();

        Button closeBtn = popup.findViewById(R.id.scoresClose);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void onBackPressed(){

        finish();
    }
}
