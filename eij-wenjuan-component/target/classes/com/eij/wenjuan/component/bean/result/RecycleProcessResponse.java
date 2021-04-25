package com.eij.wenjuan.component.bean.result;

import java.util.List;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-12
 */
public class RecycleProcessResponse {
    //地图数据
    private List<ChinaMapData> chinaMapData;

    //回收曲线
    private List<EchartsBarOption> recycleForm;

    //曝光量
    private int wenjuanRecycleBrowse;
    //回收总量
    private int recycleCount;
    // 回收率
    private int recoveryRate;

    //日环比
    private int dayRatio;
    //周环比
    private int weekRatio;
    //月环比
    private int monthRatio;

    public RecycleProcessResponse(List<ChinaMapData> chinaMapData, List<EchartsBarOption> recycleForm,
                                  int wenjuanRecycleBrowse,
                                  int recycleCount, int recoveryRate) {
        this.chinaMapData = chinaMapData;
        this.recycleForm = recycleForm;
        this.wenjuanRecycleBrowse = wenjuanRecycleBrowse;
        this.recycleCount = recycleCount;
        this.recoveryRate = recoveryRate;
    }

    public List<ChinaMapData> getChinaMapData() {
        return chinaMapData;
    }

    public void setChinaMapData(List<ChinaMapData> chinaMapData) {
        this.chinaMapData = chinaMapData;
    }

    public List<EchartsBarOption> getRecycleForm() {
        return recycleForm;
    }

    public void setRecycleForm(List<EchartsBarOption> recycleForm) {
        this.recycleForm = recycleForm;
    }

    public int getWenjuanRecycleBrowse() {
        return wenjuanRecycleBrowse;
    }

    public void setWenjuanRecycleBrowse(int wenjuanRecycleBrowse) {
        this.wenjuanRecycleBrowse = wenjuanRecycleBrowse;
    }

    public int getRecycleCount() {
        return recycleCount;
    }

    public void setRecycleCount(int recycleCount) {
        this.recycleCount = recycleCount;
    }

    public int getRecoveryRate() {
        return recoveryRate;
    }

    public void setRecoveryRate(int recoveryRate) {
        this.recoveryRate = recoveryRate;
    }

    public int getDayRatio() {
        return dayRatio;
    }

    public void setDayRatio(int dayRatio) {
        this.dayRatio = dayRatio;
    }

    public int getWeekRatio() {
        return weekRatio;
    }

    public void setWeekRatio(int weekRatio) {
        this.weekRatio = weekRatio;
    }

    public int getMonthRatio() {
        return monthRatio;
    }

    public void setMonthRatio(int monthRatio) {
        this.monthRatio = monthRatio;
    }
}
