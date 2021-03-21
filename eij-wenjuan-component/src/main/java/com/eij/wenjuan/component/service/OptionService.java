package com.eij.wenjuan.component.service;

import java.util.List;

import com.eij.wenjuan.component.bean.entity.Option;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-16
 */
public interface OptionService {
    List<Option> getOptionByQuestionIds(List<Integer> questionIdList);
}
