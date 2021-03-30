package cn.jimoos.form.attr;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:28.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeAttrValuesForm extends AbstractAdminForm4L {
    private Long attrId;
    private List<BeAttrValuesForm> valueForms;
}
