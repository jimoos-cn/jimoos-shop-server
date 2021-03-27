package cn.jimoos.service.impl;

import cn.jimoos.common.error.ErrorCodeDefine;
import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.CouponMapper;
import cn.jimoos.dao.CouponRecordMapper;
import cn.jimoos.entity.CouponEntity;
import cn.jimoos.error.CouponError;
import cn.jimoos.factory.CouponFactory;
import cn.jimoos.form.UserCouponQueryForm;
import cn.jimoos.form.be.CouponDeleteForm;
import cn.jimoos.form.be.CouponForm;
import cn.jimoos.form.be.CouponQueryForm;
import cn.jimoos.form.be.CouponStatusForm;
import cn.jimoos.model.Coupon;
import cn.jimoos.model.CouponRecord;
import cn.jimoos.repository.CouponRepository;
import cn.jimoos.service.CouponService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.UserCouponVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    CouponFactory couponFactory;
    @Resource
    CouponRecordMapper couponRecordMapper;

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
        } else if (Boolean.TRUE.equals(couponRecord.getStatus())) {
            //优惠券已使用
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
    public List<UserCouponVO> queryUserCoupon(UserCouponQueryForm userCouponQueryForm) throws BussException {
        if (userCouponQueryForm.getUserId() <= 0) {
            throw new BussException(ErrorCodeDefine.RECORD_NOT_EXISTS);
        }

        List<CouponRecord> couponRecords = couponRecordMapper.queryTable(userCouponQueryForm.toQueryMap());
        if (CollectionUtils.isEmpty(couponRecords)) {
            return new ArrayList<>();
        } else {
            List<Long> ids = couponRecords.stream().map(CouponRecord::getCouponId).collect(Collectors.toList());

            List<Coupon> coupons = couponMapper.findByIdIn(ids);
            Map<Long, Coupon> idToCouponMap = coupons.stream().collect(Collectors.toMap(coupon -> coupon.getId(), coupon -> coupon));

            return couponRecords.stream().map(
                    couponRecord -> {
                        UserCouponVO userCouponVO = new UserCouponVO();
                        BeanUtils.copyProperties(couponRecord, userCouponVO);
                        userCouponVO.setCoupon(idToCouponMap.get(couponRecord.getCouponId()));
                        return userCouponVO;
                    }
            ).collect(Collectors.toList());
        }
    }

    @Override
    public Coupon addCoupon(CouponForm couponForm) {
        CouponEntity couponEntity = couponFactory.create(couponForm);

        couponRepository.save(couponEntity);
        return couponEntity;
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
        //更新对象信息
        couponEntity.update(couponForm);
        //保存对象
        couponRepository.save(couponEntity);
        return couponEntity;
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
        long count = couponMapper.queryTableCount(queryForm.toQueryMap());

        if (count > 0) {
            return Page.create(count, couponMapper.queryTable(queryForm.toQueryMap()));
        }
        return Page.empty();
    }
}
