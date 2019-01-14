package com.ashleyjoachim.triviaapp.util;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ashleyjoachim.triviaapp.data.model.TriviaResults;

public interface TriviaHelperInterface {
    void setQuestions(TriviaResults triviaResults, TextView textView);

    void setChoices(
            TriviaResults triviaResults,
            View itemView,
            RadioGroup questionGroup,
            RadioButton choiceOne,
            RadioButton choiceTwo,
            RadioButton choiceThree,
            RadioButton choiceFour
    );

    boolean isCorrect();
}
