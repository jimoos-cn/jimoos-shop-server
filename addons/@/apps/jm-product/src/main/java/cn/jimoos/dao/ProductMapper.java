package cn.jimoos.dao;

import cn.jimoos.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询 Product 列表
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${name} 的 倒序分页查询
     * @return List<Product>
     */
    List<Product> queryTable(Map<String, Object> qm);

    /**
     * 查询 Product 总数
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${name} 的 倒序分页查询
     * @return long total
     */
    long queryTableCount(Map<String, Object> qm);
    
    /**
     * 查询 Product 列表
     *
     * @param qm ,支持  ${name} 的 倒序分页查询
     * @return List<Product>
     */
    List<Product> search(Map<String, Object> qm);

    /**
     * 查询 Product 总数
     *
     * @param qm ,支持 ${name} 的 倒序分页查询
     * @return long total
     */
    long searchCount(Map<String, Object> qm);
}