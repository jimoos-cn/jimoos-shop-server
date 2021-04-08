package cn.jimoos.factory;

import cn.jimoos.entity.ShipmentEntity;
import cn.jimoos.form.shipment.ShipmentCreateForm;
import cn.jimoos.user.model.UserAddress;
import org.springframework.stereotype.Component;

/**
 * @author keepcleargas
 * @date 1.0 Created in 2021/4/08 15:18
 */
@Component
public class ShipmentFactory {

    public ShipmentEntity create(ShipmentCreateForm form) {
        UserAddress userAddress = form.getUserAddress();
        Integer type = form.getType();
        String outTradeNo = form.getOutTradeNo();
        Long now = System.currentTimeMillis();
        ShipmentEntity shipmentEntity = new ShipmentEntity();
        shipmentEntity.setName(userAddress.getName());
        shipmentEntity.setPhone(userAddress.getPhone());
        shipmentEntity.setProvince(userAddress.getProvince());
        shipmentEntity.setCity(userAddress.getCity());
        shipmentEntity.setArea(userAddress.getArea());
        shipmentEntity.setAddress(userAddress.getAddress());
        shipmentEntity.setZipCode(userAddress.getZipCode());
        shipmentEntity.setComment("");
        shipmentEntity.setShipCode("");
        shipmentEntity.setExpress("");
        shipmentEntity.setExpressCode("");
        shipmentEntity.setStatus(ShipmentEntity.Status.TO_BE_DELIVERED.val());
        shipmentEntity.setType(type);
        shipmentEntity.setUserId(userAddress.getUserId());
        shipmentEntity.setOutTradeNo(outTradeNo);
        shipmentEntity.setCreateAt(now);
        shipmentEntity.setUpdateAt(now);
        shipmentEntity.setDeleted(false);
        return shipmentEntity;
    }
}
