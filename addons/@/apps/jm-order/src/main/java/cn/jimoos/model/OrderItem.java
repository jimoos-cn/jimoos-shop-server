package cn.jimoos.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详情
 *
 * @author :keepcleargas
 * @date :2021/4/7 11:35
 */

@Data
public class OrderItem {
    private Long id;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品封面
     */
    private String productCover;

    /**
     * 商品类别
     */
    private String productType;

    /**
     * SKU ID（可选）
     */
    private Long skuId;

    /**
     * SKU 名称（可选）
     */
    private String skuName;

    /**
     * SKU 封面（可选）
     */
    private String skuCover;

    /**
     * 订购数量
     */
    private Integer num;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 费用
     */
    private BigDecimal fee;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 优惠金额
     */
    private BigDecimal discount;
    /**
     * 实际应支付
     */
    private BigDecimal realPay;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 额外信息
     */
    private String extra;

    private Long createAt;

    private Long updateAt;

    private Boolean deleted;
}
