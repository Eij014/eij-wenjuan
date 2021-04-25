package com.eij.wenjuan.component.bean.VO;

import java.util.List;
import java.util.Map;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-24
 */
public class CrossAnalysisVO {

    private String questionX;

    private String questionY;

    private List<String> columns;

    private List<Map<String, String>> dataList;

    public String getQuestionX() {
        return questionX;
    }

    public void setQuestionX(String questionX) {
        this.questionX = questionX;
    }

    public String getQuestionY() {
        return questionY;
    }

    public void setQuestionY(String questionY) {
        this.questionY = questionY;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Map<String, String>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, String>> dataList) {
        this.dataList = dataList;
    }
}
