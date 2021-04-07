package cn.jimoos.repository;

import cn.jimoos.dao.OrderCartMapper;
import cn.jimoos.entity.OrderCartEntity;
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
}
