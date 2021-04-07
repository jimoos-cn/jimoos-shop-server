package cn.jimoos.dao;

import cn.jimoos.model.OrderCart;
import java.util.Collection;import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @param userId user Id
     * @param idCollection id 列表
     * @return affectNum
     */
    int deleteByUserIdAndIdIn(@Param("userId")Long userId,@Param("idCollection")Collection<Long> idCollection);

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