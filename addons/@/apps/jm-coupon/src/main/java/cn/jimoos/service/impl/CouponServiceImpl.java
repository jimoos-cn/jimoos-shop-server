package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.CouponMapper;
import cn.jimoos.entity.CouponEntity;
import cn.jimoos.error.CouponError;
import cn.jimoos.form.be.CouponDeleteForm;
import cn.jimoos.form.be.CouponForm;
import cn.jimoos.form.be.CouponQueryForm;
import cn.jimoos.form.be.CouponStatusForm;
import cn.jimoos.model.Coupon;
import cn.jimoos.model.CouponRecord;
import cn.jimoos.repository.CouponRepository;
import cn.jimoos.service.CouponService;
import cn.jimoos.utils.http.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 优惠券
 *
 * @author :keepcleargas
 * @date :2021-03-25 15:40.
 */
@Service
public class CouponServiceImpl implements CouponService {
    @Resource
    CouponRepository couponRepository;
    @Resource
    CouponMapper couponMapper;

    @Override
    public void takeOneCoupon(Long couponId, Long userId) throws BussException {
        CouponEntity couponEntity = couponRepository.findById(couponId);
        if (couponEntity != null) {
            couponEntity.takeOne(userId);
            couponRepository.saveRecords(couponEntity);
        } else {
            throw new BussException(CouponError.COUPON_NOT_EXIST);
        }
    }

    @Override
    public void takeOneByCode(String code, Long userId) throws BussException {
        if (StringUtils.isEmpty(code)) {
            throw new BussException(CouponError.CODE_NOT_VALID);
        }
        CouponEntity couponEntity = couponRepository.findByCode(code);
        if (couponEntity != null) {
            couponEntity.takeOne(userId);
            couponRepository.saveRecords(couponEntity);
        } else {
            throw new BussException(CouponError.CODE_NOT_VALID);
        }
    }


    @Override
    public void occupy(Long couponRecordId) throws BussException {
        CouponRecord couponRecord = couponRepository.findRecordById(couponRecordId);

        if (couponRecord == null) {
            throw new BussException(CouponError.COUPON_RECORD_NOT_EXIST);
        } else if (couponRecord.getStatus()) {
            throw new BussException(CouponError.COUPON_RECORD_USED);
        } else {
            couponRecord.setStatus(true);
            couponRecord.setUpdateAt(System.currentTimeMillis());
            couponRepository.saveRecord(couponRecord);
        }
    }

    @Override
    public void unOccupy(Long couponRecordId) throws BussException {
        CouponRecord couponRecord = couponRepository.findRecordById(couponRecordId);

        if (couponRecord == null) {
            throw new BussException(CouponError.COUPON_RECORD_NOT_EXIST);
        } else {
            couponRecord.setStatus(false);
            couponRecord.setUpdateAt(System.currentTimeMillis());
            couponRepository.saveRecord(couponRecord);
        }
    }

    @Override
    public Coupon addCoupon(CouponForm couponForm) {
        //todo add coupon
        return null;
    }

    @Override
    public Coupon updateCoupon(CouponForm couponForm) throws BussException {
        CouponEntity couponEntity = couponRepository.findById(couponForm.getId());

        if (couponEntity == null) {
            throw new BussException(CouponError.COUPON_NOT_EXIST);
        }

        if (couponEntity.hasUserRecord()) {
            throw new BussException(CouponError.COUPON_HAS_RECORDS);
        }
        //todo update coupon
        return null;
    }

    @Override
    public int deleteCoupon(CouponDeleteForm couponDeleteForm) throws BussException {
        CouponEntity couponEntity = couponRepository.findById(couponDeleteForm.getCouponId());

        if (couponEntity == null) {
            throw new BussException(CouponError.COUPON_NOT_EXIST);
        }
        if (couponEntity.hasUserRecord()) {
            throw new BussException(CouponError.COUPON_HAS_RECORDS);
        }
        return couponMapper.setDeletedTrue(couponDeleteForm.getCouponId());
    }

    @Override
    public void upOrDownCoupon(CouponStatusForm couponStatusForm) throws BussException {
        CouponEntity couponEntity = couponRepository.findById(couponStatusForm.getCouponId());
        if (couponEntity == null) {
            throw new BussException(CouponError.COUPON_NOT_EXIST);
        }

        couponEntity.setStatus(couponStatusForm.getStatus());
        couponRepository.saveRecords(couponEntity);
    }

    @Override
    public Page<Coupon> query(CouponQueryForm queryForm) {
        Long count = couponMapper.queryTableCount(queryForm.toQueryMap());

        if (count > 0) {
            return Page.create(count, couponMapper.queryTable(queryForm.toQueryMap()));
        }
        return Page.empty();
    }
}
