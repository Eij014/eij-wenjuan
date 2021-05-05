package com.eij.wenjuan.component.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.VO.CrossAnalysisVO;
import com.eij.wenjuan.component.bean.VO.ResultVO;
import com.eij.wenjuan.component.bean.entity.Option;
import com.eij.wenjuan.component.bean.entity.Question;
import com.eij.wenjuan.component.bean.entity.Result;
import com.eij.wenjuan.component.dao.ResultDao;
import com.eij.wenjuan.component.service.OptionService;
import com.eij.wenjuan.component.service.QuestionService;
import com.eij.wenjuan.component.service.ResultService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-09
 */
@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultDao resultDao;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionService optionService;

    @Override
    public int[] batchInsert(List<Result> resultList) {
        if (CollectionUtils.isEmpty(resultList)) {
            return new int[0];
        }
        return resultDao.batchInsert(resultList);
    }

    @Override
    public List<ResultVO> getByWenjuanId(int wenjuanId) {
        return resultDao.selectByWenjuanId(wenjuanId);
    }

    @Override
    public List<CrossAnalysisVO> getCrossAnalysis(int wenjuanId, List<Integer> questionIdXList, List<Integer> questionIdYList) {
        List<ResultVO> resultList = getByWenjuanId(wenjuanId);
        List<Integer> questionAllIdList = Lists.newArrayList(questionIdXList);
        questionAllIdList.addAll(questionIdYList);
        //每个用户对每个问题的回答结果
        Map<String, Map<Integer, List<ResultVO>>> userAnswerMap = getByWenjuanId(wenjuanId)
                .stream()
                .filter(o -> questionAllIdList.contains(o.getQuestionId()))
                .collect(Collectors.groupingBy(ResultVO::getUuid,
                        Collectors.groupingBy(ResultVO::getQuestionId)));
        List<Map<Integer, List<ResultVO>>> userAnswerMapList = Lists.newArrayList(getByWenjuanId(wenjuanId)
                .stream()
                .filter(o -> questionAllIdList.contains(o.getQuestionId()))
                .collect(Collectors.groupingBy(ResultVO::getUuid,
                        Collectors.groupingBy(ResultVO::getQuestionId))).values());

        List<CrossAnalysisVO> crossAnalysisVOList = Lists.newArrayList();
        //questionId -> name 映射
        Map<Integer, String> questionIdMap = questionService.getByWenjuanId(wenjuanId)
                .stream()
                .collect(Collectors.toMap(Question::getQuestionId, Question::getTitle));

        //questionId -> optionList 映射
        Map<Integer, List<Option>> optionMap = optionService.getOptionByQuestionIds(questionAllIdList)
                .stream()
                .collect(Collectors.groupingBy(Option::getQuestionId));
        questionIdXList.forEach(questionIdX -> {
            questionIdYList.forEach(questionIdY -> {
                CrossAnalysisVO crossAnalysisVO = new CrossAnalysisVO();
                crossAnalysisVO.setQuestionX(questionIdMap.get(questionIdX));
                crossAnalysisVO.setQuestionY(questionIdMap.get(questionIdY));
                Map<Integer, String> optionIdMapY = optionMap.get(questionIdY)
                        .stream()
                        .collect(Collectors.toMap(Option::getOptionId, Option::getOptionName));
                //表格列
                List<String> columns = Lists.newArrayList("X/Y");
                columns.addAll(optionIdMapY.values());
                columns.add("合计");
                crossAnalysisVO.setColumns(columns);
                //表格行
                Map<Integer, String> optionIdMapX = optionMap.get(questionIdX)
                        .stream()
                        .collect(Collectors.toMap(Option::getOptionId, Option::getOptionName));
                List<Map<String, String>> dataList = Lists.newArrayList();
                optionIdMapX.forEach((optionIdX, optionNameX) -> {
                    //计算有多少用户选择了X问题的optionNameX
                    AtomicInteger countOptionNameX = new AtomicInteger(0);
                    userAnswerMapList.forEach(o -> {
                        o.forEach((k, v) -> {
                            if (k == questionIdX
                                && v.stream().map(ResultVO::getOptionId).collect(Collectors.toList()).contains(optionIdX)) {
                                countOptionNameX.getAndIncrement();
                            }
                        });
                    });
                    Map<String, String> map = Maps.newHashMap();
                    map.put("X/Y", optionNameX);
                    optionIdMapY.forEach((optionIdY, optionNameY) -> {
                        //计算在用户选择了X问题的optionNameX基础上，有多少用户选择了optionNameY
                        AtomicInteger countOptionNameY = new AtomicInteger(0);
                        userAnswerMapList.forEach(o -> {
                            AtomicBoolean hasFind = new AtomicBoolean(false);
                            List<ResultVO> usrResultVOList = Lists.newArrayList();
                            o.values().forEach(o2 -> {
                                usrResultVOList.addAll(o2);
                            });
                            List<Integer> optionIdList = usrResultVOList
                                    .stream().map(ResultVO::getOptionId).collect(Collectors.toList());
                            if (optionIdList.contains(optionIdX) && optionIdList.contains(optionIdY)) {
                                countOptionNameY.getAndIncrement();
                            }
//                            o.forEach((k, v) -> {
//                                if (v.stream().map(ResultVO::getOptionId).collect(Collectors.toList()).contains(optionIdX)
//                                    && v.stream().map(ResultVO::getOptionId).collect(Collectors.toList()).contains(optionIdY)) {
////                                    userAnswerMapList.forEach(o2 -> {
////                                        o2.forEach((k2, v2) -> {
////                                            if (!hasFind.get() &&k2 == questionIdY &&
////                                                    v2.stream().map(ResultVO::getOptionId).collect(Collectors.toList()).contains(optionIdY)
////                                                    && v.stream().map(ResultVO::getOptionId).collect(Collectors.toList()).contains(optionIdX)) {
//                                                countOptionNameY.getAndIncrement();
////                                                hasFind.set(true);
////                                            }
////                                        });
////                                    });
//                                }
//                            });
                        });
                        double percentNumber = countOptionNameX.get() == 0
                                ? 0.0
                                : ((double) countOptionNameY.get() / countOptionNameX.get()) * 100;
                        String percentSrc = new DecimalFormat("0.0").format(percentNumber) + "%";
                        map.put(optionNameY, String.format("%s(%s)", countOptionNameY.get(), percentSrc));
                    });
                    map.put("合计", countOptionNameX.get() + "");
                    dataList.add(map);
                });
                crossAnalysisVO.setDataList(dataList);
                crossAnalysisVOList.add(crossAnalysisVO);
            });
        });
        return crossAnalysisVOList;
    }

    /**
     *  克隆巴赫系数信度分析
     */
    @Override
    public List<ResultVO> getByQuestionIdList(List<Integer> questionIdList) {
        if (CollectionUtils.isEmpty(questionIdList)) {
            return Lists.newArrayList();
        }
        return resultDao.selectByQuestionIds(questionIdList);
    }

    public double cronbach(List<Integer> questionIdList) {
        if (CollectionUtils.isEmpty(questionIdList)) {
            return 0;
        }
        int k = questionIdList.size();
        if (k == 1) {
            return 1;
        }
        List<ResultVO> resultVOList = getByQuestionIdList(questionIdList);
        //按questionId group by 求每个问题的样本方差
        Map<Integer, List<ResultVO>> questionIdMap = resultVOList
                .stream()
                .collect(Collectors.groupingBy(ResultVO::getQuestionId));
        AtomicReference<Double> varianceSum = new AtomicReference<Double>(0.00);
        questionIdMap.forEach((key, v) -> {
            varianceSum.updateAndGet(v1 -> new Double((double) (v1 + getVariance(v.stream().map(o -> {
                return Integer.parseInt(o.getText());
            }).collect(Collectors.toList())))));
        });
        //按uuid group by 求总体方差
        List<Integer> overallNumber = Lists.newArrayList();
        Map<String, List<ResultVO>> uuidMap = resultVOList
                .stream()
                .collect(Collectors.groupingBy(ResultVO::getUuid));
        uuidMap.forEach((key, v) -> {
            overallNumber.add(v.stream().mapToInt(o -> Integer.parseInt(o.getText())).sum());
        });
        double overallVariance = getVariance(overallNumber);
        return ((double) k / (k - 1)) * (1 - varianceSum.get() / overallVariance);
    }

    /**
     * 求方差
     */
    private double getVariance(List<Integer> numberList) {
        if (CollectionUtils.isEmpty(numberList)) {
            return 0;
        }
        int k = numberList.size();
        //样本和
        AtomicInteger atomicInteger = new AtomicInteger(0);
        numberList.forEach(o -> {
            atomicInteger.getAndAdd(o);
        });
        //平均数
        double average = (double) atomicInteger.get() / k;
        //方差
        AtomicReference<Double> variance = new AtomicReference<Double>(0.00);
        numberList.forEach(o -> {
            variance.updateAndGet(v -> v + Math.pow((o - average), 2));
        });
        return variance.get() / (k - 1);
    }
}
