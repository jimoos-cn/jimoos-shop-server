package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * back-end user api
 *
 * @author :keepcleargas
 * @date :2021-01-15 11:13.
 */
@RestController
@RequestMapping("/bAdmin/v1/users")
public class BeUserApi {
    @Resource
    UserService userService;

    /**
     * 禁止登录 某个用户${userId}
     *
     * @param userId 用户ID
     * @throws BussException UserError.USER_NOT_FOUND
     */
    @PostMapping(value = "/{userId}/ban", produces = "application/json;charset=utf-8")
    public void banUser(@PathVariable("userId") Long userId) throws BussException {
        userService.banUser(userId);
    }

    /**
     * 取消禁止登录 某个用户${userId}
     *
     * @param userId 用户ID
     * @throws BussException UserError.USER_NOT_FOUND
     */
    @PostMapping(value = "/{userId}/unban", produces = "application/json;charset=utf-8")
    public void unbanUser(@PathVariable("userId") Long userId) throws BussException {
        userService.unbanUser(userId);
    }
}
