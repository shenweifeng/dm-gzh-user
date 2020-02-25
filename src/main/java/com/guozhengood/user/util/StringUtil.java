package com.guozhengood.user.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    private static Log log = LogFactory.getLog(StringUtil.class);

    public static String encodeString(String str) {
        try {
            return new String(str.getBytes(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.info(String.format("[encode error] str=%s, msg=%s", str, e.getMessage()));
        }
        return str;
    }

    public static String encodeString(String str, String encode) {
        if (isEmpty(encode)) {
            encode = "UTF-8";
        }
        try {
            return new String(str.getBytes(), encode);
        } catch (UnsupportedEncodingException e) {
            log.info(String.format("[encode error] str=%s, encode=%s, msg=%s", str, encode, e.getMessage()));
        }
        return str;
    }

    /**
     * 清除所有XSS攻击的字符串
     */
    public static String getSafeStringXSS(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
            case '<':
                sb.append("&lt;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '\'':
                sb.append("&prime;");// &acute;");
                break;
            case '′':
                sb.append("&prime;");// &acute;");
                break;
            case '\"':
                sb.append("&quot;");
                break;
            case '＂':
                sb.append("&quot;");
                break;
            case '&':
                sb.append("＆");
                break;
            case '#':
                sb.append("＃");
                break;
            case '\\':
                sb.append('￥');
                break;
            case '=':
                sb.append("&#61;");
                break;
            default:
                sb.append(c);
                break;
            }
        }
        return sb.toString();
    }

    public static Integer object2Integer(Object obj) {
        if (null == obj) {
            return null;
        }
        Integer ret = null;
        try {
            ret = Integer.parseInt(String.valueOf(obj));
        } catch (NumberFormatException e) {
            ret = null;
        }
        return ret;
    }

    /**
     * 数组是否全部为数字类型，只要一个为非就返回false
     * 
     * @param src
     * @return
     */
    public static boolean isNumber(String... src) {
        if (null == src || src.length == 0) {
            return false;
        }
        for (String s : src) {
            if (StringUtils.isEmpty(s) || !NumberUtils.isNumber(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数组是否全部为日期类型，只要一个为非就返回false
     * 
     * @param src
     * @return
     */
    public static boolean isDate(String... src) {
        if (null == src || src.length == 0) {
            return false;
        }
        for (String s : src) {
            if (StringUtils.isEmpty(s) || null == DateUtil.strToDate(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 数组是否全部不为空，只要一个为非就返回false
     * 
     * @param src
     * @return
     */
    public static boolean isNotEmpty(String... src) {
        if (null == src || src.length == 0) {
            return false;
        }
        for (String s : src) {
            if (StringUtils.isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(Integer... src) {
        if (null == src || src.length == 0) {
            return false;
        }
        for (Integer s : src) {
            if (s == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^(1[2,3,4,5,6,7,8,9])\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static String formateNull(String src) {
        if (isNotEmpty(src)) {
            return src.trim();
        }
        return "";
    }

    public static String getCurrentTime() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static String getCurrentDateTime() {
        return DateUtil.dateToString(new Date(), Constant.formate_date_time_default);
    }

    public static String getCurrentDateTimeForOrder() {
        return DateUtil.dateToString(new Date(), Constant.formate_date_yyyyMMddHHmmss);
    }

    public static String getCurrentDay() {
        return DateUtil.dateToString(new Date(), Constant.formate_date_yyyyMMdd);
    }

    public static String getDateStringFromNow(int days) {
        if (days == 0) {
            DateUtil.dateToString(new Date(), "yyyy-MM-dd");
        }
        return DateUtil.dateToString(DateUtil.getDateFromNow(days), "yyyy-MM-dd");
    }

    public static void main(String[] args) {
        String mobile = "18181155866";
        System.out.println(isMobileNO(mobile));
    }

    /**
     * 获取一个32位的UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean isNotEmpty(String src) {
        return src != null && !"".equals(src.trim());
    }

    public static boolean isNotEmpty(char[] src) {
        return src != null && src.length > 0;
    }

    public static boolean isEmpty(char[] src) {
        return !isNotEmpty(src);
    }

    public static Integer string2integer(String src) {
        if (isEmpty(src)) {
            return null;
        }
        Integer ret = 0;
        try {
            ret = Integer.parseInt(src);
        } catch (NumberFormatException nfe) {
            ret = null;
        }
        return ret;
    }

    public static Double object2double(Object src, Double default_double) {
        Double ret = default_double;
        if (src != null) {
            try {
                ret = Double.parseDouble(String.valueOf(src));
            } catch (NumberFormatException nfe) {
                ret = default_double;
            }
        }
        return ret;
    }

    public static Integer string2integer(String src, int default_integer) {
        if (isEmpty(src)) {
            return default_integer;
        }
        Integer ret = 0;
        try {
            ret = Integer.parseInt(src);
        } catch (NumberFormatException nfe) {
            ret = default_integer;
        }
        return ret;
    }

    public static Double string2double(String src) {
        if (isEmpty(src)) {
            return null;
        }
        Double ret = 0.0;
        try {
            ret = Double.parseDouble(src);
        } catch (NumberFormatException nfe) {
            ret = null;
        }
        return ret;
    }

    public static boolean isEmpty(byte[] str) {
        return str != null && str.length > 0 ? false : true;
    }

    public static boolean isEmpty(Date date) {
        return date != null ? false : true;
    }

    public static boolean isEmpty(Integer src) {
        return src != null ? false : true;
    }

    public static boolean isEmpty(Object src) {
        if (null == src) {
            return true;
        }

        String typeName = src.getClass().getName();

        if (String.class.getName().equals(typeName)) {
            return isEmpty((String) src);
        }

        if (Integer.class.getName().equals(typeName)) {
            return isEmpty((Integer) src);
        }

        if (Date.class.getName().equals(typeName)) {
            return isEmpty((Date) src);
        }

        return false;
    }

    /**
     * 
     * @date 2017年8月31日 下午3:33:20
     * 
     * @author huyq
     * 
     * @Description 全部为空 返回true,否则false
     * 
     * @param src
     * @return
     */
    public static boolean isAllEmpty(String... src) {
        if (null == src || src.length == 0) {
            return true;
        }
        boolean flag = true;
        for (String s : src) {
            flag = flag && StringUtils.isEmpty(s);
            if (false == flag) {
                return flag;
            }
        }
        return flag;
    }

    public static boolean isAllEmpty(Integer... src) {
        if (null == src || src.length == 0) {
            return true;
        }
        boolean flag = true;
        for (Integer s : src) {
            flag = flag && s == null ? true : false;
            if (false == flag) {
                return flag;
            }
        }
        return flag;
    }

    public static boolean isAllEmpty(Long... src) {
        if (null == src || src.length == 0) {
            return true;
        }
        boolean flag = true;
        for (Long s : src) {
            flag = flag && s == null ? true : false;
            if (false == flag) {
                return flag;
            }
        }
        return flag;
    }

    /**
     * 判断字符串非空
     * 
     * @return false-不为空，true-为空
     */
    public static boolean isEmpty(String str) {
        return str != null && !"".equals(str.trim()) ? false : true;
    }

    public static boolean isEmpty(String[] str) {
        return str != null && str.length > 0 ? false : true;
    }

    public static Date stringToDate(String src) {
        if (isEmpty(src)) {
            return null;
        }

        if (src.indexOf(":") > -1) {
            // 包含时间
            return stringToDate(src, DateUtil.YMDHDS_DateFormat);
        }

        return stringToDate(src, DateUtil.YMD_DateFormat);
    }

    public static Date stringToDate(String src, String format) {
        if (isEmpty(src)) {
            return null;
        }

        Date ret = null;

        if (isEmpty(format)) {
            format = DateUtil.Date_defaultFormat;
        }

        SimpleDateFormat sf = new SimpleDateFormat(format);

        try {
            ret = sf.parse(src);

        } catch (ParseException e) {
            ret = null;
            log.error("format [" + src + "] to date error!");
        }

        return ret;
    }

    public static String dateToString(Date src, String format, String defaultValue) {
        if (isEmpty(src)) {
            return defaultValue;
        }
        if (isEmpty(format)) {
            format = DateUtil.Date_defaultFormat;
        }

        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(src);
    }

    public static String dateToStringYMD(Date src) {
        return dateToString(src, DateUtil.YMD_DateFormat, "");
    }
    public static String dateToStringYMDHMS(Date src) {
        return dateToString(src, DateUtil.Date_defaultFormat, "");
    }

    /**
     * 2012年6月26日9:50:19 字符串转成Integer
     * 
     * @exception NumberFormatException
     *                非法字符串，返回值为null
     * 
     */
    public static Integer stringToInteger(String src) {
        Integer ret = null;

        if (!isEmpty(src)) {
            try {
                ret = new Integer(src.trim());

            } catch (NumberFormatException e) {
                ret = null;
                log.error("format [" + src + "] to Integer error!");
            }
        }

        return ret;
    }

    /**
     * 2012年6月27日9:50:19 带默认值，用于特殊处理。 如果异常，则返回给定的默认值。
     * 
     */
    public static Integer stringToInteger(String src, Integer defaultValue) {
        Integer ret = defaultValue;

        if (!isEmpty(src)) {
            try {
                ret = new Integer(src.trim());

            } catch (NumberFormatException e) {
                ret = defaultValue;
                log.error("format [" + src + "] to Integer error!");
            }
        }

        return ret;
    }

    /**
     * 2012年11月30日8:42:18 带默认值，用于特殊处理。 如果异常，则返回给定的默认值。
     * 
     */
    public static Long stringToLong(String src, Long defaultValue) {

        Long ret = defaultValue;

        if (!isEmpty(src)) {
            try {
                ret = new Long(src.trim());

            } catch (NumberFormatException e) {
                ret = defaultValue;
                log.error("format [" + src + "] to Integer error!");
            }
        }

        return ret;
    }

    /**
     * @description 格式化NULL值，NULL值格式化返回为空字符串
     * @author swf
     * @create 2012-9-20 9:05:51
     * @param src
     *            源字符串
     * @return 返回格式化后的字符串
     */
    public static String formatNull(String src) {
        return isEmpty(src) ? "" : src.trim();
    }

    public static String formatNull(Object src) {
        return isEmpty(src) ? "" : src.toString().trim();
    }

    public static String clearBlank(String src) {
        return isEmpty(src) ? "" : src.replaceAll(" ", "");
    }

    /**
     * @description 格式化NULL值，NULL值格式化返回为空字符串
     * @author swf
     * @create 2012-9-20 9:05:51
     * @param src
     *            源字符串数组
     * @return 返回格式化后的字符串数组
     */
    public static String[] formatNull(String[] src) {
        if (!isEmpty(src)) {
            int size = src.length;

            for (int i = 0; i < size; i++) {
                src[i] = formatNull(src[i]);
            }

            return src;
        }

        return null;
    }

    public static boolean isEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        boolean isExist = false;
        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
        Matcher m = p.matcher(email);
        boolean b = m.matches();
        if (b) {
            // System.out.println("有效邮件地址");
            isExist = true;
        } else {
            // System.out.println("无效邮件地址");
        }
        return isExist;
    }

    public static int getCurrentDateMinute() {
        return NumberUtils.toInt(DateUtil.dateToString(new Date(), "mm"));
    }

    /**
     * 
     * @date 2016年9月28日 下午5:46:40
     * 
     * @author laikunzhen
     * 
     * @Description 移除斜杠开头字符
     * 
     * @param s
     * @return
     */
    public static String removeSlashHead(String s) {
        if (s != null && s.startsWith("/")) {
            s = s.substring(1);
        }
        return s;
    }

}
