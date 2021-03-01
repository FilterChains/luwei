package com.user.util.pollingalgorithm;

import java.io.Serializable;
import java.util.Objects;

public class IpData implements Serializable {

    /**
     * 地址
     */
    private String ipAddr;

    /**
     * 权重
     */
    private int weight;

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IpData)) {
            return false;
        }
        IpData ipData = (IpData) o;
        return Objects.equals(ipAddr, ipData.ipAddr) && Objects.equals(weight, ipData.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddr, weight);
    }
}
