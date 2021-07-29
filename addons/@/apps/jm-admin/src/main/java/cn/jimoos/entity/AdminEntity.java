package cn.jimoos.entity;

import cn.jimoos.model.Admin;
import cn.jimoos.model.AdminToken;
import cn.jimoos.repository.AdminRepository;
import cn.jimoos.utils.encrypt.BCrypt;
import cn.jimoos.utils.encrypt.EncryptUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 管理员对象
 *
 * @author :keepcleargas
 * @date :2021-04-26 20:29.
 */
@Data
@NoArgsConstructor
public class AdminEntity extends Admin {
    private AdminRepository adminRepository;
    private List<Long> roleIdsSet;

    public AdminEntity(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    /**
     * 验证密码
     *
     * @param pwd
     * @return boolean
     */
    public boolean validPwd(String pwd) {
        if (StringUtils.isEmpty(pwd) || !this.getEncryptedPassword().equals(BCrypt.hashpw(pwd, this.getSalt()))) {
            return false;
        }
        return true;
    }

    /**
     * 管理员新建token
     *
     * @param ip
     * @return
     */
    public AdminToken newToken(String ip) {
        AdminToken adminToken = new AdminToken();
        adminToken.setAdminId(this.getId());
        adminToken.setCreateAt(System.currentTimeMillis());
        //默认 30天
        adminToken.setExpired(System.currentTimeMillis() + 30 * 3600L * 24 * 1000);
        adminToken.setToken(EncryptUtil.md5(this.getSalt() + System.currentTimeMillis()));
        adminToken.setIp(ip);
        return adminRepository.saveSession(adminToken);
    }

    /**
     * 更新密码
     *
     * @param pwd
     */
    public void updatePwd(String pwd) {
        this.setSalt(BCrypt.gensalt());
        this.setEncryptedPassword(BCrypt.hashpw(pwd, this.getSalt()));
    }

    /**
     * 验证token
     */
    public boolean validToken(String token){
        AdminToken adminToken = adminRepository.findByToken(token);
        if (adminToken == null) {
            return false;
        }
        return true;
    }

    /**
     * 登出清理某一token
     */
    public int deleteSessionByToken(String token){
        return adminRepository.deleteSessionByToken(token);
    }

    /**
     * 管理员修改密码后，清理该管理员的所有token
     */
    public int deleteTokenById(){
        return adminRepository.deleteTokenById(this.getId());
    }

    /**
     * 清理过期token
     */
    public int deleteExpiredToken(){
        return adminRepository.deleteExpiredToken();
    }

    /**
     * 软删除
     */
    public void softDelete() {
        this.setDeleted(1);
    }
}
