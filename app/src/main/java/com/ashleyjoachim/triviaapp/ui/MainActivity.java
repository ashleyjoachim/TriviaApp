package com.ashleyjoachim.triviaapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.adapter.TriviaCategoryAdapter;
import com.ashleyjoachim.triviaapp.model.TriviaCategory;
import com.ashleyjoachim.triviaapp.model.TriviaTokenRequest;
import com.ashleyjoachim.triviaapp.model.TriviaWrapperClass;
import com.ashleyjoachim.triviaapp.network.TriviaAPICall;
import com.ashleyjoachim.triviaapp.network.TriviaServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final TriviaAPICall triviaAPICallback = TriviaServiceGenerator.createService();
    private static final String TAG = "Token";
    private RecyclerView recyclerView;
    private List<TriviaCategory> triviaCategories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.category_rv);
        TriviaCategoryAdapter triviaCategoryAdapter = new TriviaCategoryAdapter(triviaCategories);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(triviaCategoryAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        getTriviaCategoryDiscover();
        getTokenFromAPI();
    }

    public void getTriviaCategoryDiscover() {
        Call<TriviaWrapperClass> call = triviaAPICallback.getCategoryDiscover();
        call.enqueue(new Callback<TriviaWrapperClass>() {
            @Override
            public void onResponse(Call<TriviaWrapperClass> call, Response<TriviaWrapperClass> response) {
                List<TriviaCategory> responseList = response.body().getTrivia_categories();
                recyclerView.setAdapter(new TriviaCategoryAdapter(responseList));
                Log.d("MainActivity", "onResponse: " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<TriviaWrapperClass> call, Throwable t) {
                Log.d("MainActivity", "onFailure: " + t.getStackTrace());
            }
        });
    }

    public void getTokenFromAPI() {
        Call<TriviaTokenRequest> call = triviaAPICallback.getTokenDiscover("request");

        call.enqueue(new Callback<TriviaTokenRequest>() {
            @Override
            public void onResponse(Call<TriviaTokenRequest> call, Response<TriviaTokenRequest> response) {
                String token = response.body().getToken();
                Bundle bundle = new Bundle();
                bundle.putString("token", token);
                Log.e(TAG, "onResponse: " + response.body().getResponse_message());
            }

            @Override
            public void onFailure(Call<TriviaTokenRequest> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getStackTrace());
            }
        });
    }
}
