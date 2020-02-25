package com.guozhengood.user.util;

import com.guozhengood.user.domain.util.TreeNode;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author swf
 * @date 2013-9-6 下午12:15:59
 * @Description 基础工具类
 */
public class CommonUtil {

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    public static Integer getCurrentActiveUserId(HttpServletRequest request) {
        String cookie_suId = getCookie(Constant.cookie_key_suId, request);
        int suId = NumberUtils.toInt(cookie_suId);
        return suId > 0 ? suId : null;
    }

    public static Integer getCurrentActiveUserType(HttpServletRequest request) {
        String cookie_suId = getCookie(Constant.cookie_key_type, request);
        int suId = NumberUtils.toInt(cookie_suId);
        return suId > 0 ? suId : null;
    }

    public static String getCurrentActiveUserLoginName(HttpServletRequest request) {
        String cookie_suId = getCookie(Constant.cookie_key_loginName, request);
        return StringUtil.isNotEmpty(cookie_suId) ? cookie_suId : null;
    }

    public static boolean isAdmin(HttpServletRequest request) {
        Integer type = getCurrentActiveUserType(request);
        return type != null && type == Constant.user_type_vip_admin;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getMobileCode() {
        StringBuffer code = new StringBuffer(6);
        Random r = new Random();
        int i = 0;
        while (++i <= 6) {
            code.append(r.nextInt(6));
        }
        return code.toString();
    }

    public static boolean isMobile(String mobile) {
        if (StringUtil.isEmpty(mobile) || mobile.length() < 11) {
            return false;
        }
        Pattern p = Pattern.compile("^(1[3,4,5,6,7,8,9])\\d{9}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    public static String getUa(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase();
    }

    public static String getPicPath(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            return "";
        }
        return Constant.pic_server + fileName;
    }

    public static String getPicPathProductImg(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            return "";
        }
        return Constant.pic_server + Constant.dir_product_img + fileName;
    }

    public static String getPicPathUserImg(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            return "";
        }
        return Constant.pic_server + Constant.dir_user_img + fileName;
    }

    public static String getFilePathReport(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            return "";
        }
        return Constant.pic_server + Constant.dir_report + fileName;
    }

    /**
     * @param fileName
     * @return
     * @date 2014-8-28 上午12:35:24
     * @author swf
     * @Description 获取指定文件名的文件后缀
     */
    public static String getFileSuffix(String fileName) {
        if (StringUtil.isNotEmpty(fileName) && fileName.indexOf(".") > -1) {
            return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        }
        return "";
    }

    /**
     * @param file
     * @return
     * @date 2014-8-28 上午12:11:09
     * @author swf
     * @Description 新的保存文件方法
     */
    public static String saveFileNew(MultipartFile file) {
        String fileName = "";
        if (file != null && !file.isEmpty()) {
            try {
                if (file.getSize() < Constant.upload_file_size_max) {
                    String currentday = StringUtil.getCurrentDay() + Constant.split_xie;
                    String path = Constant.upload_file_path + currentday;
                    File f = new File(path);
                    boolean b = true;
                    if (!f.exists()) {
                        if (!f.mkdirs()) {
                            b = false;
                        }
                    }
                    if (b) {
                        fileName = currentday + StringUtil.getCurrentTime() + getFileSuffix(file.getOriginalFilename());
                        f = new File(Constant.upload_file_path + fileName);

                        // 如果没有该文件则创建
                        if (!f.exists()) {
                            if (!f.createNewFile()) {
                                b = false;
                            }
                        }
                        if (b) {
                            file.transferTo(f);
                        } else {
                            // 文件创建不成功
                            logger.error("user upload file failed, message: create file failed, folder:"
                                    + Constant.upload_file_path + fileName);
                        }
                        // fileName = Constant.upload_file_path_show + fileName;
                        return fileName;
                    } else {
                        // 目录创建不成功
                        logger.error("user upload file failed, message: create folder failed, folder:"
                                + Constant.upload_file_path);
                    }
                } else {
                    // 照片尺寸大
                    logger.error("user upload file failed, message: idcard size more than 10M.");
                }
            } catch (IOException e) {
                logger.error("user upload file failed, message: " + e.getMessage());
                fileName = "";
            }
        }
        return fileName;
    }

    public static String saveFile(MultipartFile file) {
        String fileName = "";
        if (file != null && !file.isEmpty()) {
            try {
                if (file.getSize() < Constant.upload_file_size_max) {
                    logger.info(file.getName());
                    byte[] buffer = new byte[4096];
                    int len = 0;
                    InputStream inputStream = file.getInputStream();
                    ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                    while ((len = inputStream.read(buffer)) > 0) {
                        outstream.write(buffer, 0, len);
                    }
                    String currentday = StringUtil.getCurrentDay() + Constant.split_xie;
                    String path = Constant.upload_file_path + currentday;
                    File f = new File(path);
                    boolean b = true;
                    if (!f.exists()) {
                        if (!f.mkdirs()) {
                            b = false;
                        }
                    }
                    if (b) {
                        fileName = currentday + StringUtil.getCurrentTime() + ".jpg";
                        f = new File(Constant.upload_file_path + fileName);
                        FileOutputStream fop = new FileOutputStream(f);

                        // 如果没有该文件则创建
                        if (!f.exists()) {
                            if (!f.createNewFile()) {
                                b = false;
                            }
                        }
                        if (b) {
                            fop.write(outstream.toByteArray());
                            fop.flush();
                            fop.close();
                        } else {
                            // 文件创建不成功
                            logger.error("user upload file failed, message: create file failed, folder:"
                                    + Constant.upload_file_path + fileName + ".jpg");
                        }
                        if (fop != null) {
                            fop.close();
                        }
                        // fileName = Constant.upload_file_path_show + fileName;
                        return fileName;
                    } else {
                        // 目录创建不成功
                        logger.error("user upload file failed, message: create folder failed, folder:"
                                + Constant.upload_file_path);
                    }
                } else {
                    // 照片尺寸大
                    logger.error("user upload file failed, message: idcard size more than 1M.");
                }
            } catch (IOException e) {
                logger.error("user upload file failed, message: " + e.getMessage());
                fileName = "";
            }
        }
        return fileName;
    }

    public static boolean isEqual(String object1, String object2) {
        return ObjectUtils.equals(object1, object2);
    }

    public static boolean isEqual(Integer object1, Integer object2) {
        return ObjectUtils.equals(object1, object2);
    }

    public static boolean isEqual(Date object1, Date object2) {
        return ObjectUtils.equals(object1, object2);
    }

    // 平台
    public static String getSign4SystemUserLoginPass(String systemUserLoginPass) {
        return MD5Encode.encode(Constant.md5_key_pass + systemUserLoginPass);
    }

    // end of netty server

    public static String getCurrentLoginName(HttpServletRequest request) {
        String ret = "default";
        if (null != request.getSession().getAttribute(Constant.key_current_loginName)) {
            ret = String.valueOf(request.getSession().getAttribute(Constant.key_current_loginName));
        }
        return ret;
    }

    public static String getIp(HttpServletRequest request) {
        String forwaredFor = request.getHeader("X-Forwarded-For");
        String ip = "";
        if (StringUtil.isNotEmpty(forwaredFor)) {
            String[] ipArray = forwaredFor.split(",");
            ip = ipArray[0];
        } else {
            ip = request.getRemoteAddr();
        }
        if (ip.startsWith("0:0:")) {
            ip = Constant.ip_default_local;
        }
        return ip;
    }

    public static void response(HttpServletResponse response, String ret) {
        response.setCharacterEncoding(Constant.charset_utf8);
        PrintWriter write = null;
        try {
            write = response.getWriter();
            write.write(ret);
        } catch (IOException e) {
        }
    }

    public static String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }

    public static String urlencode(String src) {
        String ret = src;
        try {
            ret = URLEncoder.encode(src, Constant.charset_utf8);
        } catch (UnsupportedEncodingException e) {
        }
        return ret;
    }

    public static String urldecode(String src) {
        String ret = src;
        try {
            ret = URLDecoder.decode(src, Constant.charset_utf8);
        } catch (UnsupportedEncodingException e) {
        }
        return ret;
    }

    /**
     * @param key
     * @param request
     * @return
     * @date 2014-5-27 下午4:44:58
     * @author swf
     * @Description 读取cookie中的数据
     */
    public static String getCookie(String key, HttpServletRequest request) {
        String ret = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(key)) {
                    ret = cookie.getValue();
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * @param key
     * @param response
     * @return
     * @date 2014-8-18 下午3:27:42
     * @author swf
     * @Description 删除cookie中的数据
     */
    public static void delCookie(String key, HttpServletResponse response) {
        // 在服务器端创建cookie
        Cookie ucookie = new Cookie(key, null);
        // 设置该cookie存在的时间：0-即使删除，-1-浏览器关闭后删除，其他-其他时间删除
        ucookie.setMaxAge(0);
        // cookie有效路径是网站根目录
        ucookie.setPath("/");
        // 向客户端写入
        response.addCookie(ucookie);
    }

    /**
     * @param key
     * @param value
     * @param response
     * @date 2014-5-27 下午3:47:06
     * @author swf
     * @Description 写入cookie
     */
    public static void setCookie(String key, String value, HttpServletResponse response) {
        // 在服务器端创建cookie
        Cookie ucookie = new Cookie(key, value);
        // 设置该cookie存在的时间
        ucookie.setMaxAge(60 * 60 * 24 * 60);
        // cookie有效路径是网站根目录
        ucookie.setPath("/");
        // 向客户端写入
        response.addCookie(ucookie);
    }

    /**
     * @return
     * @date 2015-2-8 下午9:11:20
     * @author swf
     * @Description 获取当前时间毫秒数
     */
    public static String getCurrrentTime4FileName() {
        return DateUtil.dateToString(new Date(), "yyyy_MM_dd_HH_ss_mm_SSS");
    }

    public static String getCurrrentDateForDir() {
        return DateUtil.dateToString(new Date(), "yyyy/MM/dd");
    }

    public static String getCurrrentTime4OrderNo() {
        return DateUtil.dateToString(new Date(), "yyyyMMddHHssmmSSS");
    }

    public static String createOrderNo() {
        return DateUtil.dateToString(new Date(), "yyyyMMddHHssmm") + getMobileCode();
    }

    public static String getPicPathVip(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            return "";
        }
        return Constant.public_pic_vip + fileName;
    }

    public static String saveFile(MultipartFile file, String fileNameOriginal, Integer userId, int type) {

        String fileNameReal = "";
        if (file == null || file.getSize() < 1 || StringUtils.isEmpty(fileNameOriginal)) {
            return fileNameReal;
        }

        String path = Constant.upload_path_real_vip + MD5Encode.getUidDir(userId);
        File f = new File(path);
        boolean b = true;
        if (!f.exists()) {
            if (!f.mkdirs()) {
                b = false;
            }
        }
        if (b) {
            try {
                fileNameReal = String.format("%s_%s_%s%s", userId, type, CommonUtil.getCurrrentTime4FileName(),
                        fileNameOriginal.substring(fileNameOriginal.lastIndexOf(".")));
                f = new File(path + fileNameReal);

                // 如果没有该文件则创建
                if (!f.exists()) {
                    if (!f.createNewFile()) {
                        b = false;
                    }
                }
                if (b) {
                    file.transferTo(f);
                } else {
                    // 文件创建不成功
                    logger.error("user upload file failed, message: create file failed, folder:" + path + fileNameReal);
                    fileNameReal = "";
                }
            } catch (IOException e) {
                fileNameReal = "";
            }
            return fileNameReal;
        } else {
            // 目录创建不成功
            logger.error("user upload file failed, message: create folder failed, folder:" + path);
            fileNameReal = "";
        }

        return fileNameReal;
    }

    /**
     * @param file
     * @param fileNameOriginal
     * @param dir
     * @return
     * @date 2016年11月24日 上午9:41:34
     * @author swf
     * @Description 文件上传
     */
    public static List<String> saveFileForDirLocal(MultipartFile file, String fileNameOriginal, String dir) {
        List<String> ret = new ArrayList<String>();
        String fileNameReal = "";
        if (file == null || file.getSize() < 1 || StringUtils.isEmpty(fileNameOriginal)) {
            return ret;
        }

        // String dir_prefix = CommonUtil.getCurrrentDateForDir();
        String path = Constant.upload_path_real_res + dir;
        File f = new File(path);
        boolean b = true;
        if (!f.exists()) {
            if (!f.mkdirs()) {
                b = false;
            }
        }
        if (b) {
            try {
                String fileName = CommonUtil.getCurrrentTime4FileName();
                String fileSuffix = fileNameOriginal.substring(fileNameOriginal.lastIndexOf("."));
                // fileNameReal = dir_prefix + "/" +
                // Constant.pic_prefix_original + fileName + fileSuffix;
                fileNameReal = Constant.pic_prefix_original + fileName + fileSuffix;
                f = new File(Constant.upload_path_real_res + dir + "/" + fileNameReal);

                // 如果没有该文件则创建
                if (!f.exists()) {
                    if (!f.createNewFile()) {
                        b = false;
                    }
                }
                if (b) {
                    file.transferTo(f);
                    // 原图
                    ret.add(fileNameReal);
                } else {
                    // 文件创建不成功
                    logger.error("user upload file failed, message: create file failed, folder:"
                            + Constant.upload_path_real_res + dir + "/" + fileNameReal);
                    fileNameReal = "";
                }
            } catch (IOException e) {
                fileNameReal = "";
            }
            return ret;
        } else {
            // 目录创建不成功
            logger.error("user upload file failed, message: create folder failed, folder:"
                    + Constant.upload_path_real_res + dir + "/");
            fileNameReal = "";
        }

        return ret;
    }

    public static String filterSpecialCharacter(String src) {
        if (StringUtil.isEmpty(src)) {
            return "";
        }
        int len = src.length();
        int code = 0;
        char[] chars = new char[len];
        int j = 0;
        for (int i = 0; i < len; i++) {
            code = src.codePointAt(i);
            // 过滤用4个或6个字节去编码一个UNICODE字符, 4字节分界线
            if (code < 65532) {
                chars[j++] = src.charAt(i);
            }
        }
        if (j > 0) {
            return String.copyValueOf(chars, 0, j);
        }
        return "";
    }

    public static String getCity4Code(String str) {
        if (str == null || str.length() < 6) {
            return null;
        }
        return str.substring(0, 4);
    }

    public static String formatMoney(String fee) {
        if (StringUtil.isEmpty(fee)) {
            return null;
        }
        fee = fee.trim();
        if (".".equals(fee)) {
            return null;
        }
        if ("0".equals(fee) || "0.0".equals(fee)) {
            return "0";
        }
        while (fee.startsWith("0")) {
            if (fee.startsWith("0.")) {
                break;
            }
            fee = fee.substring(1);
        }
        if (fee.indexOf(".") >= 0) {
            while (fee.endsWith("0")) {
                fee = fee.substring(0, fee.length() - 1);
            }
            if (fee.endsWith(".")) {
                fee = fee.substring(0, fee.length() - 1);
            }
        }
        if (".".equals(fee)) {
            return null;
        }
        if (fee.startsWith(".")) {
            return "0" + fee;
        }
        return fee;
    }

    public static void sleep(long times) {
        try {
            Thread.sleep(times);
        } catch (InterruptedException e) {
        }
    }


    /**
     * @desc: 登录密码加密策略
     * @param:
     * @return:
     * @auther: swf
     * @date: 2019/12/2 12:47
     */
    public static  String encodePass(String pass, String salt){
        //算法的名称 和 原始密码
//        String s = new SimpleHash("MD5", pass, salt, 2).toString();
//        System.out.println(s);
        return new SimpleHash("MD5", pass, salt, 2).toString();
    }

}
