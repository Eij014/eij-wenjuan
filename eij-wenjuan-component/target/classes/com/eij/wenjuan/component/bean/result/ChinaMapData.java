package com.eij.wenjuan.component.bean.result;

import java.util.List;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-12
 */
public class ChinaMapData {
    private String name;

    private int value;

    private List<City> cityList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}
