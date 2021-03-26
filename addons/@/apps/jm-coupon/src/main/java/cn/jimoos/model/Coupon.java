package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 优惠券
 *
 * @author :keepcleargas
 * @date :2021-03-26 21:36.
 */
@Data
@NoArgsConstructor
public class Coupon {
    private Long id;

    /**
     * 减的金额
     */
    private BigDecimal reduceMoney;

    /**
     * 满的金额
     */
    private BigDecimal fullMoney;

    /**
     * 优惠券描述
     */
    private String des;

    /**
     * 兑换码
     */
    private String code;

    /**
     * 有效时间开始
     */
    private Long effectiveStartTime;

    /**
     * 有效时间结束
     */
    private Long effectiveEndTime;

    /**
     * 领券开始时间
     */
    private Long startTime;

    /**
     * 领券截至时间
     */
    private Long endTime;

    /**
     * 有效时间 秒 为单位
     */
    private Integer validTime;

    /**
     * 一共多少张
     */
    private Integer totalNum;

    /**
     * 剩余发放数
     */
    private Integer remainNum;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 1 在线 0 下架
     */
    private Byte status;

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
     * 0 删除 1未删除
     */
    private Integer deleted;
}