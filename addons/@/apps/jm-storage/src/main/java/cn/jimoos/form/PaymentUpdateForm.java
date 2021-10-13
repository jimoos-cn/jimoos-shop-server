package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentUpdateForm extends AbstractAdminForm4L {
    private Boolean offlinePay;
    private Boolean wxPay;
}
