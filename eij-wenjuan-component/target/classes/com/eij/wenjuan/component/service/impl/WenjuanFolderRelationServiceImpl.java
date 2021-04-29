package com.eij.wenjuan.component.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.entity.WenjuanFolderRelation;
import com.eij.wenjuan.component.dao.WenjuanFolderRelationDao;
import com.eij.wenjuan.component.service.WenjuanFolderRelationService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-27
 */
@Service
public class WenjuanFolderRelationServiceImpl implements WenjuanFolderRelationService {

    @Autowired
    private WenjuanFolderRelationDao wenjuanFolderRelationDao;

    @Override
    public int insert(WenjuanFolderRelation wenjuanFolderRelation) {
        return wenjuanFolderRelationDao.insert(wenjuanFolderRelation);
    }

    @Override
    public int delete(int folderId, int wenjuanId) {
        return wenjuanFolderRelationDao.delete(folderId, wenjuanId);
    }
}
