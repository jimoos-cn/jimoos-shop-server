package cn.jimoos.entity;

import cn.jimoos.form.shipment.ShipmentDeliverForm;
import cn.jimoos.model.Shipment;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2021/4/8 15:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ShipmentEntity extends Shipment {

    /**
     * 发货
     *
     * @param form shipment deliver form
     */
    public void deliver(ShipmentDeliverForm form) {
        this.setStatus(Status.DELIVERED.val());
        this.setExpress(form.getExpress());
        this.setExpressCode(form.getExpressCode());
        this.setShipCode(form.getShipCode());
        this.setUpdateAt(System.currentTimeMillis());
    }

    /**
     * 签收
     */
    public void finish() {
        this.setStatus(Status.FINISH.val());
    }


    public enum Status {

        /**
         * 待发货
         */
        TO_BE_DELIVERED(0),

        /**
         * 已发货
         */
        DELIVERED(1),

        /**
         * 已签收
         */
        FINISH(2);

        private final int val;

        Status(int val) {
            this.val = val;
        }

        public int val() {
            return val;
        }
    }
}
