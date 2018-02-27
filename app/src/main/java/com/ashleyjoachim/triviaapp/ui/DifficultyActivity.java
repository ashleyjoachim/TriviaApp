package com.ashleyjoachim.triviaapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.model.CategoryCountWrapper;
import com.ashleyjoachim.triviaapp.network.TriviaAPICall;
import com.ashleyjoachim.triviaapp.network.TriviaServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DifficultyActivity extends AppCompatActivity implements View.OnClickListener {
    private static final TriviaAPICall triviaAPICallback = TriviaServiceGenerator.createService();
    public static final String EASY = "easy", MEDIUM = "medium", HARD = "hard";
    private static int id;
    private static int questionCount;
    Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        String name = getIntent().getExtras().getString("name");
        TextView textView = findViewById(R.id.textview_placeholder); //remove later
        textView.setText("Category \n" + name);// remove later
        id = getIntent().getExtras().getInt("id");

        Button easy = findViewById(R.id.button_easy);
        easy.setOnClickListener(this);

        Button medium = findViewById(R.id.button_medium);
        medium.setOnClickListener(this);

        Button hard = findViewById(R.id.button_hard);
        hard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_easy:
                setDifficulty(EASY);
                break;
            case R.id.button_medium:
                setDifficulty(MEDIUM);
                break;
            case R.id.button_hard:
                setDifficulty(HARD);
                break;
        }
    }

    public void setDifficulty(final String mode) {

        Call<CategoryCountWrapper> call = triviaAPICallback.getQuestionCount(id);
        call.enqueue(new Callback<CategoryCountWrapper>() {
            @Override
            public void onResponse(Call<CategoryCountWrapper> call, Response<CategoryCountWrapper> response) {
                switch (mode) {
                    case EASY:
                        questionCount = response.body().getCategory_question_count().getTotal_easy_question_count();
                        getCount(EASY);
                        Log.d("COUNT IN DIFFACT", "onResponse: " + questionCount);
                        break;
                    case MEDIUM:
                        questionCount = response.body().getCategory_question_count().getTotal_medium_question_count();
                        getCount(MEDIUM);
                        Log.d("COUNT", "onResponse: " + questionCount);
                        break;
                    case HARD:
                        questionCount = response.body().getCategory_question_count().getTotal_hard_question_count();
                        getCount(HARD);
                        Log.d("COUNT", "onResponse: " + questionCount);
                        break;
                }
                Log.d("DifficultyActivity", "onResponse: " + response.body().getCategory_question_count().getTotal_question_count());
            }

            @Override
            public void onFailure(Call<CategoryCountWrapper> call, Throwable t) {
                Log.d("DifficultyActivity", "onFailure: " + t.getStackTrace().toString());
            }
        });
    }

    public void getCount(String mode) {
        bundle.putString("difficulty", mode);
        bundle.putInt("id", id);
        bundle.putInt("count", questionCount);
        Intent questionActivity = new Intent(getApplicationContext(), QuestionActivity.class);
        questionActivity.putExtras(bundle);
        startActivity(questionActivity);
        Toast.makeText(getApplicationContext(), "Selected " + mode, Toast.LENGTH_SHORT).show();
    }
}
