package cn.jimoos.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单其它费用
 *
 * @author keepcleargas
 * @version 1.0 Created in 2021/4/7 11:35
 */
@Data
public class OrderItemFee {
    private Long id;

    /**
     * 费用类别
     */
    private String type;

    /**
     * 费用ID
     */
    private Long feeId;

    /**
     * 费用
     */
    private BigDecimal fee;

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
     * 额外信息
     */
    private String extras;

    private Long createAt;

    private Long updated;
}