package com.eij.wenjuan.component.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.eij.wenjuan.component.bean.VO.QuestionVO;
import com.eij.wenjuan.component.bean.VO.WenjuanDetailVO;
import com.eij.wenjuan.component.bean.VO.WenjuanVO;
import com.eij.wenjuan.component.bean.sys.SearchPaging;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-02
 */
public interface WenjuanService {
    WenjuanVO getWenjuanList(SearchPaging searchPaging);

    WenjuanDetailVO getWenjuanDetail(int wenjuanId);

    void createOrUpdateWenjuan(int wenjuanId, String imgUrl, String wenjuanTitle, String welcomeMsg, List<QuestionVO> questionVOList);

    String getInitImgUrs();

    int publish(int wenjuanId);
}
