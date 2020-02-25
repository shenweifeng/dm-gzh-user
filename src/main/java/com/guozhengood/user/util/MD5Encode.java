package com.guozhengood.user.util;

import java.security.MessageDigest;

/**
 * 
 * 类说明:为密码字符串进行MD5加密
 * 
 * @author qiujy
 * @version v1.0 Oct 18, 2005
 */
public class MD5Encode {

    /**
     * 
     * 方法说明:加密字符串
     * 
     * @param 源字符串
     * @return 加密后的字符串
     */
    public static String saveurl = "/u01/";

    /**
     * 2013年8月16日10:16:58
     * 
     * 加密规则：后续如果要更改规则，只需要改动这个方法即可。
     * 
     * @return
     */
    private static String role(int uid) {
        String ret = String.valueOf(uid);
        return ret;
    }

    public static String encode(int uid) {
        String resultString = null;
        try {
            resultString = role(uid);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 进行加密
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    public static String encode(String sourceString) {
        String resultString = null;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 进行加密
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }

    /**
     * 
     * 方法说明:把字节数组转换成字符串.
     * 
     * @param bytes
     * @return
     */
    private static final String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString();
    }

    // public static String en(String src) {
    // String result = "";
    // try {
    // BASE64Encoder base64en = new BASE64Encoder();
    // result = base64en.encode(src.getBytes());
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // return result;
    // }
    //
    // public static String unen(String src) {
    // String result = "";
    // BASE64Decoder base64en = new BASE64Decoder();
    // try {
    // result = new String(base64en.decodeBuffer(src));
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // return result;
    // }

    public static String getURL(int userid) {
        String url = "";
        String uidhash = encode(userid);
        String firstDir = uidhash.substring(0, 2);

        String imagehost = "";

        String secondDir = uidhash.substring(30, uidhash.length());
        String threeDir = uidhash.substring(2, 30);
        url = imagehost + "/" + firstDir + "/" + secondDir + "/" + threeDir + "/";
        return url;
    }

    public static String getUidDir(int uid) {
        String uidhash = encode(uid);
        if (null == uidhash) {
            return null;
        }
        return uidhash.substring(0, 2) + "/" + uidhash.substring(30, uidhash.length()) + "/" + uidhash.substring(2, 30)
                + "/";
    }

 }
