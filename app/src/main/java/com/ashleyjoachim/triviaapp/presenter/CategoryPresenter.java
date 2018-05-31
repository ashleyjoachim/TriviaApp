package com.ashleyjoachim.triviaapp.presenter;

import android.util.Log;


import com.ashleyjoachim.triviaapp.model.TriviaWrapperClass;
import com.ashleyjoachim.triviaapp.network.TriviaAPICall;
import com.ashleyjoachim.triviaapp.network.TriviaServiceGenerator;
import com.ashleyjoachim.triviaapp.view.CategoryViewInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter implements CategoryPresenterInterface {
    CategoryViewInterface cvi;
    private String TAG = "CategoryPresenter";

    public CategoryPresenter(CategoryViewInterface cvi) {
        this.cvi = cvi;
    }

    @Override
    public void getCategories() {

        getObservable().subscribeWith(getObserver());
    }

    private Observable<TriviaWrapperClass> getObservable() {
        return TriviaServiceGenerator.getRetrofit()
                .create(TriviaAPICall.class)
                .getCategoryDiscover()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private DisposableObserver<TriviaWrapperClass> getObserver() {
        return new DisposableObserver<TriviaWrapperClass>() {
            @Override
            public void onNext(@NonNull TriviaWrapperClass triviaWrapperClass) {
                Log.d(TAG, "OnNext: " + triviaWrapperClass.getResponse_code());
                cvi.displayCategories(triviaWrapperClass);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getStackTrace());
                e.printStackTrace();
                cvi.displayError("Error fetching Categories Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
                cvi.hideProgressBar();
            }
        };
    }
}
