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
    private TextView textView;
    private Button easy, medium, hard;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        String name = getIntent().getExtras().getString("name");
        textView = findViewById(R.id.textview_placeholder);
        textView.setText("Category \n" + name);
        id = getIntent().getExtras().getInt("id");

        easy = findViewById(R.id.easy);
        easy.setOnClickListener(this);
        medium = findViewById(R.id.medium);
        medium.setOnClickListener(this);
        hard = findViewById(R.id.hard);
        hard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.easy:
                setDifficulty("easy");
                Toast.makeText(getApplicationContext(), "Selected Easy", Toast.LENGTH_SHORT).show();
                break;
            case R.id.medium:
                setDifficulty("medium");
                Toast.makeText(getApplicationContext(), "Selected Medium", Toast.LENGTH_SHORT).show();
                break;
            case R.id.hard:
                setDifficulty("hard");
                Toast.makeText(getApplicationContext(), "Selected Hard", Toast.LENGTH_SHORT).show();
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
    }
}
