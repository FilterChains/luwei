//package com.luwei.util.listener;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * <p>@description : 测试 </p>
// * <p>@author : Wei.Lu</p>
// * <p>@date : 2020/7/14 11:33 </p>
// **/
//public class TestJedis {
//
//    public static void main(String[] args) {
//        JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.3.111", 6379, 2000, "n1yEl39pMQZkH");
//
//        Jedis jedis = pool.getResource();
//        jedis.set("NOTIFY", "你还在吗");
//        jedis.expire("NOTIFY", 10);
//
//        jedis.set("Hello,World", "你好吗10086");
//        jedis.expire("Hello,World", 60);
//    }
//}
