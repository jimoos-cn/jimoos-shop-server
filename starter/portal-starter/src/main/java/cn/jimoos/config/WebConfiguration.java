package cn.jimoos.config;


import cn.jimoos.filter.CorsFilter;
import cn.jimoos.filter.TokenInterceptor;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author qisheng.chen
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Value("${local.storage.root-path}")
    private String ROOT_PATH;

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenIntercetor())
                .addPathPatterns("/**")
                .excludePathPatterns("/bAdmin/v1/login","/bAdmin/v1/logout");
    }

    @Bean
    public TokenInterceptor tokenIntercetor(){
        return new TokenInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/storage/**")
                .addResourceLocations("file:" + ROOT_PATH);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
