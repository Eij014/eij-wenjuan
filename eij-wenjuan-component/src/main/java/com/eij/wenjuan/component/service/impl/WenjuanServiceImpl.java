package com.eij.wenjuan.component.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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

    private static final List<String> INIT_IMG_URL = new ArrayList<String>() {{
        add("http://eij.ink:27546/image/7ea4762c14310f93dcd44c68a30f080c.png");
        add("http://eij.ink:27546/image/c8fe02d6f4cdacd539615740348388f7.png");
        add("http://eij.ink:27546/image/42cdfd3a1a1c0b77f0e4fea29dc1a13a.png");
        add("http://eij.ink:27546/image/d1b7fc8f867405c5c521c4bf7452bfdb.png");
        add("http://eij.ink:27546/image/39020dd4e99dd0c77bbc986b53d51421.png");
        add("http://eij.ink:27546/image/79be98c2b0b451d41dc14e5bfc805c81.png");
        add("http://eij.ink:27546/image/e27cc040deae3094af367d13ed095835.png");
        add("http://eij.ink:27546/image/90c7d3919ec658e7e8f6d7bae371838c.png");
        add("http://eij.ink:27546/image/e5d5f2e2cade9f322c4f5f86e41c87e7.png");
        add("http://eij.ink:27546/image/eabee4b65b83bde7053db8c6d2918b1a.png");
        add("http://eij.ink:27546/image/6a9b68cc5119d985c863d1762a1c6a53.png");
    }};

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
            QuestionVO questionVO = new QuestionVO();
            questionVO.setQuestionId(o.getQuestionId());
            questionVO.setWenjuanId(o.getWenjuanId());
            questionVO.setTitle(o.getTitle());
            questionVO.setImgUrls(o.getImgUrls());
            questionVO.setMust(o.getMust());
            questionVO.setType(o.getType());
            questionVO.setQuestionIndex(o.getQuestionIndex());
            questionVO.setOptionList(questionIdOptionList.get(o.getQuestionId()));
            questionVOList.add(questionVO);
        });

        wenjuanDetailVO.setQuestionVOList(questionVOList);
        return wenjuanDetailVO;
    }

    @Override
    public void createOrUpdateWenjuan(int wenjuanId, String imgUrl, String wenjuanTitle, String welcomeMsg, List<QuestionVO> questionVOList) {
        String userName = LoginUserContext.getUserName();
        if (wenjuanId != 0) {

            //更新问卷
            wenjuanDao.updateWenjuan(wenjuanId, wenjuanTitle, welcomeMsg);
            //问卷下原问题
            List<Integer> questionOldList = questionService.getByWenjuanId(wenjuanId)
                    .stream()
                    .map(Question::getQuestionId)
                    .collect(Collectors.toList());
            //删除问题
            List<Integer> questionIdList = questionVOList.stream()
                    .map(Question::getQuestionId)
                    .collect(Collectors.toList());
            questionOldList.removeAll(questionIdList);
            questionService.deleteByIds(questionOldList);
            optionService.deleteByQuestionIds(questionOldList);

            //更新问题
            List<QuestionVO> questionNeedUpdateList = questionVOList
                    .stream()
                    .filter(o -> o.getQuestionId() != 0)
                    .collect(Collectors.toList());
            updateQuestion(wenjuanId, questionNeedUpdateList);
            //新增问题
            List<QuestionVO> questionNeedCreateList = questionVOList
                    .stream()
                    .filter(o -> o.getQuestionId() == 0)
                    .collect(Collectors.toList());
            createQuestion(wenjuanId, questionNeedCreateList);
        } else {
            wenjuanId = wenjuanDao.
                        insert(new Wenjuan(wenjuanTitle, welcomeMsg, userName, 0, 0, imgUrl));
            createQuestion(wenjuanId, questionVOList);
        }
    }

    @Override
    public String getInitImgUrs() {
        return INIT_IMG_URL.get(new Random().nextInt(11));
    }

    @Override
    public int publish(int wenjuanId) {
        return wenjuanDao.updateStatus(wenjuanId, 1);
    }

    private void updateQuestion(int wenjuanId, List<QuestionVO> questionVOList) {
            List<Option> optionList = Lists.newArrayList();
            questionVOList.forEach(o -> {
                optionList.addAll(o.getOptionList());
            });
            List<Question> questionList = questionVOList.stream()
                    .map(o -> {
                        return new Question(o);
                    }).collect(Collectors.toList());
            Map<Integer, List<Option>> questionIdOptionMap = questionVOList
                    .stream()
                    .collect(Collectors.toMap(QuestionVO::getQuestionId, QuestionVO::getOptionList));
            //更新问题
            questionService.updateQuestion(questionList);
            //删除选项
            List<Integer> optionIdOldList = optionService.getOptionByQuestionIds(questionVOList
                    .stream().map(Question::getQuestionId).collect(Collectors.toList()))
                    .stream().map(Option::getOptionId).collect(Collectors.toList());
            List<Integer> optionIdList = optionList.stream().map(Option::getOptionId).collect(Collectors.toList());
            optionIdOldList.removeAll(optionIdList);
            optionService.deleteByOptionIds(optionIdOldList);

            //更新选项
            List<Option> optionListNeedUpdate = optionList
                    .stream()
                    .filter(o -> o.getOptionId() != 0)
                    .collect(Collectors.toList());
            optionService.batchUpdate(optionListNeedUpdate);
            //新增选项
            List<Option> optionListNeedCreate = optionList
                .stream()
                .filter(o -> o.getOptionId() == 0)
                .collect(Collectors.toList());
            optionService.batchInsertByMap(questionIdOptionMap);

    }

    private void createQuestion(int wenjuanId, List<QuestionVO> questionNeedCreateList) {
        questionNeedCreateList
                .stream()
                .map(questionVO -> {
                    questionVO.setWenjuanId(wenjuanId);
                    int questionId = questionService.insert(new Question(questionVO));
                    optionService.batchInsert(questionVO.getOptionList().stream()
                            .map(o -> {
                                o.setQuestionId(questionId);
                                return o;
                            }).collect(Collectors.toList()));
                    return null;
                }).collect(Collectors.toList());
    }
}

