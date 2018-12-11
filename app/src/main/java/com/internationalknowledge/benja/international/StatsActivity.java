package com.internationalknowledge.benja.international;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.internationalknowledge.benja.international.Utils.ListStatsItems;
import com.internationalknowledge.benja.international.Utils.MyStatsAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.internationalknowledge.benja.international.QuestionActivity.MY_PREFS_NAME;

public class StatsActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListStatsItems> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        TextView yinYang = findViewById(R.id.yinYang);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        int flags =  prefs.getInt("flags", 0);
        yinYang.setText(flags + " ☯");

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String englandPourcentage =  prefs.getString("england_pourcentage", "N/A");
        String francePourcentage = prefs.getString("france_pourcentage", "N/A");
        String usaPourcentage = prefs.getString("usa_pourcentage", "N/A");
        String spainPourcentage = prefs.getString("spain_pourcentage", "N/A");
        String indiaPourcentage = prefs.getString("india_pourcentage", "N/A");
        String chinaPourcentage = prefs.getString("china_pourcentage", "N/A");
        String canadaPourcentage = prefs.getString("canada_pourcentage", "N/A");
        String brazilPourcentage = prefs.getString("brazil_pourcentage", "N/A");


        String[] statsList = {brazilPourcentage, canadaPourcentage, englandPourcentage, francePourcentage, usaPourcentage, indiaPourcentage, spainPourcentage, chinaPourcentage};
        String[] arrayCountries = {"Brazil","Canada","England", "France", "USA", "India", "Spain","China"};
        int[] arrayFlags = {R.drawable.br, R.drawable.ca,R.drawable.gb, R.drawable.fr, R.drawable.us, R.drawable.in, R.drawable.es,R.drawable.cn};


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
