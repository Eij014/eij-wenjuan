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

import com.eij.wenjuan.api.request.WenjuanAnswerRequest;
import com.eij.wenjuan.api.request.WenjuanEditRequest;
import com.eij.wenjuan.api.response.WenjuanResponseMessage;
import com.eij.wenjuan.component.bean.Image;
import com.eij.wenjuan.component.bean.OpenApi.AmapResponse;
import com.eij.wenjuan.component.bean.VO.WenjuanDetailVO;
import com.eij.wenjuan.component.bean.VO.WenjuanVOList;
import com.eij.wenjuan.component.bean.entity.Question;
import com.eij.wenjuan.component.bean.result.WenjuanRequest;
import com.eij.wenjuan.component.service.ImageUploadService;
import com.eij.wenjuan.component.service.QuestionService;
import com.eij.wenjuan.component.service.WenjuanService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-02-05
 */
@RestController
@RequestMapping(path = {"/wenjuan"})
public class WenjuanController {

    private static final Logger logger = LoggerFactory.getLogger(WenjuanController.class);

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private WenjuanService wenjuanService;

    @Autowired
    private QuestionService questionService;

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
    public WenjuanResponseMessage<WenjuanVOList> getWenjuanList(@RequestBody WenjuanRequest wenjuanRequest) {
        return WenjuanResponseMessage.success(wenjuanService.getWenjuanList(wenjuanRequest));
    }

    @PostMapping("/create/update")
    public WenjuanResponseMessage<Integer> createOrUpdateWenjuan(@RequestBody WenjuanEditRequest wenjuanEditRequest) {
        wenjuanService
                .createOrUpdateWenjuan(wenjuanEditRequest.getWenjuanId(),
                        wenjuanEditRequest.getImgUrl(),
                        wenjuanEditRequest.getWenjuanTitle(),
                        wenjuanEditRequest.getFolderId(),
                        wenjuanEditRequest.getType(),
                        wenjuanEditRequest.getWelcomeMsg(),
                        wenjuanEditRequest.getQuestionVOList());
        return WenjuanResponseMessage.success("保存成功", 1);
    }

    @GetMapping("/delete")
    public WenjuanResponseMessage<Integer> deleteWenjuan(@RequestParam("wenjuanId") int wenjuanId) {
        return WenjuanResponseMessage.success(wenjuanService.deleteByWenjuanId(wenjuanId));
    }

    @GetMapping("/detail")
    public WenjuanResponseMessage<WenjuanDetailVO> getWenjuanDetail(@RequestParam("wenjuanId") int wenjuanId,
                                                                    @RequestParam("type") String type) {
        return WenjuanResponseMessage.success(wenjuanService.getWenjuanDetail(wenjuanId, type));
    }

    @GetMapping("/init/img/url")
    public WenjuanResponseMessage<String> getInitImgUrl() {
        return WenjuanResponseMessage.success(wenjuanService.getInitImgUrs());
    }

    @GetMapping("/publish")
    public WenjuanResponseMessage<Integer> publish(@RequestParam("wenjuanId") int wenjuanId) {
        return WenjuanResponseMessage.success(wenjuanService.publish(wenjuanId));
    }

    @PostMapping("/answer")
    public WenjuanResponseMessage<Integer> answer(@RequestBody WenjuanAnswerRequest wenjuanAnswerRequest) {
        return WenjuanResponseMessage.success(wenjuanService.answer(wenjuanAnswerRequest.getWenjuanId(), wenjuanAnswerRequest.getResultList()));
    }

    @GetMapping("/ip")
    public WenjuanResponseMessage<AmapResponse> getAddress(@RequestParam("ip") String ip) {
        return WenjuanResponseMessage.success(wenjuanService.getAddress(ip));
    }

    @GetMapping("/get/question")
    public WenjuanResponseMessage<List<Question>> getQuestion(@RequestParam("wenjuanId") int wenjuanId) {
        return WenjuanResponseMessage.success(questionService.getByWenjuanId(wenjuanId));
    }

    @GetMapping("/quote")
    public WenjuanResponseMessage<Integer> quoteTemplate(@RequestParam("wenjuanId") int wenjuanId) {
        return WenjuanResponseMessage.success(wenjuanService.quoteTemplate(wenjuanId));
    }

}
