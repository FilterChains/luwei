package com.user.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;

/**
 * @projectName： springboot_dubbo
 * @packageName: com.luwei.util
 * @auther: luwei
 * @description: 字符串操作
 * @date: 2020/3/11 22:07
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class StringOperation {

    // 匹配汉字
    private static final String REGEX = "([\u4e00-\u9fa5]+)";
    // 匹配字母
    private static final String LETTER = "[(A-Za-z)]";
    // 匹配特殊符号
    private static final String SPECIAL = "[`~!@#$^&*()=|{}':;',\\\\[\\-　\\_±φ△ɸ●▽＊+○－%°〞％·×÷ ]<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]";
    // 指定替换符号
    private static final String ASSIGN = "@";

    public static void main(String[] args) {
        String str1 = "10*5克";
        String str2 = "(△1☑26*10)1";
        String str3 = "10g（20％50ml）";
        String str4 = "10   大秦淮区5";
        String t = "@美能asdasd/.,><\\\\';l__==--看到了@2克@12@";
        String str = "456你好132***@";

        System.err.println(charactersTheRestructuring(str2));

        // System.out.println(getStrNumber(str3));

    }

    public static String getStrNumber(String content) {
        StringBuilder stringBuilder = listToInteger(getNumbers(content));
        if (org.springframework.util.StringUtils.isEmpty(stringBuilder)) {
            return null;
        }
        return stringBuilder.toString();
    }

    public static StringBuilder listToInteger(List<Integer> integerList) {
        if (CollectionUtils.isEmpty(integerList)) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (Integer integer : integerList) {
            result.append(integer);
        }
        return result;
    }

    public static List<Integer> getNumbers(String content) {
        List<Integer> digitList = Lists.newArrayList();
        if (StringUtils.isNotBlank(content)) {
            Pattern p = compile("[^0-9]");
            Matcher m = p.matcher(content);
            String result = m.replaceAll("");
            for (int i = 0; i < result.length(); i++) {
                digitList.add(Integer.valueOf(result.substring(i, i + 1)));
            }
        }
        return digitList;
    }

    /**
     * <p>@description : 规格匹配规则 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2021/2/5 17:29 </p>
     *
     * @param resource ->规格
     * @return {@link String}
     **/
    private static String charactersTheRestructuring(String resource) {
        if (StringUtils.isEmpty(resource)) {
            return null;
        }
        // 匹配汉字
        resource = resource.replaceAll(REGEX, ASSIGN);
        // 匹配字母
        resource = resource.replaceAll(LETTER, ASSIGN);
        // 匹配特殊字符
        resource = resource.replaceAll(SPECIAL, ASSIGN);
        // 按ASSIGN分隔字符
        List<String> splitToList = Splitter.on(ASSIGN).trimResults().omitEmptyStrings()
                .splitToList(resource);
        if (CollectionUtils.isEmpty(splitToList)) {
            return null;
        }
        List<BigDecimal> numberList = splitToList.stream().filter(x -> StringUtils.isNotBlank(x) &&
                (x.matches("\\d+") || x.matches("\\d+.\\d+")))
                .map(BigDecimal::new).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(numberList)) {
            return null;
        }
        BigDecimal bigDecimal = BigDecimal.ONE;
        for (BigDecimal bd : numberList) {
            bigDecimal = bigDecimal.multiply(bd);
        }
        return bigDecimal.stripTrailingZeros().toPlainString();
    }
}

