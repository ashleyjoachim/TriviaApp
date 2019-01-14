package com.ashleyjoachim.triviaapp.ui.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.data.model.TriviaWrapperClass;
import com.ashleyjoachim.triviaapp.controller.TriviaCategoryAdapter;
import com.ashleyjoachim.triviaapp.data.rest.TriviaApi;
import com.ashleyjoachim.triviaapp.data.rest.TriviaServiceProvider;
import com.ashleyjoachim.triviaapp.util.TriviaViewInterface;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter implements TriviaViewInterface {
    private String TAG = "CategoryPresenter";
    private CompositeDisposable mDisposable;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private Context mContext;

    public CategoryPresenter(View view, Context context) {
        mContext = context;
        mRecyclerView = view.findViewById(R.id.category_rv);
        mProgressBar = view.findViewById(R.id.category_progress_bar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mDisposable = new CompositeDisposable();
    }

    public void getCategories() {
        TriviaApi triviaApi = TriviaServiceProvider.provideTriviaApi();
        Single<TriviaWrapperClass> categories = triviaApi.getCategoryDiscover();
        categories.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TriviaWrapperClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(TriviaWrapperClass triviaWrapperClass) {
                        displayData(triviaWrapperClass);
                        hideProgressBar();
                        Log.d(TAG, String.valueOf(triviaWrapperClass.getResponseCode()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        displayError("Error fetching Categories Data");
                        hideProgressBar();
                        Log.d(TAG, "Category response null");

                    }
                });
    }

    @Override
    public void displayData(TriviaWrapperClass triviaWrapperClass) {
        if (triviaWrapperClass != null) {
            mRecyclerView.setAdapter(new TriviaCategoryAdapter(triviaWrapperClass.getTriviaCategories()));
            Log.d(TAG, String.valueOf(triviaWrapperClass.getResponseCode()));
        } else {
            Log.d(TAG, "Category response null");
        }
    }

    @Override
    public void displayError(String errorMessage) {
        Toast.makeText(mContext, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void stop() {
        mDisposable.clear();
    }

    @Override
    public void destroy() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
