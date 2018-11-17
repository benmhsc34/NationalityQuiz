package com.internationalknowledge.benja.international;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.internationalknowledge.benja.international.Utils.ListStatsItems;
import com.internationalknowledge.benja.international.Utils.MyStatsAdapter;

import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListStatsItems> listItems;


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

        String englandPourcentage =  prefs.getString("england_pourcentage", "N/A");
        String francePourcentage = prefs.getString("france_pourcentage", "N/A");
        String usaPourcentage = prefs.getString("usa_pourcentage", "N/A");
        String spainPourcentage = prefs.getString("spain_pourcentage", "N/A");
        String indiaPourcentage = prefs.getString("india_pourcentage", "N/A");
        String chinaPourcentage = prefs.getString("china_pourcentage", "N/A");


        String[] statsList = {englandPourcentage, francePourcentage, usaPourcentage, indiaPourcentage, spainPourcentage, chinaPourcentage};
        String[] arrayCountries = {"England", "France", "USA", "India", "Spain","China"};
        int[] arrayFlags = {R.drawable.gb, R.drawable.fr, R.drawable.us, R.drawable.in, R.drawable.es,R.drawable.cn};


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for (int i = 0; i < arrayCountries.length; i++) {
            ListStatsItems listItemb = new ListStatsItems(arrayFlags[i], arrayCountries[i], statsList[i]);

            listItems.add(listItemb);
        }

        adapter = new MyStatsAdapter(listItems, StatsActivity.this);

        recyclerView.setAdapter(adapter);
    }
}
