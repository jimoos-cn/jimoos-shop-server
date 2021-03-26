package cn.jimoos.repository;

import cn.jimoos.dao.CouponMapper;
import cn.jimoos.dao.CouponRecordMapper;
import cn.jimoos.entity.CouponEntity;
import cn.jimoos.model.Coupon;
import cn.jimoos.model.CouponRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-25 15:31.
 */
@Repository
public class CouponRepository {
    @Resource
    CouponMapper couponMapper;
    @Resource
    CouponRecordMapper couponRecordMapper;

    public CouponEntity findById(Long couponId) {
        return wrapper(couponMapper.selectByPrimaryKey(couponId), false);
    }

    public CouponEntity findByCode(String code) {
        return wrapper(couponMapper.findFirstByCode(code), false);
    }

    public CouponRecord findRecordById(Long couponRecordId) {
        CouponRecord couponRecord = couponRecordMapper.selectByPrimaryKey(couponRecordId);

        if (couponRecord != null && !couponRecord.getDeleted()) {
            return couponRecord;
        } else {
            return null;
        }
    }

    public void saveRecord(CouponRecord couponRecord) {
        couponRecordMapper.updateByPrimaryKey(couponRecord);
    }

    public CouponRecord findRecord(Long couponId, Long userId) {
        return couponRecordMapper.findOneByCouponIdAndUserId(couponId, userId);
    }

    /**
     * @Coupon的 entity wrapper方法
     */
    private CouponEntity wrapper(Coupon coupon, boolean skipRepo) {
        if (coupon != null) {
            if (skipRepo) {
                return (CouponEntity) coupon;
            }
            CouponEntity couponEntity = new CouponEntity(this);
            BeanUtils.copyProperties(coupon, couponEntity);
            return couponEntity;
        }
        return null;
    }

    /**
     * 保存 发放记录
     *
     * @param couponEntity 优惠券对象
     */
    public void saveRecords(CouponEntity couponEntity) {
        couponMapper.updateByPrimaryKey(couponEntity);
        if (!CollectionUtils.isEmpty(couponEntity.getCouponRecordInputs())) {
            couponRecordMapper.batchInsert(couponEntity.getCouponRecordInputs());
        }
    }

    /**
     * 查询优惠券${couponId}的领取记录统计
     *
     * @param couponId 优惠券 ID
     * @return
     */
    public Long countRecords(Long couponId) {
        return couponRecordMapper.countByCouponId(couponId);
    }
}
