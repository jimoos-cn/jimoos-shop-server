package cn.jimoos.form.order;

import cn.jimoos.utils.validate.ValidateUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author :keepcleargas
 * @date :2021-04-08 15:07.
 */
@Data
public class OrderDeliverForm {
    @NotNull
    private Long orderId;
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

    public OrderDeliverForm() {
    }

    public OrderDeliverForm(@NotNull Long orderId, @NotBlank String shipCode, @NotBlank String express, @NotBlank String expressCode) {
        this.orderId = orderId;
        this.shipCode = shipCode;
        this.express = express;
        this.expressCode = expressCode;
        ValidateUtils.validate(this);
    }
}
