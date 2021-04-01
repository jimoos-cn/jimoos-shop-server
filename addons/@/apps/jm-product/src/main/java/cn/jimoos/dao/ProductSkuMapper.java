package cn.jimoos.dao;

import cn.jimoos.model.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Mapper
public interface ProductSkuMapper {

    int insert(ProductSku record);

    ProductSku selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ProductSku record);

    int batchInsert(@Param("list") List<ProductSku> list);

    List<ProductSku> findByProductId(@Param("productId") Long productId);

    /**
     * 查找 任何一个 SKU
     *
     * @param productId product Id
     * @return ProductSku
     */
    ProductSku findAnyOneByProductId(@Param("productId") Long productId);


    /**
     * 更新商品的 SKU 删除状态
     *
     * @param updatedDeleted 删除状态
     * @param productId      product Id
     * @return affectNum
     */
    int updateDeletedByProductId(@Param("updatedDeleted") Boolean updatedDeleted, @Param("productId") Long productId);

}