package cn.jimoos.entity;

import cn.jimoos.dic.OrderStatus;
import cn.jimoos.dic.ShipmentType;
import cn.jimoos.form.order.OrderDeliverForm;
import cn.jimoos.form.shipment.ShipmentDeliverForm;
import cn.jimoos.model.Shipment;
import cn.jimoos.repository.OrderRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * @author :keepcleargas
 * @date :2021-04-08 15:53.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShopOrderEntity extends OrderEntity {
    public ShopOrderEntity(OrderRepository orderRepository) {
        super(orderRepository);
    }

    public ShopOrderEntity() {
        super();
    }

    public static ShopOrderEntity clone(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }
        ShopOrderEntity shopOrderEntity = new ShopOrderEntity();
        BeanUtils.copyProperties(orderEntity, shopOrderEntity);
        return shopOrderEntity;
    }

    /**
     * 获取 物流的配送信息
     *
     * @return ShipmentDTO
     */
    public Shipment getShipment() {
        return orderRepository.getShipment(ShipmentType.DEFAULT, this.getOrderNum());
    }

    /**
     * 发货
     */
    public void deliver() {
        this.setStatus(OrderStatus.DELIVERY);
        this.setUpdateAt(System.currentTimeMillis());
    }

    public ShipmentDeliverForm toShipmentDeliverForm(OrderDeliverForm form) {
        return new ShipmentDeliverForm(ShipmentType.DEFAULT, this.getOrderNum(), form.getShipCode(), form.getExpress(), form.getExpressCode());
    }

    /**
     * 确认收货
     */
    public void confirm() {
        this.setStatus(OrderStatus.RECEIVE);
        this.setUpdateAt(System.currentTimeMillis());
    }
}
