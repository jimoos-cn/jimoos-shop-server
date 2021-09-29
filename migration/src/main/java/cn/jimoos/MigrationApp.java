package cn.jimoos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Migration APP
 *
 * @author :keepcleargas
 * @date :2018-12-05 10:44.
 */
@SpringBootApplication(scanBasePackages = {"cn.jimoos"})
public class MigrationApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(MigrationApp.class, args);
        ctx.close();
    }
}
