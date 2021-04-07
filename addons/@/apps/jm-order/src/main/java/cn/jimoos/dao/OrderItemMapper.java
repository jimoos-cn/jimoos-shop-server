package cn.jimoos.dao;

import cn.jimoos.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Order item mapper.
 *
 * @author :keepcleargas
 * @date :2021/4/7 11:35
 */
@Mapper
public interface OrderItemMapper {

    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(OrderItem record);

    /**
     * Batch insert int.
     *
     * @param list the list
     * @return the int
     */
    int batchInsert(@Param("list") List<OrderItem> list);

    /**
     * Find by order id list.
     *
     * @param orderId the order id
     * @return the list
     */
    List<OrderItem> findByOrderId(@Param("orderId") Long orderId);
}
