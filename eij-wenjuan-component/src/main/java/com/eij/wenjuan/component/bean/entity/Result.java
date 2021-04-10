package com.eij.wenjuan.component.bean.entity;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-09
 */
public class Result {
    private Integer id;

    private Integer wenjuanId;

    private Integer questionId;

    private String optionIdList;

    private String text;

    private String type;

    private Long createTime;

    private String ip;

    public Result() {
    }

    public Result(Integer wenjuanId, Integer questionId, String optionIdList, String text, String type, String ip) {
        this.wenjuanId = wenjuanId;
        this.questionId = questionId;
        this.optionIdList = optionIdList;
        this.text = text;
        this.type = type;
        this.ip = ip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWenjuanId() {
        return wenjuanId;
    }

    public void setWenjuanId(Integer wenjuanId) {
        this.wenjuanId = wenjuanId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getOptionIdList() {
        return optionIdList;
    }

    public void setOptionIdList(String optionIdList) {
        this.optionIdList = optionIdList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
