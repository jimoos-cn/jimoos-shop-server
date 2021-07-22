package cn.jimoos.factory;

import cn.jimoos.component.CodeGenComponent;
import cn.jimoos.entity.UserEntity;
import cn.jimoos.repository.UserRepository;
import cn.jimoos.utils.encrypt.BCrypt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-23 15:29.
 */
@Component
public class UserFactory {
    @Resource
    UserRepository userRepository;
    @Resource
    CodeGenComponent defaultCodeGenComponent;

    public UserEntity create(String phone) {
        UserEntity userEntity = new UserEntity(userRepository);

        userEntity.setBan(false);
        userEntity.setRole((byte) 0);
        userEntity.setGender((byte) 0);
        userEntity.setPhone(phone);
        userEntity.setSalt(BCrypt.gensalt());
        userEntity.setNickname("u" + phone.substring(0, 7) + "****");
        userEntity.setCreateAt(System.currentTimeMillis());
        userEntity.setId(0L);
        userEntity.setDeleted(false);
        userEntity.setInviteCode(defaultCodeGenComponent.getAvailableInviteCode(userEntity));
        return userEntity;
    }

    public UserEntity create(Long userId) {
        UserEntity userEntity = new UserEntity(userRepository);
        userEntity.setId(userId);
        return userEntity;
    }
}
