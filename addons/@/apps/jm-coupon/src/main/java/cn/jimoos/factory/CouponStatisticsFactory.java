package cn.jimoos.factory;
import cn.jimoos.entity.CouponStatisticsEntity;
import cn.jimoos.repository.CouponRepository;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author SiletFlower
 * @date 2021/7/22 11:18:55
 * @description
 */
@Component
public class CouponStatisticsFactory {
    @Resource
    CouponRepository couponRepository;

    public CouponStatisticsEntity create(Long couponId){
        CouponStatisticsEntity couponStatisticsEntity = new CouponStatisticsEntity(couponRepository);
        couponStatisticsEntity.setCouponId(couponId);
        return couponStatisticsEntity;
    }
}
