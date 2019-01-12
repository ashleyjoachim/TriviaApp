package com.ashleyjoachim.triviaapp.basemodel;

import com.ashleyjoachim.triviaapp.category.model.TriviaCategory;
import com.ashleyjoachim.triviaapp.questions.model.TriviaResults;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TriviaWrapperClass {
    @Expose
    @SerializedName("response_code")
    private int responseCode;

    @Expose
    @SerializedName("results")
    private List<TriviaResults> results;

    @Expose
    @SerializedName("trivia_categories")
    private List<TriviaCategory> triviaCategories;

    public int getResponseCode() {
        return responseCode;
    }

    public List<TriviaResults> getResults() {
        return results;
    }

    public List<TriviaCategory> getTriviaCategories() {
        return triviaCategories;
    }
}
