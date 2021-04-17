package com.eij.wenjuan.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eij.wenjuan.api.response.WenjuanResponseMessage;
import com.eij.wenjuan.component.bean.result.RecycleProcessResponse;
import com.eij.wenjuan.component.bean.result.WenjuanResult;
import com.eij.wenjuan.component.service.ResultService;
import com.eij.wenjuan.component.service.WenjuanService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-13
 */
@RestController
@RequestMapping(path = {"/wenjuan/result"})
public class ResultController {
    @Autowired
    private ResultService resultService;

    @Autowired
    private WenjuanService wenjuanService;

    @GetMapping("/get/recycle/form")
    public WenjuanResponseMessage<WenjuanResult> getResult(@RequestParam("wenjuanId") int wenjuanId) {
        return WenjuanResponseMessage.success(wenjuanService.getWenjuanResult(wenjuanId));
    }

    @GetMapping("/get/recycle/process")
    public WenjuanResponseMessage<RecycleProcessResponse> getWenjuanRecycleProcess(@RequestParam("wenjuanId") int wenjuanId) {
        return WenjuanResponseMessage.success(resultService.getWenjuanRecycleProcess(wenjuanId));
    }

}
