package com.example.benja.nationalityquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    String datesList[] = {"Great Britain", "France", "USA", "Spain", "China", "Japan", "Germany", "Australia"};
    Integer flagList[] = {R.drawable.gb, R.drawable.fr, R.drawable.us, R.drawable.es, R.drawable.cn, R.drawable.jp, R.drawable.de, R.drawable.au};

    public static final String countryNames = "KEY_1";
    public static final String countryFlags = "KEY_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listMood = findViewById(R.id.recyclerView);
        listMood.setAdapter(new CustomAdapter(MainActivity.this, datesList, flagList));
    }

    class CustomAdapter extends ArrayAdapter<String> {

        private final Context context;
        private final String[] countryList;
        private final Integer[] flagList;

        CustomAdapter(Context context, String[] countryList, Integer[] flagList) {
            super(context, R.layout.custom_layout, countryList);
            this.context = context;
            this.countryList = countryList;
            this.flagList = flagList;

        }

        @SuppressLint("InflateParams")
        @NonNull
        @Override
        public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if (inflater != null) {
                    view = inflater.inflate(R.layout.custom_layout, null);
                }
            }

            assert view != null;
            final TextView textView = view.findViewById(R.id.textViewCountryName);
            ImageView imageView = view.findViewById(R.id.imageViewCountryFlag);
            textView.setText(countryList[position]);
            final String country = countryList[position];
            imageView.setImageResource(flagList[position]);
            final int flag = flagList[position];
            notifyDataSetChanged();
            final SharedPreferences mPreferences = getSharedPreferences("country_data", MODE_PRIVATE);


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent greatBritainIntent = new Intent(MainActivity.this, QuestionActivity.class);
                    startActivity(greatBritainIntent);

                    mPreferences.edit().putString(countryNames, country).apply();
                    mPreferences.edit().putInt(countryFlags, flag).apply();


                }
            });

            return view;
        }
    }
}