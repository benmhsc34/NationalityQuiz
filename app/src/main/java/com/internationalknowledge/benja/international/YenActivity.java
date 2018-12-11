package com.internationalknowledge.benja.international;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import java.util.Currency;
import java.util.Locale;

import static com.internationalknowledge.benja.international.QuestionActivity.MY_PREFS_NAME;

public class YenActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {

    BillingProcessor bp;
    Button iab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yen);

     //   Currency currency = Currency.getInstance(Locale.US)
     //  System.out.println("United States: " + currency.getSymbol());

        Button cheapButton = findViewById(R.id.cheapButton);
        Button mediumButton = findViewById(R.id.mediumButton);
        Button bigButton = findViewById(R.id.bigButton);
        Button hugeButton = findViewById(R.id.hugeButton);

        bp = new BillingProcessor(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgnoVr2BZRaK8MvLo3XGNvHvQOyC7NBmuMaSy/TLfISiNL/tpbtX1Exijv4ZH/bmDw6l4bp3KLV0Idr5594owHAx7C7/7yyWuslzMRm588Qd8OBuB9C7+vDGR5qywG9+cJIQCrmufZqIoks/tSo+3HCevewNXc9AsMbSMFNOuyv/NPTFbtYBVVUc71YldkCMDr1BT0OglGhPb02noyp544BJOuduHcOa/DbRKnbZdPdS9c1lgSKTttNx2GQWFYbiGgV5CsbspIzlHDkVxFbhGfSEIczz3AgpnrCL9MSh3Gt6UuPz77VS2f9o3Ao0i5slTRR7zZZNltVMDi2eNbQGe3QIDAQAB", YenActivity.this);

        cheapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bp.purchase(YenActivity.this, "flag_purchase");

            }
        });

        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bp.purchase(YenActivity.this, "medium_flag_purchase");
            }
        });

        bigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bp.purchase(YenActivity.this, "big_flag_purchase");
            }
        });


        hugeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bp.purchase(YenActivity.this, "huge_flag_purchase");
            }
        });
    }

    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        int flags = prefs.getInt("flags", 0);
        int alreadyYen = prefs.getInt("alreadyYen", 0);
        if (alreadyYen == 0) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putInt("flags", flags + 40);
            editor.putInt("alreadyYen", 1);
            editor.apply();
        }
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


}

