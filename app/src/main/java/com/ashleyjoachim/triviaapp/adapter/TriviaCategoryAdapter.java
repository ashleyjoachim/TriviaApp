package com.ashleyjoachim.triviaapp.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.model.TriviaCategory;

import java.util.List;

public class TriviaCategoryAdapter extends RecyclerView.Adapter<TriviaCategoryViewHolder> {

    private List<TriviaCategory> triviaCategories;

    public TriviaCategoryAdapter(List<TriviaCategory> triviaCategories) {
        this.triviaCategories = triviaCategories;
    }

    @Override
    public TriviaCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_itemview, parent, false);
        return new TriviaCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TriviaCategoryViewHolder holder, final int position) {
        TriviaCategory triviaCategory = triviaCategories.get(position);
        holder.onBind(triviaCategory);

    }

    @Override
    public int getItemCount() {
        return triviaCategories.size();
    }

}
