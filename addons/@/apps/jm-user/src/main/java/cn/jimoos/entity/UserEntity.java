package cn.jimoos.entity;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.error.UserError;
import cn.jimoos.form.ProfileForm;
import cn.jimoos.form.SocialRegForm;
import cn.jimoos.model.UserSocial;
import cn.jimoos.repository.UserRepository;
import cn.jimoos.user.model.User;
import cn.jimoos.user.model.UserSession;
import cn.jimoos.utils.encrypt.BCrypt;
import cn.jimoos.utils.encrypt.EncryptUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

/**
 * 用户对象
 *
 * @author :keepcleargas
 * @date :2021-03-23 12:17.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserEntity extends User {
    private UserRepository userRepository;

    public UserEntity() {
    }

    public UserEntity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 用户新建UserSession
     *
     * @param device   设备
     * @param platform 平台类型
     * @return sessionToken
     */
    public UserSession newUserSession(String device, int platform) {
        UserSession userSession = new UserSession();
        userSession.setUserId(this.getId());
        userSession.setDevice(device);
        userSession.setPlatform(platform);
        userSession.setCreateAt(System.currentTimeMillis());
        //默认 30天
        userSession.setExpireAt(System.currentTimeMillis() + 30 * 3600L * 24 * 1000);
        userSession.setToken(EncryptUtil.md5(this.getSalt() + System.currentTimeMillis()));
        return userRepository.saveUserSession(userSession);
    }


    /**
     * 更新密码
     *
     * @param password
     */
    public void updatePwd(String password) {
        this.setSalt(BCrypt.gensalt());
        this.setPassword(BCrypt.hashpw(password, this.getSalt()));
        this.setCreateAt(System.currentTimeMillis());
    }

    /**
     * 验证密码
     *
     * @param pwd
     * @throws BussException
     */
    public void validPwd(String pwd) throws BussException {
        if (StringUtils.isEmpty(this.getPassword()) || !this.getPassword().equals(BCrypt.hashpw(pwd, this.getSalt()))) {
            throw new BussException(UserError.PWD_NOT_VALID);
        }
    }

    /**
     * 更新信息
     *
     * @param profileForm
     */
    public void updateProfile(ProfileForm profileForm) {
        if (!StringUtils.isEmpty(profileForm.getNickname())) {
            this.setNickname(profileForm.getNickname());
        }
        if (!StringUtils.isEmpty(profileForm.getAvatar())) {
            this.setAvatar(profileForm.getAvatar());
        }
        if (profileForm.getGender() != null && profileForm.getGender() != -1) {
            this.setGender(profileForm.getGender());
        }
        if (profileForm.getBirthday() != null && profileForm.getBirthday() != -1) {
            this.setBirthday(profileForm.getBirthday());
        }
        if (!StringUtils.isEmpty(profileForm.getProvince())) {
            this.setProvince(profileForm.getProvince());
        }
        if (!StringUtils.isEmpty(profileForm.getCity())) {
            this.setCity(profileForm.getCity());
        }
        if (!StringUtils.isEmpty(profileForm.getArea())) {
            this.setArea(profileForm.getArea());
        }
    }

    /**
     * 绑定社交信息
     *
     * @param socialRegForm
     */
    public UserSocial bindSocial(SocialRegForm socialRegForm) {
        UserSocial userSocial = new UserSocial();
        userSocial.setUserId(this.getId());
        userSocial.setSocialId(socialRegForm.getSocialId());
        userSocial.setSocialUnionId(socialRegForm.getUnionId());
        userSocial.setNickname(socialRegForm.getNickname());
        userSocial.setAvatar(socialRegForm.getAvatar());
        userSocial.setOther(socialRegForm.getIntro());
        userSocial.setType(socialRegForm.getSocialType());
        userSocial.setCreateAt(System.currentTimeMillis());
        userSocial.setDeleted(false);
        userRepository.saveUserSocial(userSocial);
        return userSocial;
    }

    /**
     * 设置 在某个平台的 token 失效
     *
     * @param platform 平台类型
     */
    public void setSessionExpired(int platform) {
        userRepository.setSessionExpired(this.getId(), platform);
    }

    /**
     * 设置未冻结
     */
    public void baned() {
        this.setBan(true);
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * 取消冻结
     */
    public void unBan() {
        this.setBan(false);
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * 删除账户
     */
    public void remove() {
        this.setDeleted(true);
        this.setUpdateAt(System.currentTimeMillis());
    }
}
