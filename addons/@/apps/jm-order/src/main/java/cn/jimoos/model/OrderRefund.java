package cn.jimoos.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 退款订单信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRefund {
    private Long id;

    /**
     * 退款订单号
     */
    private String refundNum;

    /**
     * 原订单号
     */
    private String orderNum;

    /**
     * 退款状态
     */
    private Byte refundStatus;

    /**
     * 原订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}
