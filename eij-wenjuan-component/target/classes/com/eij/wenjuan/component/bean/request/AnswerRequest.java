package com.eij.wenjuan.component.bean.request;

import java.util.List;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-09
 */
public class AnswerRequest {
    private int questionId;

    private String type;

    //单选
    private int optionId;

    //多选
    private List<Integer> optionIdList;

    private String text;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public List<Integer> getOptionIdList() {
        return optionIdList;
    }

    public void setOptionIdList(List<Integer> optionIdList) {
        this.optionIdList = optionIdList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
