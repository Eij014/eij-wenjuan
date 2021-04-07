package com.eij.wenjuan.component.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.entity.Option;
import com.eij.wenjuan.component.dao.OptionDao;
import com.eij.wenjuan.component.service.OptionService;
import com.google.common.collect.Lists;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-16
 */
@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionDao optionDao;

    public List<Option> getOptionByQuestionIds(List<Integer> questionIdList) {
        if (CollectionUtils.isEmpty(questionIdList)) {
            return Lists.newArrayList();
        }
        return optionDao.selectByQuestionIds(questionIdList);
    }

    @Override
    public void batchInsertByMap(Map<Integer, List<Option>> questionIdOptionMap) {
        List<Option> optionList = Lists.newArrayList();
        questionIdOptionMap.entrySet().stream()
                .map(entry -> {
                         int questionId = entry.getKey();
                         optionList.addAll(entry.getValue()
                                 .stream()
                                 .filter(o -> o.getOptionId() == 0)
                                 .map(o -> {
                                     o.setQuestionId(questionId);
                                     return o;
                                 })
                                 .collect(Collectors.toList()));
                         return null;
                    }).collect(Collectors.toList());
        optionDao.batchInsert(optionList);
    }

    @Override
    public void batchInsert(List<Option> optionList) {
        optionDao.batchInsert(optionList);
    }

    @Override
    public void batchUpdate(List<Option> optionList) {
        optionDao.batchUpdate(optionList);
    }

    @Override
    public void deleteByQuestionIds(List<Integer> questionIdList) {
        if (CollectionUtils.isNotEmpty(questionIdList)) {
            optionDao.deleteByQuestionIds(questionIdList);
        }
    }

    @Override
    public void deleteByOptionIds(List<Integer> optionIdList) {
        if (CollectionUtils.isNotEmpty(optionIdList)) {
            optionDao.deleteByOptionIds(optionIdList);
        }
    }
}
