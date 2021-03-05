package cn.jimoos.dao;

import cn.jimoos.model.BaseSettings;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2020-11-16 13:48.
 */

@Mapper
public interface BaseSettingsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseSettings record);

    int insertSelective(BaseSettings record);

    BaseSettings selectByPrimaryKey(Integer id);

    /**
     * 根据 key 获取 设置
     *
     * @param keyword,例如 PAY.ALI
     * @return
     */
    BaseSettings findOneByKeyword(@Param("keyword") String keyword);

    int updateByPrimaryKeySelective(BaseSettings record);

    int updateByPrimaryKey(BaseSettings record);

    /**
     * 查询 BaseSettings 列表
     *
     * @param qm ,${name} 的 倒序分页查询
     * @return List<BaseSettings>
     */
    List<BaseSettings> queryTable(Map qm);

    /**
     * 查询 BaseSettings 总数
     *
     * @param qm ,支持 ${startTime} - ${endTime} 的 ${name} 的 倒序分页查询
     * @return long total
     */
    int queryTableCount(Map qm);

    /**
     * 根据keyword更新content
     *
     * @param content content
     * @param keyword keyword
     * @return int
     */
    int updateContentByKeyword(@Param("content") String content, @Param("keyword") String keyword);


    int batchInsert(@Param("list") List<BaseSettings> list);
}