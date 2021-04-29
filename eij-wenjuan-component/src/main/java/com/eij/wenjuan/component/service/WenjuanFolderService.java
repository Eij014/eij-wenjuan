package com.eij.wenjuan.component.service;

import com.eij.wenjuan.component.bean.VO.WenjuanFolderVO;
import com.eij.wenjuan.component.bean.result.WenjuanRequest;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-27
 */
public interface WenjuanFolderService {

    int insert(String folderName);

    WenjuanFolderVO getWenjuanFolder(WenjuanRequest wenjuanRequest);

    int update(int folderId, String folderName);

    int delete(int folderId);
}
