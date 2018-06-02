package com.ashleyjoachim.triviaapp.difficulty.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.difficulty.model.DifficultyModel;

import java.util.List;

public class DifficultyAdapter extends RecyclerView.Adapter<DifficultyViewHolder> {
    private List<DifficultyModel> difficultyList;

    public DifficultyAdapter(List<DifficultyModel> difficultyList) {
        this.difficultyList = difficultyList;
    }

    @Override
    public DifficultyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.difficulty_itemview, parent, false);
        return new DifficultyViewHolder(v);    }

    @Override
    public void onBindViewHolder(DifficultyViewHolder holder, int position) {
        DifficultyModel difficultyModel = difficultyList.get(position);
        holder.onBind(difficultyModel);
    }

    @Override
    public int getItemCount() {
        return difficultyList.size();
    }
}
