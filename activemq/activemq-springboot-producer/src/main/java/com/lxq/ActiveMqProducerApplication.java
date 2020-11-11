package com.lxq;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * @description: springBootApplication
 * @author: lxq
 * @createDate: 2020/10/30
 * @version: 1.0
 */
@SpringBootApplication
@EnableJms //启用JMS
public class ActiveMqProducerApplication {

    //springboot启动方法
    public static void main(String[] args) {


        SpringApplication.run(ActiveMqProducerApplication.class,args);
        System.out.println("生产者启动成功!");
    }
}
