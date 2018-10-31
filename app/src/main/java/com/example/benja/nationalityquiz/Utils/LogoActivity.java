package com.example.benja.nationalityquiz.Utils;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.benja.nationalityquiz.MainActivity;
import com.example.benja.nationalityquiz.R;
import com.example.benja.nationalityquiz.StartActivity;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        int TIME_OUT = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(LogoActivity.this, StartActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);

    }
}
