package cn.jimoos.form.cart;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2020/12/28 20:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderCartForm extends AbstractUserForm4L {
    /**
     * 商品ID
     */
    @NotNull
    private Long productId;

    /**
     * SKU ID
     */
    @NotNull
    private Long skuId;

    /**
     * 当前数量
     */
    @NotNull
    private Integer num;

    /**
     * 是否选中
     */
    @NotNull
    private Boolean checked = Boolean.FALSE;
}
