package com.ashleyjoachim.triviaapp.ui;

import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;

public interface TriviaViewInterface {

    void displayData(TriviaWrapperClass triviaWrapperClass);

    void showToast(String s);

    void showProgressBar();

    void hideProgressBar();

    void displayError(String s);

    void stop();

    void destroy();
}
