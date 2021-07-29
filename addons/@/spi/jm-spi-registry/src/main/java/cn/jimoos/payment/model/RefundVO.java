package cn.jimoos.payment.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author SiletFlower
 * @date 2021/7/26 17:22:23
 * @description
 *
 */
@Data
public class RefundVO {
    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 退款金额
     */
    private BigDecimal refundMoney;

    /**
     * 原订单金额
     */
    private BigDecimal money;

    /**
     * 3 微信支付
     */
    private Integer payType;
}
