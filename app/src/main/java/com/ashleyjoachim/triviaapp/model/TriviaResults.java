package com.ashleyjoachim.triviaapp.model;


public class TriviaResults {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private String[] incorrect_answers;
    private int checkedId = -1;

    public int getCheckedId() {
        return checkedId;
    }

    public void setCheckedId(int checkedId) {
        this.checkedId = checkedId;
    }

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

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String[] getIncorrect_answers() {
        return incorrect_answers;
    }
}
