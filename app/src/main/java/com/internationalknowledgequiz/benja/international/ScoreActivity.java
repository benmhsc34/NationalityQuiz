package com.internationalknowledgequiz.benja.international;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import static com.internationalknowledgequiz.benja.international.QuestionActivity.MY_PREFS_NAME;

public class ScoreActivity extends AppCompatActivity {


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
        TextView perfectScorePresent = findViewById(R.id.perfectScorePresent);

        perfectScorePresent.setText("");

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tapAnywhere.setText(R.string.tapAnywhere);

        Intent myIntent = getIntent();
        String country = myIntent.getStringExtra("country");
        int score = myIntent.getIntExtra("score", 0);
        String image = myIntent.getStringExtra("image");


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        float theGames = prefs.getInt(country + "_games", -1);
        float theScore = prefs.getInt(country + "_score", 0);
        String thePourcentage = Math.round(theScore / theGames * 10) + "%";
        prefs.edit().putString(country + "_pourcentage", thePourcentage).apply();
        pourcentageNumberTV.setText(thePourcentage);

        Picasso.get().load(image).into(imageView);

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
