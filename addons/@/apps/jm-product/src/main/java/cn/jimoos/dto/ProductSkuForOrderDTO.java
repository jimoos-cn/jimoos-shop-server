package cn.jimoos.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2021/04/07 16:12
 */
@Data
public class ProductSkuForOrderDTO {
    /**
     * productId
     */
    private Long productId;
    /**
     * skuId
     */
    private Long skuId;
    /**
     * 名称
     */
    private String productName;
    /**
     * productCover
     */
    private String productCover;
    /**
     * 规格描述
     */
    private String attrValueIds;
    /**
     * sku 图片
     */
    private String skuCover;
    /**
     * sku 价格
     */
    private BigDecimal price;
    /**
     * 类型 0 普通商品
     */
    private Integer type;
}
