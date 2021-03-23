package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.LogoutForm;
import cn.jimoos.form.ProfileForm;
import cn.jimoos.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户模块
 *
 * @author :keepcleargas
 * @date :2021-03-23 20:27.
 */
@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserApi {
    @Resource
    UserService userService;

    /**
     * 修改用户信息
     *
     * @param profileForm 修改信息表单
     * @throws BussException UserError.USER_NOT_FOUND
     */
    @PostMapping(value = "/{userId}/profile", produces = "application/json; charset=utf-8")
    public void updateProfile(@ModelAttribute ProfileForm profileForm) throws BussException {
        userService.updateProfile(profileForm);
    }

    /**
     * 登出
     *
     * @param logoutForm {@link LogoutForm}
     */
    @PostMapping(value = "/{userId}/logout", produces = "application/json; charset=utf-8")
    public void logout(@ModelAttribute LogoutForm logoutForm) throws BussException {
        userService.logout(logoutForm);
    }
}
