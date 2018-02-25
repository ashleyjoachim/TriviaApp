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

public class DifficultyActivity extends AppCompatActivity {
    private static final TriviaAPICall triviaAPICallback = TriviaServiceGenerator.createService();
    private TextView textView;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        String name = getIntent().getExtras().getString("name");
        textView = findViewById(R.id.textview_placeholder);
        textView.setText("Category \n" + name);
        id = getIntent().getExtras().getInt("id");

        getTriviaData();
    }

    public void getTriviaData() {
        Call<TriviaWrapperClass> call =
                triviaAPICallback.getTriviaDiscover(15, id, "easy");
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
