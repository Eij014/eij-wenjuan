package com.eij.wenjuan.component.bean.VO;

import java.util.List;
import java.util.Map;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-05-13
 */
public class RecycleDataVOList {
    private int wenjuanId;

    private String wenjuanName;

    private List<RecycleDataVO> recycleDataVOList;

    private List<String> questionNameList;

    private List<Map<String, String>> recycleDataList;

    public RecycleDataVOList(List<RecycleDataVO> recycleDataVOList, List<String> questionNameList,
                             List<Map<String, String>> recycleDataList) {
        this.recycleDataVOList = recycleDataVOList;
        this.questionNameList = questionNameList;
        this.recycleDataList = recycleDataList;
    }

    public int getWenjuanId() {
        return wenjuanId;
    }

    public void setWenjuanId(int wenjuanId) {
        this.wenjuanId = wenjuanId;
    }

    public String getWenjuanName() {
        return wenjuanName;
    }

    public void setWenjuanName(String wenjuanName) {
        this.wenjuanName = wenjuanName;
    }

    public List<RecycleDataVO> getRecycleDataVOList() {
        return recycleDataVOList;
    }

    public void setRecycleDataVOList(List<RecycleDataVO> recycleDataVOList) {
        this.recycleDataVOList = recycleDataVOList;
    }

    public List<String> getQuestionNameList() {
        return questionNameList;
    }

    public void setQuestionNameList(List<String> questionNameList) {
        this.questionNameList = questionNameList;
    }

    public List<Map<String, String>> getRecycleDataList() {
        return recycleDataList;
    }

    public void setRecycleDataList(List<Map<String, String>> recycleDataList) {
        this.recycleDataList = recycleDataList;
    }
}
