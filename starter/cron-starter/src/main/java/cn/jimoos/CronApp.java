package cn.jimoos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Cron APP
 * <p>
 * 定时任务 启动器
 *
 * @author keepcleargas
 */
@SpringBootApplication(scanBasePackages = {"cn.jimoos"})
@EnableScheduling
public class CronApp {
    public static void main(String[] args) {
        SpringApplication.run(CronApp.class, args);
    }
}
