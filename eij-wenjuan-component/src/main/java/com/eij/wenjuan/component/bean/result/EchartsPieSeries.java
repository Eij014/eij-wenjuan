package com.eij.wenjuan.component.bean.result;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.eij.wenjuan.component.bean.NameValueBean;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Eij<eij00014 @ gmail.com>
 * Created on 2021-04-15
 */
public class EchartsPieSeries {
    private String name;
    private String type;
    //饼图半径
    @JsonInclude(NON_NULL)
    private List<String> radius;
    @JsonInclude(NON_NULL)
    private List<NameValueBean> data;

    public EchartsPieSeries(String name, String type, List<NameValueBean> data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<String> getRadius() {
        return radius;
    }

    public void setRadius(List<String> radius) {
        this.radius = radius;
    }

    public List<NameValueBean> getData() {
        return data;
    }

    public void setData(List<NameValueBean> data) {
        this.data = data;
    }

}
