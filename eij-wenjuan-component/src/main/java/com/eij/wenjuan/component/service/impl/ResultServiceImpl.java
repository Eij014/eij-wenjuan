package com.eij.wenjuan.component.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.VO.ResultVO;
import com.eij.wenjuan.component.bean.entity.Result;
import com.eij.wenjuan.component.dao.ResultDao;
import com.eij.wenjuan.component.service.ResultService;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-09
 */
@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultDao resultDao;

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
}
