package cn.jimoos.dao;

import cn.jimoos.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-23 21:17.
 */

@Mapper
public interface AdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long id);

    Admin findOneByUsername(@Param("username") String username);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    int batchInsert(@Param("list") List<Admin> list);

    List<Admin> findAdmins(Map map);

    List<Admin> findByIdIn(@Param("idCollection") Collection<Long> idCollection);
}
