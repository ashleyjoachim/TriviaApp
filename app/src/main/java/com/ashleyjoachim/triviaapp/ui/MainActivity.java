package com.ashleyjoachim.triviaapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.category.presenter.CategoryPresenter;

public class MainActivity extends AppCompatActivity {
    private CategoryPresenter categoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryPresenter = new CategoryPresenter(findViewById(R.id.main_activity),this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        categoryPresenter.getCategories();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        categoryPresenter.destroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        categoryPresenter.stop();
    }
}
