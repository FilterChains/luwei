package com.user.util.pollingalgorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>@description : 模拟配置文件 </p>
 * <p>@author : Wei.Lu </p>
 * <p>@date : 2021/2/24 15:34 </p>
 **/
public class IpConfig {

    private static final String IP = "192.168.3.";

    /**
     * 配置文件服务
     */
    public static final Set<IpData> IP_POOL = new HashSet<>();

    /**
     * 模拟服务器台数
     */
    public static final int NUM = 5;

    /**
     * 权重之和
     */
    public static volatile int WEIGHT_SUM;
    public static volatile int MAX_WEIGHT;


    /**
     * 权重map
     */
    public static Map<Integer, List<String>> WEIGHT_MAP;

    /**
     * 动态权重
     */
    public static Map<Integer, List<String>> DYNAMIC_WEIGHT_MAP;

    /**
     * 模拟读取配置文件，服务的配置
     */
    public static void ipFactoryService() {
        IpData ipData;
        for (int i = 0; i < NUM; i++) {
            ipData = new IpData();
            ipData.setIpAddr(IP.concat(String.valueOf(i + 1)));
            ipData.setWeight(i + 1);
            IP_POOL.add(ipData);
        }
        // 求出配置文件最大权重
        WEIGHT_SUM = IP_POOL.stream().map(IpData::getWeight).reduce(Integer::sum).orElse(0);
    }

}
