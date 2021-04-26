package cn.jimoos.factory;

import cn.jimoos.entity.AdminEntity;
import cn.jimoos.repository.AdminRepository;
import cn.jimoos.utils.encrypt.BCrypt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-26 20:29.
 */
@Component
public class AdminFactory {
    @Resource
    AdminRepository adminRepository;

    /**
     * 构建 admin对象
     *
     * @param account 账号
     * @param pwd     密码
     * @return
     */
    public AdminEntity createAdmin(String account, String pwd) {
        AdminEntity adminEntity = new AdminEntity(adminRepository);
        adminEntity.setUsername(account);
        adminEntity.setSalt(BCrypt.gensalt());
        adminEntity.setEncryptedPassword(BCrypt.hashpw(pwd, adminEntity.getSalt()));
        adminEntity.setCreateAt(System.currentTimeMillis());
        adminEntity.setDeleted(0);
        adminEntity.setBan(0);
        return adminEntity;
    }
}
