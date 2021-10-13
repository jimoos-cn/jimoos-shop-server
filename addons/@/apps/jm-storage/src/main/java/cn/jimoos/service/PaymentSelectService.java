package cn.jimoos.service;

import cn.jimoos.config.PaymentProperties;
import cn.jimoos.form.PaymentUpdateForm;
import cn.jimoos.service.impl.BaseSettingsService;
import cn.jimoos.utils.mapper.JsonMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentSelectService {
    @Resource
    private BaseSettingsService baseSettingsService;

    public PaymentProperties checkPayment() {
        return baseSettingsService.getObjectByKeyword(PaymentProperties.KEY, PaymentProperties.class);
    }

    public void changePayment(PaymentUpdateForm form) {
        PaymentProperties paymentProperties = new PaymentProperties();
        BeanUtils.copyProperties(form, paymentProperties);
        baseSettingsService.saveContentByKeyword(JsonMapper.INSTANCE.toJson(paymentProperties), PaymentProperties.KEY);
    }
}
