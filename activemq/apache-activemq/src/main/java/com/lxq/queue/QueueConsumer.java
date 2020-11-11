package com.lxq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @description: activemq简单测试入门案列-消费者
 * @author: lxq
 * @createDate: 2020/11/11
 * @version: 1.0
 */
public class QueueConsumer  {

    //定义activemq的暴露接口
    private static String brokerURL = "tcp://127.0.0.1:61616";
    //自定义队列的名称
    private static String queueName = "queue-test";

    public static void main(String[] args)  {
        try{
            //第一步，创建ConnectionFactory对象，需要指定服务端ip和端口
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
            //第二步，使用ConnectionFactory对象创建Connection对象
            Connection connection = connectionFactory.createConnection();
            //第三步，开启连接，调用connection的启动对象
            connection.start();
            //第四步，使用connection对象创建一个session对象
            /**
             * 参数一:是否开启事务
             * 参数二：
             * AUTO_ACKNOWLEDGE = 1 ：自动确认
             * CLIENT_ACKNOWLEDGE = 2：客户端手动确认
             * DUPS_OK_ACKNOWLEDGE = 3： 自动批量确认(用lazy acknowledge)
             * 一般使用第一种就行了；
             * 如果严格要求消息不丢失则选择第二种，客户端要在消费完消息后手动确认，需要代码中处理确认，并防消息重复，
             * 有一定代码开发量；第三种采用懒确认(lazy acknowledge)优化了确认消息的性能，处理后不会立即确认，
             * 因此会有消息重复发送的问题，需要注意处理。
             */
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            //第五步，使用Session对象创建一个Destination(Destination和Queue关系是接口/实现类)对象
            Queue queue =  session.createQueue(queueName);
            //第六步，使用Session对象创建一个Consumer对象
            MessageConsumer messageConsumer = session.createConsumer(queue);
            //第七步，监听消息
            Message receive = messageConsumer.receive();
            //这里循环为true标识一直运行，利用程序阻塞来一直接受消息，所以下面资源不关闭
            while(true){
                if (receive instanceof TextMessage){
                    TextMessage textMessage = (TextMessage)receive;
                    System.out.println("接受消息内容为"+textMessage.getText());
                }
                receive = messageConsumer.receive();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //第九步,关闭资源 ,
//        connection.close();
//        session.close();
//        messageConsumer.close();
//        System.out.println("信息接收成功");
    }

}
