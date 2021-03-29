package cn.jimoos.dao;

import cn.jimoos.model.ProductAttr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Mapper
public interface ProductAttrMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductAttr record);

    ProductAttr selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ProductAttr record);

    int batchInsert(@Param("list") List<ProductAttr> list);
}