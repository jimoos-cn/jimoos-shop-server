package cn.jimoos.dao;

import cn.jimoos.model.OrderItemDiscount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Order item discount mapper.
 *
 * @author keepcleargas
 * @version 1.0 Created in 2021/4/7 11:35
 */
@Mapper
public interface OrderItemDiscountMapper {
    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(OrderItemDiscount record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(OrderItemDiscount record);

    /**
     * Batch insert int.
     *
     * @param list the list
     * @return the int
     */
    int batchInsert(@Param("list") List<OrderItemDiscount> list);

    /**
     * Find by order id list.
     *
     * @param orderId the order id
     * @return the list
     */
    List<OrderItemDiscount> findByOrderId(@Param("orderId") Long orderId);
}
