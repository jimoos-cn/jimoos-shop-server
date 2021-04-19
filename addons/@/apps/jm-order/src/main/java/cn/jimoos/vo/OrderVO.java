package cn.jimoos.vo;

import cn.jimoos.entity.OrderEntity;
import cn.jimoos.entity.ShopOrderEntity;
import cn.jimoos.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-08 15:53.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderVO extends Order {
    List<OrderItem> orderItems;
    List<OrderItemDiscount> orderDiscounts;
    List<OrderItemFee> orderItemFees;
    Shipment shipment;

    public static OrderVO fromEntity(OrderEntity orderEntity) {
        OrderVO orderVo = new OrderVO();
        orderVo.setId(orderEntity.getId());
        orderVo.setOrderNum(orderEntity.getOrderNum());
        orderVo.setSubject(orderEntity.getSubject());
        orderVo.setOrderType(orderEntity.getOrderType());
        orderVo.setItemAmount(orderEntity.getItemAmount());
        orderVo.setTotalPrice(orderEntity.getTotalPrice());
        orderVo.setTotalFee(orderEntity.getTotalFee());
        orderVo.setTotalProductPrice(orderEntity.getTotalProductPrice());
        orderVo.setTotalDiscount(orderEntity.getTotalDiscount());
        orderVo.setRealPay(orderEntity.getRealPay());
        orderVo.setUserId(orderEntity.getUserId());
        orderVo.setComment(orderEntity.getComment());
        orderVo.setStatus(orderEntity.getStatus());
        orderVo.setRefund(orderEntity.getRefund());
        orderVo.setRate(orderEntity.getRate());
        orderVo.setExtra(orderEntity.getExtra());
        orderVo.setCreateAt(orderEntity.getCreateAt());
        orderVo.setUpdateAt(orderEntity.getUpdateAt());
        orderVo.setDeleted(false);

        orderVo.setOrderItems(orderEntity.getOrderItems());
        orderVo.setOrderDiscounts(orderEntity.getOrderDiscounts());
        orderVo.setOrderItemFees(orderEntity.getOrderItemFees());
        return orderVo;
    }

    public static OrderVO fromEntity(ShopOrderEntity shopOrderEntity) {
        OrderVO orderVo = new OrderVO();
        orderVo.setId(shopOrderEntity.getId());
        orderVo.setOrderNum(shopOrderEntity.getOrderNum());
        orderVo.setSubject(shopOrderEntity.getSubject());
        orderVo.setOrderType(shopOrderEntity.getOrderType());
        orderVo.setItemAmount(shopOrderEntity.getItemAmount());
        orderVo.setTotalPrice(shopOrderEntity.getTotalPrice());
        orderVo.setTotalFee(shopOrderEntity.getTotalFee());
        orderVo.setTotalProductPrice(shopOrderEntity.getTotalProductPrice());
        orderVo.setTotalDiscount(shopOrderEntity.getTotalDiscount());
        orderVo.setRealPay(shopOrderEntity.getRealPay());
        orderVo.setUserId(shopOrderEntity.getUserId());
        orderVo.setComment(shopOrderEntity.getComment());
        orderVo.setStatus(shopOrderEntity.getStatus());
        orderVo.setRefund(shopOrderEntity.getRefund());
        orderVo.setRate(shopOrderEntity.getRate());
        orderVo.setExtra(shopOrderEntity.getExtra());
        orderVo.setCreateAt(shopOrderEntity.getCreateAt());
        orderVo.setUpdateAt(shopOrderEntity.getUpdateAt());
        orderVo.setDeleted(false);

        orderVo.setShipment(shopOrderEntity.getShipment());
        orderVo.setOrderItems(shopOrderEntity.getOrderItems());
        orderVo.setOrderDiscounts(shopOrderEntity.getOrderDiscounts());
        orderVo.setOrderItemFees(shopOrderEntity.getOrderItemFees());
        return orderVo;
    }
}
