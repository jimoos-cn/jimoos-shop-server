package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.LogoutForm;
import cn.jimoos.form.ProfileForm;
import cn.jimoos.form.SocialRegForm;
import cn.jimoos.form.be.UserQueryForm;
import cn.jimoos.user.vo.UserVO;
import cn.jimoos.utils.http.Page;

/**
 * @author :keepcleargas
 * @date :2021-03-23 18:08.
 */
public interface UserService {
    /**
     * 小程序登录&注册
     *
     * @param socialRegForm 社交登录
     * @return 用户
     * @throws BussException UserError.USER_NOT_FOUND
     */
    UserVO maReg(SocialRegForm socialRegForm) throws BussException;

    /**
     * 更新用户信息
     *
     * @param profileForm 信息表单
     * @throws BussException UserError.USER_NOT_FOUND
     */
    void updateProfile(ProfileForm profileForm) throws BussException;

    /**
     * 登出
     *
     * @param logoutForm 登出表单
     * @throws BussException UserError.USER_NOT_FOUND
     */
    void logout(LogoutForm logoutForm) throws BussException;

    /**
     * 设置用户 为冻结
     *
     * @param userId 用户ID
     * @throws BussException UserError.USER_NOT_FOUND
     * @author qisheng.chen
     */
    void banUser(Long userId) throws BussException;

    /**
     * 设置用户 正常状态
     *
     * @param userId 用户ID
     * @throws BussException UserError.USER_NOT_FOUND
     * @author qisheng.chen
     */
    void unbanUser(Long userId) throws BussException;

    /**
     * 后台系统中用户管理 查询用户列表
     *
     * @param form 查询参数
     * @return Page<UserVO>
     */
    Page<UserVO> getUserInfo(UserQueryForm form);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     * @throws BussException UserError.USER_NOT_FOUND
     */
    void removeUser(Long userId) throws BussException;
}
