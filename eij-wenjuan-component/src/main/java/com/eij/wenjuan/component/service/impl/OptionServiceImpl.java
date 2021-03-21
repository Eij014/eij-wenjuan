package com.eij.wenjuan.component.service.impl;

import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.entity.Option;
import com.eij.wenjuan.component.dao.OptionDao;
import com.eij.wenjuan.component.service.OptionService;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-16
 */
@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionDao optionDao;

    public List<Option> getOptionByQuestionIds(List<Integer> questionIdList) {
        return optionDao.selectByQuestionIds(questionIdList);
    }
}
