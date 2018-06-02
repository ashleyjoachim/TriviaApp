package com.ashleyjoachim.triviaapp.questions.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.questions.model.TriviaResults;


public class TriviaQuestionViewHolder extends RecyclerView.ViewHolder {

    private TextView question;
    private RadioGroup questionGroup;
    private RadioButton choiceOne, choiceTwo, choiceThree, choiceFour;

    public TriviaQuestionViewHolder(View itemView) {
        super(itemView);

        question = itemView.findViewById(R.id.question);
        questionGroup = itemView.findViewById(R.id.choices_group);
        choiceOne = itemView.findViewById(R.id.choice_one);
        choiceTwo = itemView.findViewById(R.id.choice_two);
        choiceThree = itemView.findViewById(R.id.choice_three);
        choiceFour = itemView.findViewById(R.id.choice_four);

    }

    public void onBind(final TriviaResults triviaResults) {
        resetRadioButtons();

        TriviaHelper triviaHelper = new TriviaHelper();
        triviaHelper.setQuestions(triviaResults, question);
        triviaHelper.setChoices(triviaResults, itemView, questionGroup, choiceOne, choiceTwo, choiceThree, choiceFour);

    }

    private void resetRadioButtons() {
        choiceOne.setChecked(false);

        choiceTwo.setChecked(false);

        choiceThree.setChecked(false);

        choiceThree.setVisibility(View.VISIBLE);

        choiceFour.setChecked(false);

        choiceFour.setVisibility(View.VISIBLE);
    }

}
