package cn.jimoos.dao;

import cn.jimoos.model.ProductAttr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    int updateDeletedById(@Param("updatedDeleted") Boolean updatedDeleted, @Param("id") Long id);
    
    /**
     * 查询 ProductAttr 列表
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${name} 的 倒序分页查询
     * @return List<ProductAttr>
     */
    List<ProductAttr> queryTable(Map<String, Object> qm);

    /**
     * 查询 ProductAttr 总数
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${name} 的 倒序分页查询
     * @return long total
     */
    long queryTableCount(Map<String, Object> qm);
}