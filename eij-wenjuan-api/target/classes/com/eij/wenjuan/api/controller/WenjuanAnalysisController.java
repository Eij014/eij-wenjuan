package com.eij.wenjuan.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eij.wenjuan.api.response.WenjuanResponseMessage;
import com.eij.wenjuan.component.bean.VO.CrossAnalysisVO;
import com.eij.wenjuan.component.bean.VO.RecycleDataVOList;
import com.eij.wenjuan.component.bean.request.CrossAnalysisRequest;
import com.eij.wenjuan.component.bean.result.RecycleProcessResponse;
import com.eij.wenjuan.component.bean.result.WenjuanResult;
import com.eij.wenjuan.component.service.RecycleService;
import com.eij.wenjuan.component.service.ResultService;
import com.eij.wenjuan.component.service.WenjuanService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-13
 */
@RestController
@RequestMapping(path = {"/wenjuan/analysis"})
public class WenjuanAnalysisController {
    @Autowired
    private ResultService resultService;

    @Autowired
    private WenjuanService wenjuanService;

    @Autowired
    private RecycleService recycleService;

    @GetMapping("/get/recycle/form")
    public WenjuanResponseMessage<WenjuanResult> getResult(@RequestParam("wenjuanId") int wenjuanId) {
        return WenjuanResponseMessage.success(wenjuanService.getWenjuanResult(wenjuanId));
    }

    @GetMapping("/get/recycle/process")
    public WenjuanResponseMessage<RecycleProcessResponse> getWenjuanRecycleProcess(@RequestParam("wenjuanId") int wenjuanId) {
        return WenjuanResponseMessage.success(recycleService.getWenjuanRecycleProcess(wenjuanId));
    }

    @PostMapping("/get/cross/analysis")
    public WenjuanResponseMessage<List<CrossAnalysisVO>> getCrossAnalysis(@RequestBody CrossAnalysisRequest crossAnalysisRequest) {
        return WenjuanResponseMessage.success(resultService.getCrossAnalysis(crossAnalysisRequest.getWenjuanId(),
                crossAnalysisRequest.getQuestionIdXList(), crossAnalysisRequest.getQuestionIdYList()));

    }


    @PostMapping("/get/cronbach")
    public WenjuanResponseMessage<Double> getCrobach(@RequestBody CrossAnalysisRequest crossAnalysisRequest) {
        return WenjuanResponseMessage.success(resultService.cronbach(crossAnalysisRequest.getQuestionIdXList()));
    }

    @GetMapping("get/data")
    public WenjuanResponseMessage<RecycleDataVOList> getData(@RequestParam("wenjuanId") int wenjuanId) {
        return WenjuanResponseMessage.success(resultService.getData(wenjuanId));
    }

}
