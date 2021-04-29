package com.eij.wenjuan.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.VO.WenjuanFolderVO;
import com.eij.wenjuan.component.bean.entity.WenjuanFolder;
import com.eij.wenjuan.component.bean.result.WenjuanRequest;
import com.eij.wenjuan.component.dao.WenjuanFolderDao;
import com.eij.wenjuan.component.service.WenjuanFolderRelationService;
import com.eij.wenjuan.component.service.WenjuanFolderService;
import com.eij.wenjuan.component.utils.web.LoginUserContext;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-27
 */
@Service
public class WenjuanFolderServiceImpl implements WenjuanFolderService {

    @Autowired
    private WenjuanFolderDao wenjuanFolderDao;

    @Autowired
    private WenjuanFolderRelationService wenjuanFolderRelationService;

    @Override
    public int insert(String folderName) {
        WenjuanFolder wenjuanFolder = new WenjuanFolder();
        wenjuanFolder.setCommitter(LoginUserContext.getUserName());
        wenjuanFolder.setFolderName(folderName);
        return wenjuanFolderDao.insert(wenjuanFolder);
    }

    @Override
    public WenjuanFolderVO getWenjuanFolder(WenjuanRequest wenjuanRequest) {
        String userName = LoginUserContext.getUserName();
        WenjuanFolderVO wenjuanFolderVO = new WenjuanFolderVO();
        List<Integer> folderIdByCondition = getWenjuanIdsByCondition(wenjuanRequest.getKeywords());
        wenjuanFolderVO.setCurrentPage(wenjuanRequest.getCurrentPage());
        int limit = wenjuanRequest.getPageSize();
        int offset = (wenjuanRequest.getCurrentPage() - 1) * wenjuanRequest.getPageSize();
        int total = wenjuanFolderDao.selectTotalByUserName(userName, folderIdByCondition);
        List<WenjuanFolder> folderList
                = wenjuanFolderDao.selectByUserName(userName, folderIdByCondition, limit, offset);
        wenjuanFolderVO.setTotal(total);
        wenjuanFolderVO.setPageSize(folderList.size());
        int totalPage = (total % wenjuanRequest.getPageSize()) == 0
                ? total / wenjuanRequest.getPageSize()
                : total / wenjuanRequest.getPageSize() + 1;
        wenjuanFolderVO.setTotalPage(totalPage);
        wenjuanFolderVO.setWenjuanFolderList(folderList);
        return wenjuanFolderVO;
    }


    @Override
    public int update(int folderId, String folderName) {
        return wenjuanFolderDao.update(folderId, folderName);
    }

    @Override
    public int delete(int folderId) {
        wenjuanFolderRelationService.delete(folderId, 0);
        return wenjuanFolderDao.delete(folderId);
    }

    private List<Integer> getWenjuanIdsByCondition(String keywords) {
        return wenjuanFolderDao.selectIdsByCondition(keywords);
    }
}
