package cn.jimoos.dao;

import cn.jimoos.model.ProductSkuAttrMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Mapper
public interface ProductSkuAttrMapMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductSkuAttrMap record);

    ProductSkuAttrMap selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ProductSkuAttrMap record);

    int batchInsert(@Param("list") List<ProductSkuAttrMap> list);

    List<ProductSkuAttrMap> findBySkuId(@Param("skuId") Long skuId);

    /**
     * find by skuId in
     *
     * @param skuIdCollection skuIds
     * @return List<ProductSkuAttrMap>
     */
    List<ProductSkuAttrMap> findBySkuIdIn(@Param("skuIdCollection") Collection<Long> skuIdCollection);

    /**
     * 查询 最近任何一个 使用到 attrId 的 商品
     *
     * @param attrId attr id
     * @return ProductSkuAttrMap
     */
    ProductSkuAttrMap findAnyOneByAttrId(@Param("attrId") Long attrId);

    /**
     * 查询 最近任何一个 使用到 attrValueId 的商品
     *
     * @param attrValueId attr value id
     * @return ProductSkuAttrMap
     */
    ProductSkuAttrMap findAnyOneByAttrValueId(@Param("attrValueId") Long attrValueId);
}