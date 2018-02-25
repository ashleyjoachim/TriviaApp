package com.ashleyjoachim.triviaapp.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.model.TriviaCategory;
import com.ashleyjoachim.triviaapp.model.TriviaResults;

import java.util.ArrayList;
import java.util.List;

public class TriviaQuestionAdapter extends RecyclerView.Adapter<TriviaQuestionAdapter.TriviaQuestionViewHolder> {

    private List<TriviaCategory> triviaResults = new ArrayList<>();
    private Context context;

    public TriviaQuestionAdapter(List<TriviaCategory> triviaResults) {
        this.triviaResults = triviaResults;
    }

    @Override
    public TriviaQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_itemview, parent, false);
        context = parent.getContext();
        return new TriviaQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TriviaQuestionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return triviaResults.size();
    }

    public class TriviaQuestionViewHolder extends RecyclerView.ViewHolder {
        public TriviaQuestionViewHolder(View itemView) {
            super(itemView);

        }
    }
}
