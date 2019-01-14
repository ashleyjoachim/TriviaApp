package com.ashleyjoachim.triviaapp.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.data.model.DifficultyModel;

import java.util.List;

public class DifficultyAdapter extends RecyclerView.Adapter<DifficultyViewHolder> {
    private List<DifficultyModel> mDifficultyList;

    public DifficultyAdapter(List<DifficultyModel> difficultyList) {
        mDifficultyList = difficultyList;
    }

    @Override
    public DifficultyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.difficulty_itemview, parent, false);
        return new DifficultyViewHolder(v);    }

    @Override
    public void onBindViewHolder(DifficultyViewHolder holder, int position) {
        DifficultyModel difficultyModel = mDifficultyList.get(position);
        holder.onBind(difficultyModel);
    }

    @Override
    public int getItemCount() {
        return mDifficultyList.size();
    }
}
