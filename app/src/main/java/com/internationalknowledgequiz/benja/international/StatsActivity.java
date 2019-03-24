package com.internationalknowledgequiz.benja.international;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.internationalknowledgequiz.benja.international.Utils.ListStatsItems;
import com.internationalknowledgequiz.benja.international.Utils.MyStatsAdapter;

import java.util.ArrayList;
import java.util.List;

public class StatsActivity extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListStatsItems> listItems;
    private CollectionReference mCollectionReference = FirebaseFirestore.getInstance().collection("Questions");
    ArrayList<ListStatsItems> countryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

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

        mCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        countryList.add(new ListStatsItems((String) document.get("flag"), document.getId(), prefs.getString(document.getId() + "_pourcentage", "N/A")));
                    }
                    Log.d("debug", countryList.toString());
                } else {
                    Log.d("debug", "Error getting documents: ", task.getException());
                }
                adapter = new MyStatsAdapter(countryList, StatsActivity.this);

                recyclerView.setAdapter(adapter);
            }
        });


    }
}
