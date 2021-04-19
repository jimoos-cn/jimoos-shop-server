package cn.jimoos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 伪造 Liquibase 解决依赖 问题
 *
 * @author :keepcleargas
 * @date :2021-03-29 09:45.
 */
@Configuration
@Slf4j
public class FakeLiquibase {
    @Bean
    public Liquibase liquibase(DataSource dataSource) {
        return new Liquibase();
    }

    public static class Liquibase {

    }
}
