package com.ashleyjoachim.triviaapp.basemodel;


import com.ashleyjoachim.triviaapp.category.model.TriviaCategory;
import com.ashleyjoachim.triviaapp.questions.model.TriviaResults;

import java.util.List;

public class TriviaWrapperClass {
    private int response_code;
    private List<TriviaResults> results;
    private List<TriviaCategory> trivia_categories;

    public int getResponse_code() {
        return response_code;
    }

    public List<TriviaResults> getResults() {
        return results;
    }

    public List<TriviaCategory> getTrivia_categories() {
        return trivia_categories;
    }
}
