package com.ashleyjoachim.triviaapp.util;

import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ashleyjoachim.triviaapp.data.model.TriviaResults;

import java.util.Arrays;

public class TriviaHelper implements TriviaHelperInterface {
    private static final String TAG = "TriviaHelper";
    private static boolean mIsCorrect;

    @Override
    public boolean isCorrect() {
        return mIsCorrect;
    }

    private void setCorrect(boolean correct) {
        mIsCorrect = correct;
    }

    @Override
    public void setQuestions(TriviaResults triviaResults, TextView textView) {
        String questionText = triviaResults.getQuestion();

        String a = questionText.replace("&quot;", "\"");
        String b = a.replace("&#039;", "\'");
        String c = b.replace("&shy;", "-");
        String d = c.replace("&eacute;", "é");

        textView.setText(d);
    }

    @Override
    public void setChoices(final TriviaResults triviaResults, final View itemView,
                           RadioGroup questionGroup, RadioButton choiceOne, RadioButton choiceTwo,
                           RadioButton choiceThree, RadioButton choiceFour) {

        final String[] incorrect = triviaResults.getIncorrectAnswers();
        final String correct = triviaResults.getCorrectAnswer();

        setChoiceHelper(incorrect, correct, choiceOne, choiceTwo, choiceThree, choiceFour);

        questionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton checkedRadioButton = radioGroup.findViewById(i);
                boolean isChecked = checkedRadioButton.isChecked();
                String checkedButton = checkedRadioButton.getText().toString();

                if (isChecked) {
                    if (checkedButton.equals(correct)) {
                        setCorrect(true);
                        Log.d(TAG, "onCheckedChanged: correct");
                    } else {
                        setCorrect(false);
                        Log.d(TAG, "onCheckedChanged: incorrect");
                    }
                }
            }
        });
    }

    private void setChoiceHelper(String[] incorrect, String correct, RadioButton one,
                                 RadioButton two, RadioButton three, RadioButton four) {
        if (incorrect.length == 1) {
            if (correct.equals("True")) {
                one.setText(correct);
                two.setText(incorrect[0]);
            } else {
                one.setText(incorrect[0]);
                two.setText(correct);
            }
            three.setVisibility(View.GONE);
            four.setVisibility(View.GONE);

        } else {
            String[] choicesArray = new String[incorrect.length + 1];
            choicesArray[0] = correct;
            choicesArray[1] = incorrect[0];
            choicesArray[2] = incorrect[1];
            choicesArray[3] = incorrect[2];

            Arrays.sort(choicesArray);

            one.setText(choicesArray[0]);
            two.setText(choicesArray[1]);
            three.setText(choicesArray[2]);
            four.setText(choicesArray[3]);
        }
    }
}
