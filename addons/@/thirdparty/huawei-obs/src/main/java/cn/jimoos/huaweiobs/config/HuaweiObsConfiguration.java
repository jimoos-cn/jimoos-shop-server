package cn.jimoos.huaweiobs.config;

import cn.jimoos.service.impl.BaseSettingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2020/11/23 11:01
 */
@Slf4j
@Configuration
public class HuaweiObsConfiguration {
    @Resource
    BaseSettingsService baseSettingsService;

    @Bean(name = "huaweiObsProperties")
    public HuaweiObsProperties getHuaweiObsProperties() {
        HuaweiObsProperties huaweiObsProperties = baseSettingsService.getObjectByKeyword(HuaweiObsProperties.KEY, HuaweiObsProperties.class);
        return huaweiObsProperties == null ? new HuaweiObsProperties() : huaweiObsProperties;
    }
}
