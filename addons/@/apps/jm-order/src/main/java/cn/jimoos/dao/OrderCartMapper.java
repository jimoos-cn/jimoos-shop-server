package cn.jimoos.dao;

import cn.jimoos.model.OrderCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * The interface Order cart mapper.
 *
 * @author keepcleargas
 * @version 1.0 Created in 2021/4/7 11:35
 */
@Mapper
public interface OrderCartMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount int
     */
    int deleteByPrimaryKey(Long id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(OrderCart record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(OrderCart record);

    /**
     * Find one by sku id order cart.
     *
     * @param skuId the sku id
     * @return the order cart
     */
    OrderCart findOneBySkuId(@Param("skuId") Long skuId);

    /**
     * Find one by sku id and uid order cart.
     *
     * @param skuId the sku id
     * @param uid   the uid
     * @return the order cart
     */
    OrderCart findOneBySkuIdAndUid(@Param("skuId") Long skuId, @Param("uid") Long uid);


    /**
     * Find by id in list.
     *
     * @param idCollection the id collection
     * @param uid          the uid
     * @return the list
     */
    List<OrderCart> findByIdInAndUid(@Param("idCollection") Collection<Long> idCollection, @Param("uid") Long uid);

    /**
     * Find by uid order by create at desc list.
     *
     * @param uid the uid
     * @return the list
     */
    List<OrderCart> findByUidOrderByCreateAtDesc(@Param("uid") Long uid);
}
