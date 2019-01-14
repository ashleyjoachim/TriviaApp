package com.ashleyjoachim.triviaapp.category.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.category.model.TriviaCategory;
import com.ashleyjoachim.triviaapp.ui.DifficultyActivity;

class TriviaCategoryViewHolder extends RecyclerView.ViewHolder {
    private Button mButtonCategory;

    TriviaCategoryViewHolder(View itemView) {
        super(itemView);
        mButtonCategory = itemView.findViewById(R.id.category_button);
    }

    void onBind(TriviaCategory triviaCategory) {
        final String name = triviaCategory.getName();
        final int id = triviaCategory.getId();
        mButtonCategory.setText(name);
        mButtonCategory.setOnClickListener(v -> {
            Intent difficultyActivity = new Intent(itemView.getContext(), DifficultyActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            bundle.putString("name", name);
            difficultyActivity.putExtras(bundle);
            itemView.getContext().startActivity(difficultyActivity);
        });
    }
}
