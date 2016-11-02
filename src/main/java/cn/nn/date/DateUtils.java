package cn.nn.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/11/2.
 */
public class DateUtils {
    //日期常用转换

    public static SimpleDateFormat DEFAULT_DATE_CN = new SimpleDateFormat("yyyy年MM月dd日");
    public static SimpleDateFormat DEFAULT_DATE_CN_TIME = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
    public static SimpleDateFormat DEFAULT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat DEFAULT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat DEFAULT_SIMPLE_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static SimpleDateFormat DEFAULT_TIME = new SimpleDateFormat("HH:mm:ss");

    /**
     * 时间戳转日期字符串
     */
    public static String formatTime(Long timestamp, SimpleDateFormat format) {
        String time = null;
        try {
            time = format.format(timestamp);
        } catch (Exception e) {
        }
        if (null == time) {
            time = "";
        }
        return time;
    }

    /**
     * 时间戳转日期字符串
     */
    public static String formatDate(Date date, SimpleDateFormat format) {
        String time = null;
        try {
            time = format.format(date.getTime());
        } catch (Exception e) {
        }
        if (null == time) {
            time = "";
        }
        return time;
    }
    /**
     * 时间戳 转时间字符串 小时:分钟:秒
     */
    public static String getTime(Long timestamp) {
        return formatTime(timestamp, DEFAULT_TIME);
    }

    /**
     * 时间戳转日期时间字符串 年-月-日 小时:分钟:秒
     */
    public static String getDateTime(Long timestamp) {
        return formatTime(timestamp, DEFAULT_DATE_TIME);
    }

    /**
     * 获取当前日期的偏移时间
     */
    public static long dateToLong(int day) {
        return dateToLong(new Date().getTime(), day);
    }

    /**
     * 获取指定日期的偏移时间
     */
    public static long dateToLong(long timestamp, int day) {
        Date date = new Date(timestamp);// 取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);// 把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();
        return date.getTime();
    }

    /**
     * 获取当前年
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月
     */
    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH)+1;
    }
    /**
     * 获取当前日
     */
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DATE);
    }
    /**
     * 获取年份
     */
    public static int getYear(Long time) {
        return Integer.parseInt(formatTime(time, new SimpleDateFormat("yyyy")));
    }

    /**
     * 获取当前天
     */
    public static long getToday() {
        Calendar calendar = Calendar.getInstance();// 获取当前日期
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }
    //获得当天0点时间
    public static Long getTimestampTodayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (Long) (cal.getTimeInMillis());
    }

    //获得当天24点时间
    public static Long getTimestampTodayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (Long) (cal.getTimeInMillis());
    }

    private static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static String getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }
    /**
     * @param time 时间戳
     * @param day  待检测日期
     * @return
     */
    public static boolean isLegalDay(Long time, Integer day) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int i = 0;
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            //闰年
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                i = 1;
            else if (month == 2)
                i = 2;
            else if (month == 4 || month == 6 || month == 9 || month == 11)
                i = 3;
            else
                i = 4;
            switch (i) {
                case 1:
                    if (day >= 32 || day <= 0) {
                        return false;
                    } else {
                        return true;
                    }
                case 2:
                    if (day >= 30 || day <= 0) {
                        return false;
                    } else {
                        return true;
                    }
                case 3:
                    if (day >= 31 || day <= 0) {
                        return false;
                    } else {
                        return true;
                    }
                default:
                    return false;
            }
        } else {
            //闰年
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                i = 1;
            else if (month == 2)
                i = 2;
            else if (month == 4 || month == 6 || month == 9 || month == 11)
                i = 3;
            else
                i = 4;
            switch (i) {
                case 1:
                    if (day >= 32 || day <= 0) {
                        return false;
                    } else {
                        return true;
                    }
                case 2:
                    if (day >= 29 || day <= 0) {
                        return false;
                    } else {
                        return true;
                    }
                case 3:
                    if (day >= 31 || day <= 0) {
                        return false;
                    } else {
                        return true;
                    }
                default:
                    return false;
            }
        }
    }

}
