package cn.jimoos.dao;

import cn.jimoos.model.OrderRemindDelivery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderRemindDeliveryMapper {
    int insert(OrderRemindDelivery record);

    int updateByPrimaryKey(OrderRemindDelivery record);

    List<OrderRemindDelivery> findByOrderId(@Param("orderId") Long orderId);
}
