package com.eij.wenjuan.component.service.impl;

import static com.eij.wenjuan.component.utils.TimeUtils.DAY_SECONDS;
import static com.eij.wenjuan.component.utils.TimeUtils.DAY_SHORT_FORMAT;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eij.wenjuan.component.bean.Data;
import com.eij.wenjuan.component.bean.VO.RecycleVO;
import com.eij.wenjuan.component.bean.entity.Recycle;
import com.eij.wenjuan.component.bean.result.ChinaMapData;
import com.eij.wenjuan.component.bean.result.City;
import com.eij.wenjuan.component.bean.result.EchartsBarOption;
import com.eij.wenjuan.component.bean.result.EchartsOptionHover;
import com.eij.wenjuan.component.bean.result.EchartsSeries;
import com.eij.wenjuan.component.bean.result.OptionTitle;
import com.eij.wenjuan.component.bean.result.RecycleProcessResponse;
import com.eij.wenjuan.component.dao.RecycleDao;
import com.eij.wenjuan.component.service.RecycleService;
import com.eij.wenjuan.component.service.WenjuanBrowseService;
import com.eij.wenjuan.component.utils.TimeUtils;
import com.google.common.collect.Lists;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-13
 */
@Service
public class RecycleServiceImpl implements RecycleService {

    private static final Logger logger = LoggerFactory.getLogger(RecycleServiceImpl.class);

    private static final List<String> PROVINCE_CAPITAL = new ArrayList<String>() {{
        add("北京");
        add("上海");
        add("天津");
        add("重庆");
        add("石家庄");
        add("太原");
        add("呼和浩特");
        add("沈阳");
        add("长春");
        add("哈尔滨");
        add("南京");
        add("杭州");
        add("合肥");
        add("福州");
        add("济南");
        add("南昌");
        add("郑州");
        add("乌鲁木齐");
        add("武汉");
        add("长沙");
        add("广州");
        add("南宁");
        add("海口");
        add("成都");
        add("贵阳");
        add("昆明");
        add("拉萨");
        add("西安");
        add("西宁");
        add("兰州");
        add("银川");
    }};

    //直辖市、特别行政区、台湾省不展示city
    private static final List<String> CAPITAL_SPECIAL = new ArrayList<String>() {{
        add("北京");
        add("上海");
        add("天津");
        add("重庆");
        add("香港");
        add("澳门");
        add("台湾");
    }};

    @Autowired
    private RecycleDao recycleDao;

    @Autowired
    private WenjuanBrowseService wenjuanBrowseService;

    @Override
    public int insert(Recycle recycle) {
        return recycleDao.insert(recycle);
    }

    @Override
    public List<RecycleVO> getByWenjuanId(int wenjuanId) {
        return recycleDao.selectByWenjuanId(wenjuanId);
    }

    @Override
    public List<RecycleVO> getByWenjuanIds(List<Integer> wenjuanIdList) {
        if (CollectionUtils.isEmpty(wenjuanIdList)) {
            return Lists.newArrayList();
        }
        return recycleDao.selectByWenjuanIds(wenjuanIdList);
    }

    @Override
    public RecycleProcessResponse getWenjuanRecycleProcess(int wenjuanId) {
        List<RecycleVO> recycleVOList = getByWenjuanId(wenjuanId);
        Map<String, Map<String, List<Recycle>>> resultMap = recycleVOList.stream()
                .collect(Collectors.groupingBy(Recycle::getProvince, Collectors.groupingBy(Recycle::getCity)));
        Map<String, List<RecycleVO>> timeMap = recycleVOList.stream()
                .collect(Collectors.groupingBy(RecycleVO::getRecycleTime))
                //根据时间排序
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
        //回收曲线图
        EchartsBarOption echartsBarOption = new EchartsBarOption();
        echartsBarOption.setTitle(new OptionTitle("回收情况", "", "center", "top"));
        echartsBarOption.setyAxis(new Data());
        echartsBarOption.setxAxis(new Data(Lists.newArrayList(timeMap.keySet())));
        echartsBarOption.setLegend(new Data());
        echartsBarOption.setTooltip(new EchartsOptionHover("axis", null));
        echartsBarOption.setColor(Lists.newArrayList("#7FFF00"));
        List<Integer> data = Lists.newArrayList();
        timeMap.forEach((time, list) -> {
            data.add(list.size());
        });
        echartsBarOption.setSeries(Lists.newArrayList(new EchartsSeries("", "line", 30, data)));
        //地图
        List<ChinaMapData> chinaMapDataList = Lists.newArrayList();
        resultMap.forEach((province, cityMap) -> {
            ChinaMapData chinaMapData = new ChinaMapData();
            //省份
            chinaMapData.setName(province);
            //省份回收个数为下属城市回收个数之和
            AtomicInteger value = new AtomicInteger();
            List<City> cityList = Lists.newArrayList();
            cityMap.forEach((city, list) -> {
                cityList.add(new City(city, list.size()));
                value.addAndGet(list.size());
            });
            cityList.sort(Comparator.comparing(o -> {
                if (PROVINCE_CAPITAL.contains(o.getCityName())) {
                    return 0;
                } else {
                    return 1;
                }
            }));
            chinaMapData.setValue(value.get());
            chinaMapData.setCityList(CAPITAL_SPECIAL.contains(province)
                    ? Lists.newArrayList()
                    : cityList);
            chinaMapDataList.add(chinaMapData);
        });
        //回收曝光量
        int wenjuanRecycleBrowse = wenjuanBrowseService.getWenjuanBrowseCount(wenjuanId);
        //回收量
        int recycleCount = recycleVOList.size();
        //回收率
        double recoveryRate = recycleCount == 0
                ? 0.0
                : ((double) recycleCount / wenjuanRecycleBrowse) * 100;
        RecycleProcessResponse recycleProcessResponse =  new RecycleProcessResponse(chinaMapDataList, Lists.newArrayList(echartsBarOption),
                                          wenjuanRecycleBrowse, recycleCount, (int) Math.floor(recoveryRate));
        return getRecycleRadio(recycleVOList, recycleProcessResponse);
    }

    /**
     * 环比
     */

    private RecycleProcessResponse getRecycleRadio(List<RecycleVO> recycleVOList, RecycleProcessResponse recycleProcessResponse) {
        try {
            //日环比 (今日收集 - 昨日收集) / 昨日收集
            //1.今日收集量
            int todayRecycleCount = recycleVOList.stream().filter(o -> {
                try {
                    return o.getRecycleTime().equals(TimeUtils.timestamp2str(TimeUtils.getTimestamp(), DAY_SHORT_FORMAT));
                } catch (ParseException e) {
                    return false;
                }
            }).collect(Collectors.toList()).size();
            //2.昨日收集量

            int yesterdayRecycleCount = recycleVOList.stream().filter(o -> {
                try {
                    return o.getRecycleTime().equals(TimeUtils.timestamp2str(
                            TimeUtils.getTimestamp() - DAY_SECONDS, DAY_SHORT_FORMAT));
                } catch (ParseException e) {
                    return false;
                }
            }).collect(Collectors.toList()).size();
            double dayRatio = yesterdayRecycleCount == 0
                    ? 100
                    : (((double) (todayRecycleCount - yesterdayRecycleCount)) / yesterdayRecycleCount) * 100;
            //周环比 (本周收集 - 上周收集) / 上周收集
            //1.本周第一天、上周第一天
            long thisWeekFirstDay = TimeUtils.getWeekFirstDay(System.currentTimeMillis());
            long preWeekFirstDay = thisWeekFirstDay - 7 * DAY_SECONDS;
            //2.本周收集量
            int thisWeekRecycleCount = recycleVOList.stream().filter(o -> {
                return o.getCreateTime() >= thisWeekFirstDay;
            }).collect(Collectors.toList()).size();
            //3.上周收集量
            int preWeekRecycleCount = recycleVOList.stream().filter(o -> {
                return o.getCreateTime() >= preWeekFirstDay
                        && o.getCreateTime() < thisWeekFirstDay;
            }).collect(Collectors.toList()).size();
            double weekRatio = preWeekRecycleCount == 0
                    ? 100
                    : (((double) (thisWeekRecycleCount - preWeekRecycleCount)) / preWeekRecycleCount) * 100;
            //月环比 (本月收集 - 上月收集) / 上月收集
            //1.本月第一天、上月第一天
            long firstDayOfMonth = TimeUtils.getMonthFirstDay(System.currentTimeMillis());
            long firstDayOfPreMonth = TimeUtils.getMonthFirstDay(firstDayOfMonth * 1000 - 1);
            //2.本月收集量
            int thisMonthRecycleCount = recycleVOList.stream().filter(o -> {
                return o.getCreateTime() >= firstDayOfMonth;
            }).collect(Collectors.toList()).size();
            //3.上月收集量
            int preMonthRecycleCount = recycleVOList.stream().filter(o -> {
                return o.getCreateTime() >= firstDayOfPreMonth
                        && o.getCreateTime() < firstDayOfMonth;
            }).collect(Collectors.toList()).size();
            double monthRatio = preMonthRecycleCount == 0
                    ? 100
                    : (((double) (thisMonthRecycleCount - preMonthRecycleCount)) / preMonthRecycleCount) * 100;

            recycleProcessResponse.setDayRatio((int) Math.floor(dayRatio));
            recycleProcessResponse.setWeekRatio((int) Math.floor(weekRatio));
            recycleProcessResponse.setMonthRatio((int) Math.floor(monthRatio));
            return recycleProcessResponse;
        } catch (ParseException e) {
            logger.error("time parse error", e.getMessage());
            return recycleProcessResponse;
        }
    }
}
