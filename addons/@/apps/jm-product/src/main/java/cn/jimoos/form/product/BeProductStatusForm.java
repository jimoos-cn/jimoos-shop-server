package cn.jimoos.form.product;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :keepcleargas
 * @date :2021-03-30 21:08.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeProductStatusForm extends AbstractAdminForm4L {
    private Long productId;
    private Byte status;
    private String reason;
}
