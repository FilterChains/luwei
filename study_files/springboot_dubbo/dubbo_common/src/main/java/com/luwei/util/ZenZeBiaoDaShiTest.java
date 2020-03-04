package com.luwei.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;

/**
 * @projectName： springbootdubbo
 * @packageName: com.dubbo.common.util
 * @auther: luwei
 * @description:
 * @date: 2020/2/5 13:01
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
public class ZenZeBiaoDaShiTest {
    public static void main(String[] args) {
        // 避免输出科学计数法
        String s = new BigDecimal("9").stripTrailingZeros().toPlainString();
        System.out.println(s);
        System.out.println(!s.matches("([1-9])|([1-9]\\d{1,7})|([1-9]\\d{1,7}\\.\\d{1,2})|([0-9]\\.\\d{1,2})"));

        System.out.println("19922218826".matches("[1][0-9]{10}"));

        String str = " qwe das d 89 ";
        System.err.println(str.replaceAll("\\s*",""));

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //管理地区
        List<Integer> list = Lists.newArrayList(1,2,3,2);
        //查询地区
        List<Integer> arrayList = Lists.newArrayList(1);
        Map<Integer,List<String>> map = new HashMap<>();

        map.put(2,Lists.newArrayList("12","12"));
        map.put(3,Lists.newArrayList("12"));

        List<Integer> collect = list.stream().filter(id -> (!ObjectUtils.isEmpty(map.get(id))
                && map.get(id).size()==arrayList.size())).collect(Collectors.toList());
        System.out.println("结果:"+collect);
        String ge = "国药准字Z20026281";
        String st = null;
        System.out.println("字符串匹配:"+(ge.contains("26281")));

        HashMap<Integer, Integer> hashMap = Maps.newHashMap();
        for (int i = 0; i < 10; i++) {
            hashMap.put(i,i);
        }
        System.out.println(hashMap);
        System.out.println("12312==>"+hashMap.get(2));
        String t = "1";
        List<Integer> lt = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("长度之前:"+lt.size()+"==>"+lt);
        Integer remove = lt.remove(0);
        System.out.println("移除东西:"+remove);
        System.out.println("长度之后:"+lt.size()+"==>"+lt);
        String tw = "[22]";
        String s1 = tw.replaceAll("]", " ");
        String s2 = s1.replaceAll("\\[", ",");
        System.out.println(s1);
        System.out.println(Lists.newArrayList(s1.split("\\[")));
        List<Integer> stringList = Splitter.on(",").omitEmptyStrings().trimResults()
                .splitToList(s2).stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(stringList);
        List<Integer> lt1 = Lists.newArrayList(1, 2, 3);
        ArrayList<Integer> list1 = Lists.newArrayList(1, 2,3,4,5,6);


        List<Integer> updateId = lt1.stream().filter(list1::contains).collect(Collectors.toList());
        List<Integer> insertId = lt1.stream().filter(id ->!(list1.contains(id))).collect(Collectors.toList());

        System.out.println("修改ID:"+updateId);
        System.out.println("新增ID:"+insertId);

        System.out.println("工具去并集:"+ org.apache.commons.collections4.CollectionUtils.union(list1,lt1));
        System.out.println("工具去并集:"+ org.apache.commons.collections4.CollectionUtils.union(lt1,list1));


        String y = "（zhongguo）";
        //System.out.println(y.replaceAll("[（][）]", "()"));
        String replace = y.replace("（", "(");
        System.out.println("reap:"+replace);
        System.out.println("reap:"+replace.replace("）",")"));



        Map<Integer,Integer> map1=new HashMap<Integer,Integer>();
        List<Integer> list12 = Lists.newArrayList(1,2,3,1);
        int N = list12.size();

        /*for (Integer aList12 : list12) {
            int V = aList12;
            int count = 0;
            for (Integer aList121 : list12) {
                if (aList121 == V) {
                    count++;
                    map1.put(aList121, count);
                }
            }
        }*/

        for (Integer id : list12) {
            int count = 0;
            for (Integer value : list12) {
                if (value.equals(id)) {
                    count++;
                    map1.put(value, count);
                }
            }
        }
        System.out.println(map1);

    }
}
