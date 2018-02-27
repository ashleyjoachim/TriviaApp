package com.ashleyjoachim.triviaapp.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.model.TriviaCategory;
import com.ashleyjoachim.triviaapp.ui.DifficultyActivity;

import java.util.ArrayList;
import java.util.List;

public class TriviaCategoryAdapter extends RecyclerView.Adapter<TriviaCategoryAdapter.TriviaCategoryViewHolder> {

    private List<TriviaCategory> triviaCategories = new ArrayList<>();
    private Context context;

    public TriviaCategoryAdapter(List<TriviaCategory> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }

    @Override
    public TriviaCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_itemview, parent, false);
        context = parent.getContext();
        return new TriviaCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TriviaCategoryViewHolder holder, final int position) {
        final String name = triviaCategories.get(position).getName();
        final int id = triviaCategories.get(position).getId();

        holder.buttonCategory.setText(name);
        holder.buttonCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent difficultyActivity = new Intent(context, DifficultyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("name", name);
                difficultyActivity.putExtras(bundle);
                context.startActivity(difficultyActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return triviaCategories.size();
    }

    public class TriviaCategoryViewHolder extends RecyclerView.ViewHolder {
        private Button buttonCategory;

        public TriviaCategoryViewHolder(View itemView) {
            super(itemView);
            buttonCategory = itemView.findViewById(R.id.category_button);
        }
    }
}
