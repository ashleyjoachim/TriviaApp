package com.ashleyjoachim.triviaapp.questions.presenter;

import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;

public interface QuestionViewInterface {
    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void displayQuestions(TriviaWrapperClass triviaWrapperClass);
    void displayError(String s);
}
