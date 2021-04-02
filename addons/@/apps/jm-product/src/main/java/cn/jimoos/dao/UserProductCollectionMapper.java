package cn.jimoos.dao;

import cn.jimoos.model.UserProductCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-02 13:45.
 */

@Mapper
public interface UserProductCollectionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserProductCollection record);

    UserProductCollection selectByPrimaryKey(Long id);

    int updateByPrimaryKey(UserProductCollection record);

    int batchInsert(@Param("list") List<UserProductCollection> list);

    /**
     * 查询 用户对 某商品的收藏情况
     *
     * @param userId    用户 ID
     * @param productId 商品 ID
     * @return 收藏记录
     */
    UserProductCollection findOneByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    /**
     * 获取 用户对 商品列表的收藏情况
     *
     * @param userId              用户 ID
     * @param productIdCollection product id list
     * @return List<UserProductCollection>
     */
    List<UserProductCollection> findByUserIdAndProductIdIn(@Param("userId") Long userId, @Param("productIdCollection") Collection<Long> productIdCollection);


    /**
     * 删除 用户对 某商品的 收藏
     *
     * @param userId    用户 ID
     * @param productId 商品 ID
     * @return affectNum
     */
    int deleteByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);


}