package cn.jimoos.dao;

import cn.jimoos.model.OrderCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-07 18:19.
 */

@Mapper
public interface OrderCartMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderCart record);

    OrderCart selectByPrimaryKey(Long id);

    int updateByPrimaryKey(OrderCart record);

    int batchInsert(@Param("list") List<OrderCart> list);

    /**
     * 按 ID列表批量删除
     *
     * @param userId       user Id
     * @param idCollection id 列表
     * @return affectNum
     */
    int deleteByUserIdAndIdIn(@Param("userId") Long userId, @Param("idCollection") Collection<Long> idCollection);

    /**
     * Find one by sku id order cart.
     *
     * @param skuId the sku id
     * @return the order cart
     */
    OrderCart findOneBySkuId(@Param("skuId") Long skuId);

    /**
     * 更新用户 购物车 选中状态
     *
     * @param updatedChecked  选中状态
     * @param userId          user Id
     * @param skuIdCollection sku id collection
     * @return affectNum
     */
    int updateCheckedByUserIdAndSkuIdIn(@Param("updatedChecked") Boolean updatedChecked, @Param("userId") Long userId, @Param("skuIdCollection") Collection<Long> skuIdCollection);


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
     * find by user Id
     *
     * @param userId user Id
     * @param offset offset
     * @param limit  limit
     * @return List<OrderCart>
     */
    List<OrderCart> findByUserId(@Param("userId") Long userId, @Param("offset") int offset, @Param("limit") int limit);


}