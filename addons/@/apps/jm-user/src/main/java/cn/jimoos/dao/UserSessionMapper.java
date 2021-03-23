package cn.jimoos.dao;

import cn.jimoos.user.model.UserSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2020-11-26 10:02.
 */

@Mapper
public interface UserSessionMapper {
    int insert(UserSession record);

    /**
     * 获取用户有效的 sessionToken
     *
     * @param userId 用户 ID
     * @param now    当前事件
     * @return sessionToken 列表
     */
    List<UserSession> findByUidValid(@Param("userId") Long userId, @Param("now") Long now);

    /**
     * 设置过期时间为 当前时间
     *
     * @param userId   用户
     * @param expired  过期 ID
     * @param platform 平台类型
     */
    void setExpired(@Param("userId") Long userId, @Param("expired") Long expired, @Param("platform") int platform);
}
