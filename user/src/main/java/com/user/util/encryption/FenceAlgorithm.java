package com.user.util.encryption;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>@description : 凯撒加密演变->栅栏加密 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/14 11:53 </p>
 *
 **/
public class FenceAlgorithm {

    public static void main(String[] args) {

        String str = "http://shop.xinanyuncai.com/platformActivity/searchSupplyProduct";
        System.out.println("原文:"+str);

        String secretFile = encode(str, SECRET_KEY);
        System.err.println("栅栏加密密文:"+secretFile);

        String resourceFile = decode(secretFile,SECRET_KEY);
        System.out.println("解密:"+resourceFile);

        System.err.println("解密是否成功:"+str.equals(resourceFile));
    }

    public static final int SECRET_KEY = 4;
    
    /**
     * <p>@description : 加密 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/14 16:08 </p>
     *
      * @param resource ->原文
     * @param splitLen ->加密Key
     * @return ->密文
     **/
    public static String encode(String resource,int splitLen){
        resource = resource.replaceAll("\\s+","");
        for (int j = 0,len = splitLen - (resource.length() % splitLen); j < len; j++) {
            resource = resource.concat(STR);
        }
        // 加密明文
        return assembleCode(getSplit(resource, splitLen),splitLen);
    }

    /**
     * <p>@description : 解密 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/14 16:04 </p>
     *
     * @param cipherText ->密文
     * @param splitLen ->加密Key
     * @return String->原文
     **/
    public static String decode(String cipherText,int splitLen) {
        cipherText = cipherText.replaceAll("\\s+","");
        int length = cipherText.length();
        int i = length / splitLen;
        final int len =  (0 == length % splitLen) ? i : i + 1;
        // 解密密文
        return removeTheSuffix(assembleCode(getSplit(cipherText, len), len));
    }

    /**
     * <p>@description : 移除后缀 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/14 16:55 </p>
     *
      * @param s ->原文
     * @return String
     **/
    private static String removeTheSuffix(String s) {
        int leg = s.length();
        int k = 1;
        int indexOf = 0;
        String sub;
        do{
            sub = s.substring(0, leg - k);
            k++;
            indexOf = sub.lastIndexOf(STR);
        }while (leg == indexOf + k);
        return sub;
    }

    /**
     * <p>@description : 组装密文 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/14 12:25 </p>
     *
      * @param list ->分隔原文
     * @param splitLen ->加密Key
     * @return String ->密文
     **/
    private static String assembleCode(List<String> list,int splitLen) {
        // 组装密文
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < splitLen; j++) {
            for (String s : list) {
                String sub = s.substring(j, j + 1);
                builder.append(sub);
            }
        }
        return builder.toString().replaceAll("\\s+","");
    }

    /**
     * <p>@description : 按照秘钥分隔原文 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/14 11:51 </p>
     *
      * @param str ->原文
      * @param splitLen ->截取长度
     * @return  List<String>
     **/
    private static List<String> getSplit(String str,int splitLen) {
        int length = str.length();
        List<String> list = new ArrayList<>();
        int t = 0;
        do {
            String result = str.substring(t, Math.min(t + splitLen, length));
            int len = result.length();
            if(splitLen >len){
                do{
                    // 补充结果不足的情况
                    result = result.concat(" ");
                    len = result.length();
                }while (splitLen > len);
            }
            list.add(result);
            t += splitLen;
        } while (t < length);
        return list;
    }

    /**
     * 密文特殊符号
     */
    private static final String STR = "-";
}
