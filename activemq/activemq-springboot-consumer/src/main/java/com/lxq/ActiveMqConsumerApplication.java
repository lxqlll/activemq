package com.lxq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description: springBootApplication
 * @author: lxq
 * @createDate: 2020/10/30
 * @version: 1.0
 */
@SpringBootApplication
@EnableJms //启用JMS
@EnableScheduling
public class ActiveMqConsumerApplication {

    //springboot启动方法
    public static void main(String[] args) {
        SpringApplication.run(ActiveMqConsumerApplication.class,args);
        System.out.println("消费者启动成功-queue!");
    }
}
