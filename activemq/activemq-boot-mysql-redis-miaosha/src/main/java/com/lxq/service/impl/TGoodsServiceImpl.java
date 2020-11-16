package com.lxq.service.impl;

import com.lxq.entity.TGoods;
import com.lxq.mapper.TGoodsMapper;
import com.lxq.mq.service.Sender;
import com.lxq.service.TGoodsService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品(TGoods)表服务实现类
 *
 * @author makejava
 * @since 2020-11-13 17:59:53
 */
@Service("tGoodsService")
public class TGoodsServiceImpl implements TGoodsService {
    /**
     * 声明TGoodsMapper对象
     */
    @Resource
    private TGoodsMapper tGoodsMapper;
    /**
     *
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     *
     */
    @Resource
    private Sender sender;
    /**
     * 通过ID查询单条数据
     *
     * @param goodsId 主键
     * @return 实例对象
     */
    @Override
    public TGoods queryById(Object goodsId) {
        return this.tGoodsMapper.queryById(goodsId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TGoods> queryAllByLimit(int offset, int limit) {
        return this.tGoodsMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tGoods 实例对象
     * @return 实例对象
     */
    @Override
    public TGoods insert(TGoods tGoods) {
        this.tGoodsMapper.insert(tGoods);
        return tGoods;
    }

    /**
     * 修改数据
     *
     * @param tGoods 实例对象
     * @return 实例对象
     */
    @Override
    public TGoods update(TGoods tGoods) {
        this.tGoodsMapper.update(tGoods);
        return this.queryById(tGoods.getGoodsId());
    }

    /**
     * 通过主键删除数据
     *
     * @param goodsId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Object goodsId) {
        return this.tGoodsMapper.deleteById(goodsId) > 0;
    }

    /**
     * 修改方法
     *
     * @param tGoods 实例对象
     * @return 是否成功
     */
    @Override
    public boolean updateByPrimaryKeySelective(TGoods tGoods) {
        return this.tGoodsMapper.update(tGoods)>0?true:false;
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tGoods 实例对象
     * @return 对象列表
     */
    @Override
    public List<TGoods> queryAll(TGoods tGoods){
        return this.tGoodsMapper.queryAll(tGoods);
    }

    @Override
    public boolean secondsKill(String pid, String userId) {
        //ValueOperations对象
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        //查询redis
        String num = operations.get(pid);

        if(Integer.valueOf(num)>=0){
            int i = 0;
            sender.sendDirectQueue(pid,userId);
            System.out.println(""+i++);
            return true;
        }else{
            System.out.println("库存不足");
            return false;
        }


    }
}