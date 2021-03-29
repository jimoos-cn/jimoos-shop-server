package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * sku表
 *
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Data
@NoArgsConstructor
public class ProductSku {
    /**
     * SKU ID
     */
    private Long id;

    /**
     * 销售属性值{attr_value_id},{attr_value_id} 多个销售属性值逗号分隔
     */
    private String attrValueIds;

    /**
     * 封面
     */
    private String cover;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 展示价
     */
    private BigDecimal showPrice;

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