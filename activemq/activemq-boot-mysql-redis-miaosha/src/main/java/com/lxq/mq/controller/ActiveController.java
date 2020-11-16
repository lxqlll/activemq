package com.lxq.mq.controller;

import com.lxq.listener.ApplicationInitListener;
import com.lxq.result.Response;
import com.lxq.result.Result;
import com.lxq.result.ResultEnum;
import com.lxq.service.TGoodsService;
import com.lxq.utils.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Map;

/**
 * @description: mq控制层
 * @author: lxq
 * @createDate: 2020/11/14 0014
 * @version: 1.0
 */
@RequestMapping(value = "/mq")
@RestController
public class ActiveController {
    /**
     * 声明TGoodsService对象
     */
    @Autowired
    private TGoodsService tGoodsService;

    /**
     * 秒杀入口
     * @param pid -商品id，做检查库存使用
     * @param userId -用户id，做订单和用户关联使用（比如生成成功秒杀商品的用户订单表）
     *                我这里没做多余的逻辑，只看了相关情况的返回结果，有需要的可以自己去实现
     */
    @RequestMapping(value = "secondsKill")
     public Result secondsKill(@PathParam("pid") String pid, @PathParam("userId") String userId){

        //for(int i=0; i<100; i++) {
        boolean result = tGoodsService.secondsKill(pid, userId);

        if(result==false){
            return Response.error();
        }else{
            return Response.ok();
        }
     }


    @RequestMapping(value = "test")
    public Result test(String pid, String userId){
        tGoodsService.queryAll(null);

        return Response.error();
    }

}
