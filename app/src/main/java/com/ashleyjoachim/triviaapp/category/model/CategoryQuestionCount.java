package com.ashleyjoachim.triviaapp.category.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryQuestionCount {
    @Expose
    @SerializedName("total_question_count")
    private int totalQuestionCount;

    @Expose
    @SerializedName("total_easy_question_count")
    private int totalEasyQuestionCount;

    @Expose
    @SerializedName("total_medium_question_count")
    private int totalMediumQuestionCount;

    @Expose
    @SerializedName("total_hard_question_count")
    private int totalHardQuestionCount;

    public int getTotalQuestionCount() {
        return totalQuestionCount;
    }

    public int getTotalEasyQuestionCount() {
        return totalEasyQuestionCount;
    }

    public int getTotalMediumQuestionCount() {
        return totalMediumQuestionCount;
    }

    public int getTotalHardQuestionCount() {
        return totalHardQuestionCount;
    }
}
