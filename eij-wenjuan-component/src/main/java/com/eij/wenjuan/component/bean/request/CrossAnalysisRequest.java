package com.eij.wenjuan.component.bean.request;

import java.util.List;

/**
 * @author Eij<eij00014 @ gmail.com>
 * Created on 2021-04-24
 */
public class CrossAnalysisRequest {

    private int wenjuanId;

    private List<Integer> questionIdXList;

    private List<Integer> questionIdYList;

    public int getWenjuanId() {
        return wenjuanId;
    }

    public void setWenjuanId(int wenjuanId) {
        this.wenjuanId = wenjuanId;
    }

    public List<Integer> getQuestionIdXList() {
        return questionIdXList;
    }

    public void setQuestionIdXList(List<Integer> questionIdXList) {
        this.questionIdXList = questionIdXList;
    }

    public List<Integer> getQuestionIdYList() {
        return questionIdYList;
    }

    public void setQuestionIdYList(List<Integer> questionIdYList) {
        this.questionIdYList = questionIdYList;
    }
}
