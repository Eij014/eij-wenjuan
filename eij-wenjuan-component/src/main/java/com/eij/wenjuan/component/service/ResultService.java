package com.eij.wenjuan.component.service;

import java.util.List;

import com.eij.wenjuan.component.bean.VO.CrossAnalysisVO;
import com.eij.wenjuan.component.bean.VO.ResultVO;
import com.eij.wenjuan.component.bean.entity.Result;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-04-09
 */
public interface ResultService {

    int[] batchInsert(List<Result> resultList);

    List<ResultVO> getByWenjuanId(int wenjuanId);

    List<CrossAnalysisVO> getCrossAnalysis(int wenjuanId, List<Integer> questionIdXList, List<Integer> questionIdYList);

    List<ResultVO> getByQuestionIdList(List<Integer> questionIdList);

    double cronbach(List<Integer> questionIdList);
}
