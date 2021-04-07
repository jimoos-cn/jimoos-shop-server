package cn.jimoos.entity;

import cn.jimoos.dic.OrderStatus;
import cn.jimoos.form.shipment.ShipmentCreateForm;
import cn.jimoos.model.Order;
import cn.jimoos.model.OrderItem;
import cn.jimoos.model.OrderItemDiscount;
import cn.jimoos.model.OrderItemFee;
import cn.jimoos.repository.OrderRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单
 *
 * @author :keepcleargas
 * @date :2021-04-07 12:59.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderEntity extends Order {
    /**
     * The Order repository.
     */
    protected OrderRepository orderRepository;
    private List<OrderItem> orderItemInputs;
    private List<OrderItemDiscount> orderItemDiscountInputs;
    private List<OrderItemFee> orderItemFeeInputs;
    private ShipmentCreateForm shipmentCreateForm;

    /**
     * Instantiates a new Order entity.
     */
    public OrderEntity() {
    }

    /**
     * Instantiates a new Order entity.
     *
     * @param orderRepository the order repository
     */
    public OrderEntity(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 添加商品选项
     *
     * @param orderItem the order item
     */
    public void addOrderItem(OrderItem orderItem) {
        if (CollectionUtils.isEmpty(orderItemInputs)) {
            orderItemInputs = new ArrayList<>();
        }

        if (orderItem != null) {
            orderItemInputs.add(orderItem);
        }
    }

    /**
     * Add order items.
     *
     * @param orderItems the order items
     */
    public void addOrderItems(List<OrderItem> orderItems) {
        if (CollectionUtils.isEmpty(orderItemInputs)) {
            orderItemInputs = new ArrayList<>();
        }

        if (!CollectionUtils.isEmpty(orderItems)) {
            orderItemInputs.addAll(orderItems);
        }
    }

    /**
     * 添加优惠记录列表
     *
     * @param orderDiscounts orderDiscounts
     */
    public void addDiscountItems(List<OrderItemDiscount> orderDiscounts) {
        if (CollectionUtils.isEmpty(orderItemDiscountInputs)) {
            orderItemDiscountInputs = new ArrayList<>();
        }

        orderItemDiscountInputs.addAll(orderDiscounts);
    }


    /**
     * 添加费用记录列表
     *
     * @param orderItemFees orderDiscounts
     */
    public void addFeeItems(List<OrderItemFee> orderItemFees) {
        if (CollectionUtils.isEmpty(orderItemFeeInputs)) {
            orderItemFeeInputs = new ArrayList<>();
        }

        orderItemFeeInputs.addAll(orderItemFees);
    }

    /**
     * 获取 商品ITEM 列表
     *
     * @return order items
     */
    public List<OrderItem> getOrderItems() {
        return orderRepository.getItemByOrderId(this.getId());
    }

    /**
     * 获取优惠 列表
     *
     * @return order discounts
     */
    public List<OrderItemDiscount> getOrderDiscounts() {
        return orderRepository.getOrderItemDiscountsByOrderId(this.getId());
    }


    /**
     * 判断是否 新订单 待支付
     *
     * @return boolean boolean
     */
    public boolean isNew() {
        return this.getStatus() == OrderStatus.ORDER_NEW;
    }

    /**
     * 是否已经 发货
     *
     * @return boolean boolean
     */
    public boolean isDelivery() {
        return this.getStatus() == OrderStatus.DELIVERY;
    }

    /**
     * 取消订单 要标记订单 为取消，同时 撤回 优惠标记
     */
    public void cancel() {
        this.setStatus(OrderStatus.CANCEL);
        this.setUpdateAt(System.currentTimeMillis());

        long now = System.currentTimeMillis();
        orderItemDiscountInputs = getOrderDiscounts();
        orderItemDiscountInputs = orderItemDiscountInputs.stream().filter(OrderItemDiscount::getNeedReturn).peek(
                r -> {
                    r.setReturnStatus(true);
                    r.setReturnAt(now);
                }
        ).collect(Collectors.toList());
    }

    /**
     * Is paid boolean.
     *
     * @return the boolean
     */
    public boolean isPaid() {
        return this.getStatus() >= OrderStatus.PAID;
    }


    public void setRefund() {
        this.setRefund(true);
    }

    /**
     * Sets paid.
     */
    public void setPaid() {
        this.setStatus(OrderStatus.PAID);
        this.setUpdateAt(System.currentTimeMillis());
    }
}
