package cn.jimoos.dao;

import cn.jimoos.model.RCollectionProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:17.
 */
@Mapper
public interface RCollectionProductMapper {
    int insert(RCollectionProduct record);

    RCollectionProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKey(RCollectionProduct record);

    int batchInsert(@Param("list") List<RCollectionProduct> list);

    int deleteById(@Param("id")Long id);

    /**
     * find one by collectionId and productId
     *
     * @param collectionId 集合 ID
     * @param productId    商品 ID
     * @return RCollectionProduct
     */
    RCollectionProduct findOneByCollectionIdAndProductId(@Param("collectionId") Long collectionId, @Param("productId") Long productId);

    int deleteByCollectionId(@Param("collectionId")Long collectionId);


    /**
     * 查询 RCollectionProduct 列表
     *
     * @param qm ,支持 ${collectionId} 的 倒序分页查询
     * @return List<RCollectionProduct>
     */
    List<RCollectionProduct> queryTable(Map<String, Object> qm);

    /**
     * 查询 RCollectionProduct 总数
     *
     * @param qm ,支持 ${collectionId} 的 倒序分页查询
     * @return long total
     */
    long queryTableCount(Map<String, Object> qm);
}