package com.example.benja.nationalityquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.benja.nationalityquiz.Utils.ListItem;
import com.example.benja.nationalityquiz.Utils.MyAdapter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        float usa_games = prefs.getInt("usa_games", -1);
        float usa_score = prefs.getInt("usa_score", 0);


        int england_games = prefs.getInt("england_games", -1);
        int england_score = prefs.getInt("england_score", 0);

        int france_games = prefs.getInt("france_games", -1);
        int france_score = prefs.getInt("france_score", 0);

        int spain_games = prefs.getInt("spain_games", -1);
        int spain_score = prefs.getInt("spain_score", 0);

        int japan_games = prefs.getInt("japan_games", -1);
        int japan_score = prefs.getInt("japan_score", 0);

        int china_games = prefs.getInt("china_games", -1);
        int china_score = prefs.getInt("china_score", 0);

        int germany_games = prefs.getInt("germany_games", -1);
        int germany_score = prefs.getInt("germany_score", 0);

        int australia_games = prefs.getInt("australia_games", -1);
        int australia_score = prefs.getInt("australia_score", 0);


        String englandPourcentage = (england_score / england_games) * 10 + "%";
        if (england_games == -1) {
            englandPourcentage = "N/A";
        }
        String francePourcentage = ((france_score / france_games)) * 10 + "%";
        if (france_games == -1) {
            francePourcentage = "N/A";
        }
        String usaPourcentage = Math.round((usa_score / usa_games) * 10) + "%";
        if (usa_games == -1) {
            usaPourcentage = "N/A";
        }
        String australiaPourcentage = Math.round((australia_score / australia_games)) * 10 + "%";
        if (australia_games == -1) {
            australiaPourcentage = "N/A";
        }
        String chinaPourcentage = Math.round((china_score / china_games)) * 10 + "%";
        if (china_games == -1) {
            chinaPourcentage = "N/A";
        }
        String japanPourcentage = Math.round((japan_score / japan_games)) * 10 + "%";
        if (japan_games == -1) {
            japanPourcentage = "N/A";
        }
        String spainPourcentage = Math.round((spain_score / spain_games)) * 10 + "%";
        if (spain_games == -1) {
            spainPourcentage = "N/A";
        }
        String germanyPourcentage = Math.round((germany_score / germany_games)) * 10 + "%";
        if (germany_games == -1) {
            germanyPourcentage = "N/A";
        }


        List<String> pourcentage = Arrays.asList(englandPourcentage, francePourcentage, usaPourcentage, spainPourcentage);

        String zero = pourcentage.get(0);
        String un = pourcentage.get(1);
        String deux = pourcentage.get(2);
        String trois = pourcentage.get(3);

        Collections.sort(pourcentage);
        Collections.reverse(pourcentage);

        String statsList[] = {pourcentage.get(0), pourcentage.get(1), pourcentage.get(2), pourcentage.get(3)};

        int zzero = 0;
        int uun = 1;
        int ddeux = 2;
        int ttrois = 3;

        for (int i = 0; i < statsList.length; i++) {
            if (statsList[i].equals(zero)) {
                zzero = (i);
            }
            if (statsList[i].equals(un)) {
                uun = (i);
            }
            if (statsList[i].equals(deux)) {
                ddeux = (i);
            }
            if (statsList[i].equals(trois)) {
                ttrois = (i);
            }
        }
        String[] arrayCountries = new String[4];
        arrayCountries[zzero] = "England";
        arrayCountries[uun] = "France";
        arrayCountries[ddeux] = "USA";
        arrayCountries[ttrois] = "Spain";

        int[] arrayFlags = new int[4];
        arrayFlags[zzero] = R.drawable.gb;
        arrayFlags[uun] = R.drawable.fr;
        arrayFlags[ddeux] = R.drawable.us;
        arrayFlags[ttrois] = R.drawable.es;


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for (int i = 0; i < arrayCountries.length; i++) {
            ListItem listItemb = new ListItem(arrayFlags[i], arrayCountries[i], statsList[i]);

            listItems.add(listItemb);
        }

        adapter = new MyAdapter(listItems, this);

        recyclerView.setAdapter(adapter);
    }
}
