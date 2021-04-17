package com.eij.wenjuan.component.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Eij<eij00014@gmail.com>
 * Created on 2021-04-13
 */
public class TimeUtils {

    /**
     * 获取当前时间戳
     */
    public static long getTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

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
}
