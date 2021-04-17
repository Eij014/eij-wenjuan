package com.eij.wenjuan.api.request;

import java.util.List;

import com.eij.wenjuan.component.bean.request.AnswerRequest;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-09
 */
public class WenjuanAnswerRequest {
    private int wenjuanId;

    private List<AnswerRequest> resultList;

    public int getWenjuanId() {
        return wenjuanId;
    }

    public void setWenjuanId(int wenjuanId) {
        this.wenjuanId = wenjuanId;
    }

    public List<AnswerRequest> getResultList() {
        return resultList;
    }

    public void setResultList(List<AnswerRequest> resultList) {
        this.resultList = resultList;
    }
}
