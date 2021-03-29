package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 销售属性值
 *
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Data
@NoArgsConstructor
public class ProductAttrValue {
    /**
     * 销售属性值ID
     */
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
    private Long merchantId;

    /**
     * 销售属性ID
     */
    private Long attrId;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 0 未删除 1 已删除
     */
    private Boolean deleted;
}