package com.luwei.dubbo_consumer.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.luwei.entity.User;
import com.luwei.excelutils.ExcelException;
import com.luwei.excelutils.ExcelUtil;
import com.luwei.redisconfig.RedisUtil;
import com.luwei.service.MongoDbService;
import com.luwei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
        UserExcel listValue = (UserExcel) redisUtil.getRedisListValue("list", 10);
        //向redis里添加list集合
        boolean value = redisUtil.setRedisListValue("list", UserExcel.builder().id(1000)
                .username("测试Redis最后数据").password("测试密码").moneys(new BigDecimal("10000.99"))
                .build());

        boolean b = redisUtil.setRedisListUpdateIndexValue("list", 1,
                UserExcel.builder()
                        .id(889).username("修改Redis里对应的User")
                        .password("修改的密码").moneys(new BigDecimal("989898.89"))
                        .build());
        long indexValue = redisUtil.setRedisListRemoveIndexValue("list", 1, UserExcel.builder().id(1000)
                .username("测试Redis最后数据").password("测试密码").moneys(new BigDecimal("10000.99"))
                .build());
        boolean expire = redisUtil.setRedisListValueExpire("list", UserExcel.builder().id(1000)
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

    @RequestMapping("/checkVersion")
    public User updateUserCheckVersion(boolean temp){
        /**
         * ==========数据库实现乐观锁+version字段控制并发========
         * 银行两操作员同时操作同一账户。
         * 比如A、B操作员同时读取一余额为1000元的账户，A操作员为该账户增加100元，
         * B操作员同时为该账户扣除50元，A先提交，B后提交。最后实际账户余额为1000-50=950元，
         * 但本该为1000+100-50=1050。这就是典型的并发问题。
         * ==================================================
         *对于上面修改用户帐户信息的例子而言，假设数据库中帐户信息表中有一个version字段，当前值为1；
         * 而当前帐户余额字段(balance)为1000元。假设操作员A先更新完，操作员B后更新。
         * a、操作员A此时将其读出(version=1)，并从其帐户余额中增加100(1000+100=1100)。
         * b、在操作员A操作的过程中，操作员B也读入此用户信息(version=1)，并从其帐户余额中扣除50(1000-50=950)。
         * c、操作员A完成了修改工作，连同帐户增加后余额(1100)，提交至数据库更新，此时，数据库记录version更新为2(version=2)。
         * d、操作员B完成了操作，试图向数据库提交数据(950)，但此时比对数据库记录版本时发现，操作员B提交的数据版本号为1，
         * 数据库记录当前版本为2，不满足 “提交版本必须等于记录当前版本才能执行更新 “的乐观锁策略，
         * 因此，操作员B的提交被驳回。这样，就避免了操作员B用基于version=1的旧数据修改的结果覆盖操作员A的操作结果的可能。
         * ==================================================
         * 使用数据版本（Version）记录机制实现，这是乐观锁最常用的一种实现方式。
         * 何谓数据版本？即为数据增加一个版本标识，一般是通过为数据库表增加一个数字类型的 “version” 字段来实现。
         * 当读取数据时，将version字段的值一同读出，数据每更新一次，对此version值加一。当我们提交更新的时候，
         * 判断数据库表对应记录的当前版本信息与第一次取出来的version值进行比对，
         * 如果数据库表当前版本号与第一次取出来的version值相等，则予以更新，否则认为是过期数据
         */
        User build =  userService.getById(User.builder().id(1).build());
        BigDecimal moneys = build.getMoneys(); //并发同时获取账户余额1000元
        if(temp){
            build.setMoneys(moneys.add(new BigDecimal("100"))); //加100
            boolean a = userService.updateById(build);
            System.err.println("银行操作员A:正在执行任务"+a);
            build.setMoneys(moneys.subtract(new BigDecimal("50"))); //减50
            boolean b = userService.updateById(build);
            System.err.println("银行操作员B:正在执行任务"+b);
        }else{
            //版本控制后修改
            build.setMoneys(moneys.add(new BigDecimal("100"))); //加100
            Boolean a = userService.updateUserMsgCheckVersion(build);
            System.err.println("银行操作员A:正在执行任务"+a);
            while(!a){
                User byId = userService.getById(User.builder().id(1).build());
                byId.setMoneys(byId.getMoneys().add(new BigDecimal("100")));
                a = userService.updateUserMsgCheckVersion(byId);
                System.err.println("银行操作员A:再一次执行任务"+a);
            }
            build.setMoneys(moneys.subtract(new BigDecimal("50"))); //减50
            Boolean b = userService.updateUserMsgCheckVersion(build);
            System.err.println("银行操作员B:正在执行任务"+b);
            while(!b){
                User byId = userService.getById(User.builder().id(1).build());
                byId.setMoneys(byId.getMoneys().subtract(new BigDecimal("50")));
                b = userService.updateUserMsgCheckVersion(byId);
                System.err.println("银行操作员B:再一次执行任务"+b);
            }

        }
        return userService.getById(User.builder().id(1).build());
    }


    @PostMapping("/uploadFile")
    public List<GroupTest> uploadFiles(MultipartFile files){
        List<?> list = null;
        try {
            list = ExcelUtil.readSingleTitleExcel(files,GroupTest.class);
            System.err.println(list);
        } catch (Exception e) {
            System.out.println(e.getCause());
            System.err.println(e.getMessage());
        }
        return (List<GroupTest>) list;
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
