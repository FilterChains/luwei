package com.user.util;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * <p>@description : JAVA Algorithm -> 算法 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/5/29 10:52 </p>
 **/
public class AlgorithmUtil {
    public static void main(String[] args) {

        System.out.println("向右做位运算:" + division(8));
        System.out.println(9 / 2);

        System.out.println("字符串替换:" + replaceSpace(new StringBuffer("hello  world")));
        int[] num = {3, 4, 5, 1, 2};
        System.out.println(minNumberInRotateArray(num));


        System.err.println("结果：" + Arrays.stream(StringComplie("abc")).collect(Collectors.toList()));
    }

    /**
     * 移位运算
     */
    private static int division(final int num) {
        // >> n 为算术右移，相当于除以 2n，例如 -7 >> 2 = -2
        //return num >> 1;
        //>>> n 为无符号右移，左边会补上 0。例如 -7 >>> 2 = 1073741822。
        //return num >>> 1;
        //<< n 为算术左移，相当于乘以 2n。-7 << 2 = -28。
        return num << 1;
    }

    private static String replaceSpace(StringBuffer str) {
        String s = str.toString();
        StringBuffer buffer = new StringBuffer();
        if (null != s) {
            int length = s.length();
            for (int i = 0; i < length; i++) {
                String sub = s.substring(i, i + 1);
                if (" ".equals(sub)) {
                    buffer.append("20%");
                } else {
                    buffer.append(sub);
                }
            }
        }
        return buffer.toString();
    }

    private static int minNumberInRotateArray(int[] array) {
        if (null == array || 0 == array.length) {
            return 0;
        }
        int b = 0;
        for (int i : array) {
            if (i < b) {
                b = i;
            }
        }
        return b;
    }

    //输入一个字符串,按字典序打印出该字符串中字符的所有排列。
    // 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串
    // abc,acb,bac,bca,cab和cba。

    private static String[] StringComplie(String str) {
        int length = str.length();
        String[] list = new String[length];
        for (int i = 0; i < length; i++) {
            list[i] = str.substring(i, i + 1);
        }
        System.out.println("第一次获取数据" + Arrays.stream(list).collect(Collectors.toList()));
        int length1 = list.length;
        String[] resultList = new String[length1];
        for (int i = 0; i < length1; i++) {
            StringBuilder builder = new StringBuilder(list[i]);
            for (int j = 0; j < length1; j++) {
                if (i != j) {
                    builder.append(list[j]);
                }
            }
            System.out.println("组装结果" + builder);
            resultList[i] = builder.toString();
        }
        return resultList;
    }
}


