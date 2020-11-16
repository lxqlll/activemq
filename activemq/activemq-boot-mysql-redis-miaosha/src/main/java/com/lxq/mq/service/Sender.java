package com.lxq.mq.service;

import com.lxq.config.ActivemqConfig;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: mq消息发送服务类
 * @author: lxq
 * @createDate: 2020/11/14 0014
 * @version: 1.0
 */
@Service
@Log4j2
public class Sender {

    /**
     * 注入JmsMessagingTemplate对象 消息体
     */
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    /**
     * 发送次数和消息传递
     */
    public   int i;
    /**
     * 如果用户抢到商品发送到队列去
     * @param goodsId 商品编号
     * @param userId  用户编号
     */
    public void sendDirectQueue(String goodsId,String userId){
        try{
            log.info("------------->秒杀请求已发送，商品ID为："+goodsId+"--用户ID为："+userId);
            //创建Map集合
            Map<String,Object> map = new HashMap<>();
            map.put("goodsId",goodsId);
            map.put("userId",userId);
            /**
             * 参数ActivemqConfig.QUEUE_NAME：是指要发送的那个队列
             * 参数map：要发送的内容
             */
            jmsMessagingTemplate.convertAndSend(ActivemqConfig.QUEUE_NAME,map);
            //发送次数和消息传递
            log.info("发送请求>>>>>"+i++);
        }catch (Exception e){
            log.info("sendDirectQueue Exception："+e);
        }
    }
}
