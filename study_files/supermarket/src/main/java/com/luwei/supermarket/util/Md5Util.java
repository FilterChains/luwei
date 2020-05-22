package com.luwei.supermarket.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @projectName： supermarket
 * @packageName: com.luwei.supermarket.util
 * @auther: luwei
 * @description:
 * @date: 2020/5/14 00:29
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class Md5Util {

    public static String encoder(String data) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取md5签名工具实例
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //添加一组字节数据，可以多次调用
            messageDigest.update(data.getBytes());
            //对添加的字节数据进行md5运算，返回运算结果
            byte[] bytes = messageDigest.digest();
            //把字节结果转换为16进制字符串
            for (byte bin : bytes) {
                int result = bin & 0xFF;
                if (result < 0x10) {
                    stringBuilder.append("0");
                }
                stringBuilder.append(Integer.toHexString(result));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
