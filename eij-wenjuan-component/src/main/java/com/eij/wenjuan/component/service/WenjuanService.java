package com.eij.wenjuan.component.service;

import java.util.List;

import com.eij.wenjuan.component.bean.OpenApi.AmapResponse;
import com.eij.wenjuan.component.bean.VO.QuestionVO;
import com.eij.wenjuan.component.bean.VO.WenjuanDetailVO;
import com.eij.wenjuan.component.bean.VO.WenjuanVOList;
import com.eij.wenjuan.component.bean.request.AnswerRequest;
import com.eij.wenjuan.component.bean.result.WenjuanRequest;
import com.eij.wenjuan.component.bean.result.WenjuanResult;
/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-02
 */
public interface WenjuanService {
    WenjuanVOList getWenjuanList(WenjuanRequest wenjuanRequest);

    WenjuanDetailVO getWenjuanDetail(int wenjuanId, String type);

    int createOrUpdateWenjuan(int wenjuanId, String imgUrl, String wenjuanTitle, int folderId,
                               String type, String welcomeMsg, List<QuestionVO> questionVOList);

    int deleteByWenjuanId(int wenjuanId);

    String getInitImgUrs();

    int publish(int wenjuanId);

    int answer(int wenjuanId, List<AnswerRequest> answerRequestList);

    AmapResponse getAddress(String ip);

    WenjuanResult getWenjuanResult(int wenjuanId);

    int quoteTemplate(int wenjuanId);
}
