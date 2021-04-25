package cn.jimoos.dao;

import cn.jimoos.model.AdminLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2020-05-14 21:18.
 */

@Mapper
public interface AdminLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminLoginLog record);

    int insertSelective(AdminLoginLog record);

    AdminLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminLoginLog record);

    int updateByPrimaryKey(AdminLoginLog record);

    int batchInsert(@Param("list") List<AdminLoginLog> list);
}
