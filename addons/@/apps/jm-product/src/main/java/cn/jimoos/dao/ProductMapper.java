package cn.jimoos.dao;

import cn.jimoos.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Mapper
public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Product record);

    int batchInsert(@Param("list") List<Product> list);

    /**
     * 统计 分类下的商品数
     *
     * @param categoryId category Id
     * @return count under category Id
     */
    Long countByCategoryId(@Param("categoryId") Long categoryId);
}