package cn.jimoos.dao;

import cn.jimoos.model.ProductAttrValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Mapper
public interface ProductAttrValueMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductAttrValue record);

    ProductAttrValue selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ProductAttrValue record);

    int batchInsert(@Param("list") List<ProductAttrValue> list);
}