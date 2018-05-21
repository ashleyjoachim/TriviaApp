package com.ashleyjoachim.triviaapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.model.TriviaResults;

import java.util.List;

public class TriviaQuestionAdapter extends RecyclerView.Adapter<TriviaQuestionViewHolder> {

    private List<TriviaResults> triviaResultsList;

    public TriviaQuestionAdapter(List<TriviaResults> triviaResultsList) {
        this.triviaResultsList = triviaResultsList;
    }

    @Override
    public TriviaQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_itemview, parent, false);
        return new TriviaQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TriviaQuestionViewHolder holder, int position) {
        TriviaResults triviaResults = triviaResultsList.get(position);
        holder.onBind(triviaResults);
    }

    @Override
    public int getItemCount() {
        return triviaResultsList.size();
    }

}
