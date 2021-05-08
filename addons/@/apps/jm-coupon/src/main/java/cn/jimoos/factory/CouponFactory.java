package cn.jimoos.factory;

import cn.jimoos.entity.CouponEntity;
import cn.jimoos.form.be.BeCouponForm;
import cn.jimoos.repository.CouponRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-26 22:45.
 */
@Component
public class CouponFactory {
    @Resource
    CouponRepository couponRepository;

    public CouponEntity create(BeCouponForm couponForm) {
        CouponEntity couponEntity = new CouponEntity(couponRepository);
        couponEntity.setRemainNum(couponForm.getTotalNum());
        BeanUtils.copyProperties(couponForm, couponEntity);
        couponEntity.setUpdateAt(System.currentTimeMillis());
        couponEntity.setCreateAt(System.currentTimeMillis());
        couponEntity.setDeleted(false);
        return couponEntity;
    }
}
