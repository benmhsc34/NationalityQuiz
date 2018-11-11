package com.internationalknowledge.benja.international;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import static com.internationalknowledge.benja.international.QuestionActivity.MY_PREFS_NAME;

public class StartActivity extends AppCompatActivity {

  //  BillingProcessor bp;

  // Button iab;
   Button rateUs;
   Button moreApps;

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

        moreApps = findViewById(R.id.moreAppsButton);
        moreApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://www.instagram.com/international_knowledge_quiz/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


     /*   iab = findViewById(R.id.moreAppsButton);

        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgnoVr2BZRaK8MvLo3XGNvHvQOyC7NBmuMaSy/TLfISiNL/tpbtX1Exijv4ZH/bmDw6l4bp3KLV0Idr5594owHAx7C7/7yyWuslzMRm588Qd8OBuB9C7+vDGR5qywG9+cJIQCrmufZqIoks/tSo+3HCevewNXc9AsMbSMFNOuyv/NPTFbtYBVVUc71YldkCMDr1BT0OglGhPb02noyp544BJOuduHcOa/DbRKnbZdPdS9c1lgSKTttNx2GQWFYbiGgV5CsbspIzlHDkVxFbhGfSEIczz3AgpnrCL9MSh3Gt6UuPz77VS2f9o3Ao0i5slTRR7zZZNltVMDi2eNbQGe3QIDAQAB", this);

        iab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bp.purchase(StartActivity.this, "android.test.purchased");
            }
        });
*/

                Button playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button playButton = findViewById(R.id.playButton);

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
/*
    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
    iab.setText(R.string.moreApps);
    iab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(StartActivity.this, "More apps from Blitzzer coming very soon", Toast.LENGTH_LONG).show();
        }
    });
    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
    editor.putString("payment", "OK");
    editor.apply();
    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }
    */
}
