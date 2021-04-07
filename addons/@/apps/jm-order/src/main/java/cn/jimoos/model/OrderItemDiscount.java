package cn.jimoos.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单优惠
 *
 * @author keepcleargas
 * @version 1.0 Created in 2021/4/7 11:35
 */
@Data
public class OrderItemDiscount {
    private Long id;

    /**
     * 优惠类型
     */
    private String type;

    /**
     * 优惠描述
     */
    private String des;

    /**
     * 优惠ID
     */
    private Long discountId;

    /**
     * 优惠金额
     */
    private BigDecimal discount;

    /**
     * 是否需要退还
     */
    private Boolean needReturn;

    /**
     * 退回事件类型
     */
    private String returnType;

    /**
     * 退还状态
     */
    private Boolean returnStatus;

    /**
     * 退还执行事件
     */
    private Long returnAt;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 子订单编号
     */
    private Long orderItemId;

    /**
     * 其它信息
     */
    private String extras;

    private Long created;

    private Long updated;
}