package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.order.*;
import cn.jimoos.service.OrderComposeService;
import cn.jimoos.service.OrderService;
import cn.jimoos.vo.OrderVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单
 *
 * @author :keepcleargas
 * @date :2021-04-12 14:18.
 */
@RestController
@RequestMapping("/v1/orders")
public class OrderApi {
    @Resource
    OrderComposeService orderComposeService;
    @Resource
    OrderService orderService;

    /**
     * 添加 实体配送订单
     *
     * @param orderForm order form
     */
    @PostMapping(value = "/product", produces = "application/json; charset=utf-8")
    public OrderVO addProductOrder(@RequestBody OrderForm orderForm) throws BussException {
        return orderComposeService.addProductOrder(orderForm);
    }

    /**
     * 获取用户订单 列表
     *
     * @param orderQueryForm order query form
     * @return OrderVo
     */
    @GetMapping(value = "/query", produces = "application/json; charset=utf-8")
    public List<OrderVO> getOrderDetail(@ModelAttribute UserOrderQueryForm orderQueryForm) throws BussException {
        return orderService.userOrders(orderQueryForm);
    }

    /**
     * 获取用户订单详情
     *
     * @param orderId order id
     * @param userId  user id
     * @return OrderVo
     */
    @GetMapping(value = "/{orderId}", produces = "application/json; charset=utf-8")
    public OrderVO getOrderDetail(@PathVariable("orderId") Long orderId, @RequestParam("userId") Long userId) throws BussException {
        return orderService.getOne(userId, orderId);
    }
    
    /**
     * 确认收货
     *
     * @param confirmForm order  confirm form
     */
    @PostMapping(value = "/{orderId}/confirm", produces = "application/json; charset=utf-8")
    public void deliver(@ModelAttribute ConfirmForm confirmForm) throws BussException {
        orderComposeService.deliverProductOrder(confirmForm);
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
        orderComposeService.cancelOrder(cancelForm);
    }
}
