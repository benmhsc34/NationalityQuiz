package com.example.benja.nationalityquiz;

import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    public static final String countryName = "KEY_1";
    public static final String countryFlag = "KEY_2";
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        SharedPreferences mPreferences = getSharedPreferences("country_data", MODE_PRIVATE);
        String chosenCountry = mPreferences.getString(countryName, "");
        int correspondingFlag = mPreferences.getInt(countryFlag, 0);

        ImageView imageView = findViewById(R.id.imageViewOfFlag);
        TextView textView =findViewById(R.id.textViewOfCountry);

        imageView.setImageResource(correspondingFlag);
        textView.setText(chosenCountry);




    }
}
