package com.eij.wenjuan.component.bean.common;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-06
 */
public interface Paging {
    Integer getCurrentPage();

    Integer getPageSize();

    default Integer getOffset() {
        if (getCurrentPage() <= 0) {
            return 0;
        } else {
            return (getCurrentPage() - 1) * getPageSize();
        }
    }

    default Integer getLimit() {
        return getPageSize();
    }
}
