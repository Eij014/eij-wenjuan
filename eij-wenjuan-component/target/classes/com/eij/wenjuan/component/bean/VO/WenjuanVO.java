package com.eij.wenjuan.component.bean.VO;

import java.util.List;

import com.eij.wenjuan.component.bean.entity.Wenjuan;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-02
 */

public class WenjuanVO {
    private int total;
    private int pageSize;
    private int currentPage;
    private int totalPage;
    private List<Wenjuan> wenjuanList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Wenjuan> getWenjuanList() {
        return wenjuanList;
    }

    public void setWenjuanList(List<Wenjuan> wenjuanList) {
        this.wenjuanList = wenjuanList;
    }
}
