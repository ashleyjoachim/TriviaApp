package com.ashleyjoachim.triviaapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.adapter.TriviaQuestionAdapter;
import com.ashleyjoachim.triviaapp.model.TriviaResults;
import com.ashleyjoachim.triviaapp.model.TriviaWrapperClass;
import com.ashleyjoachim.triviaapp.network.TriviaAPICall;
import com.ashleyjoachim.triviaapp.network.TriviaServiceGenerator;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.DiscreteScrollItemTransformer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionActivity extends AppCompatActivity {
    private static final TriviaAPICall triviaAPICallback = TriviaServiceGenerator.createService();
    private static final String TAG = "MainActivity";
    private static int id;
    private static String difficulty;
    private static int count;
    private static String getToken;
    private DiscreteScrollView recyclerView;
    private List<TriviaResults> questionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intent = getIntent();
        id = intent.getExtras().getInt("id");
        difficulty = intent.getExtras().getString("difficulty");
        count = intent.getIntExtra("count", 10);
        getToken = intent.getExtras().getString("token");

        TriviaQuestionAdapter triviaQuestionAdapter = new TriviaQuestionAdapter(questionList);

        recyclerView = findViewById(R.id.question_rv);
        recyclerView.setAdapter(triviaQuestionAdapter);

        recyclerView.setItemTransformer(new DiscreteScrollItemTransformer() {
            @Override
            public void transformItem(View item, float position) {
                CubeOutTransformer cubeOutTransformer = new CubeOutTransformer();
                cubeOutTransformer.transformPage(item, position);
                cubeOutTransformer.isPagingEnabled();
            }
        });

        getTriviaData();
    }

    public void getTriviaData() {
        int limit = 12;
        Call<TriviaWrapperClass> call;

        if (count <= limit) {
            call = triviaAPICallback.getTriviaDiscover(count, id, difficulty, getToken);
        } else {
            call = triviaAPICallback.getTriviaDiscover(limit, id, difficulty, getToken);
        }

        call.enqueue(new Callback<TriviaWrapperClass>() {
            @Override
            public void onResponse(Call<TriviaWrapperClass> call, Response<TriviaWrapperClass> response) {
                List<TriviaResults> responseList = response.body().getResults();
                recyclerView.setAdapter(new TriviaQuestionAdapter(responseList));
                Log.d(TAG, "onResponse: " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<TriviaWrapperClass> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getStackTrace());
            }
        });
    }
}
