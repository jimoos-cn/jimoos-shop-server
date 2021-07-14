package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.be.UserQueryForm;
import cn.jimoos.service.UserService;
import cn.jimoos.user.vo.UserVO;
import cn.jimoos.utils.http.Page;
import org.springframework.web.bind.annotation.*;

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
     * 用户管理中查询用户列表
     *
     * @param form user query form for table
     * @return Page<UserVO>
     */
    @GetMapping(value = "/query", produces = "application/json;charset=utf-8")
    public Page<UserVO> getUserInfo(@ModelAttribute UserQueryForm form) {
        return userService.getUserInfo(form);
    }

    /**
     * 删除 某个用户${userId}
     *
     * @param userId 用户ID
     * @throws BussException UserError.USER_NOT_FOUND
     */
    @PostMapping(value = "/{userId}/remove", produces = "application/json;charset=utf-8")
    public void removeUser(@PathVariable("userId") Long userId) throws BussException {
        userService.removeUser(userId);
    }

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
