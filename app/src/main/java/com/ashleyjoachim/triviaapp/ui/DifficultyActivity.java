package com.ashleyjoachim.triviaapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ashleyjoachim.triviaapp.R;


public class DifficultyActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EASY = "Easy", MEDIUM = "Medium", HARD = "Hard";
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        String name = getIntent().getExtras().getString("name");
        TextView textView = findViewById(R.id.textview_placeholder); //remove later
        textView.setText("Category \n" + name);// remove later
        id = getIntent().getExtras().getInt("id");

        Button easy = findViewById(R.id.easy);
        easy.setOnClickListener(this);

        Button medium = findViewById(R.id.medium);
        medium.setOnClickListener(this);

        Button hard = findViewById(R.id.hard);
        hard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.easy:
                setDifficulty(EASY);
                break;
            case R.id.medium:
                setDifficulty(MEDIUM);
                break;
            case R.id.hard:
                setDifficulty(HARD);
                break;
        }
    }

    public void setDifficulty(String mode) {
        Intent questionActivity = new Intent(getApplicationContext(), QuestionActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("difficulty", mode);
        bundle.putInt("id", id);
        questionActivity.putExtras(bundle);
        startActivity(questionActivity);
        Toast.makeText(getApplicationContext(), "Selected " + mode, Toast.LENGTH_SHORT).show();
    }
}
