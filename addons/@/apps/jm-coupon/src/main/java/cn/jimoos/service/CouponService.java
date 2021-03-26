package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;

/**
 * @author :keepcleargas
 * @date :2021-03-26 21:38.
 */
public interface CouponService {
    /**
     * 发放一张优惠券
     *
     * @param couponId 优惠券 ID
     * @param userId   用户
     * @throws BussException CouponError.COUPON_NOT_EXIST
     */
    void takeOneCoupon(Long couponId, Long userId) throws BussException;

    /**
     * 使用 用户优惠券
     *
     * @param couponRecordId 用户优惠券ID
     * @throws BussException CouponError.COUPON_RECORD_NOT_EXIST | CouponError.COUPON_RECORD_USED
     */
    void occupy(Long couponRecordId) throws BussException;

    /**
     * 退回 优惠券使用
     *
     * @param couponRecordId 用户优惠券ID
     * @throws BussException CouponError.COUPON_RECORD_NOT_EXIST
     */
    void unOccupy(Long couponRecordId) throws BussException;
}
