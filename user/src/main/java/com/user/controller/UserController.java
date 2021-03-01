package com.user.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.user.config.PushEvent;
import com.user.entity.Account;
import com.user.feign.OrderServiceFeign;
import com.user.feign.ProductServiceFeign;
import com.user.service.MQProducerService;
import com.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductServiceFeign productServiceFeign;

    @Autowired
    private OrderServiceFeign orderServiceFeign;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private Redisson redisson;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private MQProducerService mqProducerService;

    private static final String KET_REDIS = "KET_REDIS";
    private static final String RED_BALL = "RED_BALL";
    private static final String BLUE_BALL = "BLUE_BALL";

    @GetMapping(value = "mg/{msg}")
    public String sends(@PathVariable String msg) {
        mqProducerService.send(Account.builder().id(1).userId(142857)
                .used(123456).residue(789654).total(10086).msg(msg).build());
        return "发送成功";
    }

    @GetMapping(value = "lottery/{count}")
    public List<String> welfareLottery(@PathVariable long count) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            list.add("第：" + i + "注" + getStrings());
        }
        return list;
    }

    private String getStrings() {
        Set<String> redBall = new HashSet<>();
        for (int i = 1; i < 33; i++) {
            redBall.add(String.valueOf(i));
        }
        Set<String> blueBall = new HashSet<>();
        for (int i = 1; i <= 16; i++) {
            blueBall.add(String.valueOf(i));
        }
        redisTemplate.opsForSet().add(RED_BALL, redBall.toArray());
        redisTemplate.opsForSet().add(BLUE_BALL, blueBall.toArray());
        String str = " 红色: ";
        StringBuilder popRedBall = new StringBuilder();
        for (int j = 0; j < 6; j++) {
            popRedBall.append(redisTemplate.opsForSet().pop(RED_BALL)).append("、");
        }
        popRedBall.append(" 篮色: ").append(redisTemplate.opsForSet().pop(BLUE_BALL));
        return str.concat(popRedBall.toString());
    }


    @GetMapping(value = "id/{id}")
    public String findUser(@PathVariable long id) {
        return JSONUtils.toJSONString(userService.getById(id));
    }

    @GetMapping("user/{string}")
    public String test(@PathVariable String string) {

        // 新增数据
        // Map<String, String> hashMap = new HashMap<>();
        // restTemplate.postForLocation("http://product/add/",JSON.toJSONString(hashMap));
        return "User服务响应:" + string;

       /* String fromOrder = restTemplate.getForObject("http://order/order/" + string, String.class);
        String fromProduct = restTemplate.getForObject("http://product/product/" + string, String.class);
        return "访问商品服务:".concat(fromProduct).concat("—————访问订单服务:").concat(fromOrder);*/
    }


    @GetMapping(value = "msg/{id}")
    public String cloudFunction(@PathVariable long id) {

        String product = productServiceFeign.getProduct(String.valueOf(id));

        String order = orderServiceFeign.findOrder(String.valueOf(id));

        return "Product：".concat(product).concat("--------Order:").concat(order);
    }

    @GetMapping(value = "po")
    // @GlobalTransactional(name = "用户端调用商品服务和订单服务",rollbackFor = Exception.class)
    public String createMsg() {
        PushEvent.pushApplicationEvent(new ApplicationEvent(new Object()) {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });
        RLock lock = redisson.getLock(KET_REDIS);
        try {
            lock.lock(30L, TimeUnit.SECONDS);
            String port = applicationContext.getEnvironment().getProperty("server.port");
            log.error("当前USER:" + port);
            String product = productServiceFeign.createProduct();
            String order = orderServiceFeign.createOrder();
            return "Product：".concat(product).concat("--------Order:").concat(order);
        } finally {
            lock.unlock();
        }
    }
}
