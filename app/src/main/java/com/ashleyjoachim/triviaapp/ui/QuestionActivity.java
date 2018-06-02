package com.ashleyjoachim.triviaapp.ui;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ashleyjoachim.triviaapp.R;
import com.ashleyjoachim.triviaapp.questions.presenter.QuestionPresenter;
import com.ashleyjoachim.triviaapp.questions.presenter.QuestionViewInterface;
import com.ashleyjoachim.triviaapp.questions.recyclerview.TriviaQuestionAdapter;
import com.ashleyjoachim.triviaapp.basemodel.TriviaWrapperClass;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.DiscreteScrollItemTransformer;

public class QuestionActivity extends AppCompatActivity implements QuestionViewInterface, View.OnClickListener {
    private String TAG = "QuestionActivity";
    private QuestionPresenter questionPresenter;
    private DiscreteScrollView questionRv;
    private ProgressBar progressBar;
    private Button nextBtn;

    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionRv = findViewById(R.id.question_rv);
        progressBar = findViewById(R.id.question_progress_bar);
        constraintLayout = findViewById(R.id.question_layout);
        nextBtn = findViewById(R.id.next_page);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("id");
        int count = intent.getIntExtra("count", 10);
        String getToken = intent.getExtras().getString("token");
        String difficulty = intent.getExtras().getString("difficulty");

        questionPresenter = new QuestionPresenter(this);
        questionPresenter.getQuestions(id, difficulty, count, getToken);

        nextBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        questionPresenter.onNextButtonClicked(QuestionActivity.this, constraintLayout, questionRv, nextBtn);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(QuestionActivity.this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void displayQuestions(TriviaWrapperClass triviaWrapperClass) {
        if (triviaWrapperClass != null) {
            questionRv.setAdapter(new TriviaQuestionAdapter(triviaWrapperClass.getResults()));

            questionRv.setItemTransformer(new DiscreteScrollItemTransformer() {
                @Override
                public void transformItem(View item, float position) {
                    CubeOutTransformer cubeOutTransformer = new CubeOutTransformer();
                    cubeOutTransformer.transformPage(item, position);
                }
            });

            Log.d(TAG, "OnSuccess: " + triviaWrapperClass.getResponse_code());

        } else {
            Log.d(TAG, "Results response null");
        }
    }

    @Override
    public void displayError(String s) {
        showToast(s);
    }

}
