package com.kh.user.model.vo;

public class Question {
    private int questionNo;
    private String questionContent;

    public Question() {}

    public Question(int questionNo, String questionContent) {
        this.questionNo = questionNo;
        this.questionContent = questionContent;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}