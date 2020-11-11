package com.lxq.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @description:监听器
 * @author: lxq
 * @createDate: 2020/10/30
 * @version: 1.0
 */
@Component
public class MsgListener {

    @JmsListener(destination = "queue-hello")
    public void receive1(TextMessage textMessage) throws JMSException {
        System.out.println("queue消费者接受信息："+textMessage.getText());
    }

    @JmsListener(destination = "topic-hello",containerFactory = "jmsTopicContainerFactory")
    public void receive2(TextMessage textMessage) throws JMSException {
        System.out.println("topic消费者接受信息："+textMessage.getText());
    }
}
