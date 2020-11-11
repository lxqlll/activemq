package com.lxq.topic;

import lombok.SneakyThrows;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @description:
 * @author: lxq
 * @createDate: 2020/11/11 0011
 * @version: 1.0
 */
public class TopicConsumer {

    private static String brokerURL="tcp://0.0.0.0:61616";
    private static String TopicName="topic-hello";

    public static void main(String[] args) throws Exception {

        ConnectionFactory factory = new ActiveMQConnectionFactory(brokerURL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TopicName);
        MessageConsumer consumer = session.createConsumer(topic);
        //监听
        consumer.setMessageListener(new MessageListener() {
            @SneakyThrows
            public void onMessage(Message message) {
                if (message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println("接收"+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        connection.close();
        session.close();
    }
}
