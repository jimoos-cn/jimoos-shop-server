package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.component.ShipmentComponent;
import cn.jimoos.context.DiscountContext;
import cn.jimoos.context.FeeContext;
import cn.jimoos.dao.OrderRemindDeliveryMapper;
import cn.jimoos.entity.OrderEntity;
import cn.jimoos.entity.ShopOrderEntity;
import cn.jimoos.error.OrderError;
import cn.jimoos.factory.OrderFactory;
import cn.jimoos.form.order.*;
import cn.jimoos.form.shipment.ShipmentConfirmForm;
import cn.jimoos.form.shipment.ShipmentDeliverForm;
import cn.jimoos.model.OrderItemDiscount;
import cn.jimoos.model.OrderItemFee;
import cn.jimoos.model.OrderRemindDelivery;
import cn.jimoos.model.Shipment;
import cn.jimoos.repository.OrderRepository;
import cn.jimoos.user.model.UserAddress;
import cn.jimoos.utils.validate.ValidateUtils;
import cn.jimoos.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * The type Order service.
 *
 * @author :keepcleargas
 * @date :2021-04-08 14:22.
 */
@Service
@Slf4j
public class OrderServiceImpl {
    /**
     * The Order factory.
     */
    @Resource
    OrderFactory orderFactory;
    /**
     * The Order repository.
     */
    @Resource
    OrderRepository orderRepository;

    /**
     * The Order remind delivery mapper.
     */
    @Resource
    OrderRemindDeliveryMapper orderRemindDeliveryMapper;
    /**
     * 配送组件
     */
    @Resource
    ShipmentComponent shipmentComponent;

    /**
     * 添加商城 订单
     *
     * @param orderForm   orderForm
     * @param userAddress userAddress
     * @return order vo
     * @throws BussException the buss exception
     */
    public OrderVO addShopOrder(OrderForm orderForm, UserAddress userAddress) throws BussException {
        ValidateUtils.validate(orderForm);
        if (userAddress == null) {
            throw new BussException(OrderError.ADDRESS_NOT_FOUND);
        }
        ShopOrderEntity orderEntity = orderFactory.createShopOrderEntity(orderForm, userAddress);

        orderRepository.saveOrderEntity(orderEntity);
        //创建优惠
        DiscountContext discountContext = new DiscountContext(orderForm.getItemDiscounts());
        List<OrderItemDiscount> orderItemDiscounts = orderFactory.createOrderItemDiscounts(discountContext.getDiscountItems(), orderEntity.getOrderItems());
        //保存优惠
        orderRepository.saveOrderItemDiscount(orderItemDiscounts);
        //add to entity
        orderEntity.addDiscountItems(orderItemDiscounts);
        //创建费用
        FeeContext feeContext = new FeeContext(orderForm.getItemFees());
        List<OrderItemFee> orderItemFees = orderFactory.createOrderItemFees(feeContext.getFeeItems(), orderEntity.getOrderItems());
        //保存费用
        orderRepository.saveOrderItemFees(orderItemFees);
        //add to entity
        orderEntity.addFeeItems(orderItemFees);
        //获取订单配送信息
        orderEntity.getShipment();

        return OrderVO.fromEntity(orderEntity);
    }

    /**
     * 发货
     *
     * @param orderDeliverForm OrderDeliverForm
     * @throws BussException the buss exception
     */
    public void deliver(OrderDeliverForm orderDeliverForm) throws BussException {
        ShopOrderEntity orderEntity = ShopOrderEntity.clone(orderRepository.findById(orderDeliverForm.getOrderId()));
        if (orderEntity != null) {
            orderEntity.deliver();
            ShipmentDeliverForm shipmentDeliverForm = orderEntity.toShipmentDeliverForm(orderDeliverForm);
            shipmentComponent.deliver(shipmentDeliverForm);
            orderRepository.saveStatus(orderEntity);
            Shipment shipmentDTO = shipmentComponent.by(0, orderEntity.getOrderNum());
        }
    }

    /**
     * 用户确认收货
     *
     * @param confirmForm the confirm form
     * @throws BussException the buss exception
     */
    public void confirmOrder(ConfirmForm confirmForm) throws BussException {
        ShopOrderEntity orderEntity = ShopOrderEntity.clone(orderRepository.findByUidAndId(confirmForm.getUserId(), confirmForm.getOrderId()));

        if (!orderEntity.isDelivery()) {
            throw new BussException(OrderError.ORDER_STATUS_NOT_VALID);
        }
        orderEntity.confirm();

        orderRepository.saveStatus(orderEntity);

        shipmentComponent.confirm(new ShipmentConfirmForm(0, orderEntity.getOrderNum()));
    }


    /**
     * 提醒发货
     *
     * @param remindDeliveryForm the remind delivery form
     * @throws BussException the buss exception
     */
    public void remindDelivery(RemindDeliveryForm remindDeliveryForm) throws BussException {
        Long orderId = remindDeliveryForm.getOrderId();
        Long userId = remindDeliveryForm.getUserId();
        OrderEntity orderEntity = orderRepository.findById(orderId);
        OrderRemindDelivery orderRemindDelivery = new OrderRemindDelivery();
        orderRemindDelivery.setOrderId(orderId);
        orderRemindDelivery.setUid(userId);
        long now = System.currentTimeMillis();
        orderRemindDelivery.setCreateAt(now);
        orderRemindDelivery.setUpdateAt(now);
        orderRemindDelivery.setDeleted(false);
        orderRemindDeliveryMapper.insert(orderRemindDelivery);
    }

    /**
     * 取消订单
     *
     * @param cancelForm the cancel form
     * @return order vo
     * @throws BussException the buss exception
     */
    public OrderVO cancelOrder(CancelForm cancelForm) throws BussException {
        if (cancelForm.getOrderId() == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }
        OrderEntity orderEntity = orderRepository.findByUidAndId(cancelForm.getUserId(), cancelForm.getOrderId());

        if (orderEntity == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }

        if (!orderEntity.isNew()) {
            throw new BussException(OrderError.ORDER_STATUS_NOT_VALID);
        }

        orderEntity.cancel();

        orderRepository.saveCancel(orderEntity);

        OrderVO orderVo = new OrderVO();
        BeanUtils.copyProperties(orderEntity, orderVo);
        return orderVo;
    }

    /**
     * 封装了 取消订单
     *
     * @param cancelForm the cancel form
     * @return order vo
     */
    public OrderVO cancelOrderWrapperException(CancelForm cancelForm) {
        try {
            return cancelOrder(cancelForm);
        } catch (BussException e) {
            log.error("取消订单 发生异常{}", cancelForm.getOrderId());
        }
        return null;
    }
}
