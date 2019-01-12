package com.ashleyjoachim.triviaapp.category.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TriviaCategory {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("name")
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
