package com.ashleyjoachim.triviaapp.questions.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;
import com.ashleyjoachim.triviaapp.questions.recyclerview.TriviaHelper;
import com.ashleyjoachim.triviaapp.network.TriviaAPICall;
import com.ashleyjoachim.triviaapp.network.TriviaServiceGenerator;
import com.ashleyjoachim.triviaapp.ui.MainActivity;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class QuestionPresenter implements QuestionPresenterInterface {

    private String TAG = "QuestionPresenter";
    private QuestionViewInterface qvi;
    private static int score = 0;

    public QuestionPresenter(QuestionViewInterface qvi) {
        this.qvi = qvi;
    }

    private Observable<TriviaWrapperClass> getObservable(int id, String difficulty, int count, String token) {

        int limit = 12;

        if (count <= limit) {
            return TriviaServiceGenerator.getRetrofit()
                    .create(TriviaAPICall.class)
                    .getTriviaDiscover(count, id, difficulty, token).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        } else {
            return TriviaServiceGenerator.getRetrofit()
                    .create(TriviaAPICall.class)
                    .getTriviaDiscover(limit, id, difficulty, token).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }

    }

    private DisposableObserver<TriviaWrapperClass> getObserver() {

        return new DisposableObserver<TriviaWrapperClass>() {
            @Override
            public void onNext(@NonNull TriviaWrapperClass triviaWrapperClass) {
                qvi.displayQuestions(triviaWrapperClass);
                Log.d(TAG, "OnNext: " + triviaWrapperClass.getResponse_code());
            }

            @Override
            public void onError(@NonNull Throwable e) {
                qvi.displayError("Error fetching Results");
                Log.d(TAG, "onError: " + e.getStackTrace());
            }

            @Override
            public void onComplete() {
                qvi.hideProgressBar();
                Log.d(TAG, "Completed");
            }
        };

    }

    @Override
    public void getQuestions(int id, String difficulty, int count, String token) {
        getObservable(id, difficulty, count, token).safeSubscribe(getObserver());
    }

    @Override
    public void onNextButtonClicked(final Context context, ConstraintLayout layout, final DiscreteScrollView scrollView, final Button next) {
        StringBuilder sb = new StringBuilder("Current Score: ");
        TriviaHelper triviaHelper = new TriviaHelper();

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View customView = layoutInflater.inflate(R.layout.dialog_answer, null);

        final Button button = customView.findViewById(R.id.closePopupBtn);
        final TextView verifyTextView = customView.findViewById(R.id.result_textview);
        final TextView scoreTextView = customView.findViewById(R.id.score_textview);

        if (triviaHelper.isCorrect()) {

            verifyTextView.setText(R.string.answer_correct);
            score++;
            scoreTextView.setText(sb.append(score * 100));

        } else {

            scoreTextView.setText(sb.append(score * 100));
            verifyTextView.setText(R.string.answer_wrong);

        }

        final PopupWindow popupWindow = new PopupWindow(customView, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
        button.setText(R.string.next_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int next = scrollView.getCurrentItem() + 1;

                scrollView.smoothScrollToPosition(next);
                Toast.makeText(context, "Current Position: " + scrollView.getCurrentItem(), Toast.LENGTH_SHORT).show();

                popupWindow.dismiss();
            }
        });

    }

}