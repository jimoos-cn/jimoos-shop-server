package cn.jimoos.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单信息
 *
 * @author :keepcleargas
 * @date :2021/4/7 11:35
 */
@Data
public class Order {
    private Long id;

    /**
     * 订单编号 50
     */
    private String orderNum;

    /**
     * 订单标题 255
     */
    private String subject;

    /**
     * 订单类别 50
     */
    private String orderType;

    /**
     * 商品 `类型` 的数量，非件数
     */
    private Integer itemAmount;

    /**
     * 总价格
     */
    private BigDecimal totalPrice;

    /**
     * 费用
     */
    private BigDecimal totalFee;

    /**
     * 商品总价
     */
    private BigDecimal totalProductPrice;

    /**
     * 总优惠
     */
    private BigDecimal totalDiscount;

    /**
     * 实际支付
     */
    private BigDecimal realPay;

    /**
     * 购买者ID
     */
    private Long userId;

    /**
     * 购买备注
     */
    private String comment;

    /**
     * 订单状态
     */
    private Byte status;

    /**
     * 是否发生退单
     */
    private Boolean refund;

    /**
     * 是否发生评价
     */
    private Boolean rate;
    /**
     * 额外字段-用于特殊业务场景下的信息的储存
     */
    private String extra;

    private Long createAt;

    private Long updateAt;

    private Boolean deleted;
}
