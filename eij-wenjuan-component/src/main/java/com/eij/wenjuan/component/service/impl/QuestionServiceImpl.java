package com.eij.wenjuan.component.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.entity.Question;
import com.eij.wenjuan.component.dao.QuestionDao;
import com.eij.wenjuan.component.service.QuestionService;

/**
 * @author Zhu Haojie<eij00014@gmail.com>
 * Created on 2021-03-14
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<Question> getByWenjuanId(int wenjuanId) {
        return questionDao.selectByWenjuanId(wenjuanId);
    }
}
