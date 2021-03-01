package com.user.util.encryption;


import org.apache.commons.lang3.StringUtils;

/**
 * <p>@description : （栅栏+维吉尼亚）加、解密 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/15 9:48 </p>
 **/
public class AssembleAlgorithm {

    public static void main(String[] args) {
        String str = "https://www.cnblogs.com/inverseEntropy/p/10151176.html";
        System.out.println("原文:" + str);

        String secretKey = "WANG";

        String encode = assembleEncode(str, secretKey);
        System.out.println("密文:" + encode);

        String decode = assembleDecode(encode, secretKey);
        System.out.println("解密:" + decode);

    }

    /**
     * <p>@description : 加密 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/15 9:55 </p>
     *
     * @param resource  ->明文
     * @param secretKey ->秘钥
     * @return String ->密文
     **/
    public static String assembleEncode(String resource, String secretKey) {
        return (StringUtils.isEmpty(resource) || StringUtils.isEmpty(secretKey)) ? resource :
                VirginiaAlgorithm.virginiaEncode(FenceAlgorithm.encode(resource, secretKey.length()), secretKey);
    }

    /**
     * <p>@description : 解密 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/15 9:57 </p>
     *
     * @param cipherText ->密文
     * @param secretKey  ->秘钥
     * @return String ->明文
     **/
    public static String assembleDecode(String cipherText, String secretKey) {
        return (StringUtils.isEmpty(cipherText) || StringUtils.isEmpty(secretKey)) ? cipherText :
                FenceAlgorithm.decode(VirginiaAlgorithm.virginiaDecode(cipherText, secretKey), secretKey.length());
    }
}
