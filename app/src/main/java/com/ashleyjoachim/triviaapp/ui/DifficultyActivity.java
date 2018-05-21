package com.ashleyjoachim.triviaapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.adapter.DifficultyAdapter;
import com.ashleyjoachim.triviaapp.model.DifficultyModel;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

public class DifficultyActivity extends AppCompatActivity {
    private static final String EASY = "easy", MEDIUM = "medium", HARD = "hard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        DiscreteScrollView difficultyRecyclerView = findViewById(R.id.difficulty_picker_rv);

        List<DifficultyModel> difficultyList = new ArrayList<>();
        difficultyList.add(new DifficultyModel(EASY));
        difficultyList.add(new DifficultyModel(MEDIUM));
        difficultyList.add(new DifficultyModel(HARD));

        DifficultyAdapter difficultyAdapter = new DifficultyAdapter(difficultyList);

        InfiniteScrollAdapter wrapper = InfiniteScrollAdapter.wrap(difficultyAdapter);

        difficultyRecyclerView.setAdapter(wrapper);

        difficultyRecyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.LEFT)
                .setPivotY(Pivot.Y.BOTTOM)
                .build());

        String name = getIntent().getExtras().getString("name");
        TextView textView = findViewById(R.id.textview_placeholder); //remove later
        textView.setText("Category \n" + name);// remove later
    }
}