package com.eij.wenjuan.component.bean;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Zhu HaojieEij<eij00014@gmail.com>
 * Created on 2021-04-10
 */
public class Data {

    @JsonInclude(NON_NULL)
    private List<String> data;

    public Data() {
    }

    public Data(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
