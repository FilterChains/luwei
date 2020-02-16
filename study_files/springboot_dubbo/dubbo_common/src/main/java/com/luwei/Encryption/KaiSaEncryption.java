package com.luwei.Encryption;

import org.springframework.util.Assert;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.Encryption
 * @auther: luwei
 * @description: 最早加密 ->凯撒加密
 * @date: 2020/2/16 20:44
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class KaiSaEncryption {

    public static void main(String[] args) {
        String a = "a b c d e f g h i j k l m n o p q r s t u v w x y z ";
        System.err.println(caesarEncryption("23wq122", 1000));
    }

    /**
     * @Title: kaiSaEncrypetion
     * @Description: 凯撒加密
     * @Param: [proclaim, secretKey]  参数: proclaim ->明文,secretKey 秘钥
     * @Param: secretKey 大于0 向左加密,小于向右加密,等于0 无意义不加密
     * @Return: java.lang.String   返回类型
     * @Date: 2020/2/16 20:48
     */
    private static String caesarEncryption(String proclaim, int secretKey) {
        //validate proclaim is not null;(验证明文)
        Assert.hasText(proclaim, "请正确输入明文,注:明文不能为空");
        //validate secretKey Reduce the number of cycles;(验证秘钥减少加密循环次数)
        int key = (-26 > secretKey || 26 < secretKey) ? secretKey % 26 :
                ((13 < secretKey) ? secretKey - 26 : (-13 > secretKey) ? 26 + secretKey : secretKey);
        //validate proclaim is number return;(再次验证明文和秘钥)
        if(proclaim.trim().matches("\\d+") || 0 == key){
            return proclaim;
        }
        // 65-90 A-Z 97-122 a-z
        int len = proclaim.length();
        char[] cipherText = new char[len];
        for (int i = 0; i < len; i++) {
            int bt = proclaim.charAt(i);
            int by = bt + key;
            int num = (0 < key) ? (by - 26) : (by + 26);
            cipherText[i] = (char) ((65 <= bt && 90 >= bt) ? (90 < by) ?
                    num : by : (97 <= bt && 122 >= bt) ? (122 < by) ?
                    num : by : bt);
        }
        return new String(cipherText);
    }

}
