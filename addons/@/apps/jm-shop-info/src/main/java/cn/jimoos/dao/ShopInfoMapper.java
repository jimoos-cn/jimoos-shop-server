package cn.jimoos.dao;

import cn.jimoos.model.ShopInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-16 22:11.
 */

@Mapper
public interface ShopInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopInfo record);

    ShopInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKey(ShopInfo record);

    int batchInsert(@Param("list") List<ShopInfo> list);

    /**
     * 获取唯一一行 商城描述
     *
     * @return ShopInfo
     */
    ShopInfo selectOne();
}