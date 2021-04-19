package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.UserCouponQueryForm;
import cn.jimoos.form.UserSatisfyQueryForm;
import cn.jimoos.service.CouponService;
import cn.jimoos.vo.UserCouponVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 优惠券
 *
 * @author :keepcleargas
 * @date :2021-03-27 21:30.
 */
@RestController
@RequestMapping("/v1/coupons")
public class CouponApi {
    @Resource
    CouponService couponService;

    /**
     * 获取我的优惠券
     *
     * @param form User Coupon Query Form
     * @return List<UserCouponVO>
     */
    @GetMapping(value = "byUserId", produces = "application/json; charset=utf-8")
    public List<UserCouponVO> byUserId(@ModelAttribute UserCouponQueryForm form) throws BussException {
        return couponService.queryUserCoupon(form);
    }

    /**
     * 查询满足满键条件的优惠券,按满减金额 从大到小
     *
     * @param form User Coupon Satisfy Query Form
     * @return List<UserCouponVO>
     */
    @GetMapping(value = "/satisfy", produces = "application/json; charset=utf-8")
    public List<UserCouponVO> byUserId(@ModelAttribute UserSatisfyQueryForm form) throws BussException {
        return couponService.querySatisfyCoupon(form);
    }

    /**
     * 兑换优惠券
     *
     * @param userId user Id
     * @param code   兑换码
     */
    @PostMapping(value = "/exchange", produces = "application/json; charset=utf-8")
    public void exchange(@RequestParam("userId") Long userId, @RequestParam("code") String code) throws BussException {
        couponService.takeOneByCode(code, userId);
    }
}
