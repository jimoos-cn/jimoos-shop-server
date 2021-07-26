package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.AdminMapper;
import cn.jimoos.entity.AdminEntity;
import cn.jimoos.error.AdminError;
import cn.jimoos.factory.AdminFactory;
import cn.jimoos.form.*;
import cn.jimoos.model.Admin;
import cn.jimoos.model.AdminLoginLog;
import cn.jimoos.repository.AdminRepository;
import cn.jimoos.service.AdminService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.utils.ip.IpUtils;
import cn.jimoos.vo.AdminVO;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Admin service.
 *
 * @author : keepcleargas
 * @date :2021-04-26
 */
@Service
public class AdminServiceImpl implements AdminService {
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


    @SneakyThrows
    @Override
    public AdminVO login(HttpServletRequest request, AdminLoginForm adminLoginForm) throws BussException {
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
            // 2021年7月22日19:28:52 modify 使用utils中的方法
            String ip = IpUtils.getIpAddr(request);
            String userAgent = IpUtils.getDeviceType(request);
            AdminVO adminVo = new AdminVO();
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


    @Override
    public AdminVO create(AdminCreateFrom adminCreateFrom) throws BussException {
        AdminEntity adminEntity = adminRepository.findByAccount(adminCreateFrom.getAccount());
        if (adminEntity != null) {
            throw new BussException(AdminError.ACCOUNT_EXIST);
        } else {
            adminEntity = adminFactory.createAdmin(adminCreateFrom.getAccount(), adminCreateFrom.getPwd());
            adminRepository.saveAdmin(adminEntity);
            AdminVO adminVo = new AdminVO();
            BeanUtils.copyProperties(adminEntity, adminVo);
            return adminVo;
        }
    }

    @Override
    public void delete(AdminDeleteForm adminDeleteForm) throws BussException {
        AdminEntity adminEntity = adminRepository.findById(adminDeleteForm.getToDeleteAdminId());
        if (adminEntity == null) {
            throw new BussException(AdminError.ADMIN_NOT_EXIST);
        } else {
            adminEntity.softDelete();
            adminRepository.saveAdmin(adminEntity);
        }
    }


    @Override
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


    @Override
    public void ban(AdminBanForm adminBanForm) throws BussException {
        AdminEntity adminEntity = adminRepository.findById(adminBanForm.getToBanAdminId());
        if (adminEntity == null) {
            throw new BussException(AdminError.ADMIN_NOT_EXIST);
        } else {
            adminEntity.setBan(adminBanForm.getBan());
            adminRepository.saveAdmin(adminEntity);
        }
    }

    @Override
    public Page<AdminVO> query(AdminQueryForm form) {
        long count = adminMapper.queryTableCount(form.toQueryMap());

        if (count > 0) {
            List<Admin> adminList = adminMapper.queryTable(form.toQueryMap());

            List<AdminVO> adminVOs = adminList.stream().map(admin -> {
                AdminVO adminVO = new AdminVO();
                BeanUtils.copyProperties(admin, adminVO);
                return adminVO;
            }).collect(Collectors.toList());
            return Page.create(count, adminVOs);
        }
        return Page.empty();
    }

    @Override
    public boolean logout(HttpServletRequest request) {
        AdminEntity adminEntity = new AdminEntity(adminRepository);
        String token = request.getHeader("Authorization");
        int i = adminEntity.deleteSessionByToken(token);
        Assert.isTrue(i > 0, "后台管理账户登出失败");
        return true;
    }
}
