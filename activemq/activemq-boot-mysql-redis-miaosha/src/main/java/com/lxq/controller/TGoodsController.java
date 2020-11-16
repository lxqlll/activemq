package com.lxq.controller;

import com.lxq.entity.TGoods;
import com.lxq.service.TGoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品(TGoods)表控制层
 *
 * @author makejava
 * @since 2020-11-13 17:59:53
 */
@RestController
@RequestMapping("tGoods")
public class TGoodsController {
    /**
     * 服务对象
     */
    @Resource
    private TGoodsService tGoodsService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TGoods selectOne(Object id) {
        return this.tGoodsService.queryById(id);
    }

}