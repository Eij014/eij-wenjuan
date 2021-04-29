package com.eij.wenjuan.api.request;

import java.util.List;

import com.eij.wenjuan.component.bean.VO.QuestionVO;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-27
 */
public class WenjuanEditRequest {
    private int wenjuanId;

    private String wenjuanTitle;

    private String welcomeMsg;

    private int folderId;

    private String imgUrl;

    private List<QuestionVO> questionVOList;

    public WenjuanEditRequest(int wenjuanId, String imgUrl, String wenjuanTitle, String welcomeMsg, List<QuestionVO> questionVOList) {
            this.wenjuanId = wenjuanId;
            this.wenjuanTitle = wenjuanTitle;
            this.welcomeMsg = welcomeMsg;
            this.imgUrl = imgUrl;
            this.questionVOList = questionVOList;
    }

    public int getWenjuanId() {
        return wenjuanId;
    }

    public void setWenjuanId(int wenjuanId) {
        this.wenjuanId = wenjuanId;
    }

    public String getWenjuanTitle() {
        return wenjuanTitle;
    }

    public void setWenjuanTitle(String wenjuanTitle) {
        this.wenjuanTitle = wenjuanTitle;
    }

    public String getWelcomeMsg() {
        return welcomeMsg;
    }

    public void setWelcomeMsg(String welcomeMsg) {
        this.welcomeMsg = welcomeMsg;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<QuestionVO> getQuestionVOList() {
        return questionVOList;
    }

    public void setQuestionVOList(List<QuestionVO> questionVOList) {
        this.questionVOList = questionVOList;
    }
}
