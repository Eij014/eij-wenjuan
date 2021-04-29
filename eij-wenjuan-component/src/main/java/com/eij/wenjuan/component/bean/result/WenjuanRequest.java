package com.eij.wenjuan.component.bean.result;

import com.eij.wenjuan.component.bean.sys.SearchPaging;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-26
 */
public class WenjuanRequest extends SearchPaging {

    private String keywords;

    private int folderId;

    public WenjuanRequest(Integer currentPage, Integer pageSize) {
        super(currentPage, pageSize);
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }
}
