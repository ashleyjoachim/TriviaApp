package com.ashleyjoachim.triviaapp.util;

import com.ashleyjoachim.triviaapp.data.model.TriviaWrapperClass;

public interface TriviaViewInterface {

    void displayData(TriviaWrapperClass triviaWrapperClass);

    void displayError(String errorMessage);

    void showProgressBar();

    void hideProgressBar();

    void stop();

    void destroy();
}
