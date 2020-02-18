package com.luwei.dubbo_consumer.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.luwei.entity.User;
import com.luwei.redisconfig.RedisUtil;
import com.luwei.service.MongoDbService;
import com.luwei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @projectName： springbootdubbo
 * @packageName: com.luwei.dubbo_consumer.controller
 * @auther: luwei
 * @description:
 * @date: 2020/1/23 19:54
 * @alert: This document is private to luwei
 * @version: 1.8.00_66
 */
@RestController
public class UserController {

    @Reference
    private UserService userService;

    @Reference
    private MongoDbService mongoDbService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/getAllUser") //redis 存储信息
    public List<User> getAllUsers(String userName, String userPassword) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture<List<User>> supplyAsync = CompletableFuture.supplyAsync(new Supplier<List<User>>() {
            @Override
            public List<User> get() {
                return userService.getAllUser(userName, userPassword);
            }
        }, executorService);
        List<User> list = null;
        try {
            list = supplyAsync.get();
            RedisUtil redisUtil = new RedisUtil(redisTemplate);
            redisUtil.setRedisList("list", list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            executorService.shutdownNow();
        }
        System.out.println("进来了！！！！！！！！！");
        executorService.shutdown();
        return list;
    }

    @GetMapping("/getUserMsg") //从redis获取信息
    public List<User> getUserMessage(HttpServletRequest request) {
        RedisUtil redisUtil = new RedisUtil(redisTemplate);
        //获取请求IP地址
        String ipAddr = getIpAddr(request);
        String validateIp = (String) redisUtil.getRedisString("validateIp");
        if (StringUtils.isNotEmpty(validateIp) && validateIp.equals(ipAddr)) {
            return Lists.newArrayList(User.builder().username("请勿重复请求").build());
        }
        redisUtil.setRedisString("validateIp", ipAddr);
        /*//获取redis里集合List的长度
        long listLen = redisUtil.getRedisListSize("list");
        //获取redis集合List指定下标index的值value
        User listValue = (User) redisUtil.getRedisListValue("list", 10);
        //向redis里添加list集合
        boolean value = redisUtil.setRedisListValue("list", User.builder().id(1000)
                .username("测试Redis最后数据").password("测试密码").moneys(new BigDecimal("10000.99"))
                .build());

        boolean b = redisUtil.setRedisListUpdateIndexValue("list", 1,
                User.builder()
                        .id(889).username("修改Redis里对应的User")
                        .password("修改的密码").moneys(new BigDecimal("989898.89"))
                        .build());
        long indexValue = redisUtil.setRedisListRemoveIndexValue("list", 1, User.builder().id(1000)
                .username("测试Redis最后数据").password("测试密码").moneys(new BigDecimal("10000.99"))
                .build());
        boolean expire = redisUtil.setRedisListValueExpire("list", User.builder().id(1000)
                .username("测试Redis最后数据").password("测试密码").moneys(new BigDecimal("10000.99"))
                .build(), 10);*/
        List<User> list = (List<User>) redisUtil.getRedisList("list", 0, -1);
        System.out.println("获取redis信息:" + list);
        redisUtil.deleteKeys("validateIp");
        return list;
    }


    @GetMapping("/sendMessage") //测试rabbitmq
    public String sendRabbitMq() {
        userService.sendRabbitMqMessage("HELLO");
        return "成功";
    }

    @RequestMapping("/findUser")
    public List<User> findUser() {
        System.out.println("请求进来了======");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("开始执行下一个任务");
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletableFuture.runAsync(()-> {
            try {
                System.out.println("开始执行插入任务====");
                Thread.sleep(10000);
                System.out.println("执行新增任务成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },executorService);
        executorService.shutdown();
        System.out.println("查看线程是否关闭"+executorService.isShutdown());
        System.out.println("跳过异步");
        return mongoDbService.save(User.builder()
                .id(999).username("小念").password("PWD142857")
                .moneys(new BigDecimal("9999.99")).build());
    }


    /**
     * @Title: getIpAddr
     * @Description: 获取请求Ip地址
     * @Param: [request]   参数
     * @Return: java.lang.String   返回类型
     * @Date: 2020/1/28 22:03
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
