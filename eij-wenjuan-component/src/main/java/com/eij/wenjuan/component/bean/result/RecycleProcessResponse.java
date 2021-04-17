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

    public RecycleProcessResponse(List<ChinaMapData> chinaMapData, List<EchartsBarOption> recycleForm) {
        this.chinaMapData = chinaMapData;
        this.recycleForm = recycleForm;
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
}
