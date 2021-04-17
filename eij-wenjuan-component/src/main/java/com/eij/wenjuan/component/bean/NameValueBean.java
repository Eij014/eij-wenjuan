package com.eij.wenjuan.component.bean;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-15
 */
public class NameValueBean {
    private int value;

    private String name;

    public NameValueBean(String name, int value) {
        this.name = name;
        this.value = value;
    }

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
}
