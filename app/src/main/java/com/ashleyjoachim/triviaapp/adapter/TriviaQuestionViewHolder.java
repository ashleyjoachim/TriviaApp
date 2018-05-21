package com.ashleyjoachim.triviaapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.model.TriviaResults;

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

    public void onBind(TriviaResults triviaResults) {
        String questionText = triviaResults.getQuestion();

        String a = questionText.replace("&quot;", "\"");
        String b = a.replace("&#039;", "\'");
        String c = b.replace("&shy;", "-");

        question.setText(c);

        String[] incorrect = triviaResults.getIncorrect_answers();
        String correct = triviaResults.getCorrect_answer();

        if (incorrect.length == 1) {
            choiceOne.setText(correct);
            choiceTwo.setText(incorrect[0]);
            choiceThree.setVisibility(View.INVISIBLE);
            choiceFour.setVisibility(View.INVISIBLE);

        } else {
            choiceOne.setText(correct);
            choiceTwo.setText(incorrect[0]);
            choiceThree.setText(incorrect[1]);
            choiceFour.setText(incorrect[2]);
        }
    }
}
