package cn.jimoos.entity;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.error.CouponError;
import cn.jimoos.model.CouponStatistics;
import cn.jimoos.repository.CouponRepository;
import cn.jimoos.vo.CouponStatisticsVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

/**
 * @author SiletFlower
 * @date 2021/7/22 11:17:57
 * @description
 */
@Data
@NoArgsConstructor
public class CouponStatisticsEntity extends CouponStatistics {
    private CouponRepository couponRepository;

    public CouponStatisticsEntity(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }


    // 计算属性
    /**
     * 置空
     */
    public void setBlank(){
        this.setTakeRate(BigDecimal.valueOf(0));
        this.setUseRate(BigDecimal.valueOf(0));
        this.setTotalUseCouponAmount(BigDecimal.valueOf(0));
        this.setTotalTurnover(BigDecimal.valueOf(0));
        this.setTotalOrder(0L);
        this.setCostEffectivenessRatio(BigDecimal.valueOf(0));
        this.setUseUnitPrice(BigDecimal.valueOf(0));
    }

    /**
     * 计算各类参数
     * @param coupon
     */
    public void compute(CouponEntity coupon){
        // 领取数
        Integer take =  coupon.getTotalNum() - coupon.getRemainNum();
        // 使用数量
        Long use = coupon.queryUseCount();
        if (take == 0) {
            // 无人领取时
            setBlank();
        }else if(use == 0) {
            // 无人使用
            setBlank();
            // 领取率
            this.setTakeRate(
                    BigDecimal.valueOf(take)
                            .divide(BigDecimal.valueOf(coupon.getTotalNum()))
                            .setScale(2, BigDecimal.ROUND_HALF_UP));
        }else{
            // 领取率
            this.setTakeRate(
                    BigDecimal.valueOf(take)
                            .divide(BigDecimal.valueOf(coupon.getTotalNum()))
                            .setScale(2, BigDecimal.ROUND_HALF_UP));
            // 使用率
            this.setUseRate(
                    BigDecimal.valueOf(use)
                            .divide(BigDecimal.valueOf(take))
                            .setScale(2, BigDecimal.ROUND_HALF_UP));
            // 优惠总金额
            this.setTotalUseCouponAmount(
                    BigDecimal.valueOf(use)
                            .multiply(coupon.getReduceMoney())
                            .setScale(2, BigDecimal.ROUND_HALF_UP));
            // 用券总成交额
            this.setTotalTurnover(coupon.queryAssociatedOrderAmount());
            // 用券总订单数 -默认设置为使用数量
            this.setTotalOrder(use);
            // 费效比
            this.setCostEffectivenessRatio(
                    this.getTotalUseCouponAmount().divide(this.getTotalTurnover())
                            .setScale(2, BigDecimal.ROUND_HALF_UP));
            // 用券笔单价
            this.setUseUnitPrice(
                    this.getTotalTurnover().divide(BigDecimal.valueOf(this.getTotalOrder()))
                            .setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }

    // Dao层相关操作

    /**
     * 查询优惠券的统计记录
     * @return
     */
    @SneakyThrows
    public CouponStatisticsVO queryCouponStatistics(){
        CouponEntity coupon = couponRepository.findById(this.getCouponId());
        if (coupon == null) {
            throw new BussException(CouponError.COUPON_NOT_EXIST);
        }
        compute(coupon);
        CouponStatisticsVO couponStatisticsVO = new CouponStatisticsVO();
        BeanUtils.copyProperties(this, couponStatisticsVO);
        return couponStatisticsVO;
    }
}
