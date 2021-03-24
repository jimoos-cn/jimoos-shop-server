package cn.jimoos.user.provider;


import cn.jimoos.user.vo.UserVO;

import java.util.List;

/**
 * 用户数据提供接口
 *
 * @author :keepcleargas
 * @date :2020-04-24 19:52.
 */
public interface UserProvider {
    /**
     * 通过邀请码获取用户信息
     *
     * @param inviteCode 邀请码
     * @return UserVO
     */
    UserVO byCode(String inviteCode);

    /**
     * 通过id 获取用户信息
     *
     * @param id 用户 ID
     * @return UserVO
     */
    UserVO byId(Long id);

    /**
     * 通过手机号 获取用户信息
     *
     * @param phone 手机号
     * @return
     */
    UserVO byPhone(String phone);

    /**
     * 通过ID 列表获取 用户信息
     *
     * @param ids 用户 ID 列表
     * @return List<UserVO>
     */
    List<UserVO> byIds(List<Long> ids);
}
