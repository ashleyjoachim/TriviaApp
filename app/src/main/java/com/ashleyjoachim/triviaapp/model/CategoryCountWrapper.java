package com.ashleyjoachim.triviaapp.model;


public class CategoryCountWrapper {
    private int category_id;
    private CategoryQuestionCount category_question_count;

    public int getCategory_id() {
        return category_id;
    }

    public CategoryQuestionCount getCategory_question_count() {
        return category_question_count;
    }

    public static class CategoryQuestionCount {
        private int total_question_count;
        private int total_easy_question_count;
        private int total_medium_question_count;
        private int total_hard_question_count;

        public int getTotal_question_count() {
            return total_question_count;
        }

        public int getTotal_easy_question_count() {
            return total_easy_question_count;
        }

        public int getTotal_medium_question_count() {
            return total_medium_question_count;
        }

        public int getTotal_hard_question_count() {
            return total_hard_question_count;
        }
    }
}
