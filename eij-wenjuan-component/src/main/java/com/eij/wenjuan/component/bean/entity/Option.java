package com.eij.wenjuan.component.bean.entity;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-14
 */
public class Option {
    private int optionId;

    private int questionId;

    private String optionName;

    private String optionIndex;

    private String imgUrl;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionIndex() {
        return optionIndex;
    }

    public void setOptionIndex(String optionIndex) {
        this.optionIndex = optionIndex;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
