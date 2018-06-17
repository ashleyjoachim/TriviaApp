package com.ashleyjoachim.triviaapp.questions.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.questions.model.TriviaResults;

import java.util.List;

public class TriviaQuestionAdapter extends RecyclerView.Adapter<TriviaQuestionViewHolder> {

    private List<TriviaResults> triviaResultsList;

    public TriviaQuestionAdapter(List<TriviaResults> triviaResultsList) {
        this.triviaResultsList = triviaResultsList;
        notifyDataSetChanged();
    }

    @Override
    public TriviaQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_itemview, parent, false);
        return new TriviaQuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TriviaQuestionViewHolder holder, final int position) {
        final TriviaResults triviaResults = triviaResultsList.get(position);
        holder.onBind(triviaResults);
    }

    @Override
    public int getItemCount() {
        return triviaResultsList.size();
    }

}
