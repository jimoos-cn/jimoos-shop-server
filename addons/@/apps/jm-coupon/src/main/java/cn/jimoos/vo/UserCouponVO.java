package cn.jimoos.vo;

import cn.jimoos.model.Coupon;
import cn.jimoos.model.CouponRecord;
import lombok.Data;

/**
 * 用户的优惠券
 *
 * @author :keepcleargas
 * @date :2021-03-27 18:55.
 */
@Data
public class UserCouponVO extends CouponRecord {
    private Coupon coupon;
}
