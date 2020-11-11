package com.lxq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @description:
 * @author: lxq
 * @createDate: 2020/11/11 0011
 * @version: 1.0
 */
public class TopicProducer {
    private static String brokerURL="tcp://127.0.0.1:61616";
    private static String TopicName="topic-hello";
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TopicName);
        MessageProducer producer = session.createProducer(topic);
        for (int i = 0; i <10; i++) {
            TextMessage textMessage = session.createTextMessage("message" + i);
            producer.send(textMessage);
        }
        producer.close();
        connection.close();
        session.close();
        System.out.println("发送");
    }
}
