package cn.jimoos.config;

import cn.jimoos.service.IBaseSettingsService;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Binary Wang
 */
@Configuration
@ConditionalOnClass(WxPayService.class)
@AllArgsConstructor
public class WxPayConfiguration {

    @Resource
    IBaseSettingsService baseSettingsService;

    @Bean
    public WxPayProperties getWxPayProperties() {
        WxPayProperties wxPayProperties = baseSettingsService.getObjectByKeyword(WxPayProperties.KEY, WxPayProperties.class);
        return wxPayProperties == null ? new WxPayProperties() : wxPayProperties;
    }

    @PostConstruct
    public void init() {
        Assert.notNull(getWxPayProperties(), "微信支付未配置，请配置后重试");
    }

    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxService() {
        WxPayProperties properties = getWxPayProperties();

        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(properties.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(properties.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(properties.getMchKey()));
        payConfig.setSubAppId(StringUtils.trimToNull(properties.getSubAppId()));
        payConfig.setSubMchId(StringUtils.trimToNull(properties.getSubMchId()));
        payConfig.setKeyPath(StringUtils.trimToNull(properties.getKeyPath()));
        payConfig.setTradeType("JSAPI");
        payConfig.setSignType("MD5");
        payConfig.setNotifyUrl(StringUtils.trimToNull(properties.getNotifyUrl()));
        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
}
