package com.eij.wenjuan.component.bean.request;

import com.eij.wenjuan.component.bean.sys.SearchPaging;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-03
 */
public class SearchRequest extends SearchPaging {

    public SearchRequest(Integer currentPage, Integer pageSize) {
        super(currentPage, pageSize);
    }

}
