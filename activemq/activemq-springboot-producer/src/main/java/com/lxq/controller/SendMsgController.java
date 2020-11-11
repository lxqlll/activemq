package com.lxq.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 对外暴露接口
 * @author: lxq
 * @createDate: 2020/10/30
 * @version: 1.0
 */
@RestController
public class SendMsgController {

    /**
     * 声明JmsTemplate对象，注入Spring容器
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 发送队列
     */
    @RequestMapping(value = "sendQueue",method = RequestMethod.GET)
    public Map<String,Object> sendQueue(){
        //实例化创建Map集合
        Map<String,Object> map = new HashMap<>();
        //设置queue名称
        jmsTemplate.setDefaultDestination(new ActiveMQQueue("queue-hello"));
        //发送
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello-queue-msg");
            }
        });
        map.put("success","发送成功！");
        return map;
    }

    /**
     * 发送主题
     */
    @RequestMapping(value = "sendTopic",method = RequestMethod.GET)
    public Map<String,Object> sendTopic(){
        //实例化创建Map集合
        Map<String,Object> map = new HashMap<>();
        //设置queue名称
        jmsTemplate.setDefaultDestination(new ActiveMQTopic("topic-hello"));
        //发送
        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello-topic-msg");
            }
        });
        map.put("success","发送成功！");
        return map;
    }
}
