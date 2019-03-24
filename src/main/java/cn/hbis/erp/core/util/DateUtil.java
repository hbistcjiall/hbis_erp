package cn.hbis.erp.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName:
 * @Description:
 * @author: xupeng
 * @date: 2019/3/20 15:01
 */
public class DateUtil {
    private static DateFormat format = new SimpleDateFormat("yyyy-MM");
    private static DateFormat formatD = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @title DateUtil.getFirstDayOfMonth
     * @description 获取月份第一天
     * @author zhangry12988
     * @time 2018-02-23 09:42
     * @param queryDate
     * @return String
     * @throws
     */
    public static String getFirstDayOfMonth(String queryDate) {
        String firstDay="";
        try {
            Date date = format.parse(queryDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            firstDay = formatD.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return firstDay;
    }
    /**
     * @title DateUtil.getLastDayOfMonth
     * @description 获取月份最后一天
     * @author zhangry12988
     * @time 2018-02-23 09:43
     * @param queryDate
     * @return String
     * @throws
     */
    public static String getLastDayOfMonth(String queryDate) {
        String lastDay="";
        try {
            Date date = format.parse(queryDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            lastDay = formatD.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lastDay;
    }
    /**
     * @Title DateUtil.getLastMonth
     * @Description 获取上个月
     * @author zhangry12988
     * @time 2017-10-26 10:15
     * @return String
     * @throws
     */
    public static String getLastMonth() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date()); // 设置为当前时间
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
            String lastMonth = format.format(calendar.getTime());
            return lastMonth;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Title DateUtil.getLastMonth
     * @Description 获取传入日期的上个月
     * @author zhangry12988
     * @time 2017-10-26 10:35
     * @param date
     * @return String
     * @throws
     */
    public static String getLastMonth(String date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(format.parse(date));
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            return format.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Title DateUtil.getTenthDayOfLastMonth
     * @Description 获取当前月day号
     * @author zhangry12988
     * @time 2017-10-26 10:40
     * @return String
     * @throws
     */
    public static String getTenthDayOfMonth(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        String time = formatD.format(calendar.getTime());
        return time;
    }

    public static String getFirstDayDateOfYear(String queryDate) {
        String firstDay="";
        try {
            Date date = format.parse(queryDate);
            final Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            final int last = cal.getActualMinimum(Calendar.DAY_OF_YEAR);
            cal.set(Calendar.DAY_OF_YEAR, last);
            firstDay = formatD.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return firstDay;
    }
    /**
     * @Title DateUtil.getNowMonth
     * @Description 获取当前月
     * @author zhangry12988
     * @time 2017-10-26 10:40
     * @return String
     * @throws
     */
    public static String getNowMonth() {
        try {
            Calendar calendar = Calendar.getInstance();
            return format.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
