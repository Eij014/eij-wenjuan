package com.eij.wenjuan.component.bean.result;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-10
 */
public class EchartsSeries {
    private String name;
    private String type;
    private int barWidth;
    //饼图半径
    @JsonInclude(NON_NULL)
    private List<String> radius;
    @JsonInclude(NON_NULL)
    private List<Integer> data;

    public EchartsSeries(String name, String type, int barWidth, List<Integer> data) {
        this.name = name;
        this.type = type;
        this.barWidth = barWidth;
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

    public int getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(int barWidth) {
        this.barWidth = barWidth;
    }

    public List<String> getRadius() {
        return radius;
    }

    public void setRadius(List<String> radius) {
        this.radius = radius;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
