package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:55.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeBatchUpdateForm extends AbstractAdminForm4L {
    private Long collectionId;
    private List<BeRCollectionProductForm> collectionProductForms;
}
