package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dic.ShipmentType;
import cn.jimoos.entity.OrderEntity;
import cn.jimoos.error.OrderError;
import cn.jimoos.form.order.CancelForm;
import cn.jimoos.form.order.ConfirmForm;
import cn.jimoos.form.order.OrderForm;
import cn.jimoos.form.order.RemindDeliveryForm;
import cn.jimoos.form.shipment.ShipmentConfirmForm;
import cn.jimoos.form.shipment.ShipmentCreateForm;
import cn.jimoos.service.OrderService;
import cn.jimoos.service.ShipmentService;
import cn.jimoos.user.model.UserAddress;
import cn.jimoos.user.provider.AddressProvider;
import cn.jimoos.vo.OrderVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单
 *
 * @author :keepcleargas
 * @date :2021-04-12 14:18.
 */
@RestController
@RequestMapping("/v1/orders")
public class OrderApi {
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
     * 寻址服务
     */
    @Resource
    AddressProvider addressProvider;

    /**
     * 添加 实体配送订单
     *
     * @param orderForm order form
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public OrderVO addShopOrder(@RequestBody OrderForm orderForm) throws BussException {
        UserAddress userAddress = addressProvider.byId(orderForm.getAddressId());
        if (userAddress == null) {
            throw new BussException(OrderError.ADDRESS_NOT_FOUND);
        }
        OrderVO orderVO = orderService.addShopOrder(orderForm, userAddress);

        //配送信息添加
        ShipmentCreateForm shipmentCreateForm = new ShipmentCreateForm(userAddress, ShipmentType.DEFAULT, orderVO.getOrderNum());
        shipmentService.create(shipmentCreateForm);

        return orderVO;
    }


    /**
     * 确认收货
     *
     * @param confirmForm order  confirm form
     */
    @PostMapping(value = "/{orderId}/confirm", produces = "application/json; charset=utf-8")
    public void deliver(@ModelAttribute ConfirmForm confirmForm) throws BussException {
        OrderEntity orderEntity = orderService.confirmOrder(confirmForm);

        shipmentService.confirm(new ShipmentConfirmForm(ShipmentType.DEFAULT, orderEntity.getOrderNum()));
    }


    /**
     * 提醒发货
     *
     * @param remindDeliveryForm remind delivery form
     */
    @PostMapping(value = "/{orderId}/remindDelivery", produces = "application/json; charset=utf-8")
    public void remindDelivery(@ModelAttribute RemindDeliveryForm remindDeliveryForm) throws BussException {
        orderService.remindDelivery(remindDeliveryForm);
    }


    /**
     * 取消订单
     *
     * @param cancelForm cancel form
     */
    @PostMapping(value = "/{orderId}/cancel", produces = "application/json; charset=utf-8")
    public void cancelOrder(@ModelAttribute CancelForm cancelForm) throws BussException {
        orderService.cancelOrder(cancelForm);
    }
}
