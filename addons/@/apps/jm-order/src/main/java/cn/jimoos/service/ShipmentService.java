package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.shipment.ShipmentConfirmForm;
import cn.jimoos.form.shipment.ShipmentCreateForm;
import cn.jimoos.form.shipment.ShipmentDeliverForm;
import cn.jimoos.model.Shipment;

import javax.annotation.Nullable;

/**
 * @author :keepcleargas
 * @date :2021-04-08 15:09.
 */
public interface ShipmentService {
    /**
     * 新建.
     *
     * @param shipmentCreateForm the shipment create form
     */
    void create(ShipmentCreateForm shipmentCreateForm);

    /**
     * 发货.
     *
     * @param form the form
     * @throws BussException the buss exception
     */
    void deliver(ShipmentDeliverForm form) throws BussException;


    /**
     * 确认收货
     *
     * @param form the form
     * @throws BussException the buss exception
     */
    void confirm(ShipmentConfirmForm form) throws BussException;

    /**
     * 获取 ShipmentDTO
     *
     * @param type       the type
     * @param outTradeNo the out trade no
     * @return the shipment dto
     */
    @Nullable
    Shipment by(Integer type, String outTradeNo);
}
