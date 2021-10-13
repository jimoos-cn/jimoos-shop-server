package cn.jimoos.service;

import cn.jimoos.config.PaymentConfiguration;
import cn.jimoos.config.PaymentProperties;
import cn.jimoos.form.BaseSettingsCreateForm;
import cn.jimoos.form.PaymentCreateForm;
import cn.jimoos.form.PaymentUpdateForm;
import cn.jimoos.service.impl.BaseSettingsService;
import cn.jimoos.utils.mapper.JsonMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentSelectService {
    @Resource
    private BaseSettingsService baseSettingsService;

    public PaymentProperties checkPayment() {
        return baseSettingsService.getObjectByKeyword(PaymentProperties.KEY, PaymentProperties.class);
    }

    public void addPayment(PaymentCreateForm form) {
        PaymentProperties paymentProperties =
                baseSettingsService.getObjectByKeyword(PaymentProperties.KEY, PaymentProperties.class);
        PaymentProperties.Config config = new PaymentProperties.Config();
        config.setName(form.getName());
        config.setKeyword(form.getKeyword());
        config.setActive(false);
        paymentProperties.getConfigs().add(config);
        baseSettingsService.saveContentByKeyword(JsonMapper.INSTANCE.toJson(paymentProperties), PaymentProperties.KEY);
        BaseSettingsCreateForm createForm = new BaseSettingsCreateForm();
        createForm.setKeyword(form.getKeyword());
        createForm.setContent(form.getContent());
        baseSettingsService.save(createForm);
    }

    public void changePayment(PaymentUpdateForm form) {
        PaymentProperties paymentProperties = new PaymentProperties();
        BeanUtils.copyProperties(form, paymentProperties);
        baseSettingsService.saveContentByKeyword(JsonMapper.INSTANCE.toJson(form), PaymentProperties.KEY);
    }
}
