package com.os.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class DateUtil extends DateUtils {
    private static final Logger logger = LogManager.getLogger();

    private DateUtil(){}

    /**
     * 默认日期格式, yyyy-MM-dd
     */
    private static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 默认时间格式, yyyy-MM-dd hh24:mm:ss
     */
    private static String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取日期字符串
     *
     * @param date
     *            日期
     * @return yyyy-MM-dd格式, 中国时间({@link java.util.Locale}.PRC)
     */
    public static String date2Str(Date date) {
        // 使用默认日期格式
        return date2Str(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * 获取日期字符串
     *
     * @param date
     *            日期
     * @param pattern
     *            日期格式
     * @return 参数pattern指定日期格式, 中国时间({@link java.util.Locale}.PRC)
     */
    public static String date2Str(Date date, String pattern) {
        // 使用上海时间
        return date2Str(date, pattern, TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 获取日期字符串
     *
     * @param date
     *            日期
     * @param timeZone
     *            地区
     * @return yyyy-MM-dd格式, 参数locale指定地区的时间
     */
    public static String date2Str(Date date, TimeZone timeZone) {
        return date2Str(date, DEFAULT_DATE_PATTERN, timeZone);
    }

    /**
     * 获取日期字符串
     *
     * @param date
     *            日期
     * @param pattern
     *            格式
     * @param timeZone
     *            地区
     * @return pattern指定格式, locale指定区域的时间
     */
    public static String date2Str(Date date, String pattern, TimeZone timeZone) {

        String formatedDate = "";
        if (date != null) {
            DateFormat format = new SimpleDateFormat(pattern);
            format.setTimeZone(timeZone);
            formatedDate = format.format(date);
        }

        return formatedDate;
    }

    /**
     * 获取时间字符串
     *
     * @param date
     *            时间
     * @retrn yyyy-MM-dd hh24:mm:ss 格式时间, 中国地区({@link java.util.Locale}.PRC)
     */
    public static String time2Str(Date date) {
        return date2Str(date, DEFAULT_TIME_PATTERN);
    }

    /**
     * 获取时间字符串
     *
     * @param date
     *            时间
     * @param pattern
     *            时间格式
     * @retrn pattern指定格式时间, 中国地区({@link java.util.Locale}.PRC)
     */
    public static String time2Str(Date date, String pattern) {
        return date2Str(date, pattern, TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 获取时间字符串
     *
     * @param date
     *            时间
     * @param timeZone
     *            地区
     * @return yyyy-MM-dd hh24:mm:ss 格式时间, locale指定地区
     */
    public static String time2Str(Date date, TimeZone timeZone) {
        return date2Str(date, DEFAULT_TIME_PATTERN, timeZone);
    }

    /**
     * 获取时间字符串
     *
     * @param date
     *            时间
     * @param pattern
     *            时间格式
     * @param timeZone
     *            地区
     * @return yyyy-MM-dd hh24:mm:ss 格式时间, locale指定地区
     */
    public static String time2Str(Date date, String pattern, TimeZone timeZone) {
        return date2Str(date, pattern, timeZone);
    }

    /**
     *
     * @Function 获取一个月的第一天
     * @param date
     * @return
     * @author zj
     * @Date 2014年10月21日
     */
    public static Date getFirstDayOfMonth(Date date) {
        return addMonths(ceiling(date, Calendar.MONTH), -1);
    }

    /**
     *
     * @Function 获取当前时间
     * @return
     * @author zj
     * @Date 2014年10月21日
     */
    public static Date getCurrentDate() {
        return getCurrentDate(TimeZone.getDefault());
    }

    /**
     *
     * @Function 指定timezone,获取当前时间
     * @return
     * @author zj
     * @Date 2014年10月21日
     */
    public static Date getCurrentDate(TimeZone timezone) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(timezone);
        return cal.getTime();
    }

    /**
     * Describle(描述)：string字符串转换成Date
     *
     * 方法名称：parseDate
     *
     * 所在类名：DateUtil
     *
     * Create Time:2015年4月23日 下午10:18:38
     *
     * @param datestr
     * @return
     */
    public static Date parseDate(String datestr) {
        try {
            return parseDate(datestr, DEFAULT_DATE_PATTERN);
        } catch (ParseException pe) {
            logger.warn("Can not transfer date string {} to Date Type.",datestr,pe);
        }
        return null;
    }

    public static Date parseTime(String datestr) {
        try {
            return parseDate(datestr, DEFAULT_TIME_PATTERN);
        } catch (ParseException pe) {
            logger.warn("Can not transfer data time string {} to DateTime Type.",datestr,pe);
        }
        return null;
    }

    /**
     * 获取一天的最后时间
     *
     * @param date
     * @return
     */
    public static Date getEndTimeOfDay(Date date) {
        return getTimeOfDay(date,23,59,59,0);
    }

    public static Date getTimeOfDay(Date date,int hour,int min,int sec,int millisec){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, min);
        c.set(Calendar.SECOND, sec);
        c.set(Calendar.MILLISECOND, millisec);
        return c.getTime();
    }

    /**
     *
     * Describle(描述)：截取时分秒。
     * 例： 如果给定时间 2015-07-36 16:34:33 , 得到结果为 16:34:33
     *
     * 方法名称：getTimeStampOfDate
     *
     * 所在类名：DateUtil
     *
     * Create Time:2015年7月28日 下午4:34:27
     *
     * @param date
     * @return
     */
    public static String getTimeStampOfDate(Date date){
        return getFragmentInHours(date,Calendar.DATE)+":"+
                getFragmentInMinutes(date,Calendar.HOUR_OF_DAY)+":"+
                getFragmentInSeconds(date,Calendar.MINUTE);
    }

    /**
     * 获取一天的零点时间
     *
     * @param date
     * @return
     */
    public static Date getStartTimeOfDay(Date date) {
        return truncate(date, Calendar.DATE);
    }

    public static boolean isDateFormat(String dateStr,final String... parsePatterns){
        return parseFormat(dateStr,parsePatterns);
    }

    /**
     *
     * Describle(描述)：
     *
     * 方法名称：parseFormat 解析日期格式是否正确
     *
     * 所在类名：DateUtil
     *
     * Create Time:2015年4月24日 下午2:18:36
     *
     * @param str
     * @param parsePatterns
     * @return
     */
    private static boolean parseFormat(String str,String[] parsePatterns){
        boolean matched = false;
        if(str == null || parsePatterns == null){
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        SimpleDateFormat parser = new SimpleDateFormat();
        for (final String parsePattern : parsePatterns) {
            parser.applyPattern(parsePattern);
            parser.setLenient(false);
            try {
                parser.parse(str);
                matched = true;
                break;
            } catch (ParseException e) {
                continue;
            }
        }
        return matched;
    }
}
