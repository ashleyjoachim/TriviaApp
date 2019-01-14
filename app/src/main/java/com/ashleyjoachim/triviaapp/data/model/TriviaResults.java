package com.ashleyjoachim.triviaapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TriviaResults {
    @Expose
    @SerializedName("category")
    private String category;

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("difficulty")
    private String difficulty;

    @Expose
    @SerializedName("question")
    private String question;

    @Expose
    @SerializedName("correct_answer")
    private String correctAnswer;

    @Expose
    @SerializedName("incorrect_answers")
    private String[] incorrectAnswers;

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String[] getIncorrectAnswers() {
        return incorrectAnswers;
    }
}
