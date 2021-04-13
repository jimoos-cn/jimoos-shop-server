package cn.jimoos.service.impl;

import cn.jimoos.common.error.ErrorCodeDefine;
import cn.jimoos.common.exception.BussException;
import cn.jimoos.dao.CouponMapper;
import cn.jimoos.dao.CouponRecordMapper;
import cn.jimoos.entity.CouponEntity;
import cn.jimoos.error.CouponError;
import cn.jimoos.factory.CouponFactory;
import cn.jimoos.form.UserCouponQueryForm;
import cn.jimoos.form.UserSatisfyQueryForm;
import cn.jimoos.form.be.*;
import cn.jimoos.model.Coupon;
import cn.jimoos.model.CouponRecord;
import cn.jimoos.repository.CouponRepository;
import cn.jimoos.service.CouponService;
import cn.jimoos.user.provider.UserProvider;
import cn.jimoos.user.vo.UserVO;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.CouponRecordVO;
import cn.jimoos.vo.UserCouponVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
    @Resource
    UserProvider userProvider;

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

        List<CouponRecord> couponRecords = couponRecordMapper.queryUserRecords(userCouponQueryForm.toQueryMap());
        if (CollectionUtils.isEmpty(couponRecords)) {
            return new ArrayList<>();
        } else {
            List<Long> ids = couponRecords.stream().map(CouponRecord::getCouponId).collect(Collectors.toList());

            List<Coupon> coupons = couponMapper.findByIdIn(ids);
            Map<Long, Coupon> idToCouponMap = coupons.stream().collect(Collectors.toMap(Coupon::getId, coupon -> coupon));

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
    public List<UserCouponVO> querySatisfyCoupon(UserSatisfyQueryForm userSatisfyQueryForm) throws BussException {
        if (userSatisfyQueryForm.getUserId() <= 0) {
            throw new BussException(ErrorCodeDefine.RECORD_NOT_EXISTS);
        }

        List<CouponRecord> couponRecords = couponRecordMapper.querySatisfyRecords(userSatisfyQueryForm.toQueryMap());
        if (CollectionUtils.isEmpty(couponRecords)) {
            return new ArrayList<>();
        } else {
            List<Long> ids = couponRecords.stream().map(CouponRecord::getCouponId).collect(Collectors.toList());

            List<Coupon> coupons = couponMapper.findByIdIn(ids);
            Map<Long, Coupon> idToCouponMap = coupons.stream().collect(Collectors.toMap(Coupon::getId, Function.identity()));

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
    public UserCouponVO findBestOneCoupon(Long userId, @NotNull BigDecimal totalRealPay) {
        CouponRecord couponRecord = couponRecordMapper.findBestOneCouponRecord(userId, totalRealPay);
        if (couponRecord == null) {
            return null;
        }

        UserCouponVO userCouponVO = new UserCouponVO();
        BeanUtils.copyProperties(couponRecord, userCouponVO);
        userCouponVO.setCoupon(couponMapper.selectByPrimaryKey(couponRecord.getCouponId()));
        return userCouponVO;
    }

    @Override
    public UserCouponVO findValidCoupon(Long couponRecordId) throws BussException {
        CouponRecord couponRecord = couponRecordMapper.selectByPrimaryKey(couponRecordId);

        if (couponRecord == null) {
            throw new BussException(CouponError.COUPON_RECORD_NOT_EXIST);
        }

        if (Boolean.TRUE.equals(couponRecord.getStatus())) {
            throw new BussException(CouponError.COUPON_RECORD_USED);
        }

        if (couponRecord.getExpired() <= System.currentTimeMillis()) {
            throw new BussException(CouponError.COUPON_RECORD_EXPIRED);
        }

        UserCouponVO userCouponVO = new UserCouponVO();
        BeanUtils.copyProperties(couponRecord, userCouponVO);
        userCouponVO.setCoupon(couponMapper.selectByPrimaryKey(couponRecord.getCouponId()));
        return userCouponVO;
    }

    @Override
    public Coupon addCoupon(BeCouponForm couponForm) {
        CouponEntity couponEntity = couponFactory.create(couponForm);

        couponRepository.save(couponEntity);
        return couponEntity;
    }

    @Override
    public Coupon updateCoupon(BeCouponForm couponForm) throws BussException {
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
    public int deleteCoupon(BeCouponDeleteForm couponDeleteForm) throws BussException {
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
    public void upOrDownCoupon(BeCouponStatusForm couponStatusForm) throws BussException {
        CouponEntity couponEntity = couponRepository.findById(couponStatusForm.getCouponId());
        if (couponEntity == null) {
            throw new BussException(CouponError.COUPON_NOT_EXIST);
        }

        couponEntity.setStatus(couponStatusForm.getStatus());
        couponRepository.saveRecords(couponEntity);
    }

    @Override
    public Page<Coupon> query(BeCouponQueryForm queryForm) {
        long count = couponMapper.queryTableCount(queryForm.toQueryMap());

        if (count > 0) {
            return Page.create(count, couponMapper.queryTable(queryForm.toQueryMap()));
        }
        return Page.empty();
    }

    @Override
    public Page<CouponRecordVO> couponRecords(BeCouponRecordQueryForm recordQueryForm) {
        long count = couponRecordMapper.queryTableCount(recordQueryForm.toQueryMap());

        if (count > 0) {
            List<CouponRecord> couponRecords = couponRecordMapper.queryTable(recordQueryForm.toQueryMap());

            List<Long> userIds = couponRecords.stream().map(CouponRecord::getUserId).collect(Collectors.toList());
            List<UserVO> userVOs = userProvider.byIds(userIds);
            Map<Long, UserVO> idToUserVOMap = userVOs.stream().collect(Collectors.toMap(UserVO::getId, Function.identity()));

            return Page.create(count, couponRecords.stream().map(couponRecord -> {
                CouponRecordVO couponRecordVO = new CouponRecordVO();
                BeanUtils.copyProperties(couponRecord, couponRecordVO);
                couponRecordVO.setUser(idToUserVOMap.get(couponRecord.getUserId()));
                return couponRecordVO;
            }).collect(Collectors.toList()));
        }

        return Page.empty();
    }
}
