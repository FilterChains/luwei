//package com.user.util.listener;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * <p>@description : 订阅者 </p>
// * <p>@author : Wei.Lu</p>
// * <p>@date : 2020/7/14 11:32 </p>
// **/
//public class Subscriber {
//
//    public static void main(String[] args) {
//        JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.3.111", 6379, 2000, "n1yEl39pMQZkH");
//
//        Jedis jedis = pool.getResource();
//        jedis.psubscribe(new KeyExpiredListener(), "__key*__:*");
//    }
//
//}
