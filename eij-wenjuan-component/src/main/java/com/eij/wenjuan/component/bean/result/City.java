package com.eij.wenjuan.component.bean.result;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-12
 */
public class City {
    private String cityName;

    private int value;

    public City(String cityName, int value) {
        this.cityName = cityName;
        this.value = value;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
