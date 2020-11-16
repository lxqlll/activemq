package com.lxq.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 商品(TGoods)实体类
 *
 * @author makejava
 * @since 2020-11-13 17:59:50
 */
@Data
@NoArgsConstructor
public class TGoods implements Serializable {
    private static final long serialVersionUID = 289336762599229746L;
    /**
    * 商品ID
    */
    private Integer goodsId;
    /**
    * 商品名称
    */
    private String goodsName;
    /**
    * 店铺id
    */
    private Long shopId;
    /**
    * 原价
    */
    private Double oriPrice;
    /**
    * 现价
    */
    private Double price;
    /**
    * 简要描述,卖点等
    */
    private String brief;
    /**
    * 详细描述
    */
    private String content;
    /**
    * 商品主图
    */
    private String picture;
    /**
    * 商品图片，以,分割
    */
    private String imgs;
    /**
    * 默认是1，表示正常状态, -1表示删除, 0下架
    */
    private Integer status;
    /**
    * 商品分类
    */
    private Integer categoryId;
    /**
    * 销量
    */
    private Integer soldNum;
    /**
    * 总库存
    */
    private Integer totalStocks;
    /**
    * 配送方式json见TransportModeVO
    */
    private Object deliveryMode;
    /**
    * 运费模板id
    */
    private Long deliveryTemplateId;
    /**
    * 录入时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date updateTime;
    /**
    * 上架时间
    */
    private Date putawayTime;
    /**
    * 版本 乐观锁
    */
    private Integer version;
    /**
    * 是否参与秒杀1是0否
    */
    private Integer ismiaosha;

    /**
     * mapper中的sql，返回全部上架（支持秒杀）的商品集合
     * @param ismiaosha  是否参与秒杀1是0否
     */
    public TGoods(Integer ismiaosha){
        ismiaosha = this.ismiaosha;
    }
}