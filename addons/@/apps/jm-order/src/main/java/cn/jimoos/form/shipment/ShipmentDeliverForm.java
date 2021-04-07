package cn.jimoos.form.shipment;

import cn.jimoos.utils.form.Form;
import cn.jimoos.utils.validate.ValidateUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2021-04-07 12:59.
 */
@Data
public class ShipmentDeliverForm implements Form {
    /**
     * 类型
     */
    @NotNull
    private Integer type;
    /**
     * 外部订单号
     */
    @NotBlank
    private String outTradeNo;
    /**
     * 物流单号
     */
    @NotBlank
    private String shipCode;
    /**
     * 快递名称
     */
    @NotBlank
    private String express;
    /**
     * 快递编码
     */
    @NotBlank
    private String expressCode;

    public ShipmentDeliverForm() {
    }

    public ShipmentDeliverForm(@NotNull Integer type, @NotBlank String outTradeNo, @NotBlank String shipCode, @NotBlank String express, @NotBlank String expressCode) {
        this.type = type;
        this.outTradeNo = outTradeNo;
        this.shipCode = shipCode;
        this.express = express;
        this.expressCode = expressCode;
        ValidateUtils.validate(this);
    }
}
