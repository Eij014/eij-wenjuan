package com.eij.wenjuan.component.bean.VO;

import java.util.List;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-05-13
 */
public class RecycleDataVO {

    private List<QuestionVO> questionVOList;

    private String location;

    public List<QuestionVO> getQuestionVOList() {
        return questionVOList;
    }

    public void setQuestionVOList(List<QuestionVO> questionVOList) {
        this.questionVOList = questionVOList;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
