package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.be.*;
import cn.jimoos.model.Coupon;
import cn.jimoos.service.CouponService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.CouponRecordVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-03-27 21:37.
 */
@RestController
@RequestMapping("/bAdmin/v1/coupons")
public class BeCouponApi {
    @Resource
    CouponService couponService;

    /**
     * 查询优惠券列表
     *
     * @param form Coupon Query Form
     * @return List<Coupon>
     */
    @GetMapping(value = "/query", produces = "application/json; charset=utf-8")
    public Page<Coupon> query(@ModelAttribute BeCouponQueryForm form) {
        return couponService.query(form);
    }

    /**
     * 添加优惠券
     *
     * @param couponForm coupon form
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public Coupon add(@ModelAttribute BeCouponForm couponForm) {
        return couponService.addCoupon(couponForm);
    }

    /**
     * 更新
     *
     * @param couponForm coupon form
     */
    @PostMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public Coupon update(@ModelAttribute BeCouponForm couponForm) throws BussException {
        return couponService.updateCoupon(couponForm);
    }

    /**
     * 查询领取记录
     *
     * @param form coupon records query form
     * @return Page<CouponRecordVO>
     */
    @GetMapping(value = "/{couponId}/records", produces = "application/json; charset=utf-8")
    public Page<CouponRecordVO> couponRecords(@ModelAttribute BeCouponRecordQueryForm form) {
        return couponService.couponRecords(form);
    }

    /**
     * 删除
     *
     * @param deleteForm coupon delete form
     * @throws BussException CouponError.COUPON_NOT_EXIST | CouponError.COUPON_HAS_RECORDS
     */
    @PostMapping(value = "/{couponId}/delete", produces = "application/json; charset=utf-8")
    public void deleteCoupon(@ModelAttribute BeCouponDeleteForm deleteForm) throws BussException {
        couponService.deleteCoupon(deleteForm);
    }

    /**
     * 上架优惠券
     *
     * @param form coupon status form
     * @throws BussException CouponError.COUPON_NOT_EXIST
     */
    @PostMapping(value = "/{couponId}/up", produces = "application/json; charset=utf-8")
    public void upCoupon(@ModelAttribute BeCouponStatusForm form) throws BussException {
        form.setStatus(true);
        couponService.upOrDownCoupon(form);
    }

    /**
     * 下架优惠券
     *
     * @param form coupon status form
     * @throws BussException CouponError.COUPON_NOT_EXIST
     */
    @PostMapping(value = "/{couponId}/down", produces = "application/json; charset=utf-8")
    public void downCoupon(@ModelAttribute BeCouponStatusForm form) throws BussException {
        form.setStatus(false);
        couponService.upOrDownCoupon(form);
    }
}
