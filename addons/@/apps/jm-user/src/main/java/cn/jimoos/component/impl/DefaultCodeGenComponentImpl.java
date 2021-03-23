package cn.jimoos.component.impl;

import cn.jimoos.component.CodeGenComponent;
import cn.jimoos.entity.UserEntity;
import cn.jimoos.repository.UserRepository;
import cn.jimoos.user.model.User;
import cn.jimoos.utils.random.CodeUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2021/1/28 16:52
 */
@Component
public class DefaultCodeGenComponentImpl implements CodeGenComponent {
    @Resource
    UserRepository userRepository;

    /**
     * 获取可用的邀请码
     *
     * @return
     */
    @Override
    public String getAvailableInviteCode(UserEntity userEntity) {
        while (true) {
            String code = CodeUtil.genInviteCode();
            User user = userRepository.getUserMapper().findOneByCode(code);
            if (user == null) {
                return code;
            } else {
                continue;
            }
        }
    }
}
