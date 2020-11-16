package com.lxq.service;

import com.lxq.entity.TGoods;
import java.util.List;

/**
 * 商品(TGoods)表服务接口
 *
 * @author makejava
 * @since 2020-11-13 17:59:52
 */
public interface TGoodsService {

    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    TGoods queryById(Object goodsId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TGoods> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tGoods 实例对象
     * @return 实例对象
     */
    TGoods insert(TGoods tGoods);

    /**
     * 修改数据
     *
     * @param tGoods 实例对象
     * @return 实例对象
     */
    TGoods update(TGoods tGoods);

    /**
     * 通过主键删除数据
     *
     * @param goodsId 主键
     * @return 是否成功
     */
    boolean deleteById(Object goodsId);

    /**
     * 修改方法
     *
     * @param tGoods 实例对象
     * @return 是否成功
     */
    boolean updateByPrimaryKeySelective(TGoods tGoods);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tGoods 实例对象
     * @return 对象列表
     */
    List<TGoods> queryAll(TGoods tGoods);


    boolean secondsKill(String pid, String userId);
}