package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.service.ShipmentService;
import cn.jimoos.entity.ShipmentEntity;
import cn.jimoos.error.OrderError;
import cn.jimoos.factory.ShipmentFactory;
import cn.jimoos.form.shipment.ShipmentConfirmForm;
import cn.jimoos.form.shipment.ShipmentCreateForm;
import cn.jimoos.form.shipment.ShipmentDeliverForm;
import cn.jimoos.model.Shipment;
import cn.jimoos.repository.ShipmentRepository;
import cn.jimoos.utils.validate.ValidateUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-08 15:10.
 */
@Component
public class ShipmentServiceImpl implements ShipmentService {
    @Resource
    ShipmentFactory shipmentFactory;
    @Resource
    ShipmentRepository shipmentRepository;


    @Override
    public void create(ShipmentCreateForm shipmentCreateForm) {
        ValidateUtils.validate(shipmentCreateForm);
        ShipmentEntity shipmentEntity = shipmentFactory.create(shipmentCreateForm);
        shipmentRepository.save(shipmentEntity);
    }

    @Override
    public void deliver(ShipmentDeliverForm form) throws BussException {
        ValidateUtils.validate(form);

        ShipmentEntity shipmentEntity = shipmentRepository.byUnionId(form.getType(), form.getOutTradeNo());
        if (shipmentEntity == null) {
            throw new BussException(OrderError.SHIPMENT_NOT_EXIST);
        }
        shipmentEntity.deliver(form);
        shipmentRepository.save(shipmentEntity);
    }

    /**
     * 确认收货
     *
     * @param form the form
     * @throws BussException the buss exception
     */
    @Override
    public void confirm(ShipmentConfirmForm form) throws BussException {
        ValidateUtils.validate(form);

        ShipmentEntity shipmentEntity = shipmentRepository.byUnionId(form.getType(), form.getOutTradeNo());
        if (shipmentEntity == null) {
            throw new BussException(OrderError.SHIPMENT_NOT_EXIST);
        }
        shipmentEntity.finish();
        shipmentRepository.save(shipmentEntity);
    }

    /**
     * 获取 ShipmentDTO
     *
     * @param type       the type
     * @param outTradeNo the out trade no
     * @return the shipment
     */
    @Override
    public Shipment by(Integer type, String outTradeNo) {
        return shipmentRepository.byUnionId(type, outTradeNo);
    }
}
