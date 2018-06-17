package com.ashleyjoachim.triviaapp.category.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.category.model.TriviaCategory;
import com.ashleyjoachim.triviaapp.ui.DifficultyActivity;

public class TriviaCategoryViewHolder extends RecyclerView.ViewHolder {
    private Button buttonCategory;

    public TriviaCategoryViewHolder(View itemView) {
        super(itemView);
        buttonCategory = itemView.findViewById(R.id.category_button);
    }

    public void onBind(TriviaCategory triviaCategory) {
        final String name = triviaCategory.getName();
        final int id = triviaCategory.getId();

        buttonCategory.setText(name);

        buttonCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent difficultyActivity = new Intent(itemView.getContext(), DifficultyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("name", name);
                difficultyActivity.putExtras(bundle);
                itemView.getContext().startActivity(difficultyActivity);
            }
        });
    }
}