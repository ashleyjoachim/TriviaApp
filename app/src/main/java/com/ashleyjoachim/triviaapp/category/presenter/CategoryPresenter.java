package com.ashleyjoachim.triviaapp.category.presenter;

import android.util.Log;


import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;
import com.ashleyjoachim.triviaapp.network.TriviaAPICall;
import com.ashleyjoachim.triviaapp.network.TriviaServiceGenerator;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CategoryPresenter implements CategoryPresenterInterface {

    private CategoryViewInterface cvi;
    private String TAG = "CategoryPresenter";

    public CategoryPresenter(CategoryViewInterface cvi) {
        this.cvi = cvi;
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
                cvi.displayCategories(triviaWrapperClass);
                Log.d(TAG, "OnNext: " + triviaWrapperClass.getResponse_code());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                cvi.displayError("Error fetching Categories Data");
                Log.d(TAG, "onError: " + e.getStackTrace());
            }

            @Override
            public void onComplete() {
                cvi.hideProgressBar();
                Log.d(TAG, "Completed");
            }
        };
    }

    @Override
    public void getCategories() {
        getObservable().safeSubscribe(getObserver());
    }
}
