package com.eij.wenjuan.component.service;

import java.util.List;

import com.eij.wenjuan.component.bean.entity.Question;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-14
 */
public interface QuestionService {
    List<Question> getByWenjuanId(int wenjuanId);
}
