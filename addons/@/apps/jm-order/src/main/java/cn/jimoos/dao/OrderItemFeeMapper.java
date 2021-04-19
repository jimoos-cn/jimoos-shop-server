package cn.jimoos.dao;

import cn.jimoos.model.OrderItemFee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * The interface Order item fee mapper.
 *
 * @author keepcleargas
 * @version 1.0 Created in 2021/4/7 11:35
 */
@Mapper
public interface OrderItemFeeMapper {
    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(OrderItemFee record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(OrderItemFee record);

    /**
     * Batch insert int.
     *
     * @param list the list
     * @return the int
     */
    int batchInsert(@Param("list") List<OrderItemFee> list);

    /**
     * Find list by order id .
     *
     * @param orderId the order id
     * @return the list
     */
    List<OrderItemFee> findByOrderId(@Param("orderId") Long orderId);

    /**
     * Find list by order id list.
     *
     * @param orderIdCollection orderId list
     * @return this list
     */
    List<OrderItemFee> findByOrderIdIn(@Param("orderIdCollection") Collection<Long> orderIdCollection);
}
