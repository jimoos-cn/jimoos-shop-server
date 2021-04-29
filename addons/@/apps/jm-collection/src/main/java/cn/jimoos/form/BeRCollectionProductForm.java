package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:36.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeRCollectionProductForm extends AbstractAdminForm4L {
    private Long id;
    private Long productId;
    private Integer sort;
    private Long collectionId;
}
