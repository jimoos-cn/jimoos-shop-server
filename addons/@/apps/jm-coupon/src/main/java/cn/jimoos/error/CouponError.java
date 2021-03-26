package cn.jimoos.error;


import cn.jimoos.common.error.IErrorCode;

/**
 * @author :keepcleargas
 * @date :2021-03-26 13:44.
 */
public enum CouponError implements IErrorCode {
    COUPON_NOT_EXIST("discount.coupon_not_exist", "优惠券不存在"),
    COUPON_ERROR("discount.coupon_error", "优惠券错误"),
    COUPON_RECORD_EXPIRED("discount.coupon_record_expired", "优惠券过期了"),
    COUPON_CONDITION_FAIL("discount.coupon_condition_fail", "优惠券条件不满足"),
    COUPON_RECORD_NOT_EXIST("discount.coupon_record_not_exist", "优惠券记录不存在"),
    COUPON_RECORD_USED("discount.coupon_record_used", "优惠券已使用"),
    COUPON_RECORD_EXIST("discount.coupon_record_exist", "优惠券已领取"),
    COUPON_NOT_ENOUGH("discount.coupon_not_enough", "优惠券不足"),
    COUPON_NOT_START("discount.coupon_not_start", "优惠券暂未开始领取"),
    COUPON_IS_EXPIRED("discount.coupon_is_expired", "优惠券领取时间已结束"),
    DISCOUNT_TYPE_NOT_SUPPORT("discount.discount_type_not_support", "优惠类型不支持");

    private String code;
    private String desc;

    CouponError(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
