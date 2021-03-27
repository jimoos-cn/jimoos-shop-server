package cn.jimoos.entity;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.error.CouponError;
import cn.jimoos.form.be.BeCouponForm;
import cn.jimoos.model.Coupon;
import cn.jimoos.model.CouponRecord;
import cn.jimoos.repository.CouponRepository;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券 实体
 *
 * @author :keepcleargas
 * @date :2021-03-26 13:30.
 */
@Data
public class CouponEntity extends Coupon {
    private CouponRepository couponRepository;
    private List<CouponRecord> couponRecordInputs;
    private CouponRecord couponRecordSet;

    public CouponEntity(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    private CouponRecord userRecord(Long userId) {
        return couponRepository.findRecord(this.getId(), userId);
    }

    /**
     * 发放优惠券
     *
     * @param userId
     */
    public void takeOne(Long userId) throws BussException {
        if (!this.getStatus()) {
            throw new BussException(CouponError.COUPON_NOT_EXIST);
        }
        if (this.getRemainNum() <= 0) {
            throw new BussException(CouponError.COUPON_NOT_ENOUGH);
        }
        long now = System.currentTimeMillis();

        if (this.getStartTime() != -1 && now < this.getStartTime()) {
            throw new BussException(CouponError.COUPON_NOT_START);
        }

        if (this.getEndTime() != -1 && now > this.getEndTime()) {
            throw new BussException(CouponError.COUPON_IS_EXPIRED);
        }

        if (userRecord(userId) != null) {
            throw new BussException(CouponError.COUPON_RECORD_EXIST);
        }

        if (CollectionUtils.isEmpty(couponRecordInputs)) {
            couponRecordInputs = new ArrayList<>();
        }
        CouponRecord couponRecordInput = new CouponRecord();
        couponRecordInput.setCouponId(this.getId());
        couponRecordInput.setCreateAt(now);
        couponRecordInput.setStatus(false);
        couponRecordInput.setDeleted(false);
        couponRecordInput.setUserId(userId);

        if (this.getValidTime() == -1 || this.getEffectiveEndTime() == -1) {
            //永久有效
            couponRecordInput.setExpired(-1L);
        } else {

            long expired = now + 1000L * this.getValidTime();
            if (expired < this.getEffectiveEndTime()) {
                couponRecordInput.setExpired(expired);
            } else {
                couponRecordInput.setExpired(this.getEffectiveEndTime());
            }
        }
        couponRecordInputs.add(couponRecordInput);

        this.setRemainNum(this.getRemainNum() - 1);
    }

    /**
     * 是否已经有用户领取过
     *
     * @return true 是 false 否
     */
    public boolean hasUserRecord() {
        return couponRepository.countRecords(this.getId()) > 0;
    }

    /**
     * 更新优惠券 字段
     *
     * @param couponForm 优惠券表单
     */
    public void update(BeCouponForm couponForm) {
        BeanUtils.copyProperties(couponForm, this);
        this.setUpdateAt(System.currentTimeMillis());
    }
}
