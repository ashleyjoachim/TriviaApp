package com.ashleyjoachim.triviaapp.view;

import com.ashleyjoachim.triviaapp.model.TriviaWrapperClass;

public interface CategoryViewInterface {

    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void displayCategories(TriviaWrapperClass triviaWrapperClass);
    void displayError(String s);

}
