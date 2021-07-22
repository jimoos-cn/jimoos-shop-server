package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.UserCouponQueryForm;
import cn.jimoos.form.UserSatisfyQueryForm;
import cn.jimoos.form.be.*;
import cn.jimoos.model.Coupon;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.CouponRecordVO;
import cn.jimoos.vo.CouponStatisticsVO;
import cn.jimoos.vo.UserCouponVO;

import java.math.BigDecimal;
import java.util.List;

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
     * 查询用户的 优惠券
     *
     * @param userCouponQueryForm 查询表单
     * @return List<UserCouponVO>
     * @throws BussException ErrorCodeDefine.RECORD_NOT_EXISTS
     */
    List<UserCouponVO> queryUserCoupon(UserCouponQueryForm userCouponQueryForm) throws BussException;

    /**
     * 查询满足 查询条件的优惠券
     *
     * @param userSatisfyQueryForm satisfy query form
     * @return List<UserCouponVO>
     * @throws BussException ErrorCodeDefine.RECORD_NOT_EXISTS
     */
    List<UserCouponVO> querySatisfyCoupon(UserSatisfyQueryForm userSatisfyQueryForm) throws BussException;

    /**
     * 查找最优的 优惠券
     *
     * @param userId       userId
     * @param totalRealPay 消费金额
     * @return UserCouponVO 优惠券
     */
    UserCouponVO findBestOneCoupon(Long userId, BigDecimal totalRealPay);

    /**
     * 查找有效的用户优惠券
     *
     * @param couponRecordId 用户优惠券 ID
     * @return UserCouponVO
     */
    UserCouponVO findValidCoupon(Long couponRecordId) throws BussException;

    /**
     * 添加优惠券
     *
     * @param couponForm Coupon Form
     * @return Coupon
     */
    Coupon addCoupon(BeCouponForm couponForm);

    /**
     * 获取一张优惠券
     *
     * @param couponId
     * @return
     */
    Coupon getOne(Long couponId) throws BussException;


    /**
     * 更新优惠券
     *
     * @param couponForm Coupon Form
     * @return Coupon
     * @throws BussException CouponError.COUPON_NOT_EXIST | CouponError.COUPON_HAS_RECORDS
     */
    Coupon updateCoupon(BeCouponForm couponForm) throws BussException;

    /**
     * 删除优惠券
     *
     * @param couponDeleteForm 删除表单
     * @return affectNum >0 执行成功
     * @throws BussException CouponError.COUPON_NOT_EXIST | CouponError.COUPON_HAS_RECORDS
     */
    int deleteCoupon(BeCouponDeleteForm couponDeleteForm) throws BussException;

    /**
     * 上下架 优惠券
     *
     * @param couponStatusForm 状态表单
     * @throws BussException CouponError.COUPON_NOT_EXIST
     */
    void upOrDownCoupon(BeCouponStatusForm couponStatusForm) throws BussException;

    /**
     * 查询优惠券列表
     *
     * @param queryForm 查询表单
     * @return Page<Coupon>
     */
    Page<Coupon> query(BeCouponQueryForm queryForm);

    /**
     * 查询优惠券领取记录
     *
     * @param recordQueryForm record query form
     * @return Page<CouponRecordVO>
     */
    Page<CouponRecordVO> couponRecords(BeCouponRecordQueryForm recordQueryForm);

    /**
     * 查询某优惠券的统计信息
     * @param couponId
     * @return
     */
    CouponStatisticsVO getCouponStatistics(Long couponId);
}
