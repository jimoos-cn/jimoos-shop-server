package cn.jimoos.form.attr;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:30.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeAttrValueDeleteForm extends AbstractAdminForm4L {
    private Long attrId;
    private Long attrValueId;
}
