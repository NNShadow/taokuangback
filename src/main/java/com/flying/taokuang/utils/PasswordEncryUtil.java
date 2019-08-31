package com.flying.taokuang.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 未使用
 */
public class PasswordEncryUtil {
    public static String encodeByMd5(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        Base64.Encoder base64Encoder = Base64.getEncoder();
        return base64Encoder.encodeToString(md5.digest(string.getBytes("utf-8")));
    }

//    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        System.out.println(PasswordEncryUtil.encodeByMd5("111"));
//        System.out.println(PasswordEncryUtil.encodeByMd5("111"));
//    }
}
