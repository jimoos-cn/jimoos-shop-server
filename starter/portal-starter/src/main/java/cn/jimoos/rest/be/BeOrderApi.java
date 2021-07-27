package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.constant.ShipmentType;
import cn.jimoos.entity.OrderEntity;
import cn.jimoos.form.order.OrderDeliverForm;
import cn.jimoos.form.order.be.BeOrderQueryForm;
import cn.jimoos.form.order.be.BeRefundDeleteForm;
import cn.jimoos.form.order.be.BeRefundQueryForm;
import cn.jimoos.form.shipment.ShipmentDeliverForm;
import cn.jimoos.service.OrderService;
import cn.jimoos.service.ShipmentService;
import cn.jimoos.utils.http.Page;
import cn.jimoos.vo.OrderRefundVO;
import cn.jimoos.vo.OrderVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单管理
 *
 * @author :keepcleargas
 * @date :2021-04-12 15:45.
 */
@RestController
@RequestMapping("/bAdmin/v1/orders")
public class BeOrderApi {
    @Resource
    OrderService orderService;
    @Resource
    ShipmentService shipmentService;


    /**
     * 订单查询
     *
     * @param beOrderQueryForm order search form
     * @return Page<OrderVO>
     */
    @GetMapping(value = "query", produces = "application/json; charset=utf-8")
    public Page<OrderVO> queryTable(@ModelAttribute BeOrderQueryForm beOrderQueryForm) {
        return orderService.qTable(beOrderQueryForm);
    }

    /**
     * 根据用户ID 订单查询
     *
     * @param beOrderQueryForm page search form
     * @return Page<OrderVO>
     */
    @GetMapping(value = "/byUserId", produces = "application/json; charset=utf-8")
    public Page<OrderVO> queryTableByUserId(@ModelAttribute BeOrderQueryForm beOrderQueryForm) {
        return orderService.qTableByUid(beOrderQueryForm);
    }

    /**
     * 后台根据订单ID 查询订单详细
     *
     * @param orderId 订单Id
     * @return OrderVO
     */
    @GetMapping(value = "/{orderId}/details", produces = "application/json; charset=utf-8")
    public OrderVO queryTableByUserId(@PathVariable("orderId") Long orderId) throws BussException {
        return orderService.getOrderDetails(orderId);
    }

    /**
     * 商家发货
     *
     * @param deliverForm order deliver form
     */
    @PostMapping(value = "/{orderId}/deliver", produces = "application/json; charset=utf-8")
    public void deliver(@ModelAttribute OrderDeliverForm deliverForm) throws BussException {
        OrderEntity orderEntity = orderService.deliver(deliverForm);
        ShipmentDeliverForm shipmentDeliverForm = new ShipmentDeliverForm(ShipmentType.DEFAULT,
                orderEntity.getOrderNum(), deliverForm.getShipCode(),
                deliverForm.getExpress(), deliverForm.getExpressCode());
        shipmentService.deliver(shipmentDeliverForm);
    }

    /**
     * 商家查看 申请退款订单
     * @param beRefundQueryForm
     * @return
     */
    @GetMapping(value = "/refund/query", produces = "application/json; charset=utf-8")
    public Page<OrderRefundVO> queryRefund(@ModelAttribute BeRefundQueryForm beRefundQueryForm) {
        return orderService.queryRefund(beRefundQueryForm);
    }

    /**
     * 商家取消 申请退款订单
     * @param beRefundDeleteForm
     * @return
     */
    @PostMapping(value = "/refund/cancel", produces = "application/json; charset=utf-8")
    public void cancelRefund(@ModelAttribute BeRefundDeleteForm beRefundDeleteForm) {
        orderService.cancelRefund(beRefundDeleteForm);
    }
}
