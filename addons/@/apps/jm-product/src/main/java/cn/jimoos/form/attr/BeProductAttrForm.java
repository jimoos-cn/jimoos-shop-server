package cn.jimoos.form.attr;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:21.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeProductAttrForm extends AbstractAdminForm4L {
    /**
     * 销售属性ID
     */
    private Long id;

    /**
     * 销售属性名称
     */
    private String name;

    /**
     * 销售属性描述
     */
    private String description;
    /**
     * 商家 ID
     */
    private Long merchantId = 0L;

    /**
     * parent_id 如果商家的attr 和 平台的 attr 重名
     */
    private Long parentId = 0L;

    private List<BeAttrValueForm> attrValues;
}
