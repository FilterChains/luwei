package com.luwei.util.pollingalgorithm;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <p>@description : 负载均衡平滑加权轮询算法 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/12/8 15:05 </p>
 *
 **/
public class SmoothPollingAlgorithm {

    /**
     * 加权总和
     */
    public static Integer DYNAMIC_WEIGHT = 0;


    public static void main(String[] args) {
        IpService ipService = new IpService();
        ipService.init();
        for (int i = 0; i < 58; i++) {
            String weightAlgorithm = smoothWeightAlgorithm(ipService);
            System.out.println("执行服务器:"+weightAlgorithm);
            System.err.println("execute Service:".concat(weightAlgorithm));
            System.err.println("动态权重:"+ipService.getIpPool());
            System.err.println("========================================");
        }
    }


    /**
     * <p>@description : 负载均衡平滑加权轮询算法 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/8 15:45 </p>
     *
      * @param ipService ->service pool
     * @return String 分发IP
     **/
    public static String smoothWeightAlgorithm(IpService ipService){
        // 获取所有加权服务器
        Map<String, Integer> ipPool = ipService.getIpPool();

        // 获取所有服务器权重值
        Collection<Integer> weightAll = ipPool.values();
        // 1、计算所有服务器权重和
        DYNAMIC_WEIGHT = weightAll.stream().mapToInt(Integer::intValue).sum();

        // 2、计算所有服务器权重占比最大的一台服务器
        String ipAddress = getMaxWeight(ipPool, weightAll);

        // 3、通过权重占比最大的服务器地址获取其权重并进行修改，
        Integer maxWeight = ipPool.get(ipAddress);
        ipPool.remove(ipAddress);
        // 用所有服务器中占比最大的权重减去所有服务器权重和
        ipPool.put(ipAddress, maxWeight-DYNAMIC_WEIGHT);
        // 赋值动态(dynamic)权重(weight)
        ipPool = new HashMap<>(ipPool);

        // 4、换算的权重在加上原服务器权重
        // 原服务权重（不会改变的）
        Map<String, Integer> map = ipService.init();

        // 计算新权重并赋值给临时权重
        Map<String, Integer> newMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            // 动态权重+服务器配置权重
            newMap.put(key, ipPool.get(key)+value);
        }
        // 设置动态权重
        ipService.setIpPool(newMap);
        return ipAddress;
    }
    
    private static String getMaxWeight(Map<String, Integer> ipPool, Collection<Integer> weightAll) {
        final Integer maxWeight = weightAll.stream().max(Comparator.comparing(Integer::intValue)).orElse(0);
        Set<Map.Entry<String, Integer>> entrySet = ipPool.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();
        String ipAddress = null;
        while(iterator.hasNext()){
            Map.Entry<String, Integer> next = iterator.next();
            // 当前服务器IP address
            ipAddress = next.getKey();
            // 当前服务器权重
            Integer weight = next.getValue();
            // 查找权重占比最大的一台服务器
            if(maxWeight.equals(weight)){
                break;
            }
        }
        return ipAddress;
    }
}
