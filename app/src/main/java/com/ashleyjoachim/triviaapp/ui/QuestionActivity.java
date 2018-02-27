package com.ashleyjoachim.triviaapp.ui;

import android.content.Intent;
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
    private static Bundle bundle = new Bundle();
    private static final String TOKEN = bundle.getString("token");
    private static int id;
    private static String difficulty;
    private static int count;
    private TextView textViewSetting;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);


        id = getIntent().getExtras().getInt("id");
        difficulty = getIntent().getExtras().getString("difficulty");
        intent = getIntent();
        count = intent.getIntExtra("count", 10);

        textViewSetting = findViewById(R.id.textview_difficulty_setting);
        textViewSetting.setText(difficulty);

        Log.e("COUNT IN QUESACT", "THIS IS THE COUNT FROM BUNDLE " + count);

        getTriviaData();
    }

    public void getTriviaData() {
        Call<TriviaWrapperClass> call = triviaAPICallback.getTriviaDiscover(count, id, difficulty, TOKEN);
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
