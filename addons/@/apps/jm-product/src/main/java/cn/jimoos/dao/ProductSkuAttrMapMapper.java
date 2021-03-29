package cn.jimoos.dao;

import cn.jimoos.model.ProductSkuAttrMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
}