package cn.jimoos.dao;

import cn.jimoos.model.AdminToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface AdminTokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AdminToken record);

    int insertSelective(AdminToken record);

    AdminToken selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AdminToken record);

    int updateByPrimaryKey(AdminToken record);

    int batchInsert(@Param("list") List<AdminToken> list);

    AdminToken findByAdminIdValid(@Param("adminId") Long adminId, @Param("now") long currentTimeMillis);

    AdminToken findByTokenValid(@Param("token") String token, @Param("now") long currentTimeMillis);
}
