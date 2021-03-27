package cn.jimoos.form.be;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 创建表单
 *
 * @author :keepcleargas
 * @date :2021-03-26 22:02.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeCouponForm extends AbstractAdminForm4L {
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
}
