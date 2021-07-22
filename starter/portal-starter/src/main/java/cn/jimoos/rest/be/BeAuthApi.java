package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.AdminLoginForm;
import cn.jimoos.service.AdminService;
import cn.jimoos.vo.AdminVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 授权登录
 *
 * @author :keepcleargas
 * @date :2021-05-07 10:39.
 */
@RestController
@RequestMapping("/bAdmin/v1")
@Slf4j
public class BeAuthApi {
    @Resource
    AdminService adminService;

    /**
     * 后台登陆
     *
     * @param adminLoginForm 登录表单
     * @throws BussException AdminError.ADMIN_NOT_EXIST
     */
    @PostMapping(value = "/login", produces = "application/json; charset=utf-8")
    public AdminVO updatePwd(HttpServletRequest request, @ModelAttribute @Valid AdminLoginForm adminLoginForm) throws BussException {
        return adminService.login(request, adminLoginForm);
    }
}
