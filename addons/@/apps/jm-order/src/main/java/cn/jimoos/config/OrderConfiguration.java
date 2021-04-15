package cn.jimoos.config;

import cn.jimoos.service.IBaseSettingsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 获取 订单配置
 *
 * @author :keepcleargas
 * @date :2021-04-15 15:18.
 */
@Configuration
@AllArgsConstructor
public class OrderConfiguration {
    @Resource
    IBaseSettingsService baseSettingsService;

    @Bean
    public OrderProperties getOrderProperties() {
        OrderProperties orderProperties = baseSettingsService.getObjectByKeyword(OrderProperties.KEY, OrderProperties.class);
        if (orderProperties == null) {
            return new OrderProperties();
        } else {
            return orderProperties;
        }
    }
}
