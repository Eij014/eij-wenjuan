package com.eij.wenjuan.component.bean.sys;

import com.eij.wenjuan.component.bean.common.Paging;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-06
 */
public class SearchPaging implements Paging {

    @ApiModelProperty("当前页号")
    private Integer currentPage = 1;

    @ApiModelProperty(value = "当前页面大小")
    private Integer pageSize = 10;

    public SearchPaging(Integer currentPage, Integer pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    @Override
    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
