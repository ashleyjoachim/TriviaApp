package com.ashleyjoachim.triviaapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.questions.presenter.QuestionPresenter;

public class QuestionActivity extends AppCompatActivity {
    private QuestionPresenter questionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionPresenter = new QuestionPresenter(findViewById(R.id.question_layout),this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
        int count = intent.getIntExtra("count", 10);
        String getToken = intent.getExtras().getString("token");
        String difficulty = intent.getExtras().getString("difficulty");
        questionPresenter.getQuestions(id, difficulty, count, getToken);
    }

    @Override
    protected void onStop() {
        super.onStop();
        questionPresenter.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        questionPresenter.destroy();
    }
}
