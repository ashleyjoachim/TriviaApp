package com.ashleyjoachim.triviaapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.model.TriviaWrapperClass;
import com.ashleyjoachim.triviaapp.network.TriviaAPICall;
import com.ashleyjoachim.triviaapp.network.TriviaServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {
    private static final TriviaAPICall triviaAPICallback = TriviaServiceGenerator.createService();
    private int id;
    private String difficulty;
    private TextView setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        id = getIntent().getExtras().getInt("id");
        difficulty = getIntent().getExtras().getString("difficulty");
        setting = findViewById(R.id.difficulty_setting);
        setting.setText(difficulty);

        getTriviaData();
    }

    public void getTriviaData() {
        Call<TriviaWrapperClass> call =
                triviaAPICallback.getTriviaDiscover(id, difficulty);
        call.enqueue(new Callback<TriviaWrapperClass>() {
            @Override
            public void onResponse(Call<TriviaWrapperClass> call, Response<TriviaWrapperClass> response) {
                Log.d("MainActivity", "onResponse: " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<TriviaWrapperClass> call, Throwable t) {
                Log.d("MainActivity", "onFailure: " + t.getStackTrace());
            }
        });
    }
}
