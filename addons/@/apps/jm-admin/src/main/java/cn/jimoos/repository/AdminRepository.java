package cn.jimoos.repository;

import cn.jimoos.dao.AdminLoginLogMapper;
import cn.jimoos.dao.AdminMapper;
import cn.jimoos.dao.AdminTokenMapper;
import cn.jimoos.entity.AdminEntity;
import cn.jimoos.model.Admin;
import cn.jimoos.model.AdminLoginLog;
import cn.jimoos.model.AdminToken;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-26 20:29.
 */
@Repository
public class AdminRepository {
    @Resource
    AdminMapper adminMapper;
    @Resource
    AdminTokenMapper adminTokenMapper;
    @Resource
    AdminLoginLogMapper adminLoginLogMapper;

    /**
     * 查找 Admin对象
     *
     * @param account
     * @return
     */
    public AdminEntity findByAccount(String account) {
        return wrapper(adminMapper.findOneByUsername(account), false);
    }

    /**
     * 获取 管理员
     *
     * @param id
     * @return
     */
    public AdminEntity findById(Long id) {
        return wrapper(adminMapper.selectByPrimaryKey(id), false);
    }

    /**
     * 保存管理员
     *
     * @param adminEntity
     * @return
     */
    public AdminEntity saveAdmin(AdminEntity adminEntity) {
        if (adminEntity.getId() != null && adminEntity.getId() > 0) {
            adminMapper.updateByPrimaryKey(adminEntity);
        } else {
            adminMapper.insert(adminEntity);
        }
        return adminEntity;
    }

    /**
     * 封装 AdminEntity
     *
     * @param admin
     * @param skipRepo
     * @return
     */
    private AdminEntity wrapper(Admin admin, boolean skipRepo) {
        if (admin != null) {
            AdminEntity adminEntity;
            if (skipRepo) {
                adminEntity = new AdminEntity();
            } else {
                adminEntity = new AdminEntity(this);

            }
            BeanUtils.copyProperties(admin, adminEntity);
            return adminEntity;
        }
        return null;
    }

    /**
     * 生成 新的 有效session
     *
     * @param session
     * @return
     */
    public AdminToken saveSession(AdminToken session) {
        adminTokenMapper.insert(session);
        return session;
    }

    /**
     * 插入管理员登录日志
     *
     * @param adminLoginLog
     */
    public void saveAdminLoginLog(AdminLoginLog adminLoginLog) {
        adminLoginLogMapper.insertSelective(adminLoginLog);
    }
}
