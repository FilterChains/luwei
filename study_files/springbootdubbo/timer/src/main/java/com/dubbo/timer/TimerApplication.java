package com.dubbo.timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@EnableScheduling //开启定时任务
@SpringBootApplication
public class TimerApplication {

    public static void main(String[] args) {

        SpringApplication.run(TimerApplication.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler(){
        //创建线程池
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        //设置线程池大小
        threadPoolTaskScheduler.setPoolSize(10);
        return threadPoolTaskScheduler;
    }

}
