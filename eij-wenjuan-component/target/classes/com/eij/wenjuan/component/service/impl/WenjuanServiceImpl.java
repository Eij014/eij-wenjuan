package com.eij.wenjuan.component.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.checkerframework.checker.nullness.Opt;
import org.checkerframework.checker.units.qual.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.VO.QuestionVO;
import com.eij.wenjuan.component.bean.VO.WenjuanDetailVO;
import com.eij.wenjuan.component.bean.VO.WenjuanVO;
import com.eij.wenjuan.component.bean.entity.Option;
import com.eij.wenjuan.component.bean.entity.Question;
import com.eij.wenjuan.component.bean.entity.Wenjuan;
import com.eij.wenjuan.component.bean.sys.SearchPaging;
import com.eij.wenjuan.component.dao.WenjuanDao;
import com.eij.wenjuan.component.service.OptionService;
import com.eij.wenjuan.component.service.QuestionService;
import com.eij.wenjuan.component.service.WenjuanService;
import com.eij.wenjuan.component.utils.web.LoginUserContext;
import com.google.common.collect.Lists;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-03-02
 */
@Service
public class WenjuanServiceImpl implements WenjuanService {

    private static final Logger logger = LoggerFactory.getLogger(WenjuanServiceImpl.class);

    @Autowired
    private WenjuanDao wenjuanDao;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionService optionService;

    @Override
    public WenjuanVO getWenjuanList(SearchPaging searchPaging) {
        String userName = LoginUserContext.getUserName();
        WenjuanVO wenjuanVO = new WenjuanVO();

        wenjuanVO.setCurrentPage(searchPaging.getCurrentPage());
        int limit = searchPaging.getPageSize();
        int offset = (searchPaging.getCurrentPage() - 1) * searchPaging.getPageSize();
        int total = wenjuanDao.selectTotalByUserName(userName);
        List<Wenjuan> wenjuanList
                = wenjuanDao.selectByUserName(userName, limit, offset);
        wenjuanVO.setWenjuanList(wenjuanList);
        wenjuanVO.setTotal(total);
        wenjuanVO.setPageSize(wenjuanList.size());
        int totalPage = (total % searchPaging.getPageSize()) == 0
                ? total / searchPaging.getPageSize()
                : total / searchPaging.getPageSize() + 1;
        wenjuanVO.setTotalPage(totalPage);
        return wenjuanVO;
    }
    @Override
    public WenjuanDetailVO getWenjuanDetail(int wenjuanId) {

        Wenjuan wenjuan = wenjuanDao.selectByWenjuanId(wenjuanId);
        WenjuanDetailVO wenjuanDetailVO = new WenjuanDetailVO(wenjuan);
        List<Question> questionList = questionService.getByWenjuanId(wenjuanId);
        //questionId <-> options
        Map<Integer, List<Option>> questionIdOptionList
                = optionService.getOptionByQuestionIds(questionList
                .stream().map(Question::getQuestionId).collect(Collectors.toList()))
                .stream().collect(Collectors.groupingBy(Option::getQuestionId));
        //question view object
        List<QuestionVO> questionVOList = Lists.newArrayList();
        questionList.forEach(o -> {
            questionVOList.add(new QuestionVO(questionIdOptionList.get(o.getQuestionId())));
        });

        wenjuanDetailVO.setSingleChoiceList(questionVOList);
        return wenjuanDetailVO;
    }
}

