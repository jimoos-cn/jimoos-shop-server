package cn.jimoos.dao;

import cn.jimoos.route.model.Route;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-01-19 11:28.
 */
@Mapper
public interface RouteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Route record);

    int insertSelective(Route record);

    Route selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);

    List<Route> findByIds(@Param("idCollection") Collection<Long> idCollection);
}