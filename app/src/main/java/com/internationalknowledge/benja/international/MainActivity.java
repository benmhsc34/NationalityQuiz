package com.internationalknowledge.benja.international;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.internationalknowledge.benja.international.Utils.ListItem;
import com.internationalknowledge.benja.international.Utils.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.internationalknowledge.benja.international.QuestionActivity.MY_PREFS_NAME;


public class MainActivity extends AppCompatActivity {

    String datesList[] = {"Brazil", "Canada","England", "France", "USA", "India","Spain","China"};
    Integer flagList[] = {R.drawable.br, R.drawable.ca,R.drawable.gb, R.drawable.fr, R.drawable.us,R.drawable.in,R.drawable.es,R.drawable.cn};
    Integer yensList[] = {100,50,10,0,0,0,0,0};

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for (int i = 0; i < datesList.length; i++) {
            ListItem listItemb = new ListItem(flagList[i], datesList[i], yensList[i]);

            listItems.add(listItemb);
        }

        adapter = new MyAdapter(listItems, this);



        recyclerView.setAdapter(adapter);
    }



}