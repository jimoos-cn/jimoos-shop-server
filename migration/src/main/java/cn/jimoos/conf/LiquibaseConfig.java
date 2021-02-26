package cn.jimoos.conf;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author: keepcleargas
 * created: 18-5-14
 * time: 下午8:17
 */
@Configuration
@Slf4j
public class LiquibaseConfig {
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        log.info("Start Default Database Liquibase Update");
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/master.xml");
        liquibase.setContexts("dev,default,production");
        liquibase.setShouldRun(true);
        return liquibase;
    }
}
