package com.ashleyjoachim.triviaapp.questions.presenter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;
import com.ashleyjoachim.triviaapp.ui.TriviaViewInterface;
import com.ashleyjoachim.triviaapp.network.TriviaApi;
import com.ashleyjoachim.triviaapp.network.TriviaRetrofitService;
import com.ashleyjoachim.triviaapp.questions.recyclerview.TriviaHelper;
import com.ashleyjoachim.triviaapp.questions.recyclerview.TriviaQuestionAdapter;
import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.Arrays;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class QuestionPresenter implements TriviaViewInterface, View.OnClickListener {

    private final String TAG = "QuestionPresenter";
    private static int score = 0;
    private CompositeDisposable mDisposable;
    private DiscreteScrollView mScrollView;
    private ConstraintLayout mConstraintLayout;
    private ProgressBar mProgressBar;
    private Context mContext;

    public QuestionPresenter(View view,
                             Context context) {
        mContext = context;
        mScrollView = view.findViewById(R.id.category_rv);
        mProgressBar = view.findViewById(R.id.category_progress_bar);
        mScrollView = view.findViewById(R.id.question_rv);
        mProgressBar = view.findViewById(R.id.question_progress_bar);
        mConstraintLayout = view.findViewById(R.id.question_layout);
        Button nextBtn = view.findViewById(R.id.next_page);
        mDisposable = new CompositeDisposable();
        nextBtn.setOnClickListener(this);
    }

    public void getQuestions(int id,
                             String difficulty,
                             int count,
                             String token) {
        TriviaApi triviaApi = TriviaRetrofitService.getTriviaApi();
        Single<TriviaWrapperClass> trivia;
        int limit = 12;

        if (count <= limit) {
            trivia = triviaApi.getTriviaDiscover(count, id, difficulty, token);
        } else {
            trivia = triviaApi.getTriviaDiscover(limit, id, difficulty, token);
        }

        trivia.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<TriviaWrapperClass>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(TriviaWrapperClass triviaWrapperClass) {
                        displayData(triviaWrapperClass);
                        hideProgressBar();
                        Log.d(TAG, "OnNext: " + triviaWrapperClass.getResponseCode());
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideProgressBar();
                        displayError("Error fetching Results");
                        Log.d(TAG, "onError: " + Arrays.toString(e.getStackTrace()));
                    }
                });
    }

    private void onNextButtonClicked(final Context context,
                                     ConstraintLayout layout,
                                     final DiscreteScrollView scrollView) {
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
        button.setOnClickListener(v -> {
            int next1 = scrollView.getCurrentItem() + 1;
            scrollView.smoothScrollToPosition(next1);
            Toast.makeText(context, "Current Position: " + scrollView.getCurrentItem(), Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
    }

    @Override
    public void displayData(TriviaWrapperClass triviaWrapperClass) {
        if (triviaWrapperClass != null) {
            mScrollView.setAdapter(new TriviaQuestionAdapter(triviaWrapperClass.getResults()));
            mScrollView.setItemTransformer((item, position) -> {
                CubeOutTransformer cubeOutTransformer = new CubeOutTransformer();
                cubeOutTransformer.transformPage(item, position);
            });

            Log.d(TAG, "OnSuccess: " + triviaWrapperClass.getResponseCode());

        } else {
            Log.d(TAG, "Results response null");
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayError(String errorMessage) {
        showToast(errorMessage);
    }

    @Override
    public void stop() {
        mDisposable.clear();
    }

    @Override
    public void destroy() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onClick(View view) {
        onNextButtonClicked(view.getContext(), mConstraintLayout, mScrollView);
    }
}
