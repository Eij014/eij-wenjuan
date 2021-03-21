package com.eij.wenjuan.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eij.wenjuan.api.response.WenjuanResponseMessage;
import com.eij.wenjuan.component.bean.Image;
import com.eij.wenjuan.component.bean.VO.WenjuanDetailVO;
import com.eij.wenjuan.component.bean.VO.WenjuanVO;
import com.eij.wenjuan.component.bean.sys.SearchPaging;
import com.eij.wenjuan.component.service.ImageUploadService;
import com.eij.wenjuan.component.service.WenjuanService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-05
 */
@RestController
@RequestMapping(path = {"/v3/wenjuan/"})
public class WenjuanController {

    private static final Logger logger = LoggerFactory.getLogger(WenjuanController.class);

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private WenjuanService wenjuanService;

    @PostMapping("/upload/image")
    public WenjuanResponseMessage<String> upload(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        String name = multipartFile.getOriginalFilename();
        InputStream inputStream = multipartFile.getInputStream();
       return WenjuanResponseMessage.success(
               imageUploadService.uploadImage(name, IOUtils.toByteArray(inputStream)));
    }

    @PostMapping("/get/image")
    public WenjuanResponseMessage<List<Image>> getImage() {
        return WenjuanResponseMessage.success(imageUploadService.getImages());
    }


    @PostMapping("/list")
    public WenjuanResponseMessage<WenjuanVO> getWenjuanList(@RequestBody SearchPaging searchPaging) {
        return WenjuanResponseMessage.success(wenjuanService.getWenjuanList(searchPaging));
    }

    @GetMapping("/detail")
    public WenjuanResponseMessage<WenjuanDetailVO> getWenjuanDetail(@RequestParam("wenjuanId") int wenjuanId) {
        return WenjuanResponseMessage.success();
    }
}
