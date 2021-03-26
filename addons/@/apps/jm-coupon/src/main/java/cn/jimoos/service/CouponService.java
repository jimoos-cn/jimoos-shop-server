package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.be.CouponDeleteForm;
import cn.jimoos.form.be.CouponForm;
import cn.jimoos.form.be.CouponQueryForm;
import cn.jimoos.form.be.CouponStatusForm;
import cn.jimoos.model.Coupon;
import cn.jimoos.utils.http.Page;

/**
 * 优惠券服务
 *
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
     * 使用 兑换码 兑换优惠券
     *
     * @param code   兑换码
     * @param userId 用户 ID
     * @throws BussException
     */
    void takeOneByCode(String code, Long userId) throws BussException;

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

    /**
     * 添加优惠券
     *
     * @param couponForm Coupon Form
     * @return Coupon
     */
    Coupon addCoupon(CouponForm couponForm);

    /**
     * 更新优惠券
     *
     * @param couponForm Coupon Form
     * @return Coupon
     * @throws BussException CouponError.COUPON_NOT_EXIST | CouponError.COUPON_HAS_RECORDS
     */
    Coupon updateCoupon(CouponForm couponForm) throws BussException;

    /**
     * 删除优惠券
     *
     * @param couponDeleteForm 删除表单
     * @return affectNum >0 执行成功
     * @throws BussException CouponError.COUPON_NOT_EXIST | CouponError.COUPON_HAS_RECORDS
     */
    int deleteCoupon(CouponDeleteForm couponDeleteForm) throws BussException;

    /**
     * 上下架 优惠券
     *
     * @param couponStatusForm 状态表单
     * @throws BussException CouponError.COUPON_NOT_EXIST
     */
    void upOrDownCoupon(CouponStatusForm couponStatusForm) throws BussException;

    /**
     * 查询优惠券列表
     *
     * @param queryForm 查询表单
     * @return Page<Coupon>
     */
    Page<Coupon> query(CouponQueryForm queryForm);
}
