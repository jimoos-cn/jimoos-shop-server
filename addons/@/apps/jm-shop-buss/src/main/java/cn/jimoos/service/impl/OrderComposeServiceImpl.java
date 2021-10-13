package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.constant.ShipmentType;
import cn.jimoos.dao.OrderCartMapper;
import cn.jimoos.entity.OrderEntity;
import cn.jimoos.error.OrderError;
import cn.jimoos.form.order.CancelForm;
import cn.jimoos.form.order.ConfirmForm;
import cn.jimoos.form.order.OrderForm;
import cn.jimoos.form.order.OrderItemForm;
import cn.jimoos.form.shipment.ShipmentConfirmForm;
import cn.jimoos.form.shipment.ShipmentCreateForm;
import cn.jimoos.model.OrderCart;
import cn.jimoos.model.OrderItemDiscount;
import cn.jimoos.order.dic.DiscountType;
import cn.jimoos.service.CouponService;
import cn.jimoos.service.OrderComposeService;
import cn.jimoos.service.OrderService;
import cn.jimoos.service.ShipmentService;
import cn.jimoos.user.model.UserAddress;
import cn.jimoos.user.provider.AddressProvider;
import cn.jimoos.vo.OrderVO;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :keepcleargas
 * @date :2021-04-13 16:49.
 */
@Service
public class OrderComposeServiceImpl implements OrderComposeService {
    /**
     * 订单服务
     */
    @Resource
    OrderService orderService;
    /**
     * 配送服务
     */
    @Resource
    ShipmentService shipmentService;
    /**
     * 优惠券占用
     */
    @Resource
    CouponService couponService;
    /**
     * 寻址服务
     */
    @Resource
    AddressProvider addressProvider;

    @Resource
    OrderCartMapper orderCartMapper;

    @Override
    public OrderVO addProductOrder(OrderForm orderForm) throws BussException {
        UserAddress userAddress = addressProvider.byId(orderForm.getAddressId());
        if (userAddress == null) {
            throw new BussException(OrderError.ADDRESS_NOT_FOUND);
        }
        OrderVO orderVO = orderService.addProductOrder(orderForm, userAddress);

        //配送信息添加
        ShipmentCreateForm shipmentCreateForm = new ShipmentCreateForm(userAddress, ShipmentType.DEFAULT, orderVO.getOrderNum());
        shipmentService.create(shipmentCreateForm);

        //优惠券有效 和占用检测
        List<OrderForm.ItemDiscount> discountList = orderForm.getItemDiscounts();
        List<Long> couponRecordIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(discountList)) {
            for (OrderForm.ItemDiscount itemDiscount : discountList) {
                if (itemDiscount.getDiscountType().getCode().equals(DiscountType.COUPON)
                        && !couponRecordIds.contains(itemDiscount.getDiscountId())) {
                    //如果有优惠券使用 则被占用
                    couponService.occupy(itemDiscount.getDiscountId());
                    couponRecordIds.add(itemDiscount.getDiscountId());
                }
            }
        }
        //从购物车下单，移除购物车商品
        int buyFrom = (int) orderForm.getExtraMap().get("buyFrom");
        if (buyFrom == 1) {
            List<OrderCart> orderCartList = orderCartMapper.findByUserId(orderForm.getUserId(), 0, Integer.MAX_VALUE);
            List<Long> productIds = orderForm.getOrderItems().stream().map(OrderItemForm::getProductId).collect(Collectors.toList());
            List<Long> cartIds = orderCartList.stream()
                    .filter(orderCart -> productIds.contains(orderCart.getProductId()))
                    .map(OrderCart::getId)
                    .collect(Collectors.toList());
            orderCartMapper.deleteByUserIdAndIdIn(orderForm.getUserId(), cartIds);
        }
        return orderVO;
    }

    @Override
    public void deliverProductOrder(ConfirmForm confirmForm) throws BussException {
        OrderEntity orderEntity = orderService.confirmOrder(confirmForm);

        shipmentService.confirm(new ShipmentConfirmForm(ShipmentType.DEFAULT, orderEntity.getOrderNum()));
    }

    @Override
    public void cancelOrder(CancelForm cancelForm) throws BussException {
        OrderVO orderVO = orderService.cancelOrder(cancelForm);

        List<OrderItemDiscount> itemDiscounts = orderVO.getOrderDiscounts();
        List<Long> couponRecordIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(itemDiscounts)) {
            for (OrderItemDiscount orderItemDiscount : itemDiscounts) {
                if (DiscountType.COUPON.equals(orderItemDiscount.getType())
                        && !couponRecordIds.contains(orderItemDiscount.getDiscountId())) {
                    //如果有优惠券使用 则被占用
                    couponService.unOccupy(orderItemDiscount.getDiscountId());
                    couponRecordIds.add(orderItemDiscount.getDiscountId());
                }
            }
        }
    }

    @Override
    public void cancelOrderExceptionWrapper(CancelForm cancelForm) {
        OrderVO orderVO = orderService.cancelOrderWrapperException(cancelForm);

        List<OrderItemDiscount> itemDiscounts = orderVO.getOrderDiscounts();
        List<Long> couponRecordIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(itemDiscounts)) {
            for (OrderItemDiscount orderItemDiscount : itemDiscounts) {
                if (DiscountType.COUPON.equals(orderItemDiscount.getType())
                        && !couponRecordIds.contains(orderItemDiscount.getDiscountId())) {
                    //如果有优惠券使用 则被占用
                    try {
                        couponService.unOccupy(orderItemDiscount.getDiscountId());
                    } catch (BussException e) {
                        e.printStackTrace();
                    }
                    couponRecordIds.add(orderItemDiscount.getDiscountId());
                }
            }
        }
    }
}
