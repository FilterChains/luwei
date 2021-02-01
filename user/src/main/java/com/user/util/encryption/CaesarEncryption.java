package com.user.util.encryption;

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
public class CaesarEncryption {

    public static void main(String[] args) {
        String str = "http://shop.xinanyuncai.com/platformActivity/searchSupplyProduct";
        System.out.println("明文:"+str);

        String encryption = caesarEncryption(str,5, coder.ENCODER);
        System.out.println("密文:"+encryption);

        String caesarEncryption = caesarEncryption(encryption, 5, coder.DECODER);
        System.out.println("解密:"+caesarEncryption);

        System.err.println("解密是否成功:"+str.equals(caesarEncryption));
    }


    private static final int TEXT_CAP_CHARACTERS = 26;
    private static final int THIRTEEN = 13;
    private static final String NUMBER = "\\d+";
    private static final int ZERO = 0;
    private static final int SIXTY_FIVE = 65;
    private static final int NINETY = 90;
    private static final int NINETY_SEVEN = 97;
    private static final int ONE_HUNDRED_TWO = 122;

    /**
     * @Title: caesarEncryption
     * @Description: 凯撒加密
     * @Param: [proclaim, secretKey,operation]  参数: proclaim ->明文,secretKey 秘钥
     * @param: coder ->加密,解密。开关
     * @Param: secretKey 大于0 向左加密,小于向右加密,等于0 无意义不加密
     * @Return: java.lang.String   返回类型
     * @Date: 2020/2/16 20:48
     */
    public static String caesarEncryption(String proclaim, int secretKey, coder coder) {
        //validate proclaim is not null;(验证明文)
        Assert.hasText(proclaim, "请正确输入明文,注:明文不能为空");
        Assert.isTrue(coder.value.equals(coder.getValue()),"请输入加、解密操作");
        //validate 验证是否是加、解密
        secretKey = secretKey * coder.getIndex();
        //validate secretKey Reduce the number of cycles;(验证秘钥减少加密循环次数)
        int key = (-TEXT_CAP_CHARACTERS > secretKey || TEXT_CAP_CHARACTERS < secretKey)
                ? secretKey % TEXT_CAP_CHARACTERS : ((THIRTEEN < secretKey)
                ? secretKey - TEXT_CAP_CHARACTERS : (-THIRTEEN > secretKey)
                ? TEXT_CAP_CHARACTERS + secretKey : secretKey);
        //validate proclaim is number return;(再次验证明文和秘钥)
        if (proclaim.trim().matches(NUMBER) || ZERO == key) {
            return proclaim;
        }
        // 65-90 A-Z 97-122 a-z
        int len = proclaim.length();
        char[] cipherText = new char[len];
        for (int i = 0; i < len; i++) {
            int bt = proclaim.charAt(i);
            int by = bt + key;
            int num = (ZERO < key) ? (by - TEXT_CAP_CHARACTERS) : (by + TEXT_CAP_CHARACTERS);
            cipherText[i] = (char) ((SIXTY_FIVE <= bt && NINETY >= bt) ? (NINETY < by) ?
                    num : (SIXTY_FIVE > by) ? num : by : (NINETY_SEVEN <= bt && ONE_HUNDRED_TWO >= bt) ?
                    (ONE_HUNDRED_TWO < by) ? num : (NINETY_SEVEN > by) ? num : by : bt);
        }
        return new String(cipherText);
    }

    /**
     * 加、解密操作
     */
    public enum coder {
        /**
         * 加密
         */
        ENCODER(1, "encoder"),
        /**
         * 解密
         */
        DECODER(-1, "decoder");
        private final int index;
        private final String value;

        coder(int index, String value) {
            this.index = index;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getIndex() {
            return index;
        }
    }
}
