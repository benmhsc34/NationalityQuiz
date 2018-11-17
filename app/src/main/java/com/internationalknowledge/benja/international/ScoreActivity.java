package com.internationalknowledge.benja.international;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import static com.internationalknowledge.benja.international.QuestionActivity.MY_PREFS_NAME;
import static java.lang.reflect.Array.getInt;

public class ScoreActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView scoreTV = findViewById(R.id.scoreTV);
        TextView scoreNumberTV = findViewById(R.id.scoreNumberTV);
        TextView pourcentageTV = findViewById(R.id.pourcentageTV);
        TextView yourScore = findViewById(R.id.youScored);
        TextView pourcentageNumberTV = findViewById(R.id.pourcentageNumberTV);
        ImageView imageView = findViewById(R.id.Logo);
        RelativeLayout relativeLayout = findViewById(R.id.relativeLayoutMain);
        TextView tapAnywhere = findViewById(R.id.tapAnywhere);

        tapAnywhere.setText(R.string.tapAnywhere);

        MobileAds.initialize(this, "ca-app-pub-7146853836816464~9147761003");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7146853836816464/4055356048");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String payment = prefs.getString("payment", "NOT");
                if (mInterstitialAd.isLoaded() && !(payment.equals("OK"))) {
                    mInterstitialAd.show();
                }
                finish();
            }
        });

        Intent myIntent = getIntent();
        String country = myIntent.getStringExtra("country");
        int score = myIntent.getIntExtra("score", 0);
        int image = myIntent.getIntExtra("image", 0);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        switch (country) {
            case "England":
                float england_games = prefs.getInt("england_games", -1);
                float england_score = prefs.getInt("england_score", 0);
                String englandPourcentage = Math.round(england_score / england_games * 10) + "%";
                prefs.edit().putString("england_pourcentage", englandPourcentage).apply();
                pourcentageNumberTV.setText(englandPourcentage);

                break;
            case "France":
                float france_games = prefs.getInt("france_games", -1);
                float france_score = prefs.getInt("france_score", 0);
                String francePourcentage = Math.round(france_score / france_games * 10) + "%";
                prefs.edit().putString("england_pourcentage", francePourcentage).apply();
                pourcentageNumberTV.setText(francePourcentage);

                break;
            case "USA":
                float usa_games = prefs.getInt("usa_games", -1);
                float usa_score = prefs.getInt("usa_score", 0);
                String usaPourcentage = Math.round(usa_score / usa_games * 10) + "%";
                prefs.edit().putString("usa_pourcentage", usaPourcentage).apply();
                pourcentageNumberTV.setText(usaPourcentage);

                break;
            case "Spain":
                float spain_games = prefs.getInt("spain_games", -1);
                float spain_score = prefs.getInt("spain_score", 0);
                String spainPourcentage = Math.round(spain_score / spain_games * 10) + "%";
                prefs.edit().putString("spain_pourcentage", spainPourcentage).apply();
                pourcentageNumberTV.setText(spainPourcentage);

                break;
            case "India":
                float india_games = prefs.getInt("india_games", -1);
                float india_score = prefs.getInt("india_score", 0);
                String indiaPourcentage = Math.round(india_score / india_games * 10) + "%";
                prefs.edit().putString("india_pourcentage", indiaPourcentage).apply();
                pourcentageNumberTV.setText(indiaPourcentage);
                break;
            case "China":
                float china_games = prefs.getInt("china_games", -1);
                float china_score = prefs.getInt("china_score", 0);
                String chinaPourcentage = Math.round(china_score / china_games * 10) + "%";
                prefs.edit().putString("china_pourcentage", chinaPourcentage).apply();
                pourcentageNumberTV.setText(chinaPourcentage);
                break;
        }

        imageView.setImageResource(image);


        yourScore.setText(R.string.youScored);

        pourcentageTV.setText(getString(R.string.percentage) + " " + country + getString(R.string.isNow));

        scoreNumberTV.setText(Integer.toString(score));

        if (score == 10) {
            scoreTV.setText(R.string.amazing);
        } else if (score == 9) {
            scoreTV.setText(R.string.prettyAmazing);
        } else if (score == 8) {
            scoreTV.setText(R.string.prettyAmazzing);
        } else if (score == 7) {
            scoreTV.setText(R.string.wellDone);
        } else if (score == 6) {
            scoreTV.setText(R.string.solidStuff);
        } else if (score == 5) {
            scoreTV.setText(R.string.cdbcdw);
        } else if (score == 4) {
            scoreTV.setText(R.string.ciot);
        } else if (score == 3) {
            scoreTV.setText(R.string.itycdb);
        } else if (score == 2) {
            scoreTV.setText(R.string.ysnalow);
        } else if (score == 1) {
            scoreTV.setText(R.string.ikyabtt);
        } else if (score == 0) {
            scoreTV.setText(R.string.dyet);
        }
    }
}
