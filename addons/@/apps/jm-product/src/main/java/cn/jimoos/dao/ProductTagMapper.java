package cn.jimoos.dao;

import cn.jimoos.model.ProductTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    /**
     * 批量查询 标签
     *
     * @param idCollection id 集合
     * @return List<ProductTag>
     */
    List<ProductTag> findByIdIn(@Param("idCollection") Collection<Long> idCollection);

    /**
     * 更新 删除状态
     *
     * @param updatedDeleted delete status
     * @param id             product tag id
     * @return affectNum
     */
    int updateDeletedById(@Param("updatedDeleted") Boolean updatedDeleted, @Param("id") Long id);

    /**
     * 查询 ProductTag 列表
     *
     * @param qm ,支持 ${type} , ${name} 的 倒序分页查询
     * @return List<ProductTag>
     */
    List<ProductTag> queryTable(Map<String, Object> qm);

    /**
     * 查询 ProductTag 总数
     *
     * @param qm ,支持 ${type} , ${name} 的 倒序分页查询
     * @return long total
     */
    long queryTableCount(Map<String, Object> qm);
}