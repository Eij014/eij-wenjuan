package com.eij.wenjuan.component.service;

import java.util.List;
import java.util.Map;

import com.eij.wenjuan.component.bean.entity.Option;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-16
 */
public interface OptionService {
    List<Option> getOptionByQuestionIds(List<Integer> questionIdList);

    void batchInsertByMap(Map<Integer, List<Option>> questionIdOptionMap);

    void batchInsert(List<Option> optionList);

    void batchUpdate(List<Option> optionList);

    void deleteByQuestionIds(List<Integer> questionIdList);

    void deleteByOptionIds(List<Integer> optionIdList);
}
