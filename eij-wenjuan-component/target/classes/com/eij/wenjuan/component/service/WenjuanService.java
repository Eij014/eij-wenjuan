package com.eij.wenjuan.component.service;

import com.eij.wenjuan.component.bean.VO.WenjuanVO;
import com.eij.wenjuan.component.bean.sys.SearchPaging;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-02
 */
public interface WenjuanService {
    WenjuanVO getWenjuanList(String userName, SearchPaging searchPaging);
}
