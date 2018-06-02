package com.ashleyjoachim.triviaapp.questions.presenter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.widget.Button;

import com.yarolegovich.discretescrollview.DiscreteScrollView;

public interface QuestionPresenterInterface {

    void getQuestions(int id, String difficulty, int count, String token);

    void onNextButtonClicked(Context context, ConstraintLayout layout, final DiscreteScrollView scrollView, Button next);
}
