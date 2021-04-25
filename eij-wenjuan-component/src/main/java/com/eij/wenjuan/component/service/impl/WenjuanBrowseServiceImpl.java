package com.eij.wenjuan.component.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.dao.WenjuanBrowseDao;
import com.eij.wenjuan.component.service.WenjuanBrowseService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-23
 */
@Service
public class WenjuanBrowseServiceImpl implements WenjuanBrowseService {

    @Autowired
    private WenjuanBrowseDao wenjuanBrowseDao;

    @Override
    public int insert(int wenjuanId) {
        return wenjuanBrowseDao.insert(wenjuanId);
    }

    @Override
    public int getWenjuanBrowseCount(int wenjuanId) {
        return wenjuanBrowseDao.selectByWenjuanId(wenjuanId);
    }
}
