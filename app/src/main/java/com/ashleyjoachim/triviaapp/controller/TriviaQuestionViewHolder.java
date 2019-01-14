package com.ashleyjoachim.triviaapp.questions.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.questions.model.TriviaResults;

class TriviaQuestionViewHolder extends RecyclerView.ViewHolder {
    private TextView mQuestion;
    private RadioGroup mQuestionGroup;
    private RadioButton mChoiceOne, mChoiceTwo, mChoiceThree, mChoiceFour;

    TriviaQuestionViewHolder(View itemView) {
        super(itemView);
        mQuestion = itemView.findViewById(R.id.question);
        mQuestionGroup = itemView.findViewById(R.id.choices_group);
        mChoiceOne = itemView.findViewById(R.id.choice_one);
        mChoiceTwo = itemView.findViewById(R.id.choice_two);
        mChoiceThree = itemView.findViewById(R.id.choice_three);
        mChoiceFour = itemView.findViewById(R.id.choice_four);
    }

    void onBind(final TriviaResults triviaResults) {
        resetRadioButtons();
        TriviaHelper triviaHelper = new TriviaHelper();
        triviaHelper.setQuestions(triviaResults, mQuestion);
        triviaHelper.setChoices(triviaResults, itemView, mQuestionGroup, mChoiceOne, mChoiceTwo, mChoiceThree, mChoiceFour);
    }

    private void resetRadioButtons() {
        mChoiceOne.setChecked(false);

        mChoiceTwo.setChecked(false);

        mChoiceThree.setChecked(false);

        mChoiceThree.setVisibility(View.VISIBLE);

        mChoiceFour.setChecked(false);

        mChoiceFour.setVisibility(View.VISIBLE);
    }
}
