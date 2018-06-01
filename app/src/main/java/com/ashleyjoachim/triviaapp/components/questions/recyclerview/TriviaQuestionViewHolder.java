package com.ashleyjoachim.triviaapp.components.questions.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.components.questions.model.TriviaResults;


public class TriviaQuestionViewHolder extends RecyclerView.ViewHolder {
    private TextView question;
    private RadioGroup questionGroup;
    private RadioButton choiceOne, choiceTwo, choiceThree, choiceFour;
    private Button resultBtn;
    private int score;

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
        setQuestions(triviaResults);
        setChoices(triviaResults);
    }

    private void setQuestions(TriviaResults triviaResults) {
        String questionText = triviaResults.getQuestion();

        String a = questionText.replace("&quot;", "\"");
        String b = a.replace("&#039;", "\'");
        String c = b.replace("&shy;", "-");
        String d = c.replace("&eacute;", "é");

        question.setText(d);
    }

    public void setChoices(final TriviaResults triviaResults) {
        final String[] incorrect = triviaResults.getIncorrect_answers();
        final String correct = triviaResults.getCorrect_answer();

        if (incorrect.length == 1) {

            if (correct.equals("True")) {
                choiceOne.setText(correct);
                choiceTwo.setText(incorrect[0]);
            } else {
                choiceOne.setText(incorrect[0]);
                choiceTwo.setText(correct);
            }

            choiceThree.setVisibility(View.GONE);
            choiceFour.setVisibility(View.GONE);
        } else {

            choiceOne.setText(correct);
            choiceTwo.setText(incorrect[0]);
            choiceThree.setText(incorrect[1]);
            choiceFour.setText(incorrect[2]);
        }


        questionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = group.findViewById(checkedId);

                boolean isChecked = checkedRadioButton.isChecked();

                if (isChecked) {

                    Toast.makeText(itemView.getContext(), "Checked: " + checkedRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();

                    if (correct.equals(checkedRadioButton.getText().toString())) {
                        Toast.makeText(itemView.getContext(), "correct", Toast.LENGTH_SHORT).show();
                        score++;
                    } else {
                        Toast.makeText(itemView.getContext(), "incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
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
