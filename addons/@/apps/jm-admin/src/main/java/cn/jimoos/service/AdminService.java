package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.*;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.AdminVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :keepcleargas
 * @date :2021-04-26 20:58.
 */
public interface AdminService {
    /**
     * 管理员登陆
     *
     * @param request        the request
     * @param adminLoginForm the admin login form
     * @return admin vo
     * @throws BussException the buss exception
     */
    AdminVO login(HttpServletRequest request, AdminLoginForm adminLoginForm) throws BussException;

    /**
     * 创建管理员
     *
     * @param adminCreateFrom the admin create from
     * @return AdminVo admin vo
     * @throws BussException the buss exception
     */
    AdminVO create(AdminCreateFrom adminCreateFrom) throws BussException;

    /**
     * 删除管理员
     *
     * @param adminDeleteForm the admin delete form
     * @throws BussException the buss exception
     */
    void delete(AdminDeleteForm adminDeleteForm) throws BussException;

    /**
     * 更新密码
     *
     * @param adminUpdatePwdForm the admin update pwd form
     * @throws BussException the buss exception
     */
    void updatePwd(AdminUpdatePwdForm adminUpdatePwdForm) throws BussException;

    /**
     * Ban/UnBan admin.
     *
     * @param adminBanForm the admin ban form
     * @throws BussException the buss exception
     */
    void ban(AdminBanForm adminBanForm) throws BussException;

    /**
     * 查询 table 列表
     *
     * @param adminQueryForm admin query form
     * @return Page<Admin>
     */
    Page<AdminVO> query(AdminQueryForm adminQueryForm);
}
