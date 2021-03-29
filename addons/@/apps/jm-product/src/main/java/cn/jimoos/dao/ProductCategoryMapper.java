package cn.jimoos.dao;

import cn.jimoos.model.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Mapper
public interface ProductCategoryMapper {
    int insert(ProductCategory record);

    ProductCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ProductCategory record);

    int batchInsert(@Param("list") List<ProductCategory> list);

    /**
     * 更新删除状态
     *
     * @param updatedDeleted delete status
     * @param id             category id
     * @return affectNum
     */
    int updateDeletedById(@Param("updatedDeleted") Boolean updatedDeleted, @Param("id") Long id);

    List<ProductCategory> findByPid(@Param("pid")Long pid);



    /**
     * 查询 ProductCategory 列表
     *
     * @param qm ,支持 ${pid}  和 ${name} 的 倒序分页查询
     * @return List<ProductCategory>
     */
    List<ProductCategory> queryTable(Map<String, Object> qm);

    /**
     * 查询 ProductCategory 总数
     *
     * @param qm ,支持 ${pid}  和 ${name} 的 倒序分页查询
     * @return long total
     */
    long queryTableCount(Map<String, Object> qm);
}