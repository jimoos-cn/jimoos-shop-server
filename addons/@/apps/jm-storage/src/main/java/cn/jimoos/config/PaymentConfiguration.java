package cn.jimoos.config;

import cn.jimoos.form.BaseSettingsCreateForm;
import cn.jimoos.service.impl.BaseSettingsService;
import cn.jimoos.utils.mapper.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class PaymentConfiguration {
    @Resource
    BaseSettingsService baseSettingsService;

    @PostConstruct
    public void getPaymentProperties() {
        PaymentProperties paymentProperties =
                baseSettingsService.getObjectByKeyword(PaymentProperties.KEY, PaymentProperties.class);
        if (paymentProperties == null) {
            defaultPaymentProperties();
        }
    }

    private void defaultPaymentProperties() {
        PaymentProperties defaultProperties = PaymentProperties.defaultProperties();
        BaseSettingsCreateForm form = new BaseSettingsCreateForm();
        form.setKeyword(PaymentProperties.KEY);
        form.setContent(JsonMapper.INSTANCE.toJson(defaultProperties));
        baseSettingsService.save(form);
    }
}
