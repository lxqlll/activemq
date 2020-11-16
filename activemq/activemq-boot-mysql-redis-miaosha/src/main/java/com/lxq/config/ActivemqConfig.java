package com.lxq.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @description: mq配置类
 * @author: lxq
 * @createDate: 2020/11/14 0014
 * @version: 1.0
 */
@Configuration
public class ActivemqConfig {
    /**
     * 定义队列名称
     */
    public static final String QUEUE_NAME = "seconds-kill-queue";

    /**
     * 创建Queue bean
     * @return
     */
    @Bean
    public Queue queue(){
        return new ActiveMQQueue(QUEUE_NAME);
    }
}