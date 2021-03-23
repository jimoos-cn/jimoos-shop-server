package cn.jimoos.component;

import cn.jimoos.entity.UserEntity;

/**
 * The interface User invite code component.
 *
 * @author wangyiwen
 * @version 1.0 Created in 2021/1/28 16:53
 * 注册成 bean 当邀请码生成规则不同时 在 api 层可 implements BeanPostProcessor 进行替换
 */
public interface CodeGenComponent {
    /**
     * 获取邀请码
     *
     * @param userEntity the user entity
     * @return the available code
     */
    String getAvailableInviteCode(UserEntity userEntity);
}
