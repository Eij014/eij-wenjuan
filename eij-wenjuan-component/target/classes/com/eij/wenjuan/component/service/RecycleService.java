package com.eij.wenjuan.component.service;

import java.util.List;

import com.eij.wenjuan.component.bean.VO.RecycleVO;
import com.eij.wenjuan.component.bean.entity.Recycle;
import com.eij.wenjuan.component.bean.result.RecycleProcessResponse;

/**
 * @author Eij<eij00014 @ gmail.com>
 * Created on 2021-04-13
 */
public interface RecycleService {
    int insert(Recycle recycle);

    List<RecycleVO> getByWenjuanId(int wenjuanId);

    RecycleProcessResponse getWenjuanRecycleProcess(int wenjuanId);
}
