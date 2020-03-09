package com.luwei.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @projectName： GitHub
 * @packageName: com.luwei.util
 * @auther: luwei
 * @description:
 * @date: 2020/3/4 17:39
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@Data
public class DemoTest implements Serializable {
    private Map<Integer,String>mp1;
    private Map<Integer, Map<Integer,String>> mp2;
}
