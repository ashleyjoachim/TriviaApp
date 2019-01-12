package com.ashleyjoachim.triviaapp.category.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryCountWrapper {
    @Expose
    @SerializedName("category_d")
    private int categoryId;

    @Expose
    @SerializedName("category_question_count")
    private CategoryQuestionCount categoryQuestionCount;

    public int getCategoryId() {
        return categoryId;
    }

    public CategoryQuestionCount getCategoryQuestionCount() {
        return categoryQuestionCount;
    }
}
