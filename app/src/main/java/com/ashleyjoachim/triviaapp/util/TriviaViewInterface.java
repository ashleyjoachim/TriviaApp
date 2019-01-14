package com.ashleyjoachim.triviaapp.ui;

import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;

public interface TriviaViewInterface {

    void displayData(TriviaWrapperClass triviaWrapperClass);

    void displayError(String errorMessage);

    void showProgressBar();

    void hideProgressBar();

    void stop();

    void destroy();
}
