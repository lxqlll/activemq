package com.lxq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @description: activemq简单测试入门案列-生产者 对应QueueConsumer类
 * @author: lxq
 * @createDate: 2020/11/11 0011
 * @version: 1.0
 */
public class QueueProducre {

    private static String brokerURL = "tcp://127.0.0.1:61616";
    private static String queueName = "queue-hello";

    public static void main(String[] args) throws JMSException {
        //第一步，创建ConnectionFactory对象，需要指定服务端ip和端口
        ConnectionFactory factory =  new ActiveMQConnectionFactory(brokerURL);
        //第二步，使用ConnectionFactory对象创建Connection对象
        Connection connection = factory.createConnection();
        //第三步，开启连接，调用connection的启动对象
        connection.start();
        //第四步，使用connection对象创建一个session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //第五步，使用Session对象创建一个Destination对象
        Queue queue =  session.createQueue(queueName);
        //第六步，使用Session对象创建一个Producer对象
        MessageProducer messageProducer = session.createProducer(queue);
        //第七步，创建一个message对象,创建一个TextMessage对象
        TextMessage textMessage = session.createTextMessage();
        //向消息里面插入内容
        textMessage.setText("hello-mq");
        //第八步，使用Producer对象发送消息
        messageProducer.send(textMessage);
        //第九步,关闭资源
        connection.close();
        session.close();
        messageProducer.close();
        System.out.println("信息发送成功");
    }
}
