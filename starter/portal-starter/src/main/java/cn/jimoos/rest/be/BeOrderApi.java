package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dic.ShipmentType;
import cn.jimoos.entity.OrderEntity;
import cn.jimoos.form.order.OrderDeliverForm;
import cn.jimoos.form.order.be.BeOrderQueryForm;
import cn.jimoos.form.shipment.ShipmentDeliverForm;
import cn.jimoos.service.OrderService;
import cn.jimoos.service.ShipmentService;
import cn.jimoos.utils.http.Page;
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

    @GetMapping(value = "query", produces = "application/json; charset=utf-8")
    public Page<OrderVO> queryTable(@ModelAttribute BeOrderQueryForm beOrderQueryForm) {
        return orderService.qTable(beOrderQueryForm);
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
}
