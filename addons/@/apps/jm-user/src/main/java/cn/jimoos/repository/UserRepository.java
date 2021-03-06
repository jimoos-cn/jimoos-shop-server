package cn.jimoos.repository;

import cn.jimoos.dao.UserAddressMapper;
import cn.jimoos.dao.UserMapper;
import cn.jimoos.dao.UserSessionMapper;
import cn.jimoos.dao.UserSocialMapper;
import cn.jimoos.entity.UserEntity;
import cn.jimoos.model.UserSocial;
import cn.jimoos.user.model.User;
import cn.jimoos.user.model.UserSession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :keepcleargas
 * @date :2020-04-24 12:11.
 */
@Repository
public class UserRepository {
    @Resource
    UserSessionMapper userSessionMapper;

    @Resource
    UserMapper userMapper;
    @Resource
    UserAddressMapper userAddressMapper;
    @Resource
    UserSocialMapper userSocialMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public UserEntity findByPhone(String phone) {
        return wrapper(userMapper.findOneByPhone(phone));
    }


    public UserEntity findByCode(String code) {
        return wrapper(userMapper.findOneByCode(code));
    }

    public UserEntity findById(Long id) {
        return wrapper(userMapper.selectByPrimaryKey(id));
    }

    public List<UserEntity> findByIds(List<Long> ids) {
        List<User> users = userMapper.findByIdIn(ids);
        return users.stream().map(user -> wrapper(user, true)).collect(Collectors.toList());
    }

    private UserEntity wrapper(User user) {
        return wrapper(user, false);
    }

    private UserEntity wrapper(User user, boolean skipRepo) {
        if (user != null) {
            UserEntity userEntity;
            if (skipRepo) {
                userEntity = new UserEntity();
            } else {
                userEntity = new UserEntity(this);
            }
            BeanUtils.copyProperties(user, userEntity);
            return userEntity;
        }
        return null;
    }

    public UserEntity save(UserEntity userEntity) {
        if (userEntity.getId() != null && userEntity.getId() > 0) {
            userMapper.updateByPrimaryKey(userEntity);
        } else {
            userMapper.insert(userEntity);
        }
        return userEntity;
    }


    /**
     * 社交登录查询
     *
     * @param socialType 社交类别
     * @param socialId
     * @param unionId
     * @return
     */
    public UserEntity findBySocial(Byte socialType, String socialId, String unionId) {
        UserSocial userSocial;
        if (!StringUtils.isEmpty(unionId)) {
            userSocial = userSocialMapper.findOneBySocialUnionId(unionId);
        } else {
            userSocial = userSocialMapper.findOneBySocialIdAndType(socialId, socialType);
        }

        if (userSocial != null && userSocial.getUserId() > 0) {
            return findById(userSocial.getUserId());
        } else {
            return null;
        }
    }

    /**
     * 绑定社交信息
     *
     * @param userSocial
     */
    public void saveUserSocial(UserSocial userSocial) {
        userSocialMapper.insert(userSocial);
    }

    /**
     * 保存 用户session
     *
     * @param userSession
     * @return
     */
    public UserSession saveUserSession(UserSession userSession) {
        userSessionMapper.insert(userSession);
        return userSession;
    }

    /**
     * 设置 用户session 失效
     *
     * @param userId
     * @param platform
     */
    public void setSessionExpired(Long userId, int platform) {
        userSessionMapper.setExpired(userId, System.currentTimeMillis(), platform);
    }
}
