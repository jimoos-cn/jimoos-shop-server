package cn.jimoos.dao;

import cn.jimoos.model.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-01-19 11:28.
 */
@Mapper
public interface BannerMapper {

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Banner record);

    List<Banner> backQuery(Map<String, Object> map);
}