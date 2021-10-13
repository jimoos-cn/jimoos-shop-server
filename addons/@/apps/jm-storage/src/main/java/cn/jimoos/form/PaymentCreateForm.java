package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentCreateForm extends AbstractAdminForm4L {
    private String name;
    private String keyword;
    private String content;
}
