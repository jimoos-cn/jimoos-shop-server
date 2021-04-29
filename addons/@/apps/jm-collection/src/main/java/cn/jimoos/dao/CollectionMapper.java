package cn.jimoos.dao;

import cn.jimoos.model.Collection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:17.
 */
@Mapper
public interface CollectionMapper {
    int insert(Collection record);

    Collection selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Collection record);

    int batchInsert(@Param("list") List<Collection> list);

    /**
     * 查询 Collection 列表
     *
     * @param qm ,支持  ${name} | ${status} 的 倒序分页查询
     * @return List<Collection>
     */
    List<Collection> queryTable(Map<String, Object> qm);

    /**
     * 查询 Collection 总数
     *
     * @param qm ,支持  ${name} | ${status}的 倒序分页查询
     * @return long total
     */
    long queryTableCount(Map<String, Object> qm);
}