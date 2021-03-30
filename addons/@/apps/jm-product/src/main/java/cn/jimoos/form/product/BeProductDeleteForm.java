package cn.jimoos.form.product;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :keepcleargas
 * @date :2021-03-30 21:12.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeProductDeleteForm extends AbstractAdminForm4L {
    private Long productId;
}
