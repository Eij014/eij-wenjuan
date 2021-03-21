package com.eij.wenjuan.component.bean.entity;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-14
 */
public class Question {
    private  int questionId;

    private int wenjuanId;

    private String type;

    //是否为必填题 0-否, 1-是
    private int must;

    private String title;

    private String imgUrls;

    private String questionIndex;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getWenjuanId() {
        return wenjuanId;
    }

    public void setWenjuanId(int wenjuanId) {
        this.wenjuanId = wenjuanId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMust() {
        return must;
    }

    public void setMust(int must) {
        this.must = must;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public String getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(String questionIndex) {
        this.questionIndex = questionIndex;
    }
}
