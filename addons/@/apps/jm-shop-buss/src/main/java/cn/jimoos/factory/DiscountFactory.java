package cn.jimoos.factory;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.error.CouponError;
import cn.jimoos.form.order.OrderForm;
import cn.jimoos.order.dic.DiscountType;
import cn.jimoos.product.model.ProductItem;
import cn.jimoos.service.CouponService;
import cn.jimoos.vo.UserCouponVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单优惠 生成工厂
 *
 * @author :keepcleargas
 * @date :2021-04-13 13:55.
 */
@Component
public class DiscountFactory {
    @Resource
    CouponService couponService;

    private List<OrderForm.ItemDiscount> findBestCouponRecord(UserCouponVO userCouponVO, BigDecimal totalRealPay, List<ProductItem> productItems, List<BigDecimal> productPays) {
        if (userCouponVO == null || userCouponVO.getCoupon() == null) {
            return new ArrayList<>();
        }

        BigDecimal totalMargin = userCouponVO.getCoupon().getReduceMoney();
        List<BigDecimal> partMargins = new ArrayList<>();
        List<OrderForm.ItemDiscount> discountItems = new ArrayList<>();
        for (int i = 0; i < productItems.size(); i++) {
            BigDecimal partMargin;
            if (i == productItems.size() - 1) {
                partMargin = totalMargin.subtract(partMargins.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
            } else {
                partMargin = productPays.get(i).divide(totalRealPay, 2, RoundingMode.CEILING).multiply(totalMargin);
                partMargins.add(partMargin);
            }
            discountItems.add(new OrderForm.ItemDiscount(productItems.get(0), DiscountType.COUPON_TYPE, userCouponVO.getId(), partMargin));
        }
        return discountItems;
    }

    /**
     * 指定优惠券 优惠
     *
     * @param userCouponId 用户优惠券 ID
     * @param userId       用户 ID
     * @param totalRealPay 实付金额
     * @param productItems 商品列表
     * @param productPays  商品实付
     * @return List<OrderForm.ItemDiscount>
     * @throws BussException CouponError.COUPON_ERROR
     */
    public List<OrderForm.ItemDiscount> findByCouponRecordId(Long userCouponId, Long userId, BigDecimal totalRealPay, List<ProductItem> productItems, List<BigDecimal> productPays) throws BussException {
        UserCouponVO userCouponVO = couponService.findValidCoupon(userCouponId);

        if (!userCouponVO.getUserId().equals(userId)) {
            throw new BussException(CouponError.COUPON_ERROR);
        }

        return findBestCouponRecord(userCouponVO, totalRealPay, productItems, productPays);
    }

    /**
     * 获取最优的优惠券
     *
     * @param userId       用户 ID
     * @param totalRealPay 实付金额
     * @param productItems 商品列表
     * @param productPays  商品实付
     * @return List<OrderForm.ItemDiscount>
     */
    public List<OrderForm.ItemDiscount> findBestCouponRecord(Long userId, BigDecimal totalRealPay, List<ProductItem> productItems, List<BigDecimal> productPays) {
        UserCouponVO couponRecord = couponService.findBestOneCoupon(userId, totalRealPay);

        return findBestCouponRecord(couponRecord, totalRealPay, productItems, productPays);
    }
}
