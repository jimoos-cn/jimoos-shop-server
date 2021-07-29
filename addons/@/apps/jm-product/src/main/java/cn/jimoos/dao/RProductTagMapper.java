package cn.jimoos.dao;

import cn.jimoos.entity.RProductTagEntity;
import cn.jimoos.model.RProductTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Mapper
public interface RProductTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RProductTag record);

    RProductTag selectByPrimaryKey(Long id);

    int updateByPrimaryKey(RProductTag record);

    int batchInsert(@Param("list") List<RProductTag> list);

    /**
     * 统计 标签下的商品数量
     *
     * @param tagId tag id
     * @return product total num under tagId
     */
    Long countByTagId(@Param("tagId") Long tagId);

    /**
     * 移除 商品标签关联
     *
     * @param productId product Id
     * @return affectNum
     */
    int deleteByProductId(@Param("productId") Long productId);

    /**
     * 根据商品 ID 查询标签
     *
     * @param productId product Id
     * @return List<RProductTag>
     */
    List<RProductTag> findByProductId(@Param("productId") Long productId);

    /**
     * 根据商品 ID列表 查询标签
     *
     * @param productIdCollection product id collection
     * @return List<RProductTag>
     */
    List<RProductTag> findByProductIdIn(@Param("productIdCollection") Collection<Long> productIdCollection);

    /**
     * 根据tagID 和 productID 删除绑定表
     * @param productTagEntity
     * @return
     */
    int deleteByTagIdAndProductId(RProductTagEntity productTagEntity);
}
