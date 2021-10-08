package cn.jimoos.config;

import cn.jimoos.service.impl.BaseSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
@Slf4j
public class LocalStorageConfiguration {
    private static LocalStorageProperties localStorageProperties;

    @Resource
    BaseSettingsService baseSettingsService;

    @Bean
    @DependsOn("liquibase")
    public LocalStorageProperties getLocalStorageProperties() {
        LocalStorageProperties localStorageProperties =
                baseSettingsService.getObjectByKeyword(LocalStorageProperties.KEY, LocalStorageProperties.class);
        return localStorageProperties == null ? new LocalStorageProperties() : localStorageProperties;
    }

    @PostConstruct
    public void init() {
        LocalStorageProperties properties = getLocalStorageProperties();
        log.info("local storage properties:{}", properties);
        if (properties == null) {
            throw new RuntimeException("未配置本地存储");
        }
        localStorageProperties = properties;
    }

    @Bean(name = "rootPath")
    public String rootPath() {
        return localStorageProperties.getRootPath();
    }

    @Bean(name = "host")
    public String host() {
        return localStorageProperties.getHost();
    }
}
