package cn.jimoos.repository;

import cn.jimoos.dao.OrderCartMapper;
import cn.jimoos.entity.OrderCartEntity;
import cn.jimoos.model.OrderCart;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-07 17:58.
 */
@Repository
public class OrderCartRepository {
    @Resource
    OrderCartMapper orderCartMapper;

    /**
     * 保存 OrderCartEntity信息
     *
     * @param orderCartEntity order cart entity
     */
    public void save(OrderCartEntity orderCartEntity) {
        if (orderCartEntity.getId() != null && orderCartEntity.getId() > 0) {
            orderCartMapper.updateByPrimaryKey(orderCartEntity);
        } else {
            orderCartMapper.insert(orderCartEntity);
        }
    }

    /**
     * 批量删除
     *
     * @param userId  user Id
     * @param cartIds cart id
     */
    public void deleteByIds(Long userId, List<Long> cartIds) {
        orderCartMapper.deleteByUserIdAndIdIn(userId, cartIds);
    }

    /**
     * 查找 购物车 item
     *
     * @param userId user Id
     * @param skuId  sku Id
     * @return OrderCart
     */
    public OrderCartEntity findOneByUserIdAndSkuId(Long userId, Long skuId) {
        return wrapper(orderCartMapper.findOneBySkuIdAndUid(skuId, userId), false);
    }

    /**
     * 根据 ID 查找 OrderCartEntity
     *
     * @param cartId cart id
     * @return OrderCartEntity
     */
    public OrderCartEntity findOneById(Long cartId) {
        return wrapper(orderCartMapper.selectByPrimaryKey(cartId), false);
    }

    /**
     * 设置为 选中状态
     *
     * @param userId user id
     * @param skuIds sku id list
     * @return affectNum
     */
    public int updateCheckedStatus(Long userId, List<Long> skuIds) {
        return orderCartMapper.updateCheckedByUserIdAndSkuIdIn(Boolean.TRUE, userId, skuIds);
    }

    /**
     * 设置为 未选中状态
     *
     * @param userId user id
     * @param skuIds sku id list
     * @return affectNum
     */
    public int updateUnCheckStatus(Long userId, List<Long> skuIds) {
        return orderCartMapper.updateCheckedByUserIdAndSkuIdIn(Boolean.FALSE, userId, skuIds);
    }

    /**
     * orderCart的 entity wrapper方法
     *
     * @param orderCart orderCart object
     * @param skipRepo  skip repo inject
     */
    private OrderCartEntity wrapper(OrderCart orderCart, boolean skipRepo) {
        if (orderCart != null) {
            OrderCartEntity orderCartEntity;
            if (skipRepo) {
                orderCartEntity = new OrderCartEntity();
            } else {
                orderCartEntity = new OrderCartEntity(this);
            }
            BeanUtils.copyProperties(orderCart, orderCartEntity);
            return orderCartEntity;
        }
        return null;
    }
}
