package com.ashleyjoachim.triviaapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.difficulty.recyclerview.DifficultyAdapter;
import com.ashleyjoachim.triviaapp.difficulty.model.DifficultyModel;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import static com.ashleyjoachim.triviaapp.Constants.EASY;
import static com.ashleyjoachim.triviaapp.Constants.HARD;
import static com.ashleyjoachim.triviaapp.Constants.MEDIUM;

public class DifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        List<DifficultyModel> difficultyList = new ArrayList<>();
        difficultyList.add(new DifficultyModel(EASY));
        difficultyList.add(new DifficultyModel(MEDIUM));
        difficultyList.add(new DifficultyModel(HARD));

        DiscreteScrollView difficultyRecyclerView = findViewById(R.id.difficulty_picker_rv);
        DifficultyAdapter difficultyAdapter = new DifficultyAdapter(difficultyList);
        InfiniteScrollAdapter wrapper = InfiniteScrollAdapter.wrap(difficultyAdapter);
        difficultyRecyclerView.setAdapter(wrapper);

        difficultyRecyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        String name = getIntent().getExtras().getString("name");
        TextView textView = findViewById(R.id.textview_placeholder);
        textView.setText(name);
    }
}