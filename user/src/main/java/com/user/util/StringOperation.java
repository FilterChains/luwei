package com.user.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    private static final String regex = "([\u4e00-\u9fa5]+)";
    // 匹配特殊符号
    private static final String special = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    // 指定替换符号
    private static final String assign = "[>@<]";

    public static void main(String[] args) {
        String str1 = "10*5克";
        String str2 = "一克10*5袋";
        String str3 = "10*5包";
        String str4 = "10*5";
        String t = "12克12";
        String str = "132更新至456你好132胡开心";
        //String s = charactersTheRestructuring(str2);
        //System.out.println("组装结果:"+s);


        System.out.println(charactersTheRestructuring(str2));
        System.out.println(getStrNumber(str2));

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
            Pattern p = Pattern.compile("[^0-9]");
            Matcher m = p.matcher(content);
            String result = m.replaceAll("");
            for (int i = 0; i < result.length(); i++) {
                digitList.add(Integer.valueOf(result.substring(i, i + 1)));
            }
        }
        return digitList;
    }
    private static Integer charactersTheRestructuring(String resource) {
        Integer value = 0;
        if (StringUtils.isNotEmpty(resource)) {
            String source = resource.replaceAll("\\s+", "");
            for (; ; ) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(source);
                if (matcher.find()) {
                    String group = matcher.group(0);
                    source = source.replace(group, assign);
                }else{
                    value = 1;
                    source = source.replaceAll(special,assign);
                    List<Integer> stringList = Splitter.on(assign).omitEmptyStrings().trimResults().splitToList(source)
                            .stream().map(Integer::parseInt).collect(Collectors.toList());
                    for (Integer rt : stringList) {
                        value *= rt;
                    }
                    break;
                }
            }
        }
        return value;
    }
}

