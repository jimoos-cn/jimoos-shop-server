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
    int insert(ProductAttrValue record);

    ProductAttrValue selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ProductAttrValue record);

    int batchInsert(@Param("list") List<ProductAttrValue> list);

    /**
     * 查询 销售属性值列表
     *
     * @param attrId attr Id
     * @return List<ProductAttrValue>
     */
    List<ProductAttrValue> findByAttrId(@Param("attrId") Long attrId);

    /**
     * 删除 销售属性 attrId 下的 值列表
     *
     * @param updatedDeleted 删除状态
     * @param attrId         attr id
     * @return affectNum
     */
    int updateDeletedByAttrId(@Param("updatedDeleted") Boolean updatedDeleted, @Param("attrId") Long attrId);

    /**
     * 删除 销售属性值
     *
     * @param updatedDeleted 删除状态
     * @param id             value id
     * @return affectNum
     */
    int updateDeletedById(@Param("updatedDeleted") Boolean updatedDeleted, @Param("id") Long id);
}