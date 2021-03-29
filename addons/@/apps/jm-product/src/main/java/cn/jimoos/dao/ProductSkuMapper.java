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
    int deleteByPrimaryKey(Long id);

    int insert(ProductSku record);

    ProductSku selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ProductSku record);

    int batchInsert(@Param("list") List<ProductSku> list);
}