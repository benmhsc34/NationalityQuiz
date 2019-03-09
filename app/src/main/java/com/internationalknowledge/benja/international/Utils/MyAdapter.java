package com.internationalknowledge.benja.international.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.internationalknowledge.benja.international.QuestionActivity;
import com.internationalknowledge.benja.international.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.internationalknowledge.benja.international.QuestionActivity.MY_PREFS_NAME;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final ListItem listItem = listItems.get(i);

        viewHolder.textViewName.setText(listItem.getName());
        Picasso.with(context).load(listItem.getFlag()).into(viewHolder.imageViewFlag);


            viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent(context, QuestionActivity.class);
                    myIntent.putExtra("countryName", listItem.getName());
                    myIntent.putExtra("countryFlag", listItem.getFlag());
                    context.startActivity(myIntent);

                }
            });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageView imageViewFlag;
        public RelativeLayout relativeLayout;
        public TextView textViewYens;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewCountryName);
            imageViewFlag = itemView.findViewById(R.id.imageViewCountryFlag);
            relativeLayout = itemView.findViewById(R.id.parent_layout);
            textViewYens = itemView.findViewById(R.id.textViewYens);
        }
    }
}
