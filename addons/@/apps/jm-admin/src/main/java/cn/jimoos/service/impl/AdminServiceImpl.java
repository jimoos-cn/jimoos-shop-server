package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.AdminMapper;
import cn.jimoos.entity.AdminEntity;
import cn.jimoos.error.AdminError;
import cn.jimoos.factory.AdminFactory;
import cn.jimoos.form.*;
import cn.jimoos.model.AdminLoginLog;
import cn.jimoos.repository.AdminRepository;
import cn.jimoos.vo.AdminVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Admin service.
 *
 * @author : keepcleargas
 * @date :2021-04-26
 */
@Service
public class AdminServiceImpl {
    /**
     * The Admin repository.
     */
    @Resource
    AdminRepository adminRepository;

    /**
     * The Admin factory.
     */
    @Resource
    AdminFactory adminFactory;

    /**
     * The Admin mapper.
     */
    @Resource
    AdminMapper adminMapper;

    /**
     * Login admin vo.
     *
     * @param request        the request
     * @param adminLoginForm the admin login form
     * @return admin vo
     * @throws BussException the buss exception
     */
    public AdminVo login(HttpServletRequest request, AdminLoginForm adminLoginForm) throws BussException {
        AdminEntity adminEntity = adminRepository.findByAccount(adminLoginForm.getAccount());
        if (adminEntity == null) {
            throw new BussException(AdminError.ADMIN_NOT_EXIST);
        } else if (adminEntity.getBan() == 1) {
            //管理员已被禁止登录
            throw new BussException(AdminError.ADMIN_IS_BAN);
        } else {
            boolean valid = adminEntity.validPwd(adminLoginForm.getPwd());
            if (!valid) {
                throw new BussException(AdminError.PWD_NOT_VALID);
            }
            Long now = System.currentTimeMillis();
            String ip = (String) request.getAttribute("ip");
            String userAgent = (String) request.getAttribute("agent");
            AdminVo adminVo = new AdminVo();
            BeanUtils.copyProperties(adminEntity, adminVo);
            adminVo.setToken(adminEntity.newToken(ip).getToken());

            //插入登录日志
            AdminLoginLog adminLoginLog = new AdminLoginLog();
            adminLoginLog.setAdminId(adminEntity.getId());
            adminLoginLog.setAgent(userAgent);
            adminLoginLog.setCreateAt(now);
            adminLoginLog.setIp(ip);
            adminRepository.saveAdminLoginLog(adminLoginLog);

            return adminVo;
        }
    }

    /**
     * 创建管理员
     *
     * @param adminCreateFrom the admin create from
     * @return AdminVo admin vo
     * @throws BussException the buss exception
     */
    public AdminVo create(AdminCreateFrom adminCreateFrom) throws BussException {
        AdminEntity adminEntity = adminRepository.findByAccount(adminCreateFrom.getAccount());
        if (adminEntity != null) {
            throw new BussException(AdminError.ACCOUNT_EXIST);
        } else {
            adminEntity = adminFactory.createAdmin(adminCreateFrom.getAccount(), adminCreateFrom.getPwd());
            adminRepository.saveAdmin(adminEntity);
            AdminVo adminVo = new AdminVo();
            BeanUtils.copyProperties(adminEntity, adminVo);
            return adminVo;
        }
    }

    /**
     * 删除管理员
     *
     * @param adminDeleteForm the admin delete form
     * @throws BussException the buss exception
     */
    public void delete(AdminDeleteForm adminDeleteForm) throws BussException {
        AdminEntity adminEntity = adminRepository.findById(adminDeleteForm.getToDeleteAdminId());
        if (adminEntity == null) {
            throw new BussException(AdminError.ADMIN_NOT_EXIST);
        } else {
            adminEntity.softDelete();
            adminRepository.saveAdmin(adminEntity);
        }
    }

    /**
     * 更新密码
     *
     * @param adminUpdatePwdForm the admin update pwd form
     * @throws BussException the buss exception
     */
    public void updatePwd(AdminUpdatePwdForm adminUpdatePwdForm) throws BussException {
        AdminEntity adminEntity;
        if (adminUpdatePwdForm.getToUpdateAdminId() == null || adminUpdatePwdForm.getToUpdateAdminId() == 0) {
            //如果没有填写 要更新的管理员ID 则 更新自己的密码
            adminEntity = adminRepository.findById(adminUpdatePwdForm.getAdminId());
        } else {
            adminEntity = adminRepository.findById(adminUpdatePwdForm.getToUpdateAdminId());
        }

        if (adminEntity == null) {
            throw new BussException(AdminError.ADMIN_NOT_EXIST);
        } else {
            adminEntity.updatePwd(adminUpdatePwdForm.getPwd());
            adminRepository.saveAdmin(adminEntity);
        }
    }

    /**
     * Ban admin.
     *
     * @param adminBanForm the admin ban form
     * @throws BussException the buss exception
     */
    public void ban(AdminBanForm adminBanForm) throws BussException {
        AdminEntity adminEntity = adminRepository.findById(adminBanForm.getToBanAdminId());
        if (adminEntity == null) {
            throw new BussException(AdminError.ADMIN_NOT_EXIST);
        } else {
            adminEntity.setBan(adminBanForm.getBan());
            adminRepository.saveAdmin(adminEntity);
        }
    }
}
