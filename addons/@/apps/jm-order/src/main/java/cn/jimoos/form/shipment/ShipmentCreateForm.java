package cn.jimoos.form.shipment;

import cn.jimoos.user.model.UserAddress;
import cn.jimoos.utils.validate.ValidateUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2021/1/14 14:10
 */
@Data
public class ShipmentCreateForm {
    @NotNull
    private UserAddress userAddress;
    @NotNull
    private Integer type;
    @NotBlank
    private String outTradeNo;

    public ShipmentCreateForm() {
    }

    public ShipmentCreateForm(UserAddress userAddress, Integer type, String outTradeNo) {
        this.userAddress = userAddress;
        this.type = type;
        this.outTradeNo = outTradeNo;
        ValidateUtils.validate(this);
    }
}
