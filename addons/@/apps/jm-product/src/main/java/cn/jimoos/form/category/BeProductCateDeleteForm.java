package cn.jimoos.form.category;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :keepcleargas
 * @date :2021-03-29 20:14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeProductCateDeleteForm extends AbstractAdminForm4L {
    private Long categoryId;
}
