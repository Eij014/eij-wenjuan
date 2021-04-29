package com.eij.wenjuan.component.bean.VO;

import java.util.List;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-02
 */

public class WenjuanVOList {
    private int total;
    private int pageSize;
    private int currentPage;
    private int totalPage;
    private List<WenjuanVO> wenjuanList;

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

    public List<WenjuanVO> getWenjuanList() {
        return wenjuanList;
    }

    public void setWenjuanList(List<WenjuanVO> wenjuanList) {
        this.wenjuanList = wenjuanList;
    }
}
