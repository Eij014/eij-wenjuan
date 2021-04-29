package com.eij.wenjuan.api.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eij.wenjuan.api.response.WenjuanResponseMessage;
import com.eij.wenjuan.component.bean.VO.WenjuanFolderVO;
import com.eij.wenjuan.component.bean.result.WenjuanRequest;
import com.eij.wenjuan.component.service.WenjuanFolderService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-27
 */
@RestController
@RequestMapping(path = {"/wenjuan/folder"})
public class WenjuanFolderController {

    @Autowired
    private WenjuanFolderService wenjuanFolderService;

    @PostMapping("/get/list")
    public WenjuanResponseMessage<WenjuanFolderVO> getWenjuanFolderList(@RequestBody WenjuanRequest wenjuanRequest) {
        return WenjuanResponseMessage.success(wenjuanFolderService.getWenjuanFolder(wenjuanRequest));
    }

    @GetMapping("/create/update")
    public WenjuanResponseMessage<Integer> createWenjuanFolder(@RequestParam("folderName") String folderName,
          @RequestParam(value = "folderId", required = false) Integer folderId) {
        if (Objects.isNull(folderId)) {
            return WenjuanResponseMessage.success(wenjuanFolderService.insert(folderName));
        } else {
            return WenjuanResponseMessage.success(wenjuanFolderService.update(folderId, folderName));
        }
    }

    @GetMapping("/delete")
    public WenjuanResponseMessage<Integer> deleteWenjuanFolder(@RequestParam("folderId") int folderId) {
        return WenjuanResponseMessage.success(wenjuanFolderService.delete(folderId));
    }
}
