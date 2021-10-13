package cn.jimoos.form;

import cn.jimoos.config.PaymentProperties;
import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentUpdateForm extends AbstractAdminForm4L {
    private List<PaymentProperties.Config> configs;
}
