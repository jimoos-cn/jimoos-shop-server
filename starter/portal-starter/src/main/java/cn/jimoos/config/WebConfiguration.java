package cn.jimoos.config;


import cn.jimoos.filter.CorsFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author qisheng.chen
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
