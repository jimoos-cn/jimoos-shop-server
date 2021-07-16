package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.be.UserQueryForm;
import cn.jimoos.service.UserService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.be.UserAddressVO;
import cn.jimoos.vo.be.UserQueryVO;
import cn.jimoos.vo.be.UserRelationVO;
import cn.jimoos.vo.be.UserSocialVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
     * @return Page<UserQueryVO>
     */
    @GetMapping(value = "/query", produces = "application/json;charset=utf-8")
    public Page<UserQueryVO> getUserInfo(@ModelAttribute UserQueryForm form) {
        return userService.getUserInfo(form);
    }

    /**
     * 删除 某个用户${userId}
     *
     * @param userId 用户ID
     * @throws BussException UserError.USER_NOT_FOUND
     */
    @PostMapping(value = "/{userId}/delete", produces = "application/json;charset=utf-8")
    public void deleteUser(@PathVariable("userId") Long userId) throws BussException {
        userService.deleteUser(userId);
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

    /**
     * 根据用户ID获取 某个用户详细信息
     *
     * @param userId 用户ID
     * @return UserVO
     */
    @GetMapping(value = "/{userId}/details", produces = "application/json;charset=utf-8")
    public UserQueryVO getUserDetail(@PathVariable("userId") Long userId) throws BussException {
        return userService.getUserDetailById(userId);
    }


    /**
     * 获取的用户地址
     *
     * @param userId 用户ID
     * @return UserVO
     */
    @GetMapping(value = "/{userId}/address", produces = "application/json;charset=utf-8")
    public List<UserAddressVO> getUserAddress(@PathVariable("userId") Long userId) {
        return userService.getUserAddress(userId);
    }

    /**
     * 获取的用户分销关系
     *
     * @param userId 用户ID
     * @return UserVO
     */
    @GetMapping(value = "/{userId}/relation", produces = "application/json;charset=utf-8")
    public UserRelationVO getUserRelation(@PathVariable("userId") Long userId) {
        return userService.getUserRelation(userId);
    }

    /**
     * 获取的社交登陆
     *
     * @param userId 用户ID
     * @return UserVO
     */
    @GetMapping(value = "/{userId}/social", produces = "application/json;charset=utf-8")
    public List<UserSocialVO> getUserSocial(@PathVariable("userId") Long userId) {
        return userService.getUserSocial(userId);
    }

}
