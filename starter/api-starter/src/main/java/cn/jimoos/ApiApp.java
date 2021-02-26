package cn.jimoos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Api APP
 *
 * @author :keepcleargas
 * @date :2018-12-05 10:44.
 */
@SpringBootApplication(scanBasePackages = {"cn.jimoos"})
public class ApiApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiApp.class, args);
    }
}
