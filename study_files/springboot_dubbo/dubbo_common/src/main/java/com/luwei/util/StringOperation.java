package com.luwei.util;

import com.google.common.base.Splitter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.jnlp.IntegrationService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private static List<String> numberList = new ArrayList<>();
    private static Set<String> chineseCharactersSet = new HashSet<>();

    public static void main(String[] args) {
        String str1 = "10*5克";
        String str2 = "一克10*5袋";
        String str3 = "10*5包";
        String str4 = "10*5";
        String t = "12克12";
        String str = "132更新至456你好132胡开心";
        String s = charactersTheRestructuring(str2);
        System.out.println("组装结果:"+s);
    }

    private static String charactersTheRestructuring(String resource) {
        if(StringUtils.isNotEmpty(resource)){
            String source = resource.replaceAll("\\s+", "");
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(source);
            if (matcher.find()) {
                String group = matcher.group(0);
                chineseCharactersSet.add(group); //获取汉字
                String replace = resource.replace(group, assign);
                numberList.add(replace);
                charactersTheRestructuring(replace);
            }else{
                String result1 = numberList.get(numberList.size() - 1);
                String replaceAll = result1.replaceAll(special, assign);
                List<Integer> stringList = Splitter.on(assign).omitEmptyStrings().trimResults()
                        .splitToList(replaceAll).stream().map(Integer::parseInt).collect(Collectors.toList());
                Integer value = 1;
                for (Integer rt : stringList) {
                    value *= rt;
                }
                StringBuilder builder = new StringBuilder(chineseCharactersSet.size());
                if (CollectionUtils.isNotEmpty(chineseCharactersSet)){
                    chineseCharactersSet.forEach(builder::append);
                }
                return value+builder.toString();
            }
        }
        return null;
    }
}

