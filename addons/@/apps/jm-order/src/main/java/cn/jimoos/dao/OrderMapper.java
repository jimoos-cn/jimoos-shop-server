package cn.jimoos.dao;

import cn.jimoos.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021/4/7 11:35
 */

@Mapper
public interface OrderMapper {
    int insert(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Order record);

    Order findOneByOrderNumAndUserId(@Param("orderNum") String orderNum, @Param("userId") Long userId);

    Order findOneByOrderNum(@Param("orderNum") String orderNum);

    Order findOneByUserIdAndId(@Param("userId") Long userId, @Param("id") Long id);

    /**
     * 查询过期订单
     */
    List<Order> findExpireOrders(@Param("status") Byte status,
                                 @Param("cancelDuration") Long cancelDuration,
                                 @Param("now") Long now);

    /**
     * 查询 用户 订单 列表
     *
     * @param qm ,支持 ${userId} & ${status} 的 倒序分页查询
     * @return List<Order>
     */
    List<Order> queryUserOrders(Map<String, Object> qm);

    /**
     * 获取 未支付的订单
     *
     * @param expired 过期时间
     * @param status  状态
     * @return 订单
     */
    List<Order> selectUnpaidOrders(@Param("expired") Long expired, @Param("status") int status);
}
