package cn.jimoos.form.attr;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:23.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeProductAttrDeleteForm extends AbstractAdminForm4L {
    private Long attrId;
}
