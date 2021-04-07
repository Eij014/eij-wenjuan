package com.eij.wenjuan.component.bean.VO;

import java.util.List;

import com.eij.wenjuan.component.bean.entity.Wenjuan;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-14
 */
public class WenjuanDetailVO extends Wenjuan {

    private List<QuestionVO> questionVOList;

    public WenjuanDetailVO(Wenjuan wenjuan) {
        this.setWenjuanId(wenjuan.getWenjuanId());
        this.setWenjuanTitle(wenjuan.getWenjuanTitle());
        this.setWelcomeMsg(wenjuan.getWelcomeMsg());
        this.setCommitter(wenjuan.getCommitter());
        this.setImgUrl(wenjuan.getImgUrl());
        this.setCreateTime(wenjuan.getCreateTime());
        this.setStatus(wenjuan.getStatus());
        this.setType(wenjuan.getType());
    }

    public List<QuestionVO> getQuestionVOList() {
        return questionVOList;
    }

    public void setQuestionVOList(List<QuestionVO> questionVOList) {
        this.questionVOList = questionVOList;
    }
}
