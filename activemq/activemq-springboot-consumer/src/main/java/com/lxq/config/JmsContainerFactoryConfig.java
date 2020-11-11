package com.lxq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;


/**
 * @description:
 * @author: lxq
 * @createDate: 2020/10/30 0030
 * @version: 1.0
 */
@Configuration
public class JmsContainerFactoryConfig {

    /**
     *  jmsTopicContainerFactory对应containerFactory = "jmsTopicContainerFactory"
     */
    @Bean(value = "jmsTopicContainerFactory")
    public DefaultJmsListenerContainerFactory jmsTopicContainerFactory(ConnectionFactory factory){

        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory
                                                         = new DefaultJmsListenerContainerFactory();
        //设置对主题的监听
        defaultJmsListenerContainerFactory.setConnectionFactory(factory);

        defaultJmsListenerContainerFactory.setPubSubDomain(true);
        return defaultJmsListenerContainerFactory;
    }
}
