package com.eij.wenjuan.component.bean.VO;

import com.eij.wenjuan.component.bean.entity.Wenjuan;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-25
 */
public class WenjuanVO extends Wenjuan {
    private int recycleCount;

    public WenjuanVO(Wenjuan wenjuan, int recycleCount) {
        super(wenjuan);
        this.recycleCount = recycleCount;
    }

    public int getRecycleCount() {
        return recycleCount;
    }

    public void setRecycleCount(int recycleCount) {
        this.recycleCount = recycleCount;
    }
}
