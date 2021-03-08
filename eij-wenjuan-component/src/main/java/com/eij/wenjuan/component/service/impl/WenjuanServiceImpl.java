package com.eij.wenjuan.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.VO.WenjuanVO;
import com.eij.wenjuan.component.bean.entity.Wenjuan;
import com.eij.wenjuan.component.bean.sys.SearchPaging;
import com.eij.wenjuan.component.dao.WenjuanDao;
import com.eij.wenjuan.component.service.WenjuanService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-02
 */
@Service
public class WenjuanServiceImpl implements WenjuanService {

    @Autowired
    private WenjuanDao wenjuanDao;

    @Override
    public WenjuanVO getWenjuanList(String userName, SearchPaging searchPaging) {
        WenjuanVO wenjuanVO = new WenjuanVO();

        wenjuanVO.setCurrentPage(searchPaging.getCurrentPage());
        int limit = searchPaging.getPageSize();
        int offset = (searchPaging.getCurrentPage() - 1) * searchPaging.getPageSize();
        int total = wenjuanDao.selectTotalByUserName(userName);
        List<Wenjuan> wenjuanList
                = wenjuanDao.selectByUserName(userName, limit, offset);
        wenjuanVO.setWenjuanList(wenjuanList);
        wenjuanVO.setTotal(total);
        wenjuanVO.setPageSize(wenjuanList.size());
        int totalPage = (total % searchPaging.getPageSize()) == 0
                ? total / searchPaging.getPageSize()
                : total / searchPaging.getPageSize() + 1;
        wenjuanVO.setTotalPage(totalPage);
        return wenjuanVO;
    }
}
