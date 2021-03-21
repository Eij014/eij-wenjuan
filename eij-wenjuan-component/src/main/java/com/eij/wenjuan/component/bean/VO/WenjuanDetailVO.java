package com.eij.wenjuan.component.bean.VO;

import java.util.List;

import com.eij.wenjuan.component.bean.entity.Wenjuan;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-14
 */
public class WenjuanDetailVO extends Wenjuan {
    private List<QuestionVO> singleChoiceList;

    private List<QuestionVO> multipleChoiceList;

    private List<QuestionVO> pictureList;

    private List<QuestionVO> videoList;

    public WenjuanDetailVO(Wenjuan wenjuan) {
        this.setWenjuanId(wenjuan.getWenjuanId());
        this.setWenjuanTitle(wenjuan.getWenjuanTitle());
        this.setCommitter(wenjuan.getCommitter());
        this.setImgUrl(wenjuan.getImgUrl());
        this.setCreateTime(wenjuan.getCreateTime());
        this.setStatus(wenjuan.getStatus());
        this.setType(wenjuan.getType());
    }

    public List<QuestionVO> getSingleChoiceList() {
        return singleChoiceList;
    }

    public void setSingleChoiceList(List<QuestionVO> singleChoiceList) {
        this.singleChoiceList = singleChoiceList;
    }

    public List<QuestionVO> getMultipleChoiceList() {
        return multipleChoiceList;
    }

    public void setMultipleChoiceList(List<QuestionVO> multipleChoiceList) {
        this.multipleChoiceList = multipleChoiceList;
    }

    public List<QuestionVO> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<QuestionVO> pictureList) {
        this.pictureList = pictureList;
    }

    public List<QuestionVO> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<QuestionVO> videoList) {
        this.videoList = videoList;
    }
}
