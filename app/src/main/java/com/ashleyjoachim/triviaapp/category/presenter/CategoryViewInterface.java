package com.ashleyjoachim.triviaapp.category.presenter;

import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;

public interface CategoryViewInterface {

    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void displayCategories(TriviaWrapperClass triviaWrapperClass);
    void displayError(String s);

}
