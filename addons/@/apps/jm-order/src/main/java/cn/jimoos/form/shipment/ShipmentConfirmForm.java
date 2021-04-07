package cn.jimoos.form.shipment;

import cn.jimoos.utils.form.Form;
import cn.jimoos.utils.validate.ValidateUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2021/1/14 14:09
 */
@Data
public class ShipmentConfirmForm implements Form {
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


    public ShipmentConfirmForm() {
    }

    public ShipmentConfirmForm(@NotNull Integer type, @NotBlank String outTradeNo) {
        this.type = type;
        this.outTradeNo = outTradeNo;
        ValidateUtils.validate(this);
    }
}
