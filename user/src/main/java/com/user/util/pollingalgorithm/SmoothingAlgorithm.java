package com.user.util.pollingalgorithm;

import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SmoothingAlgorithm {

    /**
     * <p>@description : 初始化平滑轮询算法，服务器配置文件 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/2/24 17:10 </p>
     **/
    public static void initAlgorithm() {
        IpConfig.ipFactoryService();
        resolvingServiceWeights(IpConfig.IP_POOL);
    }


    /**
     * <p>@description : resolvingServiceWeights </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/2/24 16:14 </p>
     *
     * @param ips -> 配置IP集合
     **/
    public static void resolvingServiceWeights(Set<IpData> ips) {
        Assert.notEmpty(ips, "请设置代理服务IP");
        // 按照权重分组初始化，权重容器
        LinkedHashMap<Integer, List<String>> linkedHashMap = ips.stream().collect(Collectors.groupingBy(IpData::getWeight, LinkedHashMap::new,
                Collectors.mapping(IpData::getIpAddr, Collectors.toList())));
        IpConfig.WEIGHT_MAP = linkedHashMap;
        IpConfig.DYNAMIC_WEIGHT_MAP = linkedHashMap;
    }

    /**
     * <p>@description : 获取将要代理的IP服务 </p>
     * <p>@author : Wei.Lu </p>
     * <p>@date : 2021/2/24 16:31 </p>
     *
     * @param map -> 配置IP集合
     * @return {@link  String }
     **/
    public static String getProxyIpService(Map<Integer, List<String>> map) {
        Assert.notEmpty(map, "获取配置IP服务异常");
        final Integer maxKey = map.keySet().stream().reduce(Integer::max).orElse(0);
        IpConfig.MAX_WEIGHT = maxKey;
        return map.get(maxKey).get(0);
    }

    public static void main(String[] args) throws InterruptedException {
        initAlgorithm();
        System.out.println("=======================================");
        System.out.println("初始服务配置权重:" + IpConfig.WEIGHT_MAP);
        for (int i = 0; i < IpConfig.WEIGHT_SUM; i++) {
            // 获取权重最大的服务
            String resultProxyIpService = getProxyIpService(IpConfig.DYNAMIC_WEIGHT_MAP);
            System.out.println(resultProxyIpService);
            Thread.sleep(10L);
            getDynamicWeightMap();
        }
    }

    /**
     * Nginx dubbo平滑轮询加权算法
     */
    private static void getDynamicWeightMap() {
        // 动态权重Map
        Map<Integer, List<String>> dynamicWeightMap = IpConfig.DYNAMIC_WEIGHT_MAP;
        // 动态权重
        Set<IpData> dynamicIp = new HashSet<>();
        // 原服务权重
        Map<String, Integer> ipDataMap = IpConfig.IP_POOL.stream()
                .collect(Collectors.toMap(IpData::getIpAddr, IpData::getWeight));
        Set<Map.Entry<Integer, List<String>>> entrySet = dynamicWeightMap.entrySet();
        IpData ipData;
        for (Map.Entry<Integer, List<String>> entry : entrySet) {
            Integer key = entry.getKey();
            List<String> value = entry.getValue();
            for (String s : value) {
                ipData = new IpData();
                // 获取权重
                Integer weight = ipDataMap.get(s);
                if (IpConfig.MAX_WEIGHT == key) {
                    ipData.setWeight(IpConfig.MAX_WEIGHT - IpConfig.WEIGHT_SUM + weight);
                } else {
                    ipData.setWeight(key + weight);
                }
                ipData.setIpAddr(s);
                dynamicIp.add(ipData);
            }
        }
        // 动态权重加原生权重
        IpConfig.DYNAMIC_WEIGHT_MAP = dynamicIp.stream().collect(Collectors.groupingBy(IpData::getWeight, LinkedHashMap::new,
                Collectors.mapping(IpData::getIpAddr, Collectors.toList())));
    }
}
