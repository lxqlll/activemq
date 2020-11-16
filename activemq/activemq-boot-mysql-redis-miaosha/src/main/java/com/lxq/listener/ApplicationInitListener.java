package com.lxq.listener;

import com.lxq.entity.TGoods;
import com.lxq.service.TGoodsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Iterator;
import java.util.List;

/**
 * @description: 程序启动时加载要加入秒杀的商品到redis
 * @author: lxq
 * @createDate: 2020/11/14 0014
 * @version: 1.0
 */
@Log4j2
public class ApplicationInitListener implements ApplicationListener<ContextRefreshedEvent> {

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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent()== null){
            //得到ValueOperations对象
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            log.info(">>>>>>>>>>>>项目初始化完成，执行监听器中逻辑");
            //mapper中的sql，返回全部上架（支持秒杀）的商品集合
            List<TGoods> list = tGoodsService.queryAll(new TGoods(1));
            //迭代器
            Iterator<TGoods> iterator = list.iterator();
            // 判断是否有数据iterator.hasNext()
            while (iterator.hasNext()){
                //得到TGoods
                TGoods tGoods = iterator.next();
                try {
                    //插入redis
                    operations.set(String.valueOf(tGoods.getGoodsId()), String.valueOf(tGoods.getTotalStocks()));
                    log.info("商品放成Redis成功ID："+tGoods.getGoodsId()+"商品库存："+tGoods.getTotalStocks());
                } catch (Exception e) {
                    log.error("当前商品ID："+tGoods.getGoodsId()+"库存："+tGoods.getTotalStocks()+"放入Redis缓存异常<<<<<<<<<<<<<<<<<<<<");
                    e.printStackTrace();
                }
            }
        }
    }
}
