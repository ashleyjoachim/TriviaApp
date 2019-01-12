package com.ashleyjoachim.triviaapp.category.recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.category.model.TriviaCategory;

import java.util.List;

public class TriviaCategoryAdapter extends RecyclerView.Adapter<TriviaCategoryViewHolder> {

    private List<TriviaCategory> mTriviaCategories;

    public TriviaCategoryAdapter(List<TriviaCategory> triviaCategories) {
        mTriviaCategories = triviaCategories;
    }

    @Override
    public TriviaCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_itemview, parent, false);
        return new TriviaCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TriviaCategoryViewHolder holder, final int position) {
        TriviaCategory triviaCategory = mTriviaCategories.get(position);
        holder.onBind(triviaCategory);
    }

    @Override
    public int getItemCount() {
        return mTriviaCategories.size();
    }

}
