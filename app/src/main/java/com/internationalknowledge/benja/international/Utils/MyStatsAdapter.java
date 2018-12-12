package com.internationalknowledge.benja.international.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.internationalknowledge.benja.international.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyStatsAdapter extends RecyclerView.Adapter<MyStatsAdapter.ViewHolder> {

    private List<ListStatsItems> listItems;
    private Context context;

    public MyStatsAdapter(List<ListStatsItems> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public MyStatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final ListStatsItems listItem = listItems.get(i);

        viewHolder.textViewName.setText(listItem.getCountry());
        Picasso.with(context).load(listItem.getFlag()).into(viewHolder.imageViewFlag);
        viewHolder.textViewStats.setText(listItem.getStats());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public ImageView imageViewFlag;
        public RelativeLayout relativeLayout;
        public TextView textViewStats;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewCountryName);
            imageViewFlag = itemView.findViewById(R.id.imageViewCountryFlag);
            relativeLayout = itemView.findViewById(R.id.parent_layout);
            textViewStats = itemView.findViewById(R.id.textViewYens);
        }
    }
}