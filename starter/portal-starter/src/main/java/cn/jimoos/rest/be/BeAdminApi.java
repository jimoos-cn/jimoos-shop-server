package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.*;
import cn.jimoos.service.AdminService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.AdminVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author :keepcleargas
 * @date :2021-04-26 21:01.
 */
@RestController
@RequestMapping("/bAdmin/v1/admins")
@Slf4j
public class BeAdminApi {
    @Resource
    AdminService adminService;

    /**
     * 更新密码
     *
     * @param adminUpdatePwdForm 密码更新表单
     * @throws BussException AdminError.ADMIN_NOT_EXIST
     */
    @PostMapping(value = "/pwd", produces = "application/json; charset=utf-8")
    public void updatePwd(@ModelAttribute @Valid AdminUpdatePwdForm adminUpdatePwdForm) throws BussException {
        adminService.updatePwd(adminUpdatePwdForm);
    }

    /**
     * 更新管理员的密码
     *
     * @param adminUpdatePwdForm 密码更新表单
     * @throws BussException AdminError.ADMIN_NOT_EXIST
     */
    @PostMapping(value = "/{toUpdateAdminId}/pwd", produces = "application/json; charset=utf-8")
    public void updateAdminPwd(@ModelAttribute AdminUpdatePwdForm adminUpdatePwdForm) throws BussException {
        adminService.updatePwd(adminUpdatePwdForm);
    }

    /**
     * 封禁与解禁管理员
     *
     * @param adminBanForm 管理ban 状态表单
     * @throws BussException AdminError.ADMIN_NOT_EXIST
     */
    @PostMapping(value = "/{toBanAdminId}/ban", produces = "application/json; charset=utf-8")
    public void ban(@Valid @ModelAttribute AdminBanForm adminBanForm) throws BussException {
        adminService.ban(adminBanForm);
    }

    /**
     * 删除管理员
     *
     * @param adminDeleteForm 删除管理员表单
     * @throws BussException AdminError.ADMIN_NOT_EXIST
     */
    @PostMapping(value = "/{toDeleteAdminId}/delete", produces = "application/json; charset=utf-8")
    public void deleteAdmin(@Valid @ModelAttribute AdminDeleteForm adminDeleteForm) throws BussException {
        adminService.delete(adminDeleteForm);
    }

    /**
     * 创建管理员
     *
     * @param adminCreateFrom 创建表单
     * @return AdminVo
     * @throws BussException AdminError.ACCOUNT_EXIST
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public AdminVO createAdmin(@Valid @ModelAttribute AdminCreateFrom adminCreateFrom) throws BussException {
        return adminService.create(adminCreateFrom);
    }

    /**
     * 获得管理员列表
     *
     * @return Page<AdminVO>
     */
    @GetMapping(value = "/query", produces = "application/json; charset=utf-8")
    public Page<AdminVO> getAdmin(@Valid AdminQueryForm adminSearchForm) {
        return adminService.query(adminSearchForm);
    }
}
