package com.eij.wenjuan.component.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-13
 */
public class TimeUtils {

    public static final String DAY_SHORT_FORMAT = "yyyy-MM-dd";

    public static final Long DAY_SECONDS = 60 * 60 * 24L;

    /**
     * 获取当前时间戳
     */
    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获得时间戳
     */
    public static long getTimeStamp(String dateStr, String fmt) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(fmt);
        Date date = format.parse(dateStr);
        return date.getTime();
    }
    /**
     *
     */

    /**
     * 日期转时间戳
     */
    public static long str2timestamp(String dateStr, String dateFormat) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date date = format.parse(dateStr);
        long timestamp = date.getTime() / 1000;
        return timestamp;
    }

    /**
     * 时间戳转日期
     */
    public static String timestamp2str(long timestamp, String dateFormat) throws ParseException {
        Date date = new Date(timestamp * 1000);
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    /**
     * 获取月第一天
     */
    public static long getMonthFirstDay(long timestamp) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        if (timestamp != 0) {
            calendar.setTimeInMillis(new Date(timestamp).getTime());
        }
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取周第一天
     */
    public static long getWeekFirstDay(long timestamp) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK);
        int current = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_WEEK, min - current + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }
}
