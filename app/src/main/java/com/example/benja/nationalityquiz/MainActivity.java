package com.example.benja.nationalityquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.benja.nationalityquiz.Utils.ListItem;
import com.example.benja.nationalityquiz.Utils.MyAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String datesList[] = {"Great Britain", "France", "USA", "Spain", "China", "Japan", "Germany", "Australia"};
    Integer flagList[] = {R.drawable.gb, R.drawable.fr, R.drawable.us, R.drawable.es, R.drawable.cn, R.drawable.jp, R.drawable.de, R.drawable.au};

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for (int i = 0; i < datesList.length; i++) {
            ListItem listItemb = new ListItem(flagList[i], datesList[i], "");

            listItems.add(listItemb);
        }

        adapter = new MyAdapter(listItems, this);

        recyclerView.setAdapter(adapter);
    }



}