package com.internationalknowledgequiz.benja.international;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    Button rateUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        rateUs = findViewById(R.id.rateUs);

        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://play.google.com/store/apps/details?id=com.internationalknowledge.benja.international&fbclid=IwAR3seBH95oALOaJzLZI2J7YiMv0QAYMELj0ZIlRpgSZRqj_errdD6zDuI1k"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        Button moreApps = findViewById(R.id.moreAppsButton);
        moreApps.setText("INSTAGRAM");

        moreApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.instagram.com/blitzzerentreprises/"); // missing 'http://' will cause crashe
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });





        Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(StartActivity.this, MainActivity.class);
                StartActivity.this.startActivity(myIntent);


            }
        });

        Button statsButton = findViewById(R.id.statsButton);
        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartActivity.this, StatsActivity.class);
                StartActivity.this.startActivity(myIntent);
            }
        });

        ImageView settingsIV = findViewById(R.id.settings);
        settingsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(StartActivity.this, SettingsActivity.class);
                StartActivity.this.startActivity(myIntent);
            }
        });


    }

}
