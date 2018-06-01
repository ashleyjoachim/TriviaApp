package com.ashleyjoachim.triviaapp.components.questions.presenter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;
import com.ashleyjoachim.triviaapp.network.TriviaAPICall;
import com.ashleyjoachim.triviaapp.network.TriviaServiceGenerator;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class QuestionPresenter implements QuestionPresenterInterface {

    private String TAG = "QuestionPresenter";
    private QuestionViewInterface qvi;

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
                Log.d(TAG, "OnNext: " + triviaWrapperClass.getResponse_code());
                qvi.displayQuestions(triviaWrapperClass);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: " + e.getStackTrace());
                qvi.displayError("Error fetching Results");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Completed");
                qvi.hideProgressBar();
            }
        };
    }

    @Override
    public void getQuestions(int id, String difficulty, int count, String token) {

        getObservable(id, difficulty, count, token).safeSubscribe(getObserver());

    }

    @Override
    public void onNextButtonClicked(Context context, ConstraintLayout layout, final DiscreteScrollView scrollView) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View customView = layoutInflater.inflate(R.layout.dialog_answer, null);

        Button button = customView.findViewById(R.id.closePopupBtn);

        final PopupWindow popupWindow = new PopupWindow(customView, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int next = scrollView.getCurrentItem() + 1;
                scrollView.smoothScrollToPosition(next);
                popupWindow.dismiss();
            }
        });

    }

}
