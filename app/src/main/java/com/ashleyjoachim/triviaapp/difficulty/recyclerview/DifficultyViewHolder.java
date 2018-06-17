package com.ashleyjoachim.triviaapp.difficulty.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.difficulty.model.DifficultyModel;
import com.ashleyjoachim.triviaapp.ui.QuestionActivity;

public class DifficultyViewHolder extends RecyclerView.ViewHolder {
    private TextView mode;

    public DifficultyViewHolder(View itemView) {
        super(itemView);
        mode = itemView.findViewById(R.id.button_difficulty);
    }

    public void onBind(DifficultyModel model) {
        final String setting = model.getMode();

        mode.setText(setting);
        mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("difficulty", setting);
                Intent questionActivity = new Intent(itemView.getContext(), QuestionActivity.class);
                questionActivity.putExtras(bundle);
                itemView.getContext().startActivity(questionActivity);
                Toast.makeText(itemView.getContext(), "Selected " + setting, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
