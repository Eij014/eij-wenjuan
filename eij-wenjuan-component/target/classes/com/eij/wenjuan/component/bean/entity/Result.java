package com.eij.wenjuan.component.bean.entity;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-09
 */
public class Result {
    private Integer id;

    private Integer wenjuanId;

    private Integer questionId;

    private Integer optionId;

    private String text;

    private String type;

    private Long createTime;

    private String province;

    private String city;

    private String uuid;

    public Result() {
    }

    @SuppressWarnings("ParameterNumber")
    public Result(Integer wenjuanId, Integer questionId, Integer optionId, String text,
                  String type, String province, String city, String uuid) {
        this.wenjuanId = wenjuanId;
        this.questionId = questionId;
        this.optionId = optionId;
        this.text = text;
        this.type = type;
        this.province = province;
        this.city = city;
        this.uuid = uuid;
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

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
