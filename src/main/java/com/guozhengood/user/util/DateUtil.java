package com.guozhengood.user.util;

import org.apache.commons.lang.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public final static String Date_defaultFormat            = "yyyy-MM-dd HH:mm:ss";   // default
    public final static String Y_DateFormat                  = "yyyy";                  // year
    public final static String M_DateFormat                  = "MM";                    // year
    public final static String YM_DateFormat                 = "yyyy-MM";               // month
    public final static String YM_DateFormat_CN              = "yyyy年MM月";              // month
    public final static String YMW_DateFormat                = "yyyy-MM-W'W'";          // week
    public final static String YMD_DateFormat                = "yyyy-MM-dd";            // day
    public final static String YMDH_DateFormat               = "yyyy-MM-dd HH";         // hour
    public final static String YMDHD_DateFormat              = "yyyy-MM-dd HH:mm";      // minute
    public final static String YMDHDS_DateFormat             = Date_defaultFormat;      // second
    public final static String MYSQL_DateFormat_Default      = "%Y-%m-%d %H:%i:%S";
    public final static String MYSQL_DateFormat_Y            = "%Y";
    public final static String MYSQL_DateFormat_YM           = "%Y-%m";
    public final static String MYSQL_DateFormat_YMD          = "%Y-%m-%d";
    public final static String MYSQL_DateFormat_YMDH         = "%Y-%m-%d %H";
    public final static String MYSQL_DateFormat_YMDHI        = "%Y-%m-%d %H:%i";
    public final static String MYSQL_DateFormat_YMD_HIS      = MYSQL_DateFormat_Default;

    // 偏移量标志：0-second,1-minutes,2-hour,3-day,4-month,5-year
    public final static int    date_time_offset_model_second = 0;
    public final static int    date_time_offset_model_minute = 1;
    public final static int    date_time_offset_model_hour   = 2;
    public final static int    date_time_offset_model_day    = 3;
    public final static int    date_time_offset_model_month  = 4;
    public final static int    date_time_offset_model_year   = 5;

    /**
     * 
     * @date 2014-6-3 下午11:48:55
     * 
     * @author swf
     * 
     * @Description 日期加减操作类
     * 
     * @param date
     *            需要对比的时间
     * @param type
     *            1-天，2-分钟，3-月
     * @param count
     *            计量
     * @return
     */
    public static Date getDate(Date date, int type, int count) {
        if (date == null) {
            return null;
        }
        if (count == 0) {
            return date;
        }
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(date);
        Date ret = null;
        switch (type) {
        case 1:
            // 天
            cal.add(Calendar.DAY_OF_MONTH, count);
            ret = cal.getTime();
            break;
        case 2:
            // 分钟
            cal.add(Calendar.MINUTE, count);
            ret = cal.getTime();
            break;
        case 3:
            // 月
            cal.add(Calendar.MONTH, count);
            ret = cal.getTime();
            break;
        default:
            break;
        }
        return ret;
    }

    public static Date getDateFromNow(int days) {
        Date now = new Date();
        if (days == 0) {
            return now;
        }
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    /**
     * 随机取当天8点-24点的时间
     * 
     * @return
     */
    public static Date getRandomDate() {
        Date curr = new Date();
        Date rs = null;
        // 设置范围
        Date start = DateUtils.setHours(curr, 8);
        if (start.before(curr)) {
            start = curr;
        }
        Date end = null;
        end = DateUtils.setHours(curr, 23);
        end = DateUtils.setMinutes(end, 59);
        if (start.getTime() < end.getTime()) {
            long date = random(start.getTime(), end.getTime());
            rs = new Date(date);
        }
        return rs;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    public static int getAge(Date birthday) {
        int age = 22;
        if (null != birthday) {
            Date now = new Date();
            long i = (now.getTime() - birthday.getTime()) / (1000 * 60 * 60 * 24);
            age = (int) (i / 365);
        }
        return age;
    }

    public static Date strToDate(String d) {
        Date date;
        java.text.DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = df2.parse(d);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    /**
     * 字符串转日期
     * 
     * @param d
     * @param type
     *            1：日期，2：日期+时间
     * @return
     */
    public static Date str2date(String d, int type) {
        if (StringUtil.isEmpty(d)) {
            return null;
        }
        // if (d.indexOf("-") == -1 && d.indexOf("/") == -1) {
        // return null;
        // }
        Date date = null;
        String format = "yyyy-MM-dd";
        if (d.indexOf("-") > 0) {
            // yyyy-MM-dd HH:mm:ss
            if (d.substring(0, d.indexOf("-")).length() == 2) {
                // 补全4位年份
                d = 20 + d;
            }
        } else if (d.indexOf("/") > 0) {
            format = "yyyy/MM/dd";
            // yyyy/MM/dd HH:mm:ss
            if (d.substring(0, d.indexOf("/")).length() == 2) {
                // 补全4位年份
                d = 20 + d;
            }
        } else if (d.indexOf("\\.") > 0) {
            format = "yyyy.MM.dd";
            // yyyy/MM/dd HH:mm:ss
            if (d.substring(0, d.indexOf("\\.")).length() == 2) {
                // 补全4位年份
                d = 20 + d;
            }
        } else {
            format = "yyyyMMdd";
        }
        if (type == 2) {
            format += " HH:mm:ss";
        }
        SimpleDateFormat df2 = new SimpleDateFormat(format);
        try {
            date = df2.parse(d);
        } catch (ParseException e) {
            date = null;
        }
        return date;
    }

    /**
     * 
     * @date 2018年11月13日 上午6:06:27
     * 
     * @author swf
     * 
     * @Description yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }
        String d = "";
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            d = df.format(date);
        } catch (Exception e) {
        }
        return d;
    }

    /**
     * 
     * @date 2018年11月13日 上午6:06:49
     * 
     * @author swf
     * 
     * @Description yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static String dateToStringForYMD(Date date) {
        if (date == null) {
            return "";
        }
        String d = "";
        java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d = df.format(date);
        } catch (Exception e) {
        }
        return d;
    }

    public static String dateToString(Date date, String formate) {
        if (date == null) {
            return "";
        }
        if (!StringUtil.isNotEmpty(formate)) {
            formate = "yyyy-MM-dd HH:mm:ss";
        }
        String d = "";
        java.text.DateFormat df = new SimpleDateFormat(formate);
        try {
            d = df.format(date);
        } catch (Exception e) {
        }
        return d;
    }

    /**
     * 
     * @date 2018年4月5日 下午10:16:48
     * 
     * @author swf
     * 
     * @Description 获取日期字符串（年月日）
     * 
     * @param date
     * @return
     */
    public static String getDateString(Date date) {
        if (date == null) {
            return "";
        }
        String d = "";
        java.text.DateFormat df = new SimpleDateFormat(YMD_DateFormat);
        try {
            d = df.format(date);
        } catch (Exception e) {
        }
        return d;
    }

    public static String dateToString(Date date, String formate, String defaultDate) {
        if (date == null) {
            return defaultDate;
        }
        if (!StringUtil.isNotEmpty(formate)) {
            formate = "yyyy-MM-dd HH:mm:ss";
        }
        String d = defaultDate;
        java.text.DateFormat df = new SimpleDateFormat(formate);
        try {
            d = df.format(date);
        } catch (Exception e) {
            d = defaultDate;
        }
        return d;
    }

    /**
     * 
     * @date 2015-4-3 下午10:38:03
     * 
     * @author swf
     * 
     * @Description 日期比较
     * 
     * @param d1
     * @param d2
     * 
     * @return 0：相等，正数：大于，负数：小于，-999：错误输入
     */
    public static int diffDate(Date d1, Date d2) {

        if (d1 == null || d2 == null) {
            return -999;
        }

        long ret = (d1.getTime() - d2.getTime()) / (1000 * 24 * 60 * 60);

        return (int) ret;

        // Calendar c1 = Calendar.getInstance(Locale.CHINA);
        // c1.setTime(d1);
        //
        // Calendar c2 = Calendar.getInstance(Locale.CHINA);
        // c2.setTime(d2);
        //
        // return c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR);
    }

    public static List<Integer[]> countWeeks(int year, int month) {

        String yyyyMM = "" + year + (month < 10 ? "0" + month : month);
        Date d = StringUtil.stringToDate(yyyyMM, "yyyyMM");
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(d);

        // first day
        Date d1 = StringUtil.stringToDate(yyyyMM + "01", "yyyyMMdd");
        Calendar cal1 = Calendar.getInstance(Locale.CHINA);
        cal1.setTime(d1);

        // last day
        Date d2 = StringUtil.stringToDate(yyyyMM + "01", "yyyyMMdd");
        Calendar cal2 = Calendar.getInstance(Locale.CHINA);
        cal2.setTime(d2);
        cal2.add(Calendar.MONTH, 1);
        cal2.add(Calendar.DAY_OF_YEAR, -1);
        int lastDay = cal2.get(Calendar.DAY_OF_MONTH);
        int tmp = lastDay;
        List<Integer[]> weeks = new ArrayList<Integer[]>();
        Integer[] dt = null;
        while (tmp > 0) {
            dt = new Integer[2];
            dt[0] = cal1.get(Calendar.DAY_OF_MONTH);
            if (cal1.get(Calendar.DAY_OF_MONTH) == lastDay) {
                dt[1] = cal1.get(Calendar.DAY_OF_MONTH);
                weeks.add(dt);
                break;
            }

            int day_of_week = cal1.get(Calendar.DAY_OF_WEEK);
            if (day_of_week == 7) {
                dt[1] = cal1.get(Calendar.DAY_OF_MONTH);
                weeks.add(dt);

                ++day_of_week;
                cal1.setTime(d1);
                cal1.add(Calendar.DAY_OF_YEAR, 1);
                d1 = cal1.getTime();

                if (cal1.get(Calendar.DAY_OF_MONTH) == 1) {
                    // 跨月了，直接退出，
                    tmp = 0;
                    break;
                }
                --tmp;
                continue;
            }

            while (day_of_week < 7) {
                --tmp;
                ++day_of_week;
                cal1.setTime(d1);
                cal1.add(Calendar.DAY_OF_YEAR, 1);
                d1 = cal1.getTime();
                if (day_of_week == 7) {
                    dt[1] = cal1.get(Calendar.DAY_OF_MONTH);
                    weeks.add(dt);

                    cal1.setTime(d1);
                    cal1.add(Calendar.DAY_OF_YEAR, 1);
                    d1 = cal1.getTime();

                    break;
                }
                if (cal1.get(Calendar.DAY_OF_MONTH) == lastDay) {
                    // 月末
                    dt[1] = cal1.get(Calendar.DAY_OF_MONTH);
                    weeks.add(dt);
                    tmp = 0;
                    break;
                }
            }
            if (cal1.get(Calendar.DAY_OF_MONTH) == 1) {
                break;
            }
        }

        return weeks;
    }

    public static int getCurrentYear() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(new Date());
        return cal.get(Calendar.YEAR);
    }

    public static int getCurrentMonth() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(new Date());
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 
     * @date 2018年2月25日 下午6:17:20
     * 
     * @author swf
     * 
     * @Description 获取今天是周几
     * 
     * @return
     */
    public static int getCurrentWeek() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(new Date());
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String getCurrentday() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(new Date());
        return dateToString(cal.getTime(), YMD_DateFormat);
    }

    public static String getCurrentMonthFirstDay() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(new Date());
        return String.format("%s-01", dateToString(cal.getTime(), YM_DateFormat));
    }

    public static String getCurrentWeekMonday() {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(new Date());
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return dateToString(cal.getTime(), YMD_DateFormat);
    }

    public static String[] getCurrentWeekMondayToFriday() {
        String[] ret = new String[5];
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(new Date());
        cal.setFirstDayOfWeek(Calendar.MONDAY);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        ret[0] = dateToString(cal.getTime(), YMD_DateFormat);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        ret[1] = dateToString(cal.getTime(), YMD_DateFormat);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        ret[2] = dateToString(cal.getTime(), YMD_DateFormat);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        ret[3] = dateToString(cal.getTime(), YMD_DateFormat);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        ret[4] = dateToString(cal.getTime(), YMD_DateFormat);

        return ret;
    }

    /**
     * 
     * @date 2018年2月25日 下午6:33:50
     * 
     * @author swf
     * 
     * @Description 获取指定日期，所在的星期的，星期week
     * 
     * @param time
     * @param frequency
     *            频率：每周，每2周，
     * @param week
     *            1-周一 ~ 7-周日
     * @param first
     *            true-如果是第一次，则当天计算
     * 
     *            false-当天不计算
     * @return
     */
    public static String getCurrentWeekDateByWeek(Date time, int frequency, int week, boolean first) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        int dw = dayWeek - 1;
        if (dw == 0) {
            dw = 7;
        }

        // 1周2次：特殊处理
        if (frequency == 6) {
            frequency = 1;
        }

        if (first && frequency > 1) {
            // 第一次派单，默认派发下一周的
            frequency = 1;
        }
        if (dw <= week && first) {
            // 当前周开始计算，frequency-1
            frequency = frequency - 1;
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        week = (week + 1) + frequency * 7;
        cal.add(Calendar.DATE, week - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        return StringUtil.dateToString(cal.getTime(), YMD_DateFormat, "");
    }

    /**
     * 
     * @date 2018年1月11日 下午3:25:42
     * 
     * @author swf
     * 
     * @Description 获取相差月份数
     * 
     * @param d1
     *            大-日期
     * @param d2
     *            小-日期
     * @return
     */
    public static int getDiffMonth(Date d1, Date d2) {
        if (d1 == null || d2 == null) {
            return -1;
        }

        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTime(d1);

        int m1 = cal.get(Calendar.YEAR) * 12 + cal.get(Calendar.MONTH);

        cal.setTime(d2);
        int m2 = cal.get(Calendar.YEAR) * 12 + cal.get(Calendar.MONTH);

        return m1 - m2;
    }

    /**
     * 
     * @date 2018年1月11日 下午4:56:20
     * 
     * @author swf
     * 
     * @Description 续费延期
     * 
     * @param from
     * @param month
     * @return
     */
    public static Date getDateMonthLastDay(Date from, int month) {

        if (from == null || month < 1) {
            return null;
        }

        // 1、获取m+1
        Date ret = getDate(from, 3, month + 1);

        // 2、获取当前月1日
        String s1 = dateToString(ret, "yyyy-MM-01");
        ret = str2date(s1, 1);

        // 3、日期 - 1天
        ret = getDate(ret, 1, -1);

        return ret;
    }

    public static int compareDte(Date d1, Date d2) {
        return d1.compareTo(d2);
    }

    public static Date long2date(long t) {
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.setTimeInMillis(t);
        return cal.getTime();
    }
}
