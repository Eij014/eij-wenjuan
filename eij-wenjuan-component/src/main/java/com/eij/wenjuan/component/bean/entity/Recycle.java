package com.eij.wenjuan.component.bean.entity;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-13
 */
public class Recycle {
    private Integer id;

    private Integer wenjuanId;

    private String province;

    private String city;

    private Long createTime;

    public Recycle() {
    }

    public Recycle(Integer wenjuanId, String province, String city) {
        this.wenjuanId = wenjuanId;
        this.province = province;
        this.city = city;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
