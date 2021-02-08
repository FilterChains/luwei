package com.user.util.pollingalgorithm;

import java.util.HashMap;
import java.util.Map;

public class IpService {

    /**
     * 负载均衡内置临时存储权重,dynamic weight
     */
    private static Map<String, Integer> IP_POOL = new HashMap<>();

    public Map<String, Integer> getIpPool() {
        return IP_POOL;
    }

    public void setIpPool(Map<String, Integer> ipPool) {
        IP_POOL = ipPool;
    }

    /**
     * =================Configuration=======================
     */

    /**
     * 服务器台数
     */
    private static final int NUM = 80;

    /**
     * <p>@description : 初始权重（服务器配置） </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/12/8 17:05 </p>
     *
     * @return Map<String, Integer>
     **/
    public Map<String, Integer> init() {
        String ip = "192.168.1.";
        for (int i = 2; i <= NUM; i = i * 2) {
            IP_POOL.put(ip + i, i + 5);
        }
        return IP_POOL;
    }

}
