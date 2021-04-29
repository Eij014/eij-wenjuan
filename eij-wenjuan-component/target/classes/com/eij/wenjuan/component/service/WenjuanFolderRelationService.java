package com.eij.wenjuan.component.service;

import com.eij.wenjuan.component.bean.entity.WenjuanFolderRelation;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-27
 */
public interface WenjuanFolderRelationService {

    int insert(WenjuanFolderRelation wenjuanFolderRelation);

    int delete(int folderId, int wenjuanId);


}
