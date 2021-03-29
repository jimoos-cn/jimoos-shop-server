package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 关联关系冗余表
 *
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Data
@NoArgsConstructor
public class ProductSkuAttrMap {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 销售属性ID
     */
    private Long attrId;

    /**
     * 销售属性名称
     */
    private String attrName;

    /**
     * 销售属性值ID
     */
    private Long attrValueId;

    /**
     * 销售属性值
     */
    private String attrValueName;

    /**
     * SKU ID
     */
    private Long skuId;

    /**
     * SPU ID
     */
    private Long productId;

    /**
     * 商家 ID
     */
    private Long merchantId;

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