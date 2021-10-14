package cn.jimoos.cron;

import cn.jimoos.entity.AdminEntity;
import cn.jimoos.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author SiletFlower
 * @date 2021/7/23 13:14:08
 * @description
 */
@Component
@Slf4j
public class TokenJob {
    @Resource
    AdminRepository adminRepository;

    /**
     * 每月 5号 5点进行
     */
    @Scheduled(cron = "0 0 5 5 * ?")
    public void cleanToken(){
        log.info("Clean Expired Token Job start time is {}", System.currentTimeMillis());
        int count = deleteExpiredToken();
        log.info("Clean Expired Token Count is {}", count);
    }


    /**
     * 清除过期的token
     */
    private int deleteExpiredToken(){
        AdminEntity adminEntity = new AdminEntity(adminRepository);
        return adminEntity.deleteExpiredToken();
    }


}
