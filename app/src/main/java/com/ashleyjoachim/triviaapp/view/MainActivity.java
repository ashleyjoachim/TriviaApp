package com.ashleyjoachim.triviaapp.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.adapter.TriviaCategoryAdapter;
import com.ashleyjoachim.triviaapp.model.TriviaWrapperClass;
import com.ashleyjoachim.triviaapp.presenter.CategoryPresenter;

public class MainActivity extends AppCompatActivity implements CategoryViewInterface {
    private String TAG = "MainActivity";
    private CategoryPresenter categoryPresenter;
    private RecyclerView categoryRv;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryRv = findViewById(R.id.category_rv);
        progressBar = findViewById(R.id.progressBar);

        categoryPresenter = new CategoryPresenter(this);
        categoryRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        categoryPresenter.getCategories();

    }
    
    @Override
    public void showToast(String s) {
        Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayCategories(TriviaWrapperClass triviaWrapperClass) {
        if (triviaWrapperClass != null) {
            categoryRv.setAdapter(new TriviaCategoryAdapter(triviaWrapperClass.getTrivia_categories()));
            Log.d(TAG, "OnSuccess: " + triviaWrapperClass.getResponse_code());

        } else {
            Log.d(TAG, "Movies response null");
        }
    }

    @Override
    public void displayError(String s) {
        showToast(s);
    }
}
