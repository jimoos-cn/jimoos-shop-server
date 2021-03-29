package cn.jimoos.dao;

import cn.jimoos.model.ProductTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Mapper
public interface ProductTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductTag record);

    ProductTag selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ProductTag record);

    int batchInsert(@Param("list") List<ProductTag> list);
}