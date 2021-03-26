package cn.jimoos.error;


import cn.jimoos.common.error.IErrorCode;

/**
 * @author :keepcleargas
 * @date :2021-03-26 13:44.
 */
public enum CouponError implements IErrorCode {
    COUPON_NOT_EXIST("coupon.coupon_not_exist", "优惠券不存在"),
    CODE_NOT_VALID("coupon.code_not_valid", "兑换码不存在"),
    COUPON_ERROR("coupon.coupon_error", "优惠券错误"),
    COUPON_RECORD_EXPIRED("coupon.coupon_record_expired", "优惠券过期了"),
    COUPON_CONDITION_FAIL("coupon.coupon_condition_fail", "优惠券条件不满足"),
    COUPON_RECORD_NOT_EXIST("coupon.coupon_record_not_exist", "优惠券记录不存在"),
    COUPON_RECORD_USED("coupon.coupon_record_used", "优惠券已使用"),
    COUPON_RECORD_EXIST("coupon.coupon_record_exist", "优惠券已领取"),
    COUPON_NOT_ENOUGH("coupon.coupon_not_enough", "优惠券不足"),
    COUPON_NOT_START("coupon.coupon_not_start", "优惠券暂未开始领取"),
    COUPON_IS_EXPIRED("coupon.coupon_is_expired", "优惠券领取时间已结束"),
    COUPON_TYPE_NOT_SUPPORT("coupon.coupon_type_not_support", "优惠类型不支持");

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
