package com.ashleyjoachim.triviaapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.data.model.DifficultyModel;
import com.ashleyjoachim.triviaapp.ui.QuestionActivity;

class DifficultyViewHolder extends RecyclerView.ViewHolder {
    private TextView mMode;

    DifficultyViewHolder(View itemView) {
        super(itemView);
        mMode = itemView.findViewById(R.id.button_difficulty);
    }

    void onBind(DifficultyModel model) {
        final String setting = model.getMode();
        mMode.setText(setting);
        mMode.setOnClickListener((View v) -> getDifficultyFromIntent(setting));
    }

    private void getDifficultyFromIntent(String setting) {
        Bundle bundle = new Bundle();
        bundle.putString("difficulty", setting);
        Intent questionActivity = new Intent(itemView.getContext(), QuestionActivity.class);
        questionActivity.putExtras(bundle);
        itemView.getContext().startActivity(questionActivity);
        Toast.makeText(itemView.getContext(), "Selected " + setting, Toast.LENGTH_SHORT).show();
    }
}
