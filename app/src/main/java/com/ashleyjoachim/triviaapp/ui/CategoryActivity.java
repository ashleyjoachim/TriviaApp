package com.ashleyjoachim.triviaapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.ui.presenter.CategoryPresenter;

public class CategoryActivity extends AppCompatActivity {
    private CategoryPresenter mCategoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mCategoryPresenter = new CategoryPresenter(findViewById(R.id.category_view), this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCategoryPresenter.getCategories();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCategoryPresenter.destroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCategoryPresenter.stop();
    }
}
