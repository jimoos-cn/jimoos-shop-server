package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:46.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeCollectionIdForm extends AbstractAdminPageForm4L {
    private Long id;
}
