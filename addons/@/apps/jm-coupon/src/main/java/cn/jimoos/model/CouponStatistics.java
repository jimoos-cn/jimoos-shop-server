package cn.jimoos.model;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @author SiletFlower
 * @date 2021/7/22 11:04:16
 * @description 优惠券统计数据
 */
@Data
public class CouponStatistics{

    /**
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 领取率 优惠券领取总量/优惠券发放总量；
     */
    private BigDecimal takeRate;
    /**
     * 使用率 优惠券已使用总量/优惠券已领取总量；
     */
    private BigDecimal useRate;

    /**
     * 优惠总金额 使用该优惠券优惠的总金额；
     */
    private BigDecimal totalUseCouponAmount;

    /**
     * 用券总成交额 使用该优惠券的订单付款总金额；
     */
    private BigDecimal totalTurnover;

    /**
     * 用券总订单数
     */
    private Long totalOrder;

    /**
     * 费效比 优惠总金额/用券总成交额；
     */
    private BigDecimal costEffectivenessRatio;

    /**
     * 用券笔单价 用券总成交额 / 使用该优惠券的付款订单总数；
     */
    private BigDecimal useUnitPrice;

}
