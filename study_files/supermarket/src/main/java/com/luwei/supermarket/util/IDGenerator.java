package com.luwei.supermarket.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>@Description : 订单号生成器</p>
 * <p>@Author : QiLin.Xing </p>
 * <p>@Date : 2019/10/24 0024 下午 15:24 </p>
 */
public class IDGenerator {

    /**
     * 订单号类型枚举
     */
    public enum Type {
        /**
         * 订单编号
         */
        ORDER_NO(""),
        /**
         * 联合订单编号
         */
        UNION_ORDER_NO("U"),
        /**
         * 退款单订单号
         */
        REFUND_ORDER_NO("R"),
        /**
         * 充值订单号
         */
        RECHARGE_ORDER_NO("C"),
        /**
         * 申请普通控销权限订单号
         */
        CONTROL_ORDER_NO("S"),
        /**
         * 保证金退款单编号
         */
        CONTROL_REFUND_ORDER_NO("H"),
        /**
         * 集团账号一键还款订单号
         */
        RECHARGE_GROUP_ORDER_NO("G"),
        /**
         * 导入订单订单号
         */
        IMPORT_ORDER_NO("L"),
        /**
         * 补费订单编号
         */
        COMPENSATION_ORDER_NO("B"),
        /**
         * 预购订单编号
         */
        PRE_ORDER_NO("P"),
        /**
         * 发货单编号
         */
        SHIP_ORDER_NO("F"),
        /**
         * 供应商提现单编号
         */
        SUPPLY_WITHDRAW("W");

        private String value;

        Type(String value) {
            this.value = value;
        }
    }

    /**
     * 起始的时间戳 (2019-9-27 00:00:00)
     */
    private static final long START_STAMP = 1569513600000L;

    /**
     * 序列ID所占位数
     */
    private static final long SEQUENCE_BIT = 12L;

    /**
     * 机器ID所占位数，支持0-511
     */
    private static final long MACHINE_BIT = 9L;

    /**
     * 数据标识ID所占位数，支持0-1，固定为1
     */
    private static final long DATA_CENTER_BIT = 1L;

    /**
     * 支持最大机器ID，结果511
     */
    private static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);

    /**
     * 生成序列的掩码 4095
     */
    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 机器ID向左偏移12位
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;

    /**
     * 数据标识ID向左偏移21位(12+9)
     */
    private static final long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;

    /**
     * 时间戳向左偏移22位(12+9+1)
     */
    private static final long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 数据标识ID默认为1
     */
    private long dataCenterId = 1L;

    /**
     * 机器ID，通过方式获取：
     * 一：通过启动参数-DSnowFlake.machineId传递
     * 二：通过获取本地IP地址，最后两位相加
     */
    private long machineId;

    /**
     * 毫秒内序列
     */
    private long sequence = 0L;

    /**
     * 上一次生成ID的时间戳
     */
    private long lastStamp = -1L;

    public IDGenerator() {
        String strMachineId = System.getProperty("SnowFlake.machineId");
//        logger.debug("动态参数-DSnowFlake.machineId：[{}]", machineId);
        if (strMachineId == null || "".equals(strMachineId)) {
            InetAddress addr = null;
            try {
                addr = InetAddress.getLocalHost();
                String[] ips = addr.getHostAddress().split("\\.");
                this.machineId = ConvertUtils.toLong(ips[2], -1L) + ConvertUtils.toLong(ips[3], -1L);
//                this.machineId = ConvertUtils.toLong(ips[2], -1L);
                if (this.machineId > MAX_MACHINE_NUM || machineId < 0) {
                    throw new IllegalArgumentException("获取到服务器IP失败，初始化ID生成器失败，请手动设置启动参数：-DSnowFlake.machineId，取值范围[0-" + MAX_MACHINE_NUM + "]");
                }
            } catch (UnknownHostException e) {
                throw new IllegalArgumentException("没有获取到服务器IP，初始化ID生成器失败，请手动设置启动参数：-DSnowFlake.machineId，取值范围[0-" + MAX_MACHINE_NUM + "]");
            }
        } else {
            this.machineId = ConvertUtils.toLong(strMachineId, -1L);
            if (this.machineId > MAX_MACHINE_NUM || machineId < 0) {
                throw new IllegalArgumentException("启动参数[-DSnowFlake.machineId]设置错误，请设置0-" + MAX_MACHINE_NUM + "范围的数字");
            }
        }
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    private synchronized long nextId() {
        long currStamp = getNewStamp();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (currStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (currStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStamp;

        return ((currStamp - START_STAMP) << TIMESTAMP_LEFT) | (dataCenterId << DATA_CENTER_LEFT) | (machineId << MACHINE_LEFT) | sequence;
    }

    private long getNextMill() {
        long mill = getNewStamp();
        while (mill <= lastStamp) {
            mill = getNewStamp();
        }
        return mill;
    }

    private long getNewStamp() {
        return System.currentTimeMillis();
    }

    public String generateNo(Type type) {
        return String.join("", type.value, String.valueOf(this.nextId()));
    }

    public long generateId() {
        return this.nextId();
    }
}
