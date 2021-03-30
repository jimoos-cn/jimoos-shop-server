package cn.jimoos.form.attr;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:41.
 */
@Data
public class BeAttrValueForm {
    private Long id;
    /**
     * 销售属性值
     */
    private String name;

    /**
     * 销售属性值描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 商家 ID
     */
    private Long merchantId = 0L;
}
