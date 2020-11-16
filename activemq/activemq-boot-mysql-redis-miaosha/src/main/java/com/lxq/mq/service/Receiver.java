package com.lxq.mq.service;

import com.lxq.config.ActivemqConfig;
import com.lxq.entity.TGoods;
import com.lxq.entity.TOrder;
import com.lxq.service.TGoodsService;
import com.lxq.service.TOrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;
import java.util.Date;

/**
 * @description: mq消息接收服务类
 * @author: lxq
 * @createDate: 2020/11/14 0014
 * @version: 1.0
 */
@Component
@Log4j2
public class Receiver {
    /**
     * 声明StringRedisTemplate对象
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 声明TGoodsService对象
     */
    @Autowired
    private TGoodsService tGoodsService;

    /**
     * 声明TOrderService对象
     */
    @Autowired
    private TOrderService tOrderService;

    /**
     * 发送次数和消息传递
     */
    private int i;

    /**
     * 监听队列
     * @param mapMessage map类型消息体
     */
    @JmsListener(destination = ActivemqConfig.QUEUE_NAME)
    public void receive(MapMessage mapMessage){
        try{
            //收到商品ID判断redis里面的商品库存是否为0
            String goodsId = mapMessage.getString("goodsId"); //得到商品ID
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();//得到ValueOperations对象
            Long num = operations.decrement(goodsId); //decrement方法 goodsId递减
            //如果为0 抛异常
            if(num < 0){
                /**
                 * 此处不能判断等于0，因为当商品库存为1时，Redis执行递减返回为0
                 * 如果判断为0商品最后不能卖完也就是当库存为1时此处就抛异常了
                 */
                throw new Exception("库存不足，不能在抢了");
            }
            log.info("接收时>>>>>>>>>>>"+i++);

            TGoods tGoods = new TGoods();//实例化创建TGoods对象
            tGoods.setGoodsId(Integer.valueOf(goodsId));
            tGoods.setTotalStocks(Integer.valueOf(String.valueOf(num))); //哪果不为0=则减小mysql里面该商品的库存

            //如果大于0则减少mysql里面该商品的库存
            if(!tGoodsService.updateByPrimaryKeySelective(tGoods)) {  //根据商品的id和库存同步数据到MySQL
                throw new RuntimeException("同步到商品表异常！");
            }
            //生成订单
            String uid=mapMessage.getString("userId");
            //实例化创建TOrder对象
            TOrder tOrder=new TOrder();
            tOrder.setGoodsid(Integer.valueOf(goodsId));
            tOrder.setUserid(Integer.valueOf(uid));
            tOrder.setCreatetime(new Date());
            tOrderService.insert(tOrder);
            log.info("成功了>>>>>>>>>>>"+uid+"  抢到了商品 O(∩_∩)O哈哈~");
        }catch (Exception e){
            log.info("receive Exception"+e);
        }
    }

}
