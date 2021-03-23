package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.entity.UserEntity;
import cn.jimoos.error.UserError;
import cn.jimoos.event.user.LoginEvent;
import cn.jimoos.event.user.RegEvent;
import cn.jimoos.factory.UserFactory;
import cn.jimoos.form.LogoutForm;
import cn.jimoos.form.ProfileForm;
import cn.jimoos.form.SocialRegForm;
import cn.jimoos.impl.JmSpringEventPublisher;
import cn.jimoos.repository.UserRepository;
import cn.jimoos.service.UserService;
import cn.jimoos.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2020-04-24 15:42.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    JmSpringEventPublisher eventPublisher;
    @Resource
    UserFactory userFactory;
    @Resource
    UserRepository userRepository;


    /**
     * 小程序登录注册
     *
     * @param socialRegForm 登录/注册表单
     * @return UserVo 用户对象
     */
    @Override
    public UserVO maReg(SocialRegForm socialRegForm) throws BussException {
        UserEntity userEntity = userRepository.findBySocial(socialRegForm.getSocialType(), socialRegForm.getSocialId(), socialRegForm.getUnionId());

        if (userEntity == null) {
            userEntity = userRepository.findByPhone(socialRegForm.getPhone());
            if (userEntity == null) {
                //如果不存在用户则新建
                userEntity = userFactory.create(socialRegForm.getPhone());
                userEntity.setNickname(socialRegForm.getNickname());
                userEntity.setAvatar(socialRegForm.getAvatar());
                userRepository.save(userEntity);
                userEntity.bindSocial(socialRegForm);
                //发布注册事件
                eventPublisher.publish(new RegEvent(userEntity.getId(), socialRegForm.getInviteCode(), userEntity.getCreateAt(),
                        socialRegForm.getSocialId()));
            }
            //如果 手机号 对象不为空，则 直接返回手机号的对象信息
        }
        //发布登录事件
        eventPublisher.publish(new LoginEvent(userEntity.getId()));
        UserVO userVo = new UserVO();
        BeanUtils.copyProperties(userEntity, userVo);
        userVo.setSession(userEntity.newUserSession(socialRegForm.getDevice(), socialRegForm.getPlatform()));
        return userVo;
    }

    @Override
    public void updateProfile(ProfileForm profileForm) throws BussException {
        UserEntity userEntity = userRepository.findById(profileForm.getUserId());

        if (userEntity == null) {
            throw new BussException(UserError.USER_NOT_FOUND);
        }

        userEntity.updateProfile(profileForm);
        userEntity.setUpdateAt(System.currentTimeMillis());
        userRepository.save(userEntity);
    }

    @Override
    public void logout(LogoutForm logoutForm) throws BussException {
        UserEntity userEntity = userRepository.findById(logoutForm.getUserId());

        if (userEntity == null) {
            throw new BussException(UserError.USER_NOT_FOUND);
        }

        userEntity.setSessionExpired(logoutForm.getPlatform());
    }

    @Override
    public void banUser(Long userId) throws BussException {
        UserEntity userEntity = userRepository.findById(userId);

        if (userEntity == null) {
            throw new BussException(UserError.USER_NOT_FOUND);
        }

        userEntity.baned();
        userRepository.save(userEntity);
    }

    @Override
    public void unbanUser(Long userId) throws BussException {
        UserEntity userEntity = userRepository.findById(userId);

        if (userEntity == null) {
            throw new BussException(UserError.USER_NOT_FOUND);
        }

        userEntity.unBan();
        userRepository.save(userEntity);
    }
}
