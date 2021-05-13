package com.eij.wenjuan.component.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.Data;
import com.eij.wenjuan.component.bean.NameValueBean;
import com.eij.wenjuan.component.bean.OpenApi.AmapResponse;
import com.eij.wenjuan.component.bean.VO.QuestionVO;
import com.eij.wenjuan.component.bean.VO.RecycleVO;
import com.eij.wenjuan.component.bean.VO.ResultVO;
import com.eij.wenjuan.component.bean.VO.WenjuanDetailVO;
import com.eij.wenjuan.component.bean.VO.WenjuanVO;
import com.eij.wenjuan.component.bean.VO.WenjuanVOList;
import com.eij.wenjuan.component.bean.entity.Option;
import com.eij.wenjuan.component.bean.entity.Question;
import com.eij.wenjuan.component.bean.entity.Recycle;
import com.eij.wenjuan.component.bean.entity.Result;
import com.eij.wenjuan.component.bean.entity.Wenjuan;
import com.eij.wenjuan.component.bean.entity.WenjuanFolderRelation;
import com.eij.wenjuan.component.bean.request.AnswerRequest;
import com.eij.wenjuan.component.bean.result.EchartsBarOption;
import com.eij.wenjuan.component.bean.result.EchartsOptionHover;
import com.eij.wenjuan.component.bean.result.EchartsPieOption;
import com.eij.wenjuan.component.bean.result.EchartsPieSeries;
import com.eij.wenjuan.component.bean.result.EchartsSeries;
import com.eij.wenjuan.component.bean.result.OptionTitle;
import com.eij.wenjuan.component.bean.result.WenjuanRequest;
import com.eij.wenjuan.component.bean.result.WenjuanResult;
import com.eij.wenjuan.component.contants.QuestionType;
import com.eij.wenjuan.component.contants.WenjuanStatus;
import com.eij.wenjuan.component.contants.WenjuanType;
import com.eij.wenjuan.component.dao.WenjuanDao;
import com.eij.wenjuan.component.service.OptionService;
import com.eij.wenjuan.component.service.QuestionService;
import com.eij.wenjuan.component.service.RecycleService;
import com.eij.wenjuan.component.service.ResultService;
import com.eij.wenjuan.component.service.WenjuanBrowseService;
import com.eij.wenjuan.component.service.WenjuanFolderRelationService;
import com.eij.wenjuan.component.service.WenjuanService;
import com.eij.wenjuan.component.utils.ObjectMapperUtils;
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

    private static final List<String> COLOR_LIST = new ArrayList<String>() {{
       add("#7FFF00");
       add("#FFFF00");
       add("#FF6347");
       add("#7B68EE");
       add("#00CED1");
       add("#696969");
       add("#483D8B");
       add("#008080");
       add("#FFFFE0");
       add("#FFE4B5");
    }};

    private static final List<String> IP_TEST_LIST = new ArrayList<String>() {{
        add("192.167.127.1");
        add("192.167.127.2");
        add("192.167.127.3");
        add("192.167.127.4");
        add("192.167.127.5");
        add("192.167.127.6");
        add("192.167.127.7");
        add("192.167.127.8");
        add("192.167.127.9");
        add("192.167.127.10");
    }};

    @Autowired
    private WenjuanDao wenjuanDao;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionService optionService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private RecycleService recycleService;

    @Autowired
    private WenjuanBrowseService wenjuanBrowseService;

    @Autowired
    private WenjuanFolderRelationService wenjuanFolderRelationService;

    @Override
    public WenjuanVOList getWenjuanList(WenjuanRequest wenjuanRequest) {
        String userName = LoginUserContext.getUserName();
        WenjuanVOList wenjuanVOList = new WenjuanVOList();
        List<Integer> wenjuanIdByCondition = getWenjuanIdsByCondition(userName, wenjuanRequest.getType(), wenjuanRequest.getStatus(),
                wenjuanRequest.getFolderId(), wenjuanRequest.getKeywords());
        wenjuanVOList.setCurrentPage(wenjuanRequest.getCurrentPage());
        int limit = wenjuanRequest.getPageSize();
        int offset = (wenjuanRequest.getCurrentPage() - 1) * wenjuanRequest.getPageSize();
        int total = wenjuanDao.selectTotalByUserName(wenjuanIdByCondition);
        List<Wenjuan> wenjuanList
                = wenjuanDao.selectByUserName(wenjuanIdByCondition, limit, offset);
        List<RecycleVO> recycleVOList =
        recycleService.getByWenjuanIds(wenjuanList.stream().map(Wenjuan::getWenjuanId).collect(Collectors.toList()));
        Map<Integer, List<RecycleVO>> wenjuanRecycleMap = recycleVOList
                .stream()
                .collect(Collectors.groupingBy(Recycle::getWenjuanId));
        wenjuanVOList.setWenjuanList(wenjuanList.stream().map(o -> {
            int wenjuanRecycleCount = wenjuanRecycleMap.containsKey(o.getWenjuanId())
                    ? wenjuanRecycleMap.get(o.getWenjuanId()).size()
                    : 0;
            return new WenjuanVO(o, wenjuanRecycleCount);
        }).collect(Collectors.toList()));
        wenjuanVOList.setTotal(total);
        wenjuanVOList.setPageSize(wenjuanList.size());
        int totalPage = (total % wenjuanRequest.getPageSize()) == 0
                ? total / wenjuanRequest.getPageSize()
                : total / wenjuanRequest.getPageSize() + 1;
        wenjuanVOList.setTotalPage(totalPage);
        return wenjuanVOList;
    }
    @Override
    public WenjuanDetailVO getWenjuanDetail(int wenjuanId, String type) {

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
        //回收曝光
        if (type.equals("recycle")) {
            wenjuanBrowseService.insert(wenjuanId);
        }
        return wenjuanDetailVO;
    }

    @Override
    public int createOrUpdateWenjuan(int wenjuanId, String imgUrl, String wenjuanTitle, int folderId,
                                      String type, String welcomeMsg, List<QuestionVO> questionVOList) {
        String userName = LoginUserContext.getUserName();
        //question和option设置index
        AtomicInteger questionIndex = new AtomicInteger(0);
        AtomicInteger optionIndex = new AtomicInteger();
        questionVOList.forEach(questionVO -> {
            questionVO.setQuestionIndex(questionIndex.getAndIncrement());
            if (CollectionUtils.isNotEmpty(questionVO.getOptionList())) {
                questionVO.getOptionList().forEach(option -> {
                    option.setOptionIndex(optionIndex.getAndIncrement());
                });
            }
        });
        if (wenjuanId != 0) {

            //更新问卷
            wenjuanDao.updateWenjuan(wenjuanId, imgUrl, wenjuanTitle, welcomeMsg);
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

            return wenjuanId;
        } else {
            wenjuanId = wenjuanDao.
                        insert(new Wenjuan(wenjuanTitle, welcomeMsg, userName, WenjuanType.parse(type), 0, imgUrl));
            createQuestion(wenjuanId, questionVOList);
            //问卷和文件夹关联
            if (folderId != 0) {
                wenjuanFolderRelationService.insert(new WenjuanFolderRelation(wenjuanId, folderId));
            }
            return wenjuanId;
        }
    }

    @Override
    public int deleteByWenjuanId(int wenjuanId) {
        wenjuanFolderRelationService.delete(0, wenjuanId);
        List<Integer> questionIdList = questionService.getByWenjuanId(wenjuanId)
                .stream().map(Question::getQuestionId)
                .collect(Collectors.toList());
        optionService.deleteByQuestionIds(questionIdList);
        questionService.deleteByIds(questionIdList);
        return wenjuanDao.delete(wenjuanId);
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
                if (CollectionUtils.isNotEmpty(o.getOptionList())) {
                    optionList.addAll(o.getOptionList());
                } else {
                    o.setOptionList(Lists.newArrayList());
                }
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

    public static final List<String> TEST_CITY = new ArrayList<String>() {{
        add("乌鲁木齐");
        add("深圳");
        add("佛山");
        add("珠海");
    }};

    @Override
    public int answer(int wenjuanId, List<AnswerRequest> answerRequestList) {
        List<Result> resultList = Lists.newArrayList();
        String ip = LoginUserContext.getUserContext().getIp();
        String uuip = IP_TEST_LIST.get(new Random().nextInt(10)) + System.currentTimeMillis();
//        AmapResponse  amapResponse = getAddress(ip);
        AmapResponse amapResponse = new AmapResponse();
        amapResponse.setProvince("新疆");
        amapResponse.setCity(TEST_CITY.get(new Random().nextInt(4)));
        String province = StringUtils.isNotEmpty(amapResponse.getProvince())
                ? amapResponse.getProvince().replace("市", "").replace("省", "")
                  .replace("维吾尔族自治区", "").replace("壮族自治区", "")
                  .replace("回族自治区", "").replace("自治区", "")
                  .replace("特别行政区", "")
                : "";
        String city = StringUtils.isNotEmpty(amapResponse.getCity())
                ? amapResponse.getCity()
                : "";
        //按问题类型分类
        Map<String, List<AnswerRequest>> answerMap = answerRequestList
                .stream()
                .collect(Collectors.groupingBy(AnswerRequest::getType));

        answerMap.entrySet().stream().forEach(o -> {
            QuestionType questionType = QuestionType.parse(o.getKey());
            switch (questionType) {
                case SINGLE_CHOICE:
                case PICTURE:
                case VIDEO:
                    o.getValue().forEach(answer -> {
                        resultList.add(new Result(wenjuanId, answer.getQuestionId(),
                                answer.getOptionId(), "", questionType.getNameCamel(), province, city, uuip));
                    });
                    break;
                case MULTIPLE_CHOICE:
                    o.getValue().forEach(answer -> {
                        resultList.addAll(answer.getOptionIdList().stream().map(optionId -> {
                                return new Result(wenjuanId, answer.getQuestionId(),
                                        optionId, "", questionType.getNameCamel(), province, city, uuip);
                        }).collect(Collectors.toList()));
                    });
                    break;
                case INPUT:
                case SCORE:
                    o.getValue().forEach(answer -> {
                        resultList.add(new Result(wenjuanId, answer.getQuestionId(),
                                0, answer.getText(), questionType.getNameCamel(), province, city, uuip));
                    });
                    break;
                default:
                    break;
            }
        });

        recycleService.insert(new Recycle(wenjuanId, province, city));
        resultService.batchInsert(resultList);
        return resultList.size();
    }
    @Override
    public AmapResponse getAddress(String ip) {
        String key = "99737f308b9b2d3059ba915c757b8264";
        String url = String.format("https://restapi.amap.com/v3/ip?key=%s&ip=%s", key, ip);
        try {
            InputStream is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            AmapResponse amapResponse = ObjectMapperUtils.fromJson(jsonText, AmapResponse.class);
            return amapResponse;
        } catch (IOException e) {
            logger.info("get ip address error.ip={}\nerror={}", ip, e.getMessage());
        }
        return  null;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    @Override
    public WenjuanResult getWenjuanResult(int wenjuanId) {
        List<EchartsBarOption> result = Lists.newArrayList();
        List<EchartsPieOption> pieResult = Lists.newArrayList();
        Map<Integer, String> questionNameMap = questionService.getByWenjuanId(wenjuanId)
                .stream()
                .collect(Collectors.toMap(Question::getQuestionId, Question::getTitle));
        Map<Integer, Map<Integer, String>> optionNameMap =
                optionService.getOptionByQuestionIds(Lists.newArrayList(questionNameMap.keySet()))
                .stream()
                .collect(Collectors.groupingBy(Option::getQuestionId,
                         Collectors.toMap(Option::getOptionId, Option::getOptionName)));
        //现根据questionId，再根据optionId group by,List<ResultVO>的数量为每个选项的数量
        Map<Integer, Map<Integer, List<ResultVO>>> questionIdMap = resultService.getByWenjuanId(wenjuanId)
                .stream()
                .collect(Collectors.groupingBy(ResultVO::getQuestionId,
                         Collectors.groupingBy(ResultVO::getOptionId)));
        questionIdMap.entrySet().stream().forEach(question -> {
            EchartsBarOption echartsBarOption = new EchartsBarOption();
            echartsBarOption.setQuestionId(question.getKey());
            echartsBarOption.setQuestionName(questionNameMap.get(question.getKey()));


            if (optionNameMap.containsKey(question.getKey())) {
                List<String> optionData = optionNameMap.containsKey(question.getKey())
                        ? Lists.newArrayList(optionNameMap.get(question.getKey()).values())
                        : Lists.newArrayList();
                echartsBarOption.setxAxis(new Data(optionData));
                //柱状图、折线图数据
                List<Integer> yData = Lists.newArrayList();
                //饼状图数据
                List<NameValueBean> pieData = Lists.newArrayList();
                //图例
                List<String> legendData = Lists.newArrayList();
                //总结果，用于计算饼图百分比
                AtomicInteger total = new AtomicInteger();
                //饼状图colorList
                List<String> pieColor = Lists.newArrayList();
                optionNameMap.get(question.getKey()).forEach((optionId, optionName) -> {
                    int data = question.getValue().containsKey(optionId)
                            ? question.getValue().get(optionId).size()
                            : 0;
                    total.addAndGet(data);
                    yData.add(data);
                    pieData.add(new NameValueBean(optionName, data));
                    pieColor.add(COLOR_LIST.get(pieData.size() % COLOR_LIST.size()));
                    legendData.add(optionName);
                });
                //柱状图、折线无图例
                echartsBarOption.setLegend(new Data());
                //y坐标也不设置
                echartsBarOption.setyAxis(new Data());
                //标题
                echartsBarOption.setTitle(new OptionTitle(questionNameMap.get(question.getKey()), "", "center", "top"));
                //hover效果
                echartsBarOption.setTooltip(new EchartsOptionHover("axis", null));
                //柱状图、折线图颜色
                echartsBarOption.setColor(Lists.newArrayList(COLOR_LIST.get(0)));

                pieData.forEach(o -> {
                    double percent = ((double) o.getValue() / total.get()) * 100;
                    String percentStr = Math.floor(percent) + "%";
                    o.setName(String.format("%s:(%s)", o.getName(), percentStr));

                });
                echartsBarOption.setSeries(Lists.newArrayList(new EchartsSeries("", "bar", 30, yData)
                ));
                echartsBarOption.setFormType("bar");
                if (CollectionUtils.isNotEmpty(echartsBarOption.getxAxis().getData())) {
                    result.add(echartsBarOption);
                    pieResult.add(new EchartsPieOption(echartsBarOption.getQuestionId(), echartsBarOption.getQuestionName(), "pie",
                            echartsBarOption.getTitle(), new Data(legendData),
                            Lists.newArrayList(new EchartsPieSeries("", "pie", pieData)),
                            new EchartsOptionHover("item", "{b}:{c}"),
                            pieColor));
                }
            }
        });
        return new WenjuanResult(result, pieResult);
    }

    @Override
    public int quoteTemplate(int wenjuanId) {
        Wenjuan wenjuan = wenjuanDao.selectByWenjuanId(wenjuanId);
        List<Question> questionList = questionService.getByWenjuanId(wenjuanId);
        Map<Integer, List<Option>> optionMap = optionService.getOptionByQuestionIds(
                questionList.stream().map(Question::getQuestionId).collect(Collectors.toList())
        ).stream()
         .collect(Collectors.groupingBy(Option::getQuestionId));
        List<QuestionVO> questionVOList = questionList.stream().map(o -> {
            QuestionVO questionVO = new QuestionVO(0, o.getType(), o.getMust(), o.getTitle(), o.getImgUrls(),
                    o.getQuestionIndex(), optionMap.get(o.getQuestionId())
                                          .stream().map(option -> {
                                              option.setOptionId(0);
                                              return option;
                    }).collect(Collectors.toList()));
            return questionVO;
        }).collect(Collectors.toList());
        return createOrUpdateWenjuan(0, wenjuan.getImgUrl(), wenjuan.getWenjuanTitle() + "的副本",
                0, WenjuanType.SELF.getTypeEn(), wenjuan.getWelcomeMsg(), questionVOList);
    }

    private List<Integer> getWenjuanIdsByCondition(String username, String type, String status, int folderId, String keywords) {
        return wenjuanDao.selectIdsByCondition(username, type, WenjuanStatus.parse(status), folderId, keywords);
    }
}

